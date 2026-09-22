package org.example.atumari.inquiry.service;

import java.util.List;

import org.example.atumari.inquiry.dao.InquiryDao;
import org.example.atumari.inquiry.dao.InquiryFileDao;
import org.example.atumari.inquiry.dto.InquiryDto;
import org.example.atumari.inquiry.dto.InquiryFileDto;

public class InquiryViewService {

	private final InquiryDao inquiryDao = new InquiryDao();
	private final InquiryFileDao inquiryFileDao = new InquiryFileDao();
	
	// 문의글 상세조회
	public InquiryDto getInquiryView(int inquiryNo) {
		
		return inquiryDao.getInquiry(inquiryNo);
	}
	
	// 문의글 첨부파일 상세조회
	public List<InquiryFileDto> getInquiryFiles(int inquiryNo){
		
		return inquiryFileDao.getInquiryFiles(inquiryNo);
	}

}
