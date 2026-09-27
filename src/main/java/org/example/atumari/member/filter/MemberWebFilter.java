package org.example.atumari.member.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter({"/my-info", "/my-info/modify"})
public class MemberWebFilter implements Filter {
	@Override
	public void doFilter(
			ServletRequest request,
			ServletResponse response,
			FilterChain chain
			) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession session = req.getSession(false); // 세션이 이미 존재하면 가져오고, 없으면 null 반환
		
		System.out.println("WebFilter 호출 -> 회원 세션 인증 실행");
		
		// 생성된 세션이 없거나, 세션에 로그인 정보가 없으면
		if (session == null || session.getAttribute("sessionEmail") == null) {
			System.out.println("로그인 정보 없음. 로그인 페이지로 이동");
			req.getRequestDispatcher("/WEB-INF/views/member/login.jsp")
				.forward(req, resp);
			return; // 로그인 페이지로 리다이렉트 후 필터 종료
		}
		
		chain.doFilter(request, response); // 세션에 로그인 정보가 있으면 각 기능 컨트롤러로 넘기기
	}

}
