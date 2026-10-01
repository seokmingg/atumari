package org.example.atumari.community.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	 //파일 목록 불러오기
	 public List<CommunityFileDto> getCmtyFiles(long cmtyno) {
		    List<CommunityFileDto> files = new ArrayList<>();

		    String sql =
		        "SELECT file_no, cmty_no, original_file_name, save_file_name " +
		        "FROM community_files WHERE cmty_no = ? ORDER BY file_no";

		    try (Connection con = DBConnection.getConnection();
		         PreparedStatement ps = con.prepareStatement(sql)) {

		        ps.setLong(1, cmtyno);

		        try (ResultSet rs = ps.executeQuery()) {
		            while (rs.next()) {
		                files.add(new CommunityFileDto(
		                    rs.getLong("file_no"),
		                    rs.getLong("cmty_no"),
		                    rs.getString("original_file_name"),
		                    rs.getString("save_file_name")
		                ));
		            }
		        }
		        
		        return files;

		    } catch (SQLException e) {
		        throw new RuntimeException("커뮤니티 파일 조회 실패", e);
		    } finally {
	            DBConnection.closeDB(con, ps, rs);
	        }

		}
	 //파일 불러오기
	 public CommunityFileDto getCmtyFileByNo(long fileNo) {
		 CommunityFileDto dto = null;
	        String sql = "SELECT file_no, notice_no, original_file_name, stored_file_name "
	                + "FROM community_files WHERE file_no = ?";

	        try {
	        	con = DBConnection.getConnection();
	        	ps = con.prepareStatement(sql);
	            ps.setLong(1, fileNo);
	            rs = ps.executeQuery();
	            
	            if(rs.next()){
	            	dto = mapFile(rs);
	            }
	            
	        } catch (SQLException e) {
	            throw new RuntimeException("커뮤니티 첨부파일 조회 실패", e);
	        } finally {
	            DBConnection.closeDB(con, ps, rs);
	        }
	        return dto;
	        
	    }
	 //dto 생성
	 private CommunityFileDto mapFile(ResultSet rs) throws SQLException {
	        return new CommunityFileDto(
                    rs.getLong("file_no"),
                    rs.getLong("cmty_no"),
                    rs.getString("original_file_name"),
                    rs.getString("save_file_name")
                );
	    }
	
}
