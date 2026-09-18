package org.example.atumari.member.dto;

public class MemberAuthDto {
	/*
	 * 회원 인증 정보: 회원 인증 번호, 회원번호, 비밀번호, 제공자(LOCAL, OAuth), OAuth 제공 아이디
	 */
	private Long auth_id;
	private Long member_id;
	private String password;
	private String provider;
	private String provider_id;
	
	public Long getAuth_id() {
		return auth_id;
	}
	public Long getMember_id() {
		return member_id;
	}
	public String getPassword() {
		return password;
	}
	public String getProvider() {
		return provider;
	}
	public String getProvider_id() {
		return provider_id;
	}
	public void setAuth_id(Long auth_id) {
		this.auth_id = auth_id;
	}
	public void setMember_id(Long member_id) {
		this.member_id = member_id;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public void setProvider_id(String provider_id) {
		this.provider_id = provider_id;
	}
	
}
