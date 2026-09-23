package org.example.atumari.inquiry.validator;

import org.example.atumari.inquiry.dto.InquiryDto;

public class InquiryValidator {
	
	//이메일 빈칸 검증
		public void validateNotificationEmail(InquiryDto inquiry, boolean emailNotify) {
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
		public void validateInquiry(InquiryDto inquiry) {
			  
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
