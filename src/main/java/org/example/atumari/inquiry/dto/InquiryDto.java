package org.example.atumari.inquiry.dto;

import java.time.LocalDateTime;
import java.util.List;

public class InquiryDto {
	

	    private int inquiry_no;
	    private Integer member_id;
	 

	    private String title;
	    private String writer;
	    
	    private boolean isPublic; //jsp에서 value값이 0,1로 넘어옴
	    
	    private String content;
	    private String email;
	    private String answer_content;
	    
	    private LocalDateTime created_at;
	    private LocalDateTime answered_at;
	    
	 // 상세 조회 시 첨부파일 목록
	    private List<InquiryFileDto> files;//문의 상세페이지에서 사용예정

    
    // writeController 
		public InquiryDto(Integer member_id, String title, String writer, boolean isPublic, String content, String email) 
		{
			super();
			this.member_id = member_id;
			this.title = title;
			this.writer = writer;
			this.isPublic = isPublic;
			this.content = content;
			this.email = email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public int getInquiry_no() {
			return inquiry_no;
		}


		public Integer getMember_id() {
			return member_id;
		}


		public String getTitle() {
			return title;
		}


		public String getWriter() {
			return writer;
		}


		public boolean isPublic() {
			return isPublic;
		}


		public String getContent() {
			return content;
		}


		public String getEmail() {
			return email;
		}


		public String getAnswer_content() {
			return answer_content;
		}


		public LocalDateTime getCreated_at() {
			return created_at;
		}


		public LocalDateTime getAnswered_at() {
			return answered_at;
		}


		public List<InquiryFileDto> getFiles() {
			return files;
		}
		
	    
	    
}
