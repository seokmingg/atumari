package org.example.atumari.festival.dto;

import java.time.LocalDateTime;

public class ReviewDto {
	
	private Long review_no;
    private Integer festival_no;
    private Long member_id;
    private String name;
    private String content;
    private LocalDateTime created_date;
    private LocalDateTime updated_date;
    
    public ReviewDto() {
    }
    
    
    public ReviewDto(Long review_no, Integer festival_no, Long member_id, String name, String content,
			LocalDateTime created_date, LocalDateTime updated_date) {
		this.review_no = review_no;
		this.festival_no = festival_no;
		this.member_id = member_id;
		this.name = name;
		this.content = content;
		this.created_date = created_date;
		this.updated_date = updated_date;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getReview_no() {
		return review_no;
	}
	public void setReview_no(Long review_no) {
		this.review_no = review_no;
	}
	public Integer getFestival_no() {
		return festival_no;
	}
	public void setFestival_no(Integer festival_no) {
		this.festival_no = festival_no;
	}
	public Long getMember_id() {
		return member_id;
	}
	public void setMember_id(Long member_id) {
		this.member_id = member_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getCreated_date() {
		return created_date;
	}
	public void setCreated_date(LocalDateTime created_date) {
		this.created_date = created_date;
	}
	public LocalDateTime getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(LocalDateTime updated_date) {
		this.updated_date = updated_date;
	}

    
}
