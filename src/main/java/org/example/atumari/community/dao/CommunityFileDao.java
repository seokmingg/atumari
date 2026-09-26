package org.example.atumari.community.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.atumari.common.database.DBConnection;
import org.example.atumari.community.dto.CommunityFileDto;
import org.example.atumari.festival.dao.LogPreparedStatement;

public class CommunityFileDao {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//첨부파일 정보 db에 저장
	 public int fileSave(CommunityFileDto filedto) {
	        int result = 0;
	        String sql =
	                "insert into community_files "
	                + "(cmty_no, original_file_name, save_file_name) "
	                + "values (?, ?, ?)";
	        try {
	            con = DBConnection.getConnection();
	            LogPreparedStatement ps =
	                    new LogPreparedStatement(con, sql);
	            // 회원 이메일
	            ps.setLong(1, filedto.getCmty_no());
	            // 원본 파일명
	            ps.setString(2, filedto.getOriginal_file_name());
	            // 서버 저장 파일명
	            ps.setString(3, filedto.getSave_file_name());

	            result = ps.executeUpdate();

	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(
	                    "fileSave() 오류! : "
	                    + (ps != null ? ps.toString() : "ps is null")
	            );
	        } finally {
	            DBConnection.closeDB(con, ps, rs);
	        }
	        return result;
	    }
	
	
   
	
	
}
