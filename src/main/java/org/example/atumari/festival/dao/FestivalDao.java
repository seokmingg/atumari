package org.example.atumari.festival.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.example.atumari.common.database.DBConnection;
import org.example.atumari.festival.dto.FestivalDto;
import org.example.atumari.festival.dto.PrefectureDto;

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
    	      + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) "
    	      + "on duplicate key update "
    	      + "festival_name = values(festival_name), "
    	      + "summary = values(summary), "
    	      + "start_datetime = values(start_datetime), "
    	      + "end_datetime = values(end_datetime), "
    	      + "venue_name = values(venue_name), "
    	      + "venue_address = values(venue_address), "
    	      + "access_info = values(access_info), "
    	      + "image_url = values(image_url), "
    	      + "organizer = values(organizer), "
    	      + "price_free = values(price_free), "
    	      + "price_text = values(price_text), "
    	      + "external_url = values(external_url), "
    	      + "image_source = values(image_source), "
    	      + "prefecture_no = values(prefecture_no), "
    	      + "season = values(season)";
    	
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
    
    //리스트 조회,페이지 네이션
    public List<FestivalDto> getFestivalList(String regionName, Integer prefectureNo,
    										int start,int end) {
            
        List<FestivalDto> list = new ArrayList<>();

        int offset = start - 1;
        int pageSize = end - start + 1;

        String sql =  "SELECT f.festival_no, "
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
                + "WHERE p.region_name = ? ";
               

        // 도도부현을 선택했을 때만 조건 추가
        if (prefectureNo != null) {
            sql += "AND f.prefecture_no = ? ";
        }

        sql += "ORDER BY f.start_datetime "
             + "LIMIT ?, ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {

            int index = 1;

            // 지역
            pstmt.setString(index++, regionName);

            // 도도부현
            if (prefectureNo != null) {
                pstmt.setInt(index++, prefectureNo);
            }

            // 페이지
            pstmt.setInt(index++, offset);
            pstmt.setInt(index++, pageSize);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                FestivalDto dto = new FestivalDto(
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


        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
	//도도부현 불러오기
	public List<PrefectureDto> getPrefectureList(String regionName) {

	    List<PrefectureDto> list = new ArrayList<>();

	    String sql =
	            "SELECT p.prefecture_no, "
	          + "       p.prefecture_name, "
	          + "       p.region_name "
	          + "FROM prefecture p "
	          + "WHERE p.region_name = ? "
	          + "ORDER BY p.prefecture_no";

	    try (
	        Connection con = DBConnection.getConnection();
	        PreparedStatement pstmt = con.prepareStatement(sql)
	    ) {

	        pstmt.setString(1, regionName);

	        ResultSet rs = pstmt.executeQuery();

	        while (rs.next()) {

	            PrefectureDto dto = new PrefectureDto(
	                    rs.getInt("prefecture_no"),
	                    rs.getString("prefecture_name"),
	                    rs.getString("region_name")
	            );

	            list.add(dto);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	
	public int getFestivalTotalCount(String regionName, Integer prefectureNo) {
	        
	    int totalCount = 0;

	    String sql = "SELECT COUNT(*) "
		          + "FROM festival f "
		          + "JOIN prefecture p "
		          + "ON f.prefecture_no = p.prefecture_no "
		          + "WHERE p.region_name = ? ";
	            

	    if (prefectureNo != null) {
	        sql += "AND f.prefecture_no = ? ";
	    }

	    try (
	        Connection con = DBConnection.getConnection();
	        PreparedStatement pstmt =
	                con.prepareStatement(sql)
	    ) {

	        int index = 1;

	        pstmt.setString(index++, regionName);

	        if (prefectureNo != null) {
	            pstmt.setInt(index++, prefectureNo);
	        }

	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            totalCount = rs.getInt(1);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return totalCount;
	}
	
}