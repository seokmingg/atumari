package org.example.atumari.member.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.example.atumari.member.dto.MemberDto;
import org.example.atumari.member.service.MemberService;

@WebServlet("/my-info/modify")
public class MyInfoModifyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String sessionEmail = (String) request.getSession().getAttribute("sessionEmail");
    	
    	// 세션이 없으면 로그인 페이지로
    	if (sessionEmail == null) {
    		request.getRequestDispatcher("/WEB-INF/views/member/login.jsp")
			.forward(request, response);
    	} else {
    		
    		MemberService service = new MemberService();
        	
        	// 회원정보 조회
    		MemberDto memberDto = service.getMemberInfo(sessionEmail);
    		
    		request.setAttribute("myInfo", memberDto);
        	
    		request.getRequestDispatcher("/WEB-INF/views/member/my-info-modify.jsp")
    		.forward(request, response);
    	}
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String sessionEmail = (String) request.getSession().getAttribute("sessionEmail");
    	
    	// 세션이 없으면 로그인 페이지로
    	if (sessionEmail == null) {
    		request.getRequestDispatcher("/WEB-INF/views/member/login.jsp")
			.forward(request, response);
    	} else {
    		
    		MemberService service = new MemberService();
        	
        	// 회원정보 조회
    		MemberDto memberDto = service.getMemberInfo(sessionEmail);
    		
    		request.setAttribute("myInfo", memberDto);
        	
            request.getRequestDispatcher("/WEB-INF/views/member/my-info-modify.jsp")
                    .forward(request, response);
    	}
    }
}
