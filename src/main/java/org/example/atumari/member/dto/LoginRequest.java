package org.example.atumari.member.dto;

public class LoginRequest {
	// 로그린 입력값 dto: jsp에서 입력받은 검증값을 Service에서 재차 검증
    private String email;
    private String password;
    
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
