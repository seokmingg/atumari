package org.example.atumari.community.controller;

import java.io.IOException;

import org.example.atumari.community.dto.CommunityDto;
import org.example.atumari.community.service.CommunityService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/community/update")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,        // 1MB
	    maxFileSize = 1024 * 1024 * 10,         // 10MB
	    maxRequestSize = 1024 * 1024 * 20       // 20MB
	)
public class CommunityUpdateController extends HttpServlet {
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	CommunityService communityService = new CommunityService();
    	
    	Long cmtyno = Long.parseLong(request.getParameter("cmtyNo"));
    	
    	CommunityDto cmtydto = communityService.getCommunityView(cmtyno);
    	
    	request.setAttribute("cmtydto", cmtydto);
    	request.setAttribute("cmtyFiles", communityService.getCommunityFiles(cmtyno));
    	request.setAttribute("sessionEmail", (String) request.getSession().getAttribute("sessionEmail"));
    	
        request.getRequestDispatcher("/WEB-INF/views/community/update.jsp")
                .forward(request, response);
        
    }
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
		// 게시글 번호
		Long cmtyno = Long.parseLong(request.getParameter("cmtyNo"));
		// 로그인 사용자 (작성자)
        String sessionEmail =
                (String) request.getSession().getAttribute("sessionEmail");
        // 일반 form 데이터
        String title = request.getParameter("title");
        	title = getSingleQuot(title);
        String content = request.getParameter("content");
        	content = getSingleQuot(content);
    	// 기존 파일 번호
        String fileNoParam = request.getParameter("fileNo");
        

        System.out.println("fileNoParam : " + fileNoParam);
        
        Long fileNo = null;

        if (fileNoParam != null && !fileNoParam.isBlank()) {
            fileNo = Long.parseLong(fileNoParam);
        }
        // 기존 이미지 삭제 여부
        String deleteImage = request.getParameter("deleteImage");
        // 새 이미지
        Part imagePart = request.getPart("image");

//        System.out.println("cmtyNo : " + cmtyno);
//        System.out.println("fileNo : " + fileNo);
//        System.out.println("deleteImage : " + deleteImage);
//        System.out.println("image : " + imagePart);

        CommunityService communityService = new CommunityService();


        int result = communityService.update(
		        				cmtyno,
		                        sessionEmail,
		                        title,
		                        content,
		                        imagePart,
		                        deleteImage,
		                        fileNo
		                );
        // 저장 성공
        if (result == 1) {
        	response.sendRedirect(request.getContextPath() + "/community/view?cmtyNo="+cmtyno);
			return;
        } else {
        	request.setAttribute(
                    "errorMessage",
                    "게시물 수정에 실패했습니다."
            );
        	response.sendRedirect(request.getContextPath() + "/community/update?cmtyNo="+cmtyno);
			return;
        }
		
	}
	//작은따옴표 변환
    private String getSingleQuot(String str) {
		str = str.replaceAll("'", "&#39;");
		return str;
	}
}
