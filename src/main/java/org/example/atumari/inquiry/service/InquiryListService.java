package org.example.atumari.inquiry.service;

import java.util.List;

import org.example.atumari.inquiry.dao.InquiryDao;
import org.example.atumari.inquiry.dto.InquiryDto;

public class InquiryListService {
	
	private final InquiryDao inquiryDao = new InquiryDao();
	
	   private static final int PAGE_SIZE = 6;

	// 문의글 조회
	public List<InquiryDto> getInquiryList(String searchType,String keyword,int page, String status) {
		
		int offset = (page-1)*PAGE_SIZE; 
		// 앞에서 몇 개를 건너뛸 것인가
		// 1을 빼는 것은 첫번째 페이지에서는 1~6이 출력되야하므로 건너뛸 필요가 없음
		
		return inquiryDao.findInquiryList(searchType,keyword,PAGE_SIZE,offset,status);
	}
	
	// 검색 조건에 맞는 전체 문의글 개수(문의페이지)
	public int getTotalCount(String searchType,String keyword, String status) {
		
		return inquiryDao.getTotalInquiryCount(searchType, keyword, status);
	}
	
	// 전체 페이지 개수
	public int getTotalPages(int totalCount) {
		
		int totalPages = (int)Math.ceil((double)totalCount/PAGE_SIZE);
		
		return totalPages;
	}
	
	// 통일된 페이지 사이즈를 공유할 수 있음
	public int getPageSize() {
	    return PAGE_SIZE;
	}

}
