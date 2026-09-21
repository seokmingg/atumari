package org.example.atumari.inquiry.dto;

import java.time.LocalDateTime;
import java.util.List;

public class InquiryDto {
	

	    private int inquiry_no;
	    private Long member_id;

	    private String title;
	    private String writer;
	    private String status;
	    
	    private boolean isPublic; //jsp에서 value값이 0,1로 넘어옴
	    private boolean fileIs; //jsp에서 value값이 0,1로 넘어옴
	    
	    private String content;
	    private String email;
	    private String answer_content;
	    
	    private LocalDateTime created_at;
	    private LocalDateTime answered_at;
	    
	 // 상세 조회 시 첨부파일 목록
	    private List<InquiryFileDto> files;//문의 상세페이지에서 사용예정

    
    // writeController 
		public InquiryDto( Long member_id, String title, String writer, boolean isPublic, String content, String email) 
		{
			super();
			this.title = title;
			this.writer = writer;
			this.member_id = member_id;
			this.isPublic = isPublic;
			this.content = content;
			this.email = email;
		}


		public boolean isFileIs() {
			return fileIs;
		}


		public void setFileIs(boolean fileIs) {
			this.fileIs = fileIs;
		}


		public InquiryDto() {
			// TODO Auto-generated constructor stub
		}


		public String getStatus() {
			return status;
		}


		public void setStatus(String status) {
			this.status = status;
		}


		public void setInquiry_no(int inquiry_no) {
			this.inquiry_no = inquiry_no;
		}


		public void setTitle(String title) {
			this.title = title;
		}


		public void setPublic(boolean isPublic) {
			this.isPublic = isPublic;
		}


		public void setContent(String content) {
			this.content = content;
		}


		public void setAnswer_content(String answer_content) {
			this.answer_content = answer_content;
		}


		public void setCreated_at(LocalDateTime created_at) {
			this.created_at = created_at;
		}


		public void setAnswered_at(LocalDateTime answered_at) {
			this.answered_at = answered_at;
		}


		public void setFiles(List<InquiryFileDto> files) {
			this.files = files;
		}


		public void setWriter(String writer) {
			this.writer = writer;
		}

		

//		public void setPassword(String password) {
//			this.password = password;
//		}
//
//
//		public String getPassword() {
//			return password;
//		}


		public void setMember_id(Long member_id) {
			this.member_id = member_id;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public int getInquiry_no() {
			return inquiry_no;
		}


		public Long getMember_id() {
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
