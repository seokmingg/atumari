package org.example.atumari.member.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import org.example.atumari.member.dto.SignupRequest;
import org.example.atumari.member.service.MemberService;

@WebServlet("/signup")
public class SignupController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/member/signup.jsp")
                .forward(request, response);
    }
    
    // 테스트 완료: 정상적으로 post 요청 받음
    // TODO. doPost() 마저 작성
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.setCharacterEncoding("utf-8");

    	String email = request.getParameter("email");
    	String name = request.getParameter("userName");
    	String password = request.getParameter("password");
    	String passwordConfirm = request.getParameter("passwordConfirm");
    	String agree = request.getParameter("agree"); // 이용약관 동의 체크박스 -> 체크하면 "on" 반환
    	
    	// 입력값이 있을 때만 실행
    	if (email != null) {
    		SignupRequest signup = new SignupRequest();
        	
        	signup.setEmail(email);
        	signup.setName(name);
        	signup.setPassword(passwordConfirm);
        	signup.setPasswordConfirm(passwordConfirm);
        	signup.setAgree(agree.equals("on")); // boolean으로 dto에 전달

        	MemberService service = new MemberService();
        	
        	try {
				int result = service.signup(signup);
				
				if (result == 1) {
					request.setAttribute("msg", "회원 등록 성공");
				} else {
					request.setAttribute("msg", "회원 등록 실패");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
        	
    	}
//            request.getRequestDispatcher("/WEB-INF/views/member/signup.jsp")
//                    .forward(request, response);
    	
    	
    }
}
