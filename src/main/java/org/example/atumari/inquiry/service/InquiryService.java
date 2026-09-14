package org.example.atumari.inquiry.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.example.atumari.config.FileConfig;
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
	
	
	//파일 첨부 3개까지 가능
	private static final int MAX_FILE_COUNT = 3;
	
	//파일 사이즈 각 10MB까지 가능
	private static final long MAX_FILE_SIZE =
	        10L * 1024 * 1024;
	
	// 허용할 첨부파일 확장자
    private static final List<String> ALLOWED_EXTENSIONS =
            List.of("jpg", "jpeg", "png", "pdf",
                    "doc", "docx", "xls", "xlsx");
	
    //문의등록 순서대로 실행
	public void createInquiry(InquiryDto inquiry,
            List<Part> files,
            boolean emailNotify) {

	
		
	// 1. 문의 내용 검증
	validateInquiry(inquiry);
	
	// 2. 이메일 처리
	validateNotificationEmail(inquiry,emailNotify);
	
	// 3. 첨부파일 검증
	validateFiles(files);
	
	// 경로 테스트용
    //testUploadPath();
	
	// 4. 문의 저장
	int inquiry_no = inquiryDao.insertInquiry(inquiry);
	
	if (inquiry_no <= 0) {
	    throw new RuntimeException("문의 등록에 실패했습니다.");
	}
	
	// 5. 첨부파일 저장
	saveFiles(files, inquiry_no);
	}
	
	/*private void testUploadPath() {

		  String uploadPath = FileConfig.getUploadPath();

		    Path uploadDir = Paths.get(uploadPath);

		    try {
		        Files.createDirectories(uploadDir);

		        System.out.println(
		            "첨부파일 저장 경로: "
		            + uploadDir.toAbsolutePath()
		        );

		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	}
*/	
	
	//첨부파일 저장
	private void saveFiles(List<Part> files, int inquiryNo) {
		
		String uploadPath = FileConfig.getUploadPath(); 
		//상위폴더 경로 가져오기
		//C:/atumari_uploads
		

		System.out.println("uploadPath = " + uploadPath);
		Path uploadDir = Paths.get(uploadPath,"inquiry"); 
		//하위폴더 경로 붙이기
		//C:/atumari_uploads/inquiry/
		
		try {
			//경로에 폴더가 없으면 자동 생성
			Files.createDirectories(uploadDir);
			
			for(Part file : files) {
				String originalFileName = file.getSubmittedFileName(); 
				//사용자가 첨부한 원본 파일명
				//photo.jpg
				
				String storedFileName = UUID.randomUUID() + "_" + originalFileName; 
				//저장용 파일명(식별을 명확히 하기위해)
				//550e8400-e29b-41d4-a716-446655440000_photo.jpg
				
				
			     //파일의 최종 저장 위치
			     Path targetPath = uploadDir.resolve(storedFileName);
			     //저장할 폴더 경로(uploadDir)와 파일이름(storedFileName)을 합쳐서 최종저장위치를 만듦
			     //C:/atumari_uploads/inquiry/550e8400-e29b-41d4-a716-446655440000_photo.jpg
			     
			     // 1. 실제 파일 저장
		            try (InputStream inputStream = file.getInputStream()) {

		                Files.copy(
		                    inputStream, //업로드된 파일의 실제 내용(데이터)을 읽어오는 통로
		                    targetPath //어디에 저장할지, 최종 저장 위치
		                );
		                
		            }
		         // 2. 파일정보 DTO 생성
		            InquiryFileDto fileDto = new InquiryFileDto(inquiryNo, originalFileName, storedFileName);
		         
		         // 3. 파일정보 DB저장
 		            int result = inquiryFileDao.insertFile(fileDto);
 		            
		         // 4. DB저장 실패 확인 
		            if(result<1) {
		            	throw new RuntimeException(
		            	        "첨부파일 정보 저장에 실패했습니다."
		            	    );
		            }
			}
		} catch (IOException e) {
			throw new RuntimeException(
		            "첨부파일 저장에 실패했습니다.",
		            e
		        );
		}
		
	}


	//첨부파일 검증
	private void validateFiles(List<Part> files) {
		//파일 개수 
		if(files.size() > MAX_FILE_COUNT) {
			throw new IllegalArgumentException("添付ファイルは3個まで登録できます。");
		}
		
		for(Part file : files) {
			
			//파일명
			String fileName = file.getSubmittedFileName();
			
			if(fileName == null || fileName.trim().isEmpty()) {
				throw new IllegalArgumentException("ファイル名が正しくありません。");
			}
			
					
				if(file.getSize() > MAX_FILE_SIZE) {
					throw new IllegalArgumentException("1ファイルあたりのサイズは10MB以下にしてください。");
				}
			
			//파일 형식 확인(파일이름에서 확장자를 뽑아서 허용된 형식인지 확인)
			int dotIndex = fileName.lastIndexOf("."); //lastIndexOf(".")는 문자열 안에서 가장 마지막에 나오는 .의 위치를 찾아줌
			
			if(dotIndex == -1) {//-1은 .을 찾지 못했다는 뜻 > 즉, 확장자를 판단할 수 없음
				throw new IllegalArgumentException("許可されていないファイル形式です。");
			}
			
			//확장자 추출
			String extension = fileName.substring(dotIndex + 1).toLowerCase(); //.다음부터 문자열을 반환 + 소문자변환
			
			//허용 확장자인지 확인
			if(!ALLOWED_EXTENSIONS.contains(extension)) {
				throw new IllegalArgumentException("許可されていないファイル形式です。");
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
	
	/*member테이블에 저장된 email주소를 사용
	 * private void setNotificationEmail(
	        InquiryDto inquiry,
	        boolean emailNotify) {

	    if (!emailNotify) {
	        inquiry.setEmail(null);
	        return;
	    }

	    String memberEmail =
	        memberDao.findEmailByMemberId(
	            inquiry.getMember_id()
	        );

	    if (memberEmail == null) {
	        throw new IllegalArgumentException(
	            "登録されたメールアドレスがありません。"
	        );
	    }

	    inquiry.setEmail(memberEmail);
	}
	*/
	
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
			
			//제목 글자수 확인
			    if (inquiry.getTitle().length() > 100) {
			        throw new IllegalArgumentException(
			            "タイトルは100文字以下で入力してください。"
			        		//제목은 100자 이하로 입력해주세요.
			        );
			    }
			    
			//내용 빈칸 검사
			    if (inquiry.getContent() == null ||
			    		inquiry.getContent().trim().isEmpty()) {

			        throw new IllegalArgumentException(
			            "お問い合わせ内容をご入力ください。"
			        		//문의 내용을 입력해주세요.
			        );
			    }
			    
			//내용 글자수 확인
			    if (inquiry.getContent().length() > 2000) {
			        throw new IllegalArgumentException(
			            "お問い合わせ内容は2000文字以下で入力してください。"
			        		//문의 내용은 2000자 이하로 입력해주세요.
			        );
			    }
	}
	
	
}
