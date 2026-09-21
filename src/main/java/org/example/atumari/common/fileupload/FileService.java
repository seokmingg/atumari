package org.example.atumari.common.fileupload;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.example.atumari.common.storage.S3Storage;

import jakarta.servlet.http.Part;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

public class FileService {
	
	
	  private static final int MAX_FILE_COUNT = 3; //저장 가능 파일 최대갯수
	  private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; //파일크기 1개 최대 10MB

	  private static final Set<String> ALLOWED_EXTENSIONS =
	            Set.of("jpg", "jpeg", "png", "pdf", "doc", "docx", "xls", "xlsx"); //업로드 가능 파일 확장자
	    
	    

	/**
	 * 첨부파일 1개를 S3에 저장합니다.
	 *
	 * <p>folder에 "notice", "inquiry", "community" 등을 넘기면
	 * {@code folder/UUID_원본파일명} 형태의 S3 객체 키로 저장됩니다.</p>
	 *
	 * <pre>{@code
	 * StoredFile storedFile = fileService.saveFile(file, "notice");
	 * String originalName = storedFile.getOriginalFileName();
	 * String objectKey = storedFile.getStoredFileName(); // notice/UUID_file.pdf
	 * }</pre>
	 *
	 * @param file   업로드할 파일
	 * @param folder S3에서 구분할 상위 폴더명
	 * @return 원본 파일명과 S3 객체 키를 담은 StoredFile
	 */
	public StoredFile saveFile(Part file, String folder) {
		String originalFileName = file.getSubmittedFileName();
		String storedFileName = UUID.randomUUID() + "_" + originalFileName;
		String objectKey = folder + "/" + storedFileName;

		try (InputStream inputStream = file.getInputStream()) {
			S3Storage.upload(
					objectKey,
					inputStream,
					file.getSize(),
					file.getContentType()
			);
			return new StoredFile(originalFileName, objectKey);
		} catch (IOException e) {
			throw new RuntimeException("첨부파일 저장에 실패했습니다.", e);
		}
	}

	/**
	 * DB에 저장된 S3 객체 키로 파일 내용을 불러옵니다.
	 *
	 * <pre>{@code
	 * ResponseBytes<GetObjectResponse> fileData =
	 *         fileService.downloadFile(fileDto.getStoredFileName());
	 * }</pre>
	 *
	 * @param objectKey DB에 저장된 전체 S3 객체 키
	 *                  (예: notice/UUID_file.pdf)
	 * @return 파일 내용과 S3 응답 정보
	 */
	public ResponseBytes<GetObjectResponse> downloadFile(String objectKey) {
		if (objectKey == null || objectKey.isBlank()) {
			throw new IllegalArgumentException("파일 저장 경로가 올바르지 않습니다.");
		}

		return S3Storage.download(objectKey);
	}

	/**
	 * DB에 저장된 S3 객체 키를 사용해 파일을 삭제합니다.
	 * objectKey가 null이거나 빈 문자열이면 삭제를 수행하지 않습니다.
	 *
	 * <pre>{@code
	 * fileService.deleteFile(fileDto.getStoredFileName());
	 * }</pre>
	 *
	 * @param objectKey DB에 저장된 전체 S3 객체 키
	 *                  (예: notice/UUID_file.pdf)
	 */
	public void deleteFile(String objectKey) {
		if (objectKey == null || objectKey.isBlank()) {
			return;
		}

		S3Storage.delete(objectKey);
	}



	/**
	 * S3에 업로드하기 전에 파일 개수, 크기, 확장자를 검증합니다.
	 * 현재 파일은 최대 3개, 각 파일은 최대 10MB까지 허용합니다.
	 *
	 * <pre>{@code
	 * fileService.validateFiles(files);
	 * }</pre>
	 *
	 * @param files 검증할 첨부파일 목록
	 * @throws IllegalArgumentException 파일이 허용 조건을 만족하지 않을 때
	 */
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
