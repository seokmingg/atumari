package org.example.atumari.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.example.atumari.member.dto.MemberAuthDto;

public class MemberAuthDao {
	/**
	 * 회원 인증 데이터의 조회와 저장을 구현할 DAO입니다.
	 */
	private MemberAuthDao() {}
	private static MemberAuthDao dao = new MemberAuthDao();
	public static MemberAuthDao getDao() {	return dao;	}
	
	public int insertMemberAuth(Connection con, MemberAuthDto memberAuthDto) {
		int result = 0;
		
		String sql = "INSERT INTO member_auth (member_id, password, provider, provider_id) "
					+ "VALUES (?, ?, ?, null);";
		
		try (PreparedStatement ps = 
				con.prepareStatement(sql)) {
			ps.setLong(1, memberAuthDto.getMember_id());
			ps.setString(2, memberAuthDto.getPassword());
			ps.setString(3, memberAuthDto.getProvider());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
