package org.example.atumari.member.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.example.atumari.member.dto.MemberDto;
import org.example.atumari.member.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/my-info/exit")
public class ExitController extends HttpServlet {
	
	private final MemberService memberService = new MemberService();
       
    /**
     * 회원 탈퇴 컨트롤러
     */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionId = request.getParameter("sessionId");
		
		try {
			int result = memberService.exit(sessionId);
			
			if (result == 1) {
				request.getSession().invalidate(); // 세션 정보 삭제
				request.setAttribute("msg", "회원 탈퇴 성공");
				
				// 탈퇴 성공하면 로그인 페이지로 이동 후 컨트롤러 종료
				response.sendRedirect(request.getContextPath() + "/login");
				return;
			}
			
			request.setAttribute("msg", "회원 탈퇴 실패. 웹 관리자에게 문의.");
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			e.getMessage();
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원 탈퇴 중 문제가 발생했습니다. 웹 관리자에게 문의 바랍니다.");
		}
		
		// 실패하면 다시 회원 정보 조회 후 마이페이지로
		String sessionEmail = (String) request.getSession().getAttribute("sessionEmail");
		
		MemberDto memberDto = memberService.getMemberInfo(sessionEmail);
		
		request.setAttribute("myInfo", memberDto);
		
		request.getRequestDispatcher("/WEB-INF/views/member/my-info.jsp")
		.forward(request, response);
		
	}

}
