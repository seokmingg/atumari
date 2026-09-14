package org.example.atumari.inquiry.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.example.atumari.inquiry.dto.InquiryDto;
import org.example.atumari.inquiry.service.InquiryService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;


@WebServlet("/inquiry/write")
@MultipartConfig
public class InquiryWriteController extends HttpServlet {
	
/*	Controller
  	→ request / response / session을 다룸
	→ 사용자가 누구인지 확인
	→ Service에 필요한 값 전달
*/	
	   private final InquiryService inquiryService = new InquiryService();
	
	   @Override
	    protected void doGet(HttpServletRequest request,
	                         HttpServletResponse response)
	            throws ServletException, IOException {

	        String view = "/WEB-INF/views/inquiry/inquiry_write.jsp";
	        request.getRequestDispatcher(view).forward(request, response);
	    }
	   
	   @Override
	    protected void doPost(HttpServletRequest request,
	                          HttpServletResponse response)
	                          throws ServletException, IOException {
		   
	
		   //문의 등록 폼에서 보낸 데이터 받기
	        String title = request.getParameter("title");
	        
	        HttpSession session = request.getSession(false);

	        if (session == null) {
	            response.sendRedirect(
	                    request.getContextPath() + "/login"
	                );
	                return;
	        }

	        String writer =
	                (String) session.getAttribute("sessionName");
	        
	        if (writer == null || writer.trim().isEmpty()) {
	            response.sendRedirect(
	                request.getContextPath() + "/login"
	            );
	            return;
	        }

//	        Integer member_id =
//	                (Integer) session.getAttribute("sessionMemberId");

//	        
//	        if (member_id == null) {
//	            response.sendRedirect(
//	                    request.getContextPath() + "/login"
//	                );
//	                return;
//	        }
	        
	        boolean isPublic = "1".equals(request.getParameter("isPublic"));//1이면 공개 true로 바꿔서 dto에 저장
	        boolean emailNotify = "1".equals(request.getParameter("emailNotify"));
	        
	        String email = request.getParameter("email");
	        String content = request.getParameter("content");
	        
	        InquiryDto inquiry = new InquiryDto(1,title, writer, isPublic, content, email);
	        
	        List<Part> files = new ArrayList<>();

	        for (Part part : request.getParts()) {
	            if ("inquiryFile".equals(part.getName())
	                    && part.getSize() > 0) {
	                files.add(part);
	            }
	        }
	        
	       //Service 에서 던져진 예외 처리, Service 형식검증 등에 맞지 않으면 예외가 발생
	       // Service에서 발생한 검증 오류 및 처리 오류를 Controller에서 처리
	        try {

	            inquiryService.createInquiry(
	                inquiry,
	                files,
	                emailNotify
	            );

	            response.sendRedirect(
	                request.getContextPath() + "/inquiry/list"
	            );
	        
	        //사용자 입력 검증 실패용
	        } catch (IllegalArgumentException e) {

	            request.setAttribute(
	                "errorMessage",
	                e.getMessage()
	            );

	            request.getRequestDispatcher(
	                "/WEB-INF/views/inquiry/inquiry_write.jsp"//추후 view로 보낼지 한번 생각해보기
	            ).forward(request, response);
	        
	         // DB 저장, 파일 저장 등 처리 중 오류
	        } catch (RuntimeException e) {
	        	
	        e.printStackTrace();

	        request.setAttribute(
	            "errorMessage",
	            "お問い合わせの登録中にエラーが発生しました。"
	        );

	        request.getRequestDispatcher(
	            "/WEB-INF/views/inquiry/inquiry_write.jsp"
	        ).forward(request, response);
	    }
	       
	       
	    }

}
