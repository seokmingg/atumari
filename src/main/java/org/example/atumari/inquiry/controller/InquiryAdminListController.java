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
 * Servlet implementation class InquiryAdminListController
 */
@WebServlet("/inquiry/admin/list")
public class InquiryAdminListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
			   
			   //검색 조건
			   String searchType = request.getParameter("searchType");
			   String keyword = request.getParameter("keyword");
			   
			   //답변 상태
			   String status = request.getParameter("status");
			   
			   //허용된 답변 상태인지 확인
			   if(!"WAITING".equals(status) && !"COMPLETED".equals(status)) {
				   status = null;
			   }
			   
			   
			   List<InquiryDto> inquiryList = inquiryListService.getInquiryList(searchType,keyword,page,status);
			   
			   // 검색 및 답변상태 조회시 전체 글 수 
			   int totalCount = inquiryListService.getTotalCount(searchType,keyword,status);
			   int totalPages = inquiryListService.getTotalPages(totalCount);
			   
			   // 검색 조건과 관계없는 전체 글 수
			   int allInquiryCount =
			       inquiryListService.getTotalCount("", "", "");
			   // 답변 상태 waiting 인 문의글 수
			   int waitingInquiryCount =
			       inquiryListService.getTotalCount("", "", "WAITING");
			   // 답변 상태 completed 인 문의글 수
			   int completedInquiryCount =
			       inquiryListService.getTotalCount("", "", "COMPLETED");
		
			   
			   
			   request.setAttribute("keyword", keyword);// 검색 키워드
			   request.setAttribute("searchType", searchType);// 검색 select
			   request.setAttribute("inquiryList", inquiryList);// 현재 페이지에 보여줄 문의글 목록
			   request.setAttribute("totalCount", totalCount); // 검색조건에 맞는 전체 문의 수
			   request.setAttribute("allInquiryCount", allInquiryCount); // 검색 조건 관계없는 전체 문의 수
			   request.setAttribute("waitingInquiryCount", waitingInquiryCount); // 답변미응답 문의 수
			   request.setAttribute("completedInquiryCount", completedInquiryCount); // 답변 완료 문의 수
			   request.setAttribute("totalPages", totalPages); // 전체 페이지 개수
			   request.setAttribute("page", page); // 현재 몇페이지인지
		//	   request.setAttribute("status", status);// 답변상태

		       String view = "/WEB-INF/views/inquiry/admin_inquiry_list.jsp";

		       request.getRequestDispatcher(view)
		               .forward(request, response);
		   }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
