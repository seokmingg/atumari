package org.example.atumari.inquiry.dto;

import java.time.LocalDateTime;
import java.util.List;

public class InquiryDto {
	

	    private int inquiry_no;
	    private int member_id;
	 

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
}
