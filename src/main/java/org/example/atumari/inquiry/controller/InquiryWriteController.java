package org.example.atumari.inquiry.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InquiryWriteController
 */
@WebServlet("/inquiry/write")
public class InquiryWriteController extends HttpServlet {
	
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

	        String title = request.getParameter("title");
	        String writer = request.getParameter("writer");
	        String isPublic = request.getParameter("isPublic");
	        String emailNotify = request.getParameter("emailNotify");
	        String email = request.getParameter("email");
	        String inquiryFile = request.getParameter("inquiryFile");
	        String content = request.getParameter("content");

	       

	        // DB 저장 등의 처리
	    }

}
