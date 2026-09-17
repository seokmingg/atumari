package org.example.atumari.community.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.example.atumari.common.database.DBConnection;
import org.example.atumari.community.dto.CommunityFileDto;
import org.example.atumari.community.dto.CommunityPostDto;
import org.example.atumari.festival.dao.LogPreparedStatement;

/**
 * 커뮤니티 데이터의 조회와 저장을 구현할 DAO입니다.
 */
public class CommunityDao {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	    // =========================================================
	    // 게시물 저장
	    // DB에서 생성된 cmty_no를 반환
	    // =========================================================
	    public Long communitySave(CommunityPostDto cmtydto) {

	        Long cmtyNo = null;

	        String sql =
	                "insert into community "
	                + "(member_id, title, content) "
	                + "values ("
	                + "(select id from member where email = ?), "
	                + "?, "
	                + "?) "
	                + "returning cmty_no into ?";

	        try {

	            con = DBConnection.getConnection();

	            LogPreparedStatement ps =
	                    new LogPreparedStatement(con, sql);

	            // 1. 회원 이메일
	            ps.setString(1, cmtydto.getMember_email());

	            // 2. 제목
	            ps.setString(2, cmtydto.getTitle());

	            // 3. 내용
	            ps.setString(3, cmtydto.getContent());

	            // 4. DB에서 생성되는 cmty_no
	            ps.registerOutParameter(4, Types.NUMERIC);

	            ps.executeUpdate();

	            // DB가 생성한 cmty_no 가져오기
	            cmtyNo = ps.getLong(4);

	        } catch (Exception e) {

	            e.printStackTrace();

	            System.out.println(
	                    "communitySave() 오류! : "
	                    + (ps != null ? ps.toString() : "ps is null")
	            );

	        } finally {

	            DBConnection.closeDB(con, ps, rs);
	        }

	        return cmtyNo;
	    }

	    // 첨부파일 저장
	    public int fileSave(CommunityFileDto filedto) {
	        int result = 0;
	        String sql =
	                "insert into community_file "
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
