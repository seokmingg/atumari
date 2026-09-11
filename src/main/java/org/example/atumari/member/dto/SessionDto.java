package org.example.atumari.member.dto;

public class SessionDto {
	// 로그인 세션 dto
	private String sessionEmail;
	private String sessionName;
	private String sessionLevel;
	
	public String getSessionEmail() {
		return sessionEmail;
	}
	public String getSessionName() {
		return sessionName;
	}
	public String getSessionLevel() {
		return sessionLevel;
	}
	public void setSessionEmail(String sessionEmail) {
		this.sessionEmail = sessionEmail;
	}
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
	public void setSessionLevel(String sessionLevel) {
		this.sessionLevel = sessionLevel;
	}
	
}
