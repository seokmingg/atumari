package org.example.atumari.inquiry.service;


import java.util.ArrayList;
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
	
	
    // 문의 등록
	public void createInquiry(InquiryDto inquiry,
            List<Part> files,
            boolean emailNotify) {
		
			
		// 1. 문의 내용, 작성자 검증
		validator.validateInquiry(inquiry);
		
		// 2. 이메일 알림 서비스 여부
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
				
		// 2. 이메일 알림 서비스 여부
		validator.validateNotificationEmail(inquiry,emailNotify);
		
		// 3. 새로 첨부한 파일 검증
		fileService.validateFiles(newFiles); // 기존 파일은 이미 검증해 저장되어 있음 현재는 새로운 저장할 파일에 대한 검증 필요
		
		// 4. 현재 문의에 저장되어 있는 기존 파일 조회
	    List<InquiryFileDto> existingFiles =
	            inquiryFileDao.getInquiryFiles(inquiry.getInquiry_no());

	    // 5. 삭제 요청한 파일 검증
	    List<InquiryFileDto> deleteFiles = new ArrayList<>(); // 검증완료된 삭제할 파일저장
	    
	    for(InquiryFileDto existingFile: existingFiles) {
	    	
	    	// 해당 파일을 찾았는지 확인하기 위한 변수
	    	boolean found = false;
	    	
	    	for(Integer deleteFileNo : deleteFileNos) {
		    
	    		if(existingFile.getFile_no() == deleteFileNo) {
		    		found = true;
		    		
		    		// 실제 삭제할 파일정보 저장
		    		deleteFiles.add(existingFile);
		    		
		    		break;// ← 안쪽 for문만 종료, 가장 가까운 반복문 종료
		    	}
	    	}
	    	// 기존 파일목록에서 찾지 못함
	    	if(!found) {
	    		throw new IllegalArgumentException("削除するファイル情報が正しくありません。");
	    	}
	    }
	    
	    // 6. 수정 후 최종 파일 개수 검증
	    int fileCount = existingFiles.size() - deleteFiles.size() + newFiles.size();
		
	    fileService.validateFileCount(fileCount);
	    
		// 7. 수정된 문의정보 DB 저장
		int result = inquiryDao.updateInquiry(inquiry);
		
		
		if (result <= 0) {
		    throw new RuntimeException("문의 수정에 실패했습니다.");
		}
		
		// 8. 삭제 요청된 기존 첨부파일 삭제
		// → deleteFileNos에 포함된 파일만 DB + S3에서 삭제
		for(InquiryFileDto deleteFile : deleteFiles) {
			
			// S3에 저장된 실제 파일 삭제
			fileService.deleteFile(deleteFile.getStored_file_name());
			
			// DB의 파일정보 삭제
			int deleteResult = inquiryFileDao.deleteFile(deleteFile.getFile_no());
			
			if(deleteResult <= 0) {
				throw new RuntimeException("첨부파일 정보 삭제에 실패했습니다.");
			}
		}
		
		// 9. 새로 첨부한 파일 저장
		// → S3 저장 + inquiry_file INSERT
		for(Part newFile : newFiles) {
			
			// S3에 새로 첨부한 파일 저장
			StoredFile storedFile = fileService.saveFile(newFile, "inquiry");
			
			// DB 저장용 DTO 생성
			InquiryFileDto inquiryFile = new InquiryFileDto(
											inquiry.getInquiry_no(),
											storedFile.getOriginalFileName(),
											storedFile.getStoredFileName());
			
			// DB에 파일 정보 저장
			int saveResult = inquiryFileDao.insertFile(inquiryFile);
			
			if(saveResult <= 0) {
				throw new RuntimeException("새로 첨부한 파일 정보 저장에 실패했습니다.");
			}
		}
	}
	

	
	
	
}
