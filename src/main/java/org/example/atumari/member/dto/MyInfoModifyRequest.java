package org.example.atumari.member.dto;

public class MyInfoModifyRequest {
	// 마이페이지 정보 수정 입력값 dto: jsp에서 입력받은 검증값을 Service에서 재차 검증
	private String email;
	private String name;
	private String nickname;
	private String tel;
	
	public String getEmail() {
		return email;
	}
	public String getName() {
		return name;
	}
	public String getNickname() {
		return nickname;
	}
	public String getTel() {
		return tel;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
}
