package org.example.atumari.community.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.atumari.common.database.DBConnection;
import org.example.atumari.community.dto.CommunityDto;

/**
 * 커뮤니티 데이터의 조회와 저장을 구현할 DAO입니다.
 */
public class CommunityDao {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	// 게시물 저장
	public Long communitySave(CommunityDto cmtydto) {
	    Long cmty_no = null;
	    String sql =
	            "insert into community "
	            + "(member_id, title, content) "
	            + "values ("
	            + "(select id from member where email = ?), "
	            + "?, "
	            + "?)";
	    try {
	        con = DBConnection.getConnection();
	        ps = con.prepareStatement(
	                sql,
	                java.sql.Statement.RETURN_GENERATED_KEYS
	        );
	        ps.setString(1, cmtydto.getMember_email());
	        ps.setString(2, cmtydto.getTitle());
	        ps.setString(3, cmtydto.getContent());

	        ps.executeUpdate();
	        // DB에서 생성된 cmty_no 가져오기
	        rs = ps.getGeneratedKeys();
	        if (rs.next()) {
	            cmty_no = rs.getLong(1);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("communitySave() 오류!!");
	    } finally {
	        DBConnection.closeDB(con, ps, rs);
	    }
	    return cmty_no;
	}
	//조회수 증가
	public int setHitCount(Long no) {
		int result = 0;
		String sql = "update community\r\n"
				+ "		set hit = hit + 1\r\n"
				+ "		where cmty_no = ? ";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql.toString());
			ps.setLong(1, no);
			result = ps.executeUpdate();
		} catch(Exception e) {
			System.out.println("setHitCount() 오류"+ps.toString());
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}	
		return result;
	}
	
	//커뮤니티 글 삭제
	public int deleteCommunity(Long cmtyNo) {

	    int result = 0;

	    String sql =
	            "DELETE c " +
	            "FROM community c " +
	            "JOIN member m ON c.member_id = m.id " +
	            "WHERE c.cmty_no = ? ";

	    try {
	        con = DBConnection.getConnection();
	        ps = con.prepareStatement(sql);

	        ps.setLong(1, cmtyNo);

	        result = ps.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("deleteCommunity() 오류!");

	    } finally {
	        DBConnection.closeDB(con, ps, rs);
	    }

	    return result;
	}
	
	// 커뮤니티 글 수정
	public int updateCommunity(long cmtyNo, String memberEmail, String title, String content) {

	    int result = 0;

	    String sql =
	            "UPDATE community c "
	          + "JOIN member m ON c.member_id = m.id "
	          + "SET c.title = ?, "
	          + "    c.content = ?, "
	          + "    c.update_date = NOW() "
	          + "WHERE c.cmty_no = ? "
	          + "AND m.email = ?";

	    try {
	        con = DBConnection.getConnection();
	        ps = con.prepareStatement(sql);

	        ps.setString(1, title);
	        ps.setString(2, content);
	        ps.setLong(3, cmtyNo);
	        ps.setString(4, memberEmail);

	        result = ps.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("updateCommunity() 오류!");

	    } finally {
	        DBConnection.closeDB(con, ps, rs);
	    }

	    return result;
	}

	//게시물 총 갯수
	public int countCommunity(String searchType, String search) {
		int count = 0;
		StringBuilder sql = new StringBuilder(
				"SELECT COUNT(*) AS count " +
		        "FROM atumari.community c " +
		        "JOIN atumari.member m ON c.member_id = m.id\r\n"
		);
		boolean hasKeyword = search != null && !search.isBlank();

        if (hasKeyword) {
        	sql.append("WHERE ");
        	 if ("content".equals(searchType)) {
                 sql.append("c.content LIKE ?");
             } else if ("title".equals(searchType)) {
                 sql.append("c.title LIKE ?");
             } else if ("content_title".equals(searchType)) {
                 sql.append("(c.content LIKE ? OR c.title LIKE ?)");
             } else if ("writer".equals(searchType)) {
                 sql.append("m.name LIKE ?");
             }
        }
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql.toString());
			if (hasKeyword) {
		        ps.setString(1, "%" + search + "%");
		        if ("content_title".equals(searchType)) {
		            ps.setString(2, "%" + search + "%");
		        }
		    }
			rs = ps.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count");
			}
		} catch(Exception e) {
			System.out.println("countCommunity() 오류!!"+ps.toString());
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return count;
	}
	
	//리스트 불러오기
	public List<CommunityDto> getCommunityList(int limit, int offset, String searchType,
			String search) {
		List<CommunityDto> dtos = new ArrayList<CommunityDto>();
		StringBuilder sql = new StringBuilder(
				"select c.cmty_no, m.name, c.title, c.content, DATE_FORMAT(c.reg_date, '%Y.%m.%d.') AS reg_date, c.hit\r\n"
				+ "from atumari.community c, atumari.member m\r\n"
				+ "where c.member_id = m.id\r\n");
		boolean hasKeyword = search != null && !search.isBlank();

        if (hasKeyword) {
            sql.append(" AND ");
            if(searchType.equals("content")) sql.append("c.content like ?");
            else if(searchType.equals("title")) sql.append("c.title like ?");
            else if(searchType.equals("content_title")) sql.append("(c.content like ? or c.title like ?)");
            else if(searchType.equals("writer")) sql.append("m.name like ?");
            
        }
        sql.append(" ORDER BY c.cmty_no DESC LIMIT ? OFFSET ?");
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql.toString());
			
			int index = 1;
			if (hasKeyword) {
		        ps.setString(index++, "%" + search + "%");
		        if ("content_title".equals(searchType)) {
		            ps.setString(index++, "%" + search + "%");
		        }
		    }
			ps.setInt(index++, limit);
            ps.setInt(index, offset);
			
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                	dtos.add(mapCmty(rs));
                }
            }
		} catch(Exception e) {
			e.printStackTrace();
            System.out.println("getCommunityList() 오류 :"+ps.toString());
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return dtos;
	}
	
	//상세조회
	public CommunityDto getCommunityView(long cmtyno) {
		CommunityDto dto = null;
		String sql = "select m.email, m.name, c.title, c.content, c.reg_date, c.update_date, c.hit\r\n"
				+ "from atumari.community c, atumari.member m \r\n"
				+ "where c.member_id = m.id\r\n"
				+ "and cmty_no = ?";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql.toString());
			ps.setLong(1, cmtyno);
            
			rs = ps.executeQuery();
			if(rs.next()) {
				dto = new CommunityDto(cmtyno, 
									rs.getString("email"),
									rs.getString("name"), 
									rs.getString("title"), 
									rs.getString("content"), 
									rs.getString("reg_date"), 
									rs.getString("update_date"),
									rs.getInt("hit"),
									0);
			}
		} catch(Exception e) {
			e.printStackTrace();
            System.out.println("getCommunityView() 오류:"+ps.toString());
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return dto;
	}
	//인기글 불러오기 
	public List<CommunityDto> getCommunityHitList() {
		List<CommunityDto> dtos = new ArrayList<CommunityDto>();
		String sql = "select c.cmty_no, m.name, c.title, c.content, DATE_FORMAT(c.reg_date, '%Y.%m.%d.') AS reg_date, c.hit\r\n"
				+ "from atumari.community c\r\n"
				+ "JOIN atumari.member m ON m.id = c.member_id\r\n"
				+ "WHERE c.hit >= 50\r\n"
				+ "ORDER BY c.hit DESC, c.cmty_no desc\r\n"
				+ "LIMIT 3";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql.toString());
			rs = ps.executeQuery();
            while (rs.next()) {
            	dtos.add(mapCmty(rs));
            }
		} catch(Exception e) {
			e.printStackTrace();
            System.out.println("getCommunityHitList() 오류 :"+ps.toString());
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return dtos;
	}
	

	//리스트 저장
		private CommunityDto mapCmty(ResultSet rs) throws SQLException {
			CommunityDto cmty = new CommunityDto(rs.getLong("cmty_no"), 
												rs.getString("name"), 
												rs.getString("title").replace("&#39;", "'"), 
												rs.getString("content"), 
												rs.getString("reg_date"), 
												rs.getInt("hit"),
												0);
	        return cmty;
	    }
		
	
}
