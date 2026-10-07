package org.example.atumari.inquiry.controller;

import java.io.IOException;
import java.util.List;

import org.example.atumari.inquiry.dto.InquiryDto;
import org.example.atumari.inquiry.dto.InquiryFileDto;
import org.example.atumari.inquiry.service.AdminService;
import org.example.atumari.inquiry.service.InquiryViewService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class InquiryAdminViewController
 */
@WebServlet("/inquiry/admin/view")
public class InquiryAdminViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final InquiryViewService inquiryViewService = new InquiryViewService();
	private final AdminService adminService = new AdminService();

	   @Override
	    protected void doGet(HttpServletRequest request,
	                         HttpServletResponse response)
	            throws ServletException, IOException {
		   
		   // 로그인 여부 + 관리자 확인
		    HttpSession session = request.getSession(false);
		    
		    if(session == null || 
		    		!"admin".equals(session.getAttribute("sessionLevel")) ) {
		    	response.sendRedirect(request.getContextPath() + "/member/login");
		    	return;
		    }
		    
		   
			String inquiryNoParam = request.getParameter("inquiryNo");
	        int inquiryNo = Integer.parseInt(inquiryNoParam);
	        
	        
	        // 문의글 상세조회
		  	InquiryDto inquiryDto = inquiryViewService.getInquiryView(inquiryNo);
		  	
		  	// 존재하지 않는 문의
		  	if(inquiryDto == null) {
		  		response.sendRedirect(request.getContextPath() + "inquiry/list");
		  		return;
		  	}
		   	
		
		 // 첨부파일 상세조회
		  	List<InquiryFileDto> fileDtos = inquiryViewService.getInquiryFiles(inquiryNo);
		 
		    request.setAttribute("inquiryDto", inquiryDto);
	        request.setAttribute("fileDtos", fileDtos);
		    
		    
	        String view = "/WEB-INF/views/inquiry/admin_inquiry_view.jsp";

	        request.getRequestDispatcher(view)
	               .forward(request, response);
	        
	   }
	   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 여부 + 관리자 확인
	    HttpSession session = request.getSession(false);
	    
	    if(session == null || 
	    		!"admin".equals(session.getAttribute("sessionLevel")) ) {
	    	response.sendRedirect(request.getContextPath() + "/member/login");
	    	return;
	    }
	    
	    // 문의 글 번호 , 사용자가 값을 문자열이나 확인불가한 문자로 값을 변경할 경우를 대비
	    int inquiryNo;
	    try {
	        inquiryNo = Integer.parseInt(request.getParameter("inquiryNo"));
	        //양수가 맞는 지 확인
	        if (inquiryNo <= 0) {
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	            return;
	        }
	    } catch (NumberFormatException e) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	        return;
	    }
	    
	    // 문의 답변
	    String answerContent = request.getParameter("answerContent");
	    try {
	        // 답변 저장 + 답변 시간 기록 + COMPLETED로 변경
	    	adminService.saveAnswer(inquiryNo,answerContent);
	    	
	    	 response.sendRedirect(
	    		        request.getContextPath()
	    		        + "/inquiry/admin/view?inquiryNo=" + inquiryNo
	    		    );
	    		    return;

	   } catch (IllegalArgumentException e) {
		   // 입력값 또는 문의 존재 여부 검증 실패
	    		    request.setAttribute("errorMessage", e.getMessage());

	   } catch (RuntimeException e) {
		   // DB 처리 등 오류
	    		    e.printStackTrace();

	    		    request.setAttribute(
	    		        "errorMessage",
	    		        "回答の保存中にエラーが発生しました。"
	    		    );
	   }
	    
	    
	 // 실패 시 상세 화면 다시 표시
	    InquiryDto inquiryDto =
	        inquiryViewService.getInquiryView(inquiryNo);

	    if (inquiryDto == null) {
	        response.sendError(HttpServletResponse.SC_NOT_FOUND);
	        return;
	    }

	    // 관리자가 입력했던 답변 유지, DB에는 저장되지 않고 dto에만 담겨있는 상태
	    inquiryDto.setAnswer_content(answerContent);

	    request.setAttribute("inquiryDto", inquiryDto);
	    request.setAttribute(
	        "fileDtos",
	        inquiryViewService.getInquiryFiles(inquiryNo)
	    );

	    request.getRequestDispatcher(
	        "/WEB-INF/views/inquiry/admin_inquiry_view.jsp"
	    ).forward(request, response);   
	    return;
	}

}
