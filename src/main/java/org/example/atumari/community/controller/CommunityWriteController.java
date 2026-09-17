package org.example.atumari.community.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.example.atumari.community.dto.CommunityPostDto;
import org.example.atumari.config.FileConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/community/write")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,        // 1MB
	    maxFileSize = 1024 * 1024 * 10,         // 10MB
	    maxRequestSize = 1024 * 1024 * 20       // 20MB
	)
public class CommunityWriteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/community/write_test.jsp")
                .forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");

        // =========================
        // 로그인 사용자
        // =========================
        String sessionEmail =
                (String) request.getSession()
                                .getAttribute("sessionEmail");

        // =========================
        // 일반 form 데이터
        // =========================
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        // =========================
        // 이미지 파일
        // =========================
        Part imagePart = request.getPart("image");

        String imageFileName = null;

        if (imagePart != null && imagePart.getSize() > 0) {
        	
            // 업로드 폴더
        	String uploadPath = FileConfig.getUploadPath(); 
	    		//상위폴더 경로 가져오기
	    		//C:/atumari_uploads
	    		
	
	    		System.out.println("uploadPath = " + uploadPath);
	    		Path uploadDir = Paths.get(uploadPath,"community"); 
	    		//하위폴더 경로 붙이기
	    		//c: git/atumari_upload/community
	
	        // =========================
	        // 값 확인
	        // =========================
	        System.out.println("==============================");
	        System.out.println("sessionEmail : " + sessionEmail);
	        System.out.println("title       : " + title);
	        System.out.println("content     : " + content);
	        System.out.println("image       : " + imageFileName);
	        System.out.println("==============================");
	
	        // =========================
	        // DTO 생성
	        // =========================
	        CommunityPostDto cmtydto =
	                new CommunityPostDto(
	                        sessionEmail,
	                        title,
	                        content
	                );
	
	        /*
	         * 여기에서 DAO/Service를 호출해서
	         * DB에 게시글을 저장하면 됨.
	         *
	         * 예:
	         *
	         * communityService.write(cmtydto, imageFileName);
	         */
	
	        // =========================
	        // 작성 페이지로 이동
	        // =========================
	        request.getRequestDispatcher(
	                "/WEB-INF/views/community/list.jsp"
	        ).forward(request, response);
        }			 
    }	
}
