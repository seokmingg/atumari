package org.example.atumari.community.dto;

public class CommunityCommentDto {
    private Long comment_no;
    private Long member_id;
    private String member_name;
    private String content;
    private String reg_date;
    private String update_date;
    private int is_delete;
    
    //댓글 표시
	public CommunityCommentDto(Long comment_no, Long member_id, String member_name, String content, String reg_date,
			String update_date, int is_delete) {
		this.comment_no = comment_no;
		this.member_id = member_id;
		this.member_name = member_name;
		this.content = content;
		this.reg_date = reg_date;
		this.update_date = update_date;
		this.is_delete = is_delete;
	}
	//댓글 저장 및 수정
	public CommunityCommentDto(Long comment_no, Long member_id, String content, String reg_date) {
		this.comment_no = comment_no;
		this.member_id = member_id;
		this.content = content;
		this.reg_date = reg_date;
	}
	


}
