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
	
	//커뮤니티 글 삭제
	public static void deleteCommunity(Long cmtyNo) {
		// TODO Auto-generated method stub
		
	}

	//게시물 총 갯수
	public int countCommunity(String SearchType, String search) {
		int count = 0;
		String sql = "SELECT COUNT(*) as count FROM atumari.community\r\n"
				+ "where ? like ? ";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, SearchType);
			ps.setString(2, "%"+search+"%");
		} catch(Exception e) {
			System.out.println("countCommunity() 오류!!");
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return count;
	}
	
	//리스트 불러오기
	public List<CommunityDto> getCommunityList(int pageSize, int offset, String normalizedSearchType,
			String normalizedKeyword) {
		List<CommunityDto> dtos = new ArrayList<CommunityDto>();
		String sql = "";
		try {
			con = DBConnection.getConnection();
			
		} catch(Exception e) {
			
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return dtos;
	}



	
}
