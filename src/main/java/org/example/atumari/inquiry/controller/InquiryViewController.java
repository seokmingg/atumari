package org.example.atumari.inquiry.controller;

import java.io.IOException;

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
	  @Override
	    protected void doGet(HttpServletRequest request,
	                         HttpServletResponse response)
	            throws ServletException, IOException {


	        String view = "/WEB-INF/views/inquiry/inquiry_view.jsp";

	        request.getRequestDispatcher(view)
	               .forward(request, response);
	   }
}

