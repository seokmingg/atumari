package org.example.atumari.member.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String loginName = (String)session.getAttribute("sessionName");
		
		if (loginName != null) {
			// 세션 삭제 후 로그아웃 처리
			session.invalidate();
		}
		
//		request.getRequestDispatcher("/WEB-INF/views/home/index.jsp")
//			.forward(request, response);
		
		response.sendRedirect(request.getContextPath() + "/");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
