package org.example.atumari.inquiry.controller;

import java.io.IOException;

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
	   @Override
	    protected void doGet(HttpServletRequest request,
	                         HttpServletResponse response)
	            throws ServletException, IOException {


	        String view = "/WEB-INF/views/inquiry/inquiry_list.jsp";

	        request.getRequestDispatcher(view)
	               .forward(request, response);
	   }
}
