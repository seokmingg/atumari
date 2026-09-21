package org.example.atumari.common.fileupload;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.example.atumari.config.FileConfig;
import org.example.atumari.inquiry.dto.InquiryFileDto;

import jakarta.servlet.http.Part;

public class FileService {
	
	
	  private static final int MAX_FILE_COUNT = 3; //저장 가능 파일 최대갯수
	  private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; //파일크기 1개 최대 10MB

	  private static final Set<String> ALLOWED_EXTENSIONS =
	            Set.of("jpg", "jpeg", "png", "pdf", "doc", "docx", "xls", "xlsx"); //업로드 가능 파일 확장자
	    
	    

	//첨부파일 저장(한번에 하나만 저장)
	public StoredFile saveFile(Part file, String folder) {
		
		String uploadPath = FileConfig.getUploadPath(); 
		//상위폴더 경로 가져오기
		//C:/atumari_uploads
		

		Path uploadDir = Paths.get(uploadPath,folder); 
		//하위폴더 경로 붙이기
		//C:/atumari_uploads/inquiry/
		
		
			//경로에 폴더가 없으면 자동 생성
			try {
				Files.createDirectories(uploadDir);
				
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
			            return new StoredFile(originalFileName,storedFileName);
				 
			} catch (IOException e) {
					throw new RuntimeException("첨부파일 저장에 실패했습니다.",e);
			}
	}

	

	//첨부파일 검증
	public void validateFiles(List<Part> files) {
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


}
