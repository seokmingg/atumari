package org.example.atumari.inquiry.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class InquiryAdminViewController
 */
@WebServlet("/inquiry/admin/view")
public class InquiryAdminViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	   @Override
	    protected void doGet(HttpServletRequest request,
	                         HttpServletResponse response)
	            throws ServletException, IOException {


	        String view = "/WEB-INF/views/inquiry/admin_inquiry_view.jsp";

	        request.getRequestDispatcher(view)
	               .forward(request, response);
	   }
	   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
