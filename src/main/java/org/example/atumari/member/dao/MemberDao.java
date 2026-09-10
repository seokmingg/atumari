package org.example.atumari.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.example.atumari.common.database.DBConnection;
import org.example.atumari.member.dto.LoginRequest;
import org.example.atumari.member.dto.SignupRequest;
/**
 * 회원 데이터의 조회와 저장을 구현할 DAO입니다.
 */
public class MemberDao {
	private MemberDao() {}
	private static MemberDao dao = new MemberDao();
	public static MemberDao getDao() {	return dao; }
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	// 회원 정보 삽입 -> 자동 생성 id 값 획득
	public Long insertMember(Connection con, SignupRequest signup) throws SQLException {
		
		String sql = "INSERT INTO member (EMAIL, NAME) \r\n"
				+ "VALUES (?, ?);";
		
		try (PreparedStatement ps = 
				con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			ps.setString(1, signup.getEmail());
			ps.setString(2, signup.getName());
			
			ps.executeUpdate();
			
			// 생성된 id 획득
			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					return rs.getLong(1);
				}
			}
		}
		
		return null;
	}

	// 회원 이메일 중복 체크
	public int checkEmailCount(String email) {
		int count = 0;
		String sql = "SELECT COUNT(*) AS COUNT\r\n"
				+ "FROM member\r\n"
				+ "WHERE email = ?";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, email);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt(1);
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return count;
	}
	
	// 로그인 - 해시된 비밀번호 값 조회
	public String getDBPassword(LoginRequest login) {
		String dbPassword = "";
		
		String sql = "SELECT a.password\r\n"
				+ "FROM member_auth a, member m\r\n"
				+ "WHERE a.member_id = m.id\r\n"
				+ "AND m.email = ?";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, login.getEmail());
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				dbPassword = rs.getString(1);
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dbPassword;
	}

	// 로그인 - 가입 회원 조회
	public int checkMemberCount(String email, String email2) {
		
		return 0;
	}

	
}
