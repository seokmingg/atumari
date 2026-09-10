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

	// 로그인 - 로그인한 회원 이름 조회
	public String getLoginName(String email, String dbPassword) {
		String loginName = "";
		/*
		 * 우선 exit_date(탈퇴일자) 존재 여부 포함해 회원 검증 구현
		 * 추후 논의를 거쳐 SOFT DELETE를 구현하는 경우 쿼리 수정 예정
		 * */
		String sql = "SELECT m.name\r\n"
				+ "FROM member m, member_auth a\r\n"
				+ "WHERE m.id = a.member_id\r\n"
				+ "AND m.email = ?\r\n"
				+ "AND a.password = ?\r\n"
				+ "AND m.exit_date is null";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, email);
			ps.setString(2, dbPassword);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				loginName = rs.getString(1);
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return loginName;
	}

	
}
