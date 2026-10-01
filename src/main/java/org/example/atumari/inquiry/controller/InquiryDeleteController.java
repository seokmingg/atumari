package org.example.atumari.inquiry.controller;

import java.io.IOException;

import org.example.atumari.inquiry.service.InquiryService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class InquiryDeleteController
 */
@WebServlet("/inquiry/delete")
public class InquiryDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final InquiryService inquiryService = new InquiryService();
	
	 @Override
	    protected void doPost(
	            HttpServletRequest request,
	            HttpServletResponse response)
	            throws ServletException, IOException {
		 
		 // 로그인 여부 확인
		 HttpSession session = request.getSession(false);
		 
		 if(session == null || session.getAttribute("sessionId") == null) {
			 response.sendRedirect(request.getContextPath() + "/member/login");
			 
			 return;
		 }
		 
		 // 로그인한 회원 PK
		 Long memberId = (Long)session.getAttribute("sessionId");
		 
		 // 관리자인지 확인
		 boolean isAdmin =
				    "admin".equals(session.getAttribute("sessionLevel"));

		 
		 // 삭제할 문의글 번호
		 int inquiryNo = Integer.parseInt(request.getParameter("inquiryNo"));
	
		 // 문의 삭제
		 inquiryService.deleteInquiry(inquiryNo, memberId, isAdmin);
		 
		 // 삭제 성공 후 목록으로 이동
		 response.sendRedirect(request.getContextPath()+"/inquiry/list");
	 }

}
