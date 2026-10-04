package org.example.atumari.inquiry.service;

import org.example.atumari.inquiry.dao.InquiryDao;

public class AdminService {
	
	private final InquiryDao inquiryDao = new InquiryDao();

	// 답변 등록 및 답변 상태, 답변 등록 날짜 변동
	public void saveAnswer (int inquiryNo, String answerContent) {
		int result =inquiryDao.saveInquiryAnswer(inquiryNo,answerContent);
		
		if(result <= 0) {
			throw new RuntimeException("문의 수정에 실패했습니다.");
		}
	}
	
	

}
