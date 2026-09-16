package org.example.atumari.config;

public class FileConfig {
	//파일 업로드와 관련된 설정값을 보관하기 위한 클래스
	
	
	private static String uploadPath;
	//uploadPath: 첨부파일을 저장할 경로를 보관하는 변수
	//static  → FileConfig 객체를 만들지 않아도 하나의 값을 공용으로 보관
	
	    public static void setUploadPath(String path) {
	        uploadPath = path;
	    } //외부에서 전달받은 경로 path를 위에 선언한 uploadPath에 넣어줌

	    public static String getUploadPath() {
	        return uploadPath;
	        //메서드 호출하면 저장된 경로를 반환
	    }
	    
	    //즉 Initializer에서 경로를 받아 저장하고 InquiryService로 값(저장된 경로)을 돌려주는 역할
}
