package org.example.atumari.inquiry.service;


import java.util.List;

import org.example.atumari.common.fileupload.FileService;
import org.example.atumari.common.fileupload.StoredFile;
import org.example.atumari.inquiry.dao.InquiryDao;
import org.example.atumari.inquiry.dao.InquiryFileDao;
import org.example.atumari.inquiry.dto.InquiryDto;
import org.example.atumari.inquiry.dto.InquiryFileDto;

import jakarta.servlet.http.Part;

public class InquiryService {
	/*
	 Service
	→ 전달받은 데이터를 이용해 비즈니스 로직 수행
	→ 검증
	→ DAO 호출
	 */

	private final InquiryDao inquiryDao = new InquiryDao();	
	private final InquiryFileDao inquiryFileDao = new InquiryFileDao();	
	private final FileService fileService = new FileService();
	
	
    //문의등록 순서대로 실행
	public void createInquiry(InquiryDto inquiry,
            List<Part> files,
            boolean emailNotify) {
		
			
		// 1. 문의 내용, 작성자 검증
		validateInquiry(inquiry);
		
		// 2. 비공개 비밀번호 처리
		//validatePassword(inquiry);
		
		
		// 2. 이메일 처리
		validateNotificationEmail(inquiry,emailNotify);
		
		// 3. 첨부파일 검증
		fileService.validateFiles(files); //이미 전체 파일리스트를 넘겨서 검사 for문필요X
		
		// 4. 문의정보 DB 저장
		int inquiry_no = inquiryDao.insertInquiry(inquiry);
		
		if (inquiry_no <= 0) {
		    throw new RuntimeException("문의 등록에 실패했습니다.");
		}
		
		// 5. 첨부파일 저장
		for(Part file : files) {
			
			StoredFile storedFile = fileService.saveFile(file, "inquiry");
			
			// 6. 파일 DB정보 생성
			InquiryFileDto inquiryFile = 
					new InquiryFileDto(inquiry_no, storedFile.getOriginalFileName(), storedFile.getStoredFileName());
			
			// 7. 파일정보 DB 저장
			int result = inquiryFileDao.insertFile(inquiryFile);
			
			if(result <= 0) {
			    throw new RuntimeException(
	                    "첨부파일 정보 저장에 실패했습니다."
	            );
			}
		}
	}	
	

	//이메일 빈칸 검증
	private void validateNotificationEmail(InquiryDto inquiry, boolean emailNotify) {
	    // 이메일 알림을 받지 않음
	    if (!emailNotify) {
	        inquiry.setEmail(null);
	        return;
	    }

	    // 이메일 알림을 받음
	    String email = inquiry.getEmail();

	    if (email == null || email.trim().isEmpty()) {
	        throw new IllegalArgumentException(
	            "メールアドレスを入力してください。"
	        );
	    }

	    // 앞뒤 공백 제거 후 DTO에 다시 저장
	    inquiry.setEmail(email.trim());
	}
	
	
	
	//문의 내용 검증
	private void validateInquiry(InquiryDto inquiry) {
		  
			//제목 빈칸 검사
			if (inquiry.getTitle() == null ||
				  inquiry.getTitle().trim().isEmpty()) {

			        throw new IllegalArgumentException(
			            "タイトルを入力してください。"
			        		//제목을 입력해주세요.
			        );
			    }
			
			//제목 앞뒤 공백 제거
			inquiry.setTitle(inquiry.getTitle().trim());
			
			//제목 글자수 확인
			    if (inquiry.getTitle().length() > 100) {
			        throw new IllegalArgumentException(
			            "タイトルは100文字以下で入力してください。"
			        		//제목은 100자 이하로 입력해주세요.
			        );
			    }
				
				
			//작성자 확인
			    if (inquiry.getWriter() == null ||
			    	    inquiry.getWriter().trim().isEmpty()) {

			    	    throw new IllegalArgumentException(
			    	        "お名前を入力してください。"
			    	    );
			    	}

			    	inquiry.setWriter(inquiry.getWriter().trim());
			    
			//내용 빈칸 검사
			    if (inquiry.getContent() == null ||
			    		inquiry.getContent().trim().isEmpty()) {

			        throw new IllegalArgumentException(
			            "お問い合わせ内容をご入力ください。"
			        		//문의 내용을 입력해주세요.
			        );
			    }
			    
			    
			//내용 앞뒤 공백 제거
			 inquiry.setContent(inquiry.getContent().trim());    
			//내용 글자수 확인
			    if (inquiry.getContent().length() > 2000) {
			        throw new IllegalArgumentException(
			            "お問い合わせ内容は2000文字以下で入力してください。"
			        		//문의 내용은 2000자 이하로 입력해주세요.
			        );
			    }
			    
			    
	}
	
	
}
