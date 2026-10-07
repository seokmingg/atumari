package org.example.atumari.community.dto;

public class CommunityDto {
	private Long cmty_no;
    private String member_email;
    private String member_name;
    private String title;
    private String content;
    private String attach;
    private String reg_date;
    private String update_date;
    private int hit;
    private int like;
    
    //신규 게시물 저장 및 업데이트시 사용. 新規ポストアップロード・アップデート
	public CommunityDto(String member_email, String title, String content) {
		this.member_email = member_email;
		this.title = title;
		this.content = content;
	}
	//게시물 세부정보 ポスト内容
	public CommunityDto(Long cmty_no, String member_email, String member_name, String title, String content,
			String reg_date, String update_date, int hit, int like) {
		this.cmty_no = cmty_no;
		this.member_email = member_email;
		this.member_name = member_name;
		this.title = title;
		this.content = content;
		this.reg_date = reg_date;
		this.update_date = update_date;
		this.hit = hit;
		this.like = like;
	}
	//게시물 리스트 
	public CommunityDto(Long cmty_no, String member_name, String title, String content, String reg_date, int hit, int like) {
		super();
		this.cmty_no = cmty_no;
		this.member_name = member_name;
		this.title = title;
		this.content = content;
		this.reg_date = reg_date;
		this.hit = hit;
		this.like = like;
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
	
	public int getLike() {
		return like;
	}
	
	
	
    
    

    
}
