package org.example.atumari.member.dto;

public class SignupRequest {
	// 회원가입 입력값 dto: jsp에서 입력받은 검증값을 Service에서 재차 검증
    private String email;
    private String name;
    private String password;
    private String passwordConfirm;
    private boolean agree; // 이용약관 동의 체크박스
    
	public String getEmail() {
		return email;
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public boolean getAgree() {
		return agree;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public void setAgree(boolean agree) {
		this.agree = agree;
	}
    
}
