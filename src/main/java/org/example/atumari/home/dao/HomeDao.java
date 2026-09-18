package org.example.atumari.home.dao;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.example.atumari.common.database.DBConnection;
import org.example.atumari.festival.dto.FestivalDto;

public class HomeDao {
	
	Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    //인덱스 검색
    public List<FestivalDto> searchFestivalList(
            String keyword,
            LocalDate startDate,
            LocalDate endDate) {

        List<FestivalDto> list = new ArrayList<>();

        String sql =
                "SELECT f.festival_no, "
              + "       f.prefecture_no, "
              + "       f.festival_name, "
              + "       p.prefecture_name, "
              + "       f.summary, "
              + "       f.image_url, "
              + "       f.season, "
              + "       f.start_datetime, "
              + "       f.end_datetime "
              + "FROM festival f "
              + "JOIN prefecture p "
              + "ON f.prefecture_no = p.prefecture_no "
              + "WHERE 1=1 ";

        if (keyword != null && !keyword.trim().isEmpty()) {

            sql += "AND f.festival_name LIKE ? ";

        }

        if (startDate != null && endDate != null) {

            sql += "AND f.end_datetime >= ? "
                 + "AND f.start_datetime < ? ";

        }

        sql += "ORDER BY f.start_datetime";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {

            int index = 1;

            // 검색어
            if (keyword != null && !keyword.trim().isEmpty()) {

                pstmt.setString(
                        index++,
                        "%" + keyword.trim() + "%"
                );

            }

            // 날짜
            if (startDate != null && endDate != null) {

                LocalDate nextDay =
                        endDate.plusDays(1);

                pstmt.setTimestamp(
                        index++,
                        Timestamp.valueOf(
                                startDate.atStartOfDay()
                        )
                );

                pstmt.setTimestamp(
                        index++,
                        Timestamp.valueOf(
                                nextDay.atStartOfDay()
                        )
                );

            }

            ResultSet rs =
                    pstmt.executeQuery();

            while (rs.next()) {

                FestivalDto dto =
                        new FestivalDto(
                                rs.getInt("festival_no"),
                                rs.getInt("prefecture_no"),
                                rs.getString("prefecture_name"),
                                rs.getString("festival_name"),
                                rs.getString("summary"),
                                rs.getString("image_url"),
                                rs.getString("season"),
                                rs.getTimestamp("start_datetime")
                                  .toLocalDateTime(),
                                rs.getTimestamp("end_datetime")
                                  .toLocalDateTime()
                        );

                list.add(dto);
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return list;
    }

}
