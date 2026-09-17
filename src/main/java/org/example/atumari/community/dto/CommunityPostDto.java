package org.example.atumari.community.dto;

public class CommunityPostDto {
	private Long cmty_no;
    private String member_email;
    private String member_name;
    private String title;
    private String content;
    private String attach;
    private String reg_date;
    private String update_date;
    private int hit;
    
    //신규 게시물 저장 및 업데이트시 사용. 新規ポストアップロード・アップデート
	public CommunityPostDto(String member_email, String title, String content) {
		this.member_email = member_email;
		this.title = title;
		this.content = content;
	}
	//게시물 세부정보 ポスト内容
	public CommunityPostDto(Long cmty_no, String member_email, String member_name, String title, String content,
			String attach, String reg_date, String update_date, int hit) {
		this.cmty_no = cmty_no;
		this.member_email = member_email;
		this.member_name = member_name;
		this.title = title;
		this.content = content;
		this.attach = attach;
		this.reg_date = reg_date;
		this.update_date = update_date;
		this.hit = hit;
	}
	public Long getCmty_no() {
		return cmty_no;
	}
	public String getMember_email() {
		return member_email;
	}
	public String getMember_name() {
		return member_name;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getAttach() {
		return attach;
	}
	public String getReg_date() {
		return reg_date;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public int getHit() {
		return hit;
	}
	
	
    
    

    
}
