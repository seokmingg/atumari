package org.example.atumari.member.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import org.example.atumari.member.dto.LoginRequest;
import org.example.atumari.member.service.MemberService;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/member/login.jsp")
                .forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	
    	String email = request.getParameter("email");
    	String password = request.getParameter("password");
    	
    	// 입력값이 있을 때만 실행
    	if (email != null) {
    		LoginRequest login = new LoginRequest();
    		
    		login.setEmail(email);
    		login.setPassword(password);
    		
    		MemberService service = new MemberService();
    		
    		try {
				int count = service.login(login);
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	
    	request.getRequestDispatcher("/WEB-INF/views/home/index.jsp")
        .forward(request, response);
    	
    }
}
