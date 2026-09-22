package org.example.atumari.inquiry.dto;

public class InquiryFileDto {

		private int file_no;
	    private int inquiry_no;

	    private String original_file_name;
	    private String stored_file_name;
	    
	    //문의 글 등록시 파일저장
		public InquiryFileDto(int inquiry_no, String original_file_name, String stored_file_name) {
			super();
			this.inquiry_no = inquiry_no;
			this.original_file_name = original_file_name;
			this.stored_file_name = stored_file_name;
		}
	    
	    
		public InquiryFileDto() {
			// TODO Auto-generated constructor stub
		}


		public void setFile_no(int file_no) {
			this.file_no = file_no;
		}


		public void setInquiry_no(int inquiry_no) {
			this.inquiry_no = inquiry_no;
		}


		public void setOriginal_file_name(String original_file_name) {
			this.original_file_name = original_file_name;
		}


		public void setStored_file_name(String stored_file_name) {
			this.stored_file_name = stored_file_name;
		}


		public int getFile_no() {
			return file_no;
		}
		public int getInquiry_no() {
			return inquiry_no;
		}
		public String getOriginal_file_name() {
			return original_file_name;
		}
		public String getStored_file_name() {
			return stored_file_name;
		}
		
	
	    
}
