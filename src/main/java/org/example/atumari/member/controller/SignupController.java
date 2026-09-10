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
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");

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
        	signup.setPassword(password);
        	signup.setPasswordConfirm(passwordConfirm);
        	/*
        	 * refactor: "String".equals(value) 형태가 구조상 조금 더 안정적이라는 피드백 반영
        	 * 				null.equals() 예외 방지
        	 * */
        	signup.setAgree("on".equals(agree)); // boolean으로 dto에 전달

        	MemberService service = new MemberService();
        	
        	try {
				int result = service.signup(signup);

				if (result == 1) {
					request.setAttribute("msg", "회원 등록 성공");
					// 성공할 때만 로그인 페이지로
					response.sendRedirect(request.getContextPath() + "/login");
					return;
				} 
				
				// 실패하면
				request.setAttribute("msg", "회원 등록 실패");
//				잘못된 입력값 처리
			} catch (IllegalArgumentException e) { 
				e.printStackTrace();
				e.getMessage();
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("msg", "회원 등록 과정 중 문제가 발생했습니다. 웹 관리자에게 문의 바랍니다.");
			}
        	
        	request.getRequestDispatcher("/WEB-INF/views/member/signup.jsp")
        		.forward(request, response);
    	}
    	
    	
    }
}
