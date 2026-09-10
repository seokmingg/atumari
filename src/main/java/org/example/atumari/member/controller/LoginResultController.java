package org.example.atumari.member.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import org.example.atumari.member.dto.LoginRequest;
import org.example.atumari.member.service.MemberService;

import at.favre.lib.crypto.bcrypt.BCrypt;

@WebServlet("/loginresult")
public class LoginResultController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	
    	String email = request.getParameter("email");
    	String password = request.getParameter("password");
    	
    	LoginRequest login = new LoginRequest();
		
		login.setEmail(email);
		// 입력받은 비밀번호
		login.setPassword(password);
    	
    	MemberService service = new MemberService();
    	
    	try {
			String loginName = service.login(login);
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			if (!"".equals(loginName)) out.print("로그인에 성공했습니다. " + loginName + "님 환영합니다!");
			else out.print("이메일이나 비밀번호를 다시 확인해 주세요.");
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
}
