package org.example.atumari.community.dto;

import java.sql.Timestamp;

public class CommunityCommentDto {
	private Long cmty_no;
    private Long comment_no;
    private String member_email;
    private String member_name;
    private Long parent_no;
    private String content;
    private Timestamp reg_date;
    private Timestamp update_date;
    private int is_delete;
    
    //댓글 표시 コメント表示
	public CommunityCommentDto(Long cmty_no, 
								Long comment_no, 
								Long parent_no, 
								String member_email, 
								String member_name, 
								String content, 
								Timestamp reg_date,
								Timestamp update_date) {
		this.cmty_no = cmty_no;
		this.comment_no = comment_no;
		this.parent_no = parent_no;
		this.member_email = member_email;
		this.member_name = member_name;
		this.content = content;
		this.reg_date = reg_date;
		this.update_date = update_date;
	}
	//댓글 저장 및 수정　コメント登録・修正
	public CommunityCommentDto(Long cmty_no, 
								Long parent_no, 
								String member_email, 
								String content) {
		this.cmty_no = cmty_no;
		this.parent_no = parent_no;
		this.member_email = member_email;
		this.content = content;
	}
	public Long getCmty_no() {
		return cmty_no;
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
	public Long getParent_no() {
		return parent_no;
	}
	public String getContent() {
		return content;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public Timestamp getUpdate_date() {
		return update_date;
	}
	public int getIs_delete() {
		return is_delete;
	}
	
	

}
