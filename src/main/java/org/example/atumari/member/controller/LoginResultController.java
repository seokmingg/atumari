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
	/*
	 * 로그인 결과를 알럿으로 사용자에게 보여주는 컨트롤러
	 * */
	
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//    	doPost(request, response);
//    }
    
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
			
			if (!"".equals(loginName)) out.print(loginName + "様、あつまりへようこそ！");
			else out.print("メールアドレスやパスワードをもう一度確認してください。");
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
}
