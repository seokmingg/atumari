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

	//상세조회
	public List<CommunityCommentDto> getCommunityCommentView(long cmtyno) {
		List<CommunityCommentDto> dtos = new ArrayList<CommunityCommentDto>();
		String sql = "select cc.comment_no, m.email, m.name, cc.content, cc.reg_date, cc.update_date\r\n"
				+ "from atumari.community c, atumari.member m, atumari.community_comments cc\r\n"
				+ "where cc.member_id = m.id\r\n"
				+ "and c.cmty_no = ?\r\n"
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
											rs.getString("email"),
											rs.getString("name"),
											rs.getString("content"),
											rs.getString("reg_date"),
											rs.getString("update_date")
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
	
	
}
