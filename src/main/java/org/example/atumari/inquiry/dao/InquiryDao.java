package org.example.atumari.inquiry.dao;

import org.example.atumari.inquiry.dto.InquiryDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.example.atumari.common.database.DBConnection;

public class InquiryDao {
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public int insertInquiry(InquiryDto inquiry) {
		
		int inquiry_no=0;
		
		String sql ="INSERT INTO inquiry " +
			    "(member_id, title, writer, is_public, content, email) " +
			    "VALUES (?, ?, ?, ?, ?, ?)";
		
		try{
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			//두번째 인수 의미:INSERT에서 DB가 자동 생성한 키도 나한테 돌려줘라는 의미
				ps.setLong(1, inquiry.getMember_id());
				ps.setString(2, inquiry.getTitle());
				ps.setString(3, inquiry.getWriter());
				ps.setBoolean(4, inquiry.isPublic());
				ps.setString(5, inquiry.getContent());
				ps.setString(6, inquiry.getEmail());
				
			int result = ps.executeUpdate();
			
			if(result > 0) {
				rs = ps.getGeneratedKeys();//JDBC에는 INSERT하면 DB가 자동 생성한 PK를 바로 받을 수 있는 기능
				/*SELECT MAX(inquiry_no) 같은 방식으로 다시 조회하는 건 
				 * 여러 사용자가 동시에 글을 작성하면 잘못된 번호를 가져올 위험이 있으므로 사용하지 않는 게 좋음*/
				
				if(rs.next()) {
					inquiry_no = rs.getInt(1);
				}
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
				
		return inquiry_no;
	}

}
