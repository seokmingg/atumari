package org.example.atumari.festival.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

import org.example.atumari.common.database.DBConnection;
import org.example.atumari.festival.dto.FestivalDto;

public class FestivalDao {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void saveFestivals(List<FestivalDto> dtos) {

        String sql =
                "insert into atumari.festival "
              + "(festival_id, festival_name, summary, "
              + "start_datetime, end_datetime, venue_name, "
              + "venue_address, access_info, image_url, organizer, "
              + "price_free, price_text, external_url, image_source, "
              + "prefecture_no, season) "
              + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            con = DBConnection.getConnection();

            LogPreparedStatement logPs =
                    new LogPreparedStatement(con, sql);

            ps = logPs;

            for (FestivalDto dto : dtos) {

                // 필수값 검사
                if (dto.getFestival_id() == null ||
                    dto.getFestival_name() == null ||
                    dto.getFestival_name().isBlank()) {

                    System.out.println(
                            "필수데이터가 없어서 저장하지 않았습니다.");
                    continue; //필수데이터 없으면 다음 dto(축제데이터)로 넘어가기
                }

                // 1. festival_id
                ps.setInt(1, dto.getFestival_id());

                // 2. festival_name
                ps.setString(2, dto.getFestival_name());

                // 3. summary
                ps.setString(3, dto.getSummary());

                // 4. start_datetime
                if (dto.getStartDateTime() != null) {
                    ps.setTimestamp(
                            4,
                            Timestamp.valueOf(dto.getStartDateTime()));
                } else {
                    ps.setNull(4, Types.TIMESTAMP);
                }

                // 5. end_datetime
                if (dto.getEndDateTime() != null) {
                    ps.setTimestamp(
                            5,
                            Timestamp.valueOf(dto.getEndDateTime()));
                } else {
                    ps.setNull(5, Types.TIMESTAMP);
                }

                // 6. venue_name
                ps.setString(6, dto.getVenue_name());

                // 7. venue_address
                ps.setString(7, dto.getVenue_address());

                // 8. access_info
                ps.setString(8, dto.getAccess_info());

                // 9. image_url
                ps.setString(9, dto.getImage_url());

                // 10. organizer
                ps.setString(10, dto.getOrganizer());

                // 11. price_free
                if (dto.getPrice_free() != null) {
                    ps.setBoolean(11, dto.getPrice_free());
                } else {
                    ps.setNull(11, Types.BOOLEAN);
                }

                // 12. price_text
                ps.setString(12, dto.getPrice_text());

                // 13. external_url
                ps.setString(13, dto.getExternal_url());

                // 14. image_source
                ps.setString(14, dto.getImage_source());

                // 15. prefecture_no
                if (dto.getPrefecture_no() != null) {
                    ps.setInt(15, dto.getPrefecture_no());
                } else {
                    ps.setNull(15, Types.INTEGER);
                }

                // 16. season
                ps.setString(16, dto.getSeason());
                
                /*festival_id, festival_name → 이미 필수값 검사했으므로 다시 검사할 필요 없음
					String 계열 → setString()에 그대로 넣어도 대체로 괜찮음
					LocalDateTime, Integer, Boolean처럼 null 가능 + primitive JDBC setter 사용 → 별도 처리 필요*/

                // INSERT 실행
                ps.executeUpdate();
            }

        } catch (SQLException e) {

            e.printStackTrace();

            if (ps != null) {
                System.out.println(
                        "saveFestivals() 오류 SQL : " + ps.toString());
            }

        } finally {

            DBConnection.closeDB(con, ps, rs);
        }
    }
}