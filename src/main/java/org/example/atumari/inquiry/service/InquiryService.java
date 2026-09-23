package org.example.atumari.inquiry.service;


import java.util.List;

import org.example.atumari.common.fileupload.FileService;
import org.example.atumari.common.fileupload.StoredFile;
import org.example.atumari.inquiry.dao.InquiryDao;
import org.example.atumari.inquiry.dao.InquiryFileDao;
import org.example.atumari.inquiry.dto.InquiryDto;
import org.example.atumari.inquiry.dto.InquiryFileDto;
import org.example.atumari.inquiry.validator.InquiryValidator;

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
	private final InquiryValidator validator = new InquiryValidator();
	
	
    // 문의등록
	public void createInquiry(InquiryDto inquiry,
            List<Part> files,
            boolean emailNotify) {
		
			
		// 1. 문의 내용, 작성자 검증
		validator.validateInquiry(inquiry);
		
		// 2. 이메일 처리
		validator.validateNotificationEmail(inquiry,emailNotify);
		
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
	
	// 문의 수정
	public void updateInquiry(InquiryDto inquiry, List<Integer> deleteFileNos, List<Part> newFiles, boolean emailNotify) {
		
		// 1. 문의 내용, 작성자 검증
		validator.validateInquiry(inquiry);
				
		// 2. 이메일 처리
		validator.validateNotificationEmail(inquiry,emailNotify);
		
		// 3. 새로 첨부한 파일 검증
		fileService.validateFiles(newFiles); // 기존 파일은 이미 검증해 저장되어 있음 현재는 새로운 저장할 파일에 대한 검증 필요
		
		// 4. 문의정보 DB 저장
		int inquiry_no = inquiryDao.updateInquiry(inquiry);
		
		if (inquiry_no <= 0) {
		    throw new RuntimeException("문의 수정에 실패했습니다.");
		}
	}
	

	
	
	
}
