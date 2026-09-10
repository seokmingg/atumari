package org.example.atumari.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.example.atumari.common.database.DBConnection;
import org.example.atumari.member.dao.MemberAuthDao;
import org.example.atumari.member.dao.MemberDao;
import org.example.atumari.member.dto.LoginRequest;
import org.example.atumari.member.dto.MemberAuthDto;
import org.example.atumari.member.dto.MemberDto;
import org.example.atumari.member.dto.SignupRequest;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * 회원 관련 업무 로직을 구현할 서비스입니다.
 */
public class MemberService {

	// 회원가입
	public int signup(SignupRequest signup) throws SQLException {
		Connection con = null; // Service에서 Connection 생성
		
		try {
			// Connection 생성
			con = DBConnection.getConnection();
			con.setAutoCommit(false); // 자동 커밋 끄기
			
			// DAO 호출
			MemberDao memberDao = MemberDao.getDao();
			
			// signup.jsp 입력값 검증
			if (!signup.getEmail().matches("^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) { // 이메일
				throw new IllegalArgumentException("有効なメールアドレスを入力してください。");
			}
			
			if (!signup.getPassword().equals(signup.getPasswordConfirm())) { // 비밀번호 일치
				throw new IllegalArgumentException("同じパスワードを入力してください。");
			}
			// Feat. 비밀번호 값 길이 검증 추가
			if (signup.getPassword().length() < 8 || signup.getPassword().length() > 20) { // 비밀번호 일치
				throw new IllegalArgumentException("パスワードは8文字以上20文字以下で入力してください。");
			}
			
			if (!signup.getAgree()) { // 이용약관 동의 체크박스
				throw new IllegalArgumentException("利用規約とプライバシーポリシーに同意してください.");
			}
			
			// 회원 정보
			MemberDto memberDto = new MemberDto();
			
			memberDto.setEmail(signup.getEmail());
			memberDto.setName(signup.getName());
			String hashPw = BCrypt.withDefaults().hashToString(10, signup.getPassword().toCharArray()); // 60자 해시값 반환
			
			// 인증 정보
			MemberAuthDto memberAuthDto = new MemberAuthDto();
			
			memberAuthDto.setPassword(hashPw);
			memberAuthDto.setProvider("LOCAL");
			
			Long memberId = memberDao.insertMember(con, signup);
			
			if (memberId == null || memberId <= 0) {
				throw new SQLException("회원번호 ID 생성 실패");
			}
			
			memberAuthDto.setMember_id(memberId);
			
			MemberAuthDao authDao = MemberAuthDao.getDao();
			
			// 회원 인증 정보 삽입 (성공하면 1, 실패하면 0 반환)
			int result = authDao.insertMemberAuth(con, memberAuthDto);
			
			// 처리가 모두 성공하면 커밋
			con.commit();
			
			return result;
			
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

	// CheckEmailController 이메일 중복 체크
	public int checkDuplicateEmail(String email) {
		int count = 0;
		
		// DAO 호출
		MemberDao memberDao = MemberDao.getDao();
					
		count = memberDao.checkEmailCount(email);
		
		return count;
		
	}

	// 로그인
	public int login(LoginRequest login) throws SQLException {
		int count = 0;
		
		// login.jsp 입력값 검증
		if (!login.getEmail().matches("^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) { // 이메일
			throw new IllegalArgumentException("有効なメールアドレスを入力してください。");
		}
		
//		if (login.getPassword().length() < 8 || login.getPassword().length() > 20) { // 비밀번호 일치
//			throw new IllegalArgumentException("パスワードは8文字以上20文字以下で入力してください。");
//		}	
		
		// DAO 호출
		MemberDao memberDao = MemberDao.getDao();
		
		// DB에서 해시된 비밀번호 획득
		String dbPassword = memberDao.getDBPassword(login);
		
		// 가입 여부 조회 (count 0이면 없는 회원, 1 이상이면 존재하는 회원)
		count = memberDao.checkMemberCount(login.getEmail(), dbPassword);
		
		return count;
	}
}
