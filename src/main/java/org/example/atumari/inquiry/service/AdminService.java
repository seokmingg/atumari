package org.example.atumari.inquiry.service;

import org.example.atumari.inquiry.dao.InquiryDao;
import org.example.atumari.inquiry.validator.InquiryValidator;

public class AdminService {
	
	private final InquiryDao inquiryDao = new InquiryDao();
	private final InquiryValidator validator = new InquiryValidator();

	// 답변 등록 및 답변 상태, 답변 등록 날짜 변동
	public void saveAnswer (int inquiryNo, String answerContent) {
		
		// 답변 빈칸 검사
		validator.validateAnswer(answerContent);
		
		int result =inquiryDao.saveInquiryAnswer(inquiryNo,answerContent);
		
		if(result <= 0) {
			throw new RuntimeException("관리자 문의 답변 저장에 실패했습니다.");
		}
	}
	
	// 답변 삭제
	public void deleteAnswer(int inquiryNo) {
		int result = inquiryDao.deleteInquiryAnswer(inquiryNo);
		
		if(result <= 0) {
			throw new RuntimeException("관리자 문의 답변 삭제에 실패했습니다.");
		}
	}
	
	

}
