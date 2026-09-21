package org.example.atumari.home.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.example.atumari.common.database.DBConnection;
import org.example.atumari.festival.dto.FestivalDto;

public class HomeDao {
	
	Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
	 // 인덱스 검색
	 public List<FestivalDto> searchFestivalList(
	         String keyword,
	         LocalDate startDate,
	         LocalDate endDate,
	         int start,
	         int pageSize) {
	
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
	
	     // 검색어
	     if (keyword != null && !keyword.trim().isEmpty()) {
	
	         sql += "AND (f.festival_name LIKE ? "
	              + "OR p.prefecture_name LIKE ? "
	              + "OR f.season LIKE ?) ";
	     }
	
	     // 날짜 범위
	     if (startDate != null && endDate != null) {
	
	         sql += "AND f.end_datetime >= ? "
	              + "AND f.start_datetime < ? ";
	     }
	
	     sql += "ORDER BY f.start_datetime ";
	     sql += "LIMIT ?, ?";
	
	     try (
    		 Connection con = DBConnection.getConnection();
    		 PreparedStatement pstmt = con.prepareStatement(sql)
	     ) {
	
	         int index = 1;
	
	         // 검색어
	         if (keyword != null && !keyword.trim().isEmpty()) {
	
	             String searchKeyword = keyword.trim();
	
	             // 계절 검색용 키워드 변환
	             String seasonKeyword = searchKeyword;
	
	             if (searchKeyword.equals("春")) {
	                 seasonKeyword = "봄";
	             } else if (searchKeyword.equals("夏")) {
	                 seasonKeyword = "여름";
	             } else if (searchKeyword.equals("秋")) {
	                 seasonKeyword = "가을";
	             } else if (searchKeyword.equals("冬")) {
	                 seasonKeyword = "겨울";
	             }
	
	             // 축제명
	             pstmt.setString(index++, "%" + searchKeyword + "%");
	
	             // 지역명
	             pstmt.setString(index++, "%" + searchKeyword + "%");
	
	             // 계절
	             pstmt.setString(index++, "%" + seasonKeyword + "%");
	         }
	
	         // 날짜 범위
	         if (startDate != null && endDate != null) {
	
	             LocalDate nextDay = endDate.plusDays(1);
	
	             pstmt.setTimestamp(
	                 index++,
	                 Timestamp.valueOf(startDate.atStartOfDay())
	             );
	
	             pstmt.setTimestamp(
	                 index++,
	                 Timestamp.valueOf(nextDay.atStartOfDay())
	             );
	         }
	
	         // 페이징
	         pstmt.setInt(index++, start);
	         pstmt.setInt(index++, pageSize);
	
	         try (ResultSet rs = pstmt.executeQuery()) {
	
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
	                         rs.getTimestamp("start_datetime").toLocalDateTime(),
	                         rs.getTimestamp("end_datetime").toLocalDateTime()
	                     );
	
	                 list.add(dto);
	             }
	         }
	
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	
	     return list;
	 }




	// 인덱스 검색 전체 개수
	public int getFestivalTotalCount(
	        String keyword,
	        LocalDate startDate,
	        LocalDate endDate) {

	    int totalCount = 0;

	    String sql =
	            "SELECT COUNT(*) "
	          + "FROM festival f "
	          + "JOIN prefecture p "
	          + "ON f.prefecture_no = p.prefecture_no "
	          + "WHERE 1=1 ";

	    // 검색어
	    // 축제명 + 개최지역 + 계절
	    if (keyword != null && !keyword.trim().isEmpty()) {

	        sql += "AND (f.festival_name LIKE ? "
	             + "OR p.prefecture_name LIKE ? "
	             + "OR f.season LIKE ?) ";
	    }

	    // 날짜
	    if (startDate != null && endDate != null) {

	        sql += "AND f.end_datetime >= ? "
	             + "AND f.start_datetime < ? ";
	    }

	    try (
	        Connection con = DBConnection.getConnection();
	        PreparedStatement pstmt = con.prepareStatement(sql)
	    ) {

	        int index = 1;

	        // 검색어
	        // 축제명 + 개최지역 + 계절
	        if (keyword != null && !keyword.trim().isEmpty()) {

	            String searchKeyword = keyword.trim();

	            // 계절 검색용 키워드 변환
	            String seasonKeyword = searchKeyword;

	            if (searchKeyword.equals("春")) {
	                seasonKeyword = "봄";
	            } else if (searchKeyword.equals("夏")) {
	                seasonKeyword = "여름";
	            } else if (searchKeyword.equals("秋")) {
	                seasonKeyword = "가을";
	            } else if (searchKeyword.equals("冬")) {
	                seasonKeyword = "겨울";
	            }

	            // 축제명
	            pstmt.setString(
	                    index++,
	                    "%" + searchKeyword + "%"
	            );

	            // 개최지역
	            pstmt.setString(
	                    index++,
	                    "%" + searchKeyword + "%"
	            );

	            // 계절
	            pstmt.setString(
	                    index++,
	                    "%" + seasonKeyword + "%"
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

	        if (rs.next()) {
	            totalCount =
	                    rs.getInt(1);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return totalCount;
	}
	
	//다가오는 축제
	public List<FestivalDto> getUpcomingFestivalList() {

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
	          + "WHERE f.start_datetime >= NOW() "
	          + "ORDER BY f.start_datetime ASC "
	          + "LIMIT 3";

	    try (
	        Connection con = DBConnection.getConnection();
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery()
	    ) {

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

