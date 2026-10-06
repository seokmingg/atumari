package org.example.atumari.member.controller;

import java.io.IOException;

import org.example.atumari.member.dto.MemberDto;
import org.example.atumari.member.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/my-info")
public class MyInfoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// Refactor: MemberWebFilter에서 먼저 회원 세션 검증 후 실행
    	
    	String sessionEmail = (String) request.getSession().getAttribute("sessionEmail");
    	
    	MemberService service = new MemberService();
		
		// 회원정보 조회
		MemberDto memberDto = service.getMemberInfo(sessionEmail);
		
		request.setAttribute("myInfo", memberDto);
		
		request.getRequestDispatcher("/WEB-INF/views/member/my-info.jsp")
			.forward(request, response);
    	
    }
}
