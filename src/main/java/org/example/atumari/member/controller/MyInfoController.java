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
    	
    	String sessionEmail = (String) request.getSession().getAttribute("sessionEmail");
    	
    	// 세션이 없으면 로그인 페이지로
    	if (sessionEmail == null) {
    		request.getRequestDispatcher("/WEB-INF/views/member/login.jsp")
    			.forward(request, response);
    	} else {
			// 서비스로 세션 이메일 넘기기
    		// 서비스에서 이메일로 db 조회(dao)해서 회원 정보 획득해 넘기기
    		// 다시 컨트롤러에서 셋 어트리뷰트한 다음
    		// jsp에서 jstl로 출력
    		
    		MemberService service = new MemberService();
    		
    		MemberDto memberDto = service.getMemberInfo(sessionEmail);
    		
			request.getRequestDispatcher("/WEB-INF/views/member/my-info.jsp")
				.forward(request, response);
    	}
    	
    }
}
