package org.example.atumari.community.dto;

public class CommunityPostDto {
	private Long cmty_no;
    private Long member_id;
    private String member_name;
    private String title;
    private String content;
    private String attach;
    private String reg_date;
    private String update_date;
    private int hit;
    
    //신규 게시물 저장 및 업데이트시 사용. 新規ポストアップロード・アップデート
	public CommunityPostDto(Long cmty_no, Long member_id, String title, String content, String attach, String reg_date) {
		this.cmty_no = cmty_no;
		this.member_id = member_id;
		this.title = title;
		this.content = content;
		this.attach = attach;
		this.reg_date = reg_date;
	}
	//게시물 세부정보 ポスト内容
	public CommunityPostDto(Long cmty_no, Long member_id, String member_name, String title, String content,
			String attach, String reg_date, String update_date, int hit) {
		this.cmty_no = cmty_no;
		this.member_id = member_id;
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
	public Long getMember_id() {
		return member_id;
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
