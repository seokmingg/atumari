package org.example.atumari.community.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import org.example.atumari.common.database.DBConnection;
import org.example.atumari.community.dto.CommunityCommentDto;

public class CommunityCommentDao {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	//댓글 전체 조회
	public List<CommunityCommentDto> getCommunityCommentView(Long cmtyno) {
		List<CommunityCommentDto> dtos = new ArrayList<CommunityCommentDto>();
		String sql = "select cc.comment_no, cc.parent_no, m.email, m.name, cc.content, cc.reg_date, cc.update_date\r\n"
				+ "from atumari.community c, atumari.member m, atumari.community_comments cc\r\n"
				+ "where cc.member_id = m.id\r\n"
				+ "and c.cmty_no = cc.cmty_no\r\n"
				+ "and cc.cmty_no = ?\r\n"
				+ "ORDER BY\r\n"
				+ "    COALESCE(cc.parent_no, cc.comment_no),\r\n"
				+ "    CASE\r\n"
				+ "        WHEN cc.parent_no IS NULL THEN 0\r\n"
				+ "        ELSE 1\r\n"
				+ "    END,\r\n"
				+ "    cc.comment_no;";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql.toString());
			ps.setLong(1, cmtyno);
			rs = ps.executeQuery();
			while(rs.next()) {
				CommunityCommentDto dto = new CommunityCommentDto(cmtyno,
											rs.getLong("comment_no"),
											rs.getLong("parent_no"),
											rs.getString("email"),
											rs.getString("name"),
											rs.getString("content"),
											rs.getTimestamp("reg_date"),
											rs.getTimestamp("update_date")
											);
				dtos.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
            System.out.println("getCommunityCommentView() 오류:"+ps.toString());
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return dtos;
	}
	//댓글 저장
	public int saveComment(CommunityCommentDto dto) {
		int result = 0;
		String sql = "insert into atumari.community_comments\r\n"
				+ "(cmty_no, member_id, parent_no, content)\r\n"
				+ "values\r\n"
				+ "(?, (SELECT id\r\n"
				+ "     FROM atumari.member\r\n"
				+ "     WHERE email = ?), ?, ?)";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql.toString());
			ps.setLong(1, dto.getCmty_no());
			ps.setString(2, dto.getMember_email());
			if(dto.getParent_no() != null) {
				ps.setLong(3, dto.getParent_no());
			} else {
				ps.setNull(3, java.sql.Types.BIGINT);
			}
			ps.setString(4, dto.getContent());
			result = ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
            System.out.println("getCommunityCommentView() 오류:"+ps.toString());
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}
	//원게시물 삭제시 댓글 일괄 삭제
	public int deleteCommentsByCmtyNo(Long cmtyNo) {

	    String sql1 = "DELETE FROM atumari.community_comments\r\n"
	    		+ "	            WHERE cmty_no = ?\r\n"
	    		+ "	              AND parent_no IS NOT NULL";

	    String sql2 = "DELETE FROM atumari.community_comments\r\n"
	    		+ "	            WHERE cmty_no = ?";

	    int result = 0;

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps1 = conn.prepareStatement(sql1);
	         PreparedStatement ps2 = conn.prepareStatement(sql2)) {

	        // 답글 먼저 삭제
	        ps1.setLong(1, cmtyNo);
	        result += ps1.executeUpdate();

	        // 부모 댓글 삭제
	        ps2.setLong(1, cmtyNo);
	        result += ps2.executeUpdate();

	        return result;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0;
	    }
	}
	//댓글 삭제
		public int deleteComment(Long commentNo) {
		    int result = 0;
		    String sql =
		            "UPDATE atumari.community_comments "
        		  + "SET content = ? , "
       	          + "    update_date = NOW() "
       	          + "WHERE comment_no = ? ";
		    try {
		        con = DBConnection.getConnection();
		        ps = con.prepareStatement(sql);
		        ps.setString(1, "このコメントは削除されました。");
		        ps.setLong(2, commentNo);

		        result = ps.executeUpdate();

		    } catch (Exception e) {
		        e.printStackTrace();
		        System.out.println("deleteComment() 오류!"+ps.toString());

		    } finally {
		        DBConnection.closeDB(con, ps, rs);
		    }
		    return result;
		}
	
}
