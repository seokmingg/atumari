package org.example.atumari.inquiry.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.atumari.common.database.DBConnection;
import org.example.atumari.inquiry.dto.InquiryFileDto;

public class InquiryFileDao {

	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	// 문의 글 등록시 첨부파일 저장
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
	
	// 상세페이지 첨부파일 조회
	public List<InquiryFileDto> getInquiryFiles(int inquiryNo) {
		List<InquiryFileDto> fileDtos = new ArrayList<>(); 
		
		String sql="select file_no, original_file_name, stored_file_name\r\n"
				+ "from inquiry_file\r\n"
				+ "where inquiry_no = ?";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, inquiryNo);
			rs = ps.executeQuery(); 
			
			while(rs.next()) {

			    InquiryFileDto fileDto = new InquiryFileDto();

			    fileDto.setFile_no(rs.getInt("file_no"));
			    fileDto.setOriginal_file_name(
			            rs.getString("original_file_name")
			    );
			    fileDto.setStored_file_name(
			            rs.getString("stored_file_name")
			    );
				
					fileDtos.add(fileDto);
				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		
		return fileDtos;
	}

	
	// 문의 수정및 삭제시 첨부파일 삭제
	public int deleteFile(int file_no) {
		
		int result =0;
		
		String sql="delete from inquiry_file\r\n"
				+ "where file_no =?";
		 try {

		        con = DBConnection.getConnection();
		        ps = con.prepareStatement(sql);

		        ps.setInt(1, file_no);

		        result = ps.executeUpdate();

		    } catch (SQLException e) {

		        e.printStackTrace();

		    } finally {

		        DBConnection.closeDB(con, ps, rs);
		    }

		
		return result;
	}

	
}
