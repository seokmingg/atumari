package org.example.atumari.member.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import org.example.atumari.member.dto.MemberDto;
import org.example.atumari.member.dto.MyInfoModifyRequest;
import org.example.atumari.member.dto.SessionDto;
import org.example.atumari.member.dto.SignupRequest;
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
    		
        	request.setCharacterEncoding("utf-8");

        	//String email = request.getParameter("email");
        	String name = request.getParameter("userName");
        	String nickname = request.getParameter("nickname");
        	String tel = request.getParameter("tel");
        	
    		MyInfoModifyRequest modify = new MyInfoModifyRequest();
    		modify.setEmail(sessionEmail);
    		modify.setName(name);
    		modify.setTel(tel);
    		modify.setNickname(nickname);
    		
        	MemberService service = new MemberService();
        	
        	try {
				int result = service.modify(modify);

				if (result == 1) {
					
					// 모든 페이지에서 사용할 세션 설정
					HttpSession session = request.getSession();
					// refactor: 별도의 세션 dto 사용
					SessionDto sessionDto = new SessionDto();
					
					session.setAttribute("sessionName", modify.getName()); // refactor: 헤더에 출력할 세션 이름을 변경한 회원 이름으로 바꾸기
					
					request.setAttribute("msg", "회원 정보 수정 성공");
					// 성공할 때만 다시 마이페이지로
					response.sendRedirect(request.getContextPath() + "/my-info");
					return;
				} 
				
				// 실패하면
				request.setAttribute("msg", "회원 정보 수정 실패");
//    				잘못된 입력값 처리
			} catch (IllegalArgumentException e) { 
				e.printStackTrace();
				e.getMessage();
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("msg", "회원 정보 수정 중 문제가 발생했습니다. 웹 관리자에게 문의 바랍니다.");
			}
        	
        	request.getRequestDispatcher("/WEB-INF/views/member/my-info-modify.jsp")
        		.forward(request, response);
        	
    	}
    }
}
