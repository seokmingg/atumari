package org.example.atumari.community.dto;

public class CommunityCommentDto {
    private Long comment_no;
    private String member_email;
    private String member_name;
    private String content;
    private String reg_date;
    private String update_date;
    private int is_delete;
    
    //댓글 표시 コメント表示
	public CommunityCommentDto(Long comment_no, String member_email, String member_name, String content, String reg_date,
			String update_date, int is_delete) {
		this.comment_no = comment_no;
		this.member_email = member_email;
		this.member_name = member_name;
		this.content = content;
		this.reg_date = reg_date;
		this.update_date = update_date;
		this.is_delete = is_delete;
	}
	//댓글 저장 및 수정　コメント登録・修正
	public CommunityCommentDto(Long comment_no, String member_email, String content, String reg_date) {
		this.comment_no = comment_no;
		this.member_email = member_email;
		this.content = content;
		this.reg_date = reg_date;
	}
	public Long getComment_no() {
		return comment_no;
	}
	public String getMember_email() {
		return member_email;
	}
	public String getMember_name() {
		return member_name;
	}
	public String getContent() {
		return content;
	}
	public String getReg_date() {
		return reg_date;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public int getIs_delete() {
		return is_delete;
	}
	
	

}
