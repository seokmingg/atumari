package org.example.atumari.member.service;

import org.example.atumari.member.dto.MemberDto;
import org.example.atumari.member.dto.SignupRequest;

/**
 * 회원 관련 업무 로직을 구현할 서비스입니다.
 */
public class MemberService {

	// 회원가입
	public void signup(SignupRequest signup) {
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
		
		// MemberDto 생성
		MemberDto member = new MemberDto();
		
		member.setEmail(signup.getEmail());
		member.setName(signup.getName());
		// TODO. 비밀번호 해시한 뒤 MemberDto에 값 마저 담기
		
		// MemberAuthDto 생성
	}
}
