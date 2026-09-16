package org.example.atumari.config;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


//Spring에게: 이 클래스는 네가 관리해야 하는 클래스
//애플리케이션이 시작될 때 Spring이 FileConfigInitializer를 발견하고 객체를 직접 만들어줌
//즉, 우리가 객체를 만들지 않아도 됨

@Component 
public class FileConfigInitializer {
	//파일 설정값을 초기화하는 클래스

    public FileConfigInitializer(Environment environment) {
    	/*일반 메서드 아님 생성자!
    	Spring이 자신이 관리하고 있는 Environment 객체를 넣어줌.
		이게 Spring에서 자주 말하는 의존성 주입(Dependency Injection, DI)의 한 예
    	Environment는 쉽게 말하면 Spring이 가지고 있는 설정값을 조회할 수 있게 해주는 객체
		*/
    	
        String uploadPath =
                environment.getProperty("file-storage.upload-path");
        //getProperty(): 이 이름의 설정값을 찾아줘
        //yml파일에서 해당 주소값을 찾아 uploadPath에 저장
        

        FileConfig.setUploadPath(uploadPath);
    }
}
