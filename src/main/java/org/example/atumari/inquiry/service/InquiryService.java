package org.example.atumari.inquiry.service;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.example.atumari.common.database.DBConnection;
import org.example.atumari.common.fileupload.FileService;
import org.example.atumari.common.fileupload.StoredFile;
import org.example.atumari.inquiry.dao.InquiryDao;
import org.example.atumari.inquiry.dao.InquiryFileDao;
import org.example.atumari.inquiry.dto.InquiryDto;
import org.example.atumari.inquiry.dto.InquiryFileDto;
import org.example.atumari.inquiry.validator.InquiryValidator;

import jakarta.servlet.http.Part;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

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
	
	
	// 문의 등록 - 트랜잭션 적용
	public void createInquiry(InquiryDto inquiry, List<Part> files) {

	    // 1. 문의 내용 검증
	    validator.validateInquiry(inquiry);

	    // 2. 첨부파일 검증
	    fileService.validateFiles(files);

	    // 3. S3 업로드 파일 기록
	    List<String> uploadedFiles = new ArrayList<>();
	    // DB 작업에 실패했을 때 이 목록을 이용해 파일을 삭제

	    boolean commitAttempted = false;
	    //커밋 시도 전 오류 발생, 커밋을 시도한 이후 오류 발생을 구분하기 위한 변수

	    // 4. Connection 생성
	    try (Connection con = DBConnection.getConnection()) {

	        con.setAutoCommit(false);

	        try {
	            // 5. 문의글 DB 등록
	            int inquiryNo = inquiryDao.insertInquiry(con, inquiry);

	            // 6. 첨부파일 S3 업로드 및 DB 등록
	            for (Part file : files) {

	                StoredFile storedFile =
	                        fileService.saveFile(file, "inquiry");

	                // S3 업로드 성공한 파일 기록
	                uploadedFiles.add(storedFile.getStoredFileName());

	                InquiryFileDto fileDto = new InquiryFileDto(
	                        inquiryNo,
	                        storedFile.getOriginalFileName(),
	                        storedFile.getStoredFileName()
	                );

	                int result = inquiryFileDao.insertFile(con, fileDto);

	                if (result != 1) {
	                    throw new SQLException("첨부파일 DB 등록 실패");
	                }
	            }

	            // 7. 모든 DB 작업 성공
	            commitAttempted = true;
	            con.commit();

	        } catch (Exception e) {

	            // 8. DB 롤백
	            try {
	                con.rollback();
	            } catch (SQLException rollbackError) {
	                e.addSuppressed(rollbackError);
	            }

	            // 9. 커밋 시도 전 실패한 경우 S3 파일 삭제
	            if (!commitAttempted) {

	                for (String objectKey : uploadedFiles) {
	                    try {
	                        fileService.deleteFile(objectKey);
	                    } catch (Exception cleanupError) {
	                        e.addSuppressed(cleanupError);
	                    }
	                }
	            }

	            throw e;
	        }

	    } catch (Exception e) {
	        throw new RuntimeException("문의 등록에 실패했습니다.", e);
	    }
	}
	
	
	// 문의 수정
	public void updateInquiry(InquiryDto inquiry, List<Integer> deleteFileNos, List<Part> newFiles) {
		
		// 0. 문의 존재 여부 + 작성자 본인 확인
	    InquiryDto existingInquiry =
	        inquiryDao.getInquiryByInquiryNoAndMemberId(
	            inquiry.getInquiry_no(),
	            inquiry.getMember_id()
	        );

	    if (existingInquiry == null) {
	        throw new SecurityException(
	            "お問い合わせが存在しないか、修正する権限がありません。"
	        );
	    }
		
		// 1. 문의 내용, 작성자 검증
		validator.validateInquiry(inquiry);
				
		// 2. 새로 첨부한 파일 검증
		fileService.validateFiles(newFiles); // 기존 파일은 이미 검증해 저장되어 있음 현재는 새로운 저장할 파일에 대한 검증 필요
		
		// 3. 현재 문의에 저장되어 있는 기존 파일 조회
	    List<InquiryFileDto> existingFiles =
	            inquiryFileDao.getInquiryFiles(inquiry.getInquiry_no());

	    // 4. 삭제 요청 중복 검증
	    Set<Integer> uniqueDeleteFileNos =
	            new HashSet<>(deleteFileNos);

	    if (uniqueDeleteFileNos.size() != deleteFileNos.size()) {
	        throw new IllegalArgumentException(
	                "削除するファイル番号が重複しています。"
	        );
	    }
	    
	    // 4. 삭제 대상 파일 확인
	    List<InquiryFileDto> deleteFiles = new ArrayList<>(); // 검증완료된 삭제할 파일 저장할 리스트
	    
	    for(Integer deleteFileNo : deleteFileNos) {
	    	
	    	// 기존저장된 파일과 삭제체크를 한 파일 번호 일치를 확인하기 위한 변수
	    	boolean found = false;
	    	
	    	for(InquiryFileDto existingFile: existingFiles) {
		    
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
	    
	    // 7. 신규 S3 업로드 파일 관리
	    List<StoredFile> uploadedFiles = new ArrayList<>();

	    boolean commitAttempted = false; // db 커밋을 시도했는지, 오류 발생 시 신규 S3파일을 보상 삭제해도 되는지 판단
	    boolean commitSucceeded = false; // db 커밋이 정상적으로 완료됐는지, 기존 S3 파일을 삭제해도 되는지 판단

	    try {
	        // 8. 신규 첨부파일을 S3에 먼저 업로드
	        for (Part file : newFiles) {

	            StoredFile storedFile =
	                    fileService.saveFile(file, "inquiry");

	            uploadedFiles.add(storedFile);
	        }

	        // 9. DB 트랜잭션 시작
	        try (Connection con = DBConnection.getConnection()) {

	            con.setAutoCommit(false);

	            try {
	                // 10. 문의글 DB 수정
	                int result = inquiryDao.updateInquiry(con, inquiry);

	                if (result != 1) {
	                    throw new SQLException("문의글 수정에 실패했습니다.");
	                }

	                // 11. 신규 첨부파일 정보 DB 등록
	                for (StoredFile storedFile : uploadedFiles) {

	                    InquiryFileDto fileDto = new InquiryFileDto(
	                            inquiry.getInquiry_no(),
	                            storedFile.getOriginalFileName(),
	                            storedFile.getStoredFileName()
	                    );

	                    int insertResult =
	                            inquiryFileDao.insertFile(con, fileDto);

	                    if (insertResult != 1) {
	                        throw new SQLException(
	                                "신규 첨부파일 정보 등록에 실패했습니다."
	                        );
	                    }
	                }

	                // 12. 기존 첨부파일 정보 DB 삭제
	                for (InquiryFileDto deleteFile : deleteFiles) {

	                    int deleteResult =
	                            inquiryFileDao.deleteFile(
	                                    con,
	                                    deleteFile.getFile_no()
	                            );

	                    if (deleteResult != 1) {
	                        throw new SQLException(
	                                "기존 첨부파일 정보 삭제에 실패했습니다."
	                        );
	                    }
	                }

	                // 13. 모든 DB 변경 확정
	                commitAttempted = true;
	                con.commit();
	                commitSucceeded = true;

	            } catch (Exception e) {

	                try {
	                    con.rollback();
	                } catch (SQLException rollbackError) {
	                    e.addSuppressed(rollbackError);
	                }

	                throw e;
	            }
	        }

	    } catch (Exception e) {

	        // 14. 커밋 시도 전 실패한 경우 신규 S3 파일 정리
	        if (!commitAttempted) {
	        	// 신규 업로드한 S3 파일 보상 삭제
	            for (StoredFile storedFile : uploadedFiles) {

	                try {
	                    fileService.deleteFile(
	                            storedFile.getStoredFileName()
	                    );
	                } catch (Exception cleanupError) {
	                    e.addSuppressed(cleanupError);
	                }
	            }
	        }

	        throw new RuntimeException("문의 수정 실패", e);
	    }

	    // 15. DB 커밋 성공 후 기존 S3 파일 삭제
	    if (commitSucceeded) {

	        for (InquiryFileDto deleteFile : deleteFiles) {

	            try {
	                fileService.deleteFile(
	                        deleteFile.getStored_file_name()
	                );

	            } catch (Exception e) {
	                // DB 커밋이 끝났으므로 롤백할 수 없음
	                // 실제 운영에서는 삭제 재시도 작업을 기록하는 것이 좋음
	                System.err.println(
	                        "S3 파일 삭제 실패: "
	                        + deleteFile.getStored_file_name()
	                );
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	
	// 문의 삭제
	public void deleteInquiry(int inquiryNo, Long memberId, boolean isAdmin) {

		// 0. 삭제할 문의 조회
		InquiryDto inquiry = inquiryDao.getInquiry(inquiryNo);
		
		if(inquiry == null) {
			throw new IllegalArgumentException("お問い合わせが存在しません。");
		}
		
		// 1. 작성자 본인 또는 관리자만 삭제 가능 
		boolean isOwner = 
				memberId != null && 
				memberId.equals(inquiry.getMember_id());
		
		if(!isOwner && !isAdmin) {
			throw new IllegalArgumentException("削除する権限がありません。");
		}
		

	    // 2. 첨부파일 조회
	    List<InquiryFileDto> files =
	        inquiryFileDao.getInquiryFiles(inquiryNo);
	 

	    // 3. 문의글 삭제
	    int result = inquiryDao.deleteInquiry(inquiryNo);

	    if (result <= 0) {
	        throw new RuntimeException(
	            "문의 삭제에 실패했습니다."
	        );
	    }
	    
	    // 4. DB삭제 성공후 S3 실제 파일 삭제
	    for (InquiryFileDto file : files) {

	        fileService.deleteFile(
	            file.getStored_file_name()
	        );
	    }


	    // inquiry_file은 ON DELETE CASCADE로 자동 삭제
	}

	// 파일다운로드시 DB에서 파일조회
	public InquiryFileDto getInquiryFile(int fileNo) {
		return inquiryFileDao.findById(fileNo);
	}
	
	// 파일 다운로드
	public ResponseBytes<GetObjectResponse> downloadInquiryFile(InquiryFileDto file) {
        if (file == null) {
            throw new IllegalArgumentException("첨부파일을 찾을 수 없습니다.");
        }
        return fileService.downloadFile(file.getStored_file_name());
    }
	
	
}
