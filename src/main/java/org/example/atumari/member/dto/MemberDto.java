package org.example.atumari.member.dto;

public class MemberDto {
    private Long id;
    private String email;
    private String name;
    private String nickname;
    private String tel;
    private String reg_date;
    private String modify_date;
    private String exit_date;
    private String rk;
    private String filepath;
    
	public Long getId() {
		return id;
	}
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
	public String getReg_date() {
		return reg_date;
	}
	public String getModify_date() {
		return modify_date;
	}
	public String getExit_date() {
		return exit_date;
	}
	public String getRk() {
		return rk;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setId(Long id) {
		this.id = id;
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
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public void setModify_date(String modify_date) {
		this.modify_date = modify_date;
	}
	public void setExit_date(String exit_date) {
		this.exit_date = exit_date;
	}
	public void setRk(String rk) {
		this.rk = rk;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
    
}
