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

	// 게시물 저장
	public Long communitySave(CommunityPostDto cmtydto) {

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
	    
	    

    // 첨부파일 저장
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
