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
		   int totalCount = inquiryListService.getTotalCount(searchType,keyword);
		   int totalPages = inquiryListService.getTotalPages(totalCount,page);
		   
		   request.setAttribute("inquiryList",inquiryList);// 현재 페이지에 보여줄 문의글 목록
		   request.setAttribute("totalCount", totalCount); // 검색조건에 맞는 전체 문의글 개수
		   request.setAttribute("totalPages", totalPages); // 전체 페이지 개수
		   request.setAttribute("page", page); // 현재 몇페이지인지

	        String view = "/WEB-INF/views/inquiry/inquiry_list.jsp";
	        
	        request.getRequestDispatcher(view)
	               .forward(request, response);
	   }
}
