package org.example.atumari.inquiry.service;

import java.util.List;

import org.example.atumari.inquiry.dao.InquiryDao;
import org.example.atumari.inquiry.dto.InquiryDto;

public class InquiryListService {
	
	private final InquiryDao inquiryDao = new InquiryDao();

	public List<InquiryDto> getInquiryList(String searchType,String keyword,int page) {
		
		int pageSize = 6; // 몇개의 글을 가져올건가
		int offset = (page-1)*pageSize; 
		// 앞에서 몇 개를 건너뛸 것인가
		// 1을 빼는 것은 첫번째 페이지에서는 1~6이 출력되야하므로 건너뛸 필요가 없음
		
		return inquiryDao.findInquiryList(searchType,keyword,pageSize,offset);
	}

}
