package org.example.atumari.member.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import org.example.atumari.member.dto.LoginRequest;
import org.example.atumari.member.service.MemberService;

import at.favre.lib.crypto.bcrypt.BCrypt;

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
    		// 로그인 입력값 이중 검증 위해 별도의 dto 사용
    		LoginRequest login = new LoginRequest();
    		
    		login.setEmail(email);
    		// 입력받은 비밀번호
    		login.setPassword(password);
    		
    		MemberService service = new MemberService();
    		
    		try {
				String loginName = service.login(login);
				
				if (!"".equals(loginName)) {
					request.setAttribute("msg", "로그인 성공");
					
					// 모든 페이지에서 사용할 세션 설정
					HttpSession session = request.getSession();
					session.setAttribute("sessionEmail", email);
					session.setAttribute("sessionName", loginName);
					
					// 관리자 이메일 회원이면
					if ("admin@atumari.co.jp".equals(email)) {
						session.setAttribute("sessionLevel", "admin"); // 관리자 세션
					} else {
						session.setAttribute("sessionLevel", "member"); // 일반 회원 세션
					}
					
					session.setMaxInactiveInterval(60 * 60 * 4); // 세션 유지 시간(4시간)
					
					// 로그인 성공하면 인덱스 페이지로
					response.sendRedirect(request.getContextPath() + "/home");
					return;
				} 
				
				// 로그인 실패하면
				request.setAttribute("msg", "로그인 실패");
			// 잘못된 입력값 처리
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				e.getMessage();
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("msg", "로그인 과정 중 문제가 발생했습니다. 웹 관리자에게 문의 바랍니다.");
			}
    		
    		request.getRequestDispatcher("/WEB-INF/views/member/login.jsp")
    			.forward(request, response);
    	}
    	
    	
    }
}
