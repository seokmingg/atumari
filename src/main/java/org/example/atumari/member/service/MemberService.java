package org.example.atumari.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.example.atumari.common.database.DBConnection;
import org.example.atumari.member.dao.MemberDao;
import org.example.atumari.member.dto.MemberAuthDto;
import org.example.atumari.member.dto.MemberDto;
import org.example.atumari.member.dto.SignupRequest;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * 회원 관련 업무 로직을 구현할 서비스입니다.
 */
public class MemberService {

	// 회원가입
	public void signup(SignupRequest signup) throws SQLException {
		Connection con = null; // Service에서 Connection 생성
		
		try {
			// Connection 생성
			con = DBConnection.getConnection();
			con.setAutoCommit(false); // 자동 커밋 끄기
			
			// signup.jsp 입력값 검증
			if (!signup.getEmail().matches("^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) { // 이메일
				throw new IllegalArgumentException("有効なメールアドレスを入力してください。");
			}
			
			if (!signup.getPassword().equals(signup.getPasswordConfirm())) { // 비밀번호
				throw new IllegalArgumentException("同じパスワードを入力してください。");
			}
			
			if (!signup.getAgree()) { // 이용약관 동의 체크박스
				throw new IllegalArgumentException("利用規約とプライバシーポリシーに同意してください.");
			}
			
			// 회원 정보
			MemberDto member = new MemberDto();
			
			member.setEmail(signup.getEmail());
			member.setName(signup.getName());
			// TODO. 비밀번호 해시한 뒤 MemberDto에 값 마저 담기
			String hashPw = BCrypt.withDefaults().hashToString(10, signup.getPassword().toCharArray()); // 60자 해시값 반환
			
			// 인증 정보
			MemberAuthDto memberAuth = new MemberAuthDto();
			
			memberAuth.setPassword(hashPw);
			memberAuth.setProvider("LOCAL");
			
			// DAO 호출
			MemberDao dao = MemberDao.getDao();
			Long memberId = dao.insertMember(con, signup);
			
			
			// 처리가 모두 성공하면 커밋
			con.commit();
			
		} catch (Exception e) {
			// 하나라도 실패하면 전체 롤백
			if (con != null) {
				con.rollback();
			}
			
			throw e;
			
		} finally {
			if (con != null) {
				con.close();
			}
		}
		
	}
}
