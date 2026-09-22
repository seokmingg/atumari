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
		    // 첨부파일 상세조회
		  	List<InquiryFileDto> fileDtos = inquiryViewService.getInquiryFiles(inquiryNo);
		 
		    request.setAttribute("inquiryDto", inquiryDto);
	        request.setAttribute("fileDtos", fileDtos);
	        
	        String view = "/WEB-INF/views/inquiry/inquiry_view.jsp";
	        request.getRequestDispatcher(view)
	               .forward(request, response);
	   }
}

