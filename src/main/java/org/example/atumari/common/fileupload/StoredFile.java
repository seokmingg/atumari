package org.example.atumari.common.fileupload;

public class StoredFile {
	//파일저장에 사용할 이름 보관했다가 Service에 돌려주는 역할

	//사용자가 업로드한 원래 파일명
	private String originalFileName;
	
	//서버에 실제로 저장된 파일명
	private String storedFileName;
	
	//생성자
	public StoredFile(String originalFileName,String storedFileName) {
		 this.originalFileName = originalFileName;
	     this.storedFileName = storedFileName;
	}
	
	public String getOriginalFileName() {
		return originalFileName;
	}
	
	public String getStoredFileName() {
		return storedFileName;
	}
}
