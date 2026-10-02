package org.example.atumari.inquiry.controller;

import java.io.IOException;
import java.util.List;

import org.example.atumari.inquiry.dto.InquiryDto;
import org.example.atumari.inquiry.dto.InquiryFileDto;
import org.example.atumari.inquiry.service.InquiryViewService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class InquiryViewController
 */
@WebServlet("/inquiry/view")
public class InquiryViewController extends HttpServlet {
	
	private final InquiryViewService inquiryViewService = new InquiryViewService();
	//private final InquiryDto inquiryDto = new InquiryDto();
	//private final InquiryDao inquiryDao = new InquiryDao();
	
	  @Override
	    protected void doGet(HttpServletRequest request,
	                         HttpServletResponse response)
	            throws ServletException, IOException {
		  
		  	String inquiryNoParam = request.getParameter("inquiryNo");
	        int inquiryNo = Integer.parseInt(inquiryNoParam);
	        
	        
	        // 문의글 상세조회
		  	InquiryDto inquiryDto = inquiryViewService.getInquiryView(inquiryNo);
		  	
		  	// 존재하지 않는 문의
		  	if(inquiryDto == null) {
		  		response.sendRedirect(request.getContextPath() + "inquiry/list");
		  		return;
		  	}
		  	
		  	// 비공개 문의일 때만 권한 확인
		  	if(!inquiryDto.isPublic()) {
		  		HttpSession session = request.getSession(false);
		  		
		  		// sessionId가 널인지 판단
		  		Long memberId = session == null ? null : (Long)session.getAttribute("sessionId");
		  		
		  		// 세션 정보가 있다면 그 정보는 db에 저장된 작성자 아이디와 동일해야함
		  		boolean isOwner = memberId != null && memberId.equals(inquiryDto.getMember_id());
		  		
		  		// 관리자인지 아닌지 sessionLevel로 확인
		  		boolean isAdmin = session != null && "admin".equals(session.getAttribute("sessionLevel"));
		  
		  		if(!isOwner && !isAdmin) {
		  			response.sendRedirect(request.getContextPath()+"/inquiry/list?error=private");
		  			return;
		  		}
		  		
		  	}
		  	
		    // 첨부파일 상세조회
		  	List<InquiryFileDto> fileDtos = inquiryViewService.getInquiryFiles(inquiryNo);
		 
		    request.setAttribute("inquiryDto", inquiryDto);
	        request.setAttribute("fileDtos", fileDtos);
	        
	        String view = "/WEB-INF/views/inquiry/inquiry_view.jsp";
	        request.getRequestDispatcher(view)
	               .forward(request, response);
	   }
}

