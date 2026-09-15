package org.example.atumari.inquiry.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.atumari.common.database.DBConnection;
import org.example.atumari.inquiry.dto.InquiryFileDto;

public class InquiryFileDao {

	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public int insertFile(InquiryFileDto fileDto) {

		int result = 0;
		
		String sql="insert into inquiry_file\r\n"
				+ "(inquiry_no, original_file_name, stored_file_name)\r\n"
				+ "values\r\n"
				+ "(?,?,?)";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1,fileDto.getInquiry_no());
			ps.setString(2, fileDto.getOriginal_file_name());
			ps.setString(3, fileDto.getStored_file_name());
			
			result = ps.executeUpdate();
		
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}
	
}
