package org.example.atumari.inquiry.controller;

import java.io.IOException;
import java.util.List;

import org.example.atumari.inquiry.dto.InquiryDto;
import org.example.atumari.inquiry.service.InquiryListService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InquiryListController
 */
@WebServlet("/inquiry/list")
public class InquiryListController extends HttpServlet {
	
		private final InquiryListService inquiryListService = new InquiryListService();
	
	   @Override
	    protected void doGet(HttpServletRequest request,
	                         HttpServletResponse response)
	            throws ServletException, IOException {
		   
		   //페이지네이션
		   String pageParam = request.getParameter("page");
		   int page = 1; //페이지 선택을 하지 않았을경우 기본 1번째 페이지를 보여줌
		   
		   if(pageParam != null) {
			   page = Integer.parseInt(pageParam);
		   }
		   
		   String searchType = request.getParameter("searchType");
		   String keyword = request.getParameter("keyword");
		   
		   List<InquiryDto> inquiryList = inquiryListService.getInquiryList(searchType,keyword,page);
		   
		   request.setAttribute("inquiryList",inquiryList);

	        String view = "/WEB-INF/views/inquiry/inquiry_list.jsp";
	        
	        request.getRequestDispatcher(view)
	               .forward(request, response);
	   }
}
