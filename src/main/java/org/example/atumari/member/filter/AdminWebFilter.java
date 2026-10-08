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

/* 
 * 관리자 전용 페이지 접근 권한을 검증하는 WebFilter 클래스입니다.
 * 검증이 필요하신 분은 아래 @WebFilter 어노테이션에 해당 기능 url을 지유롭게 추가하실 수 있습니다.
 * */

// 어노테이션 괄호 안에 관리자 여부 인증 필요한 url 추가
// refactor: 기존 회원 전용 페이지 검증 필터에서 관리자 전용 페이지 검증 로직 별도 클래스로 분리
@WebFilter({"/inquiry/admin/list", "/inquiry/admin/view",
			"/notice/delete", "/notice/edit", "/notice/write"})
public class AdminWebFilter implements Filter {
	@Override
	public void doFilter(
			ServletRequest request,
			ServletResponse response,
			FilterChain chain
			) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession session = req.getSession(false); // 세션이 이미 존재하면 가져오고, 없으면 null 반환
		
		System.out.println("AdminWebFilter 호출 -> 관리자 세션 인증 실행");
		
		// 생성된 세션이 없거나, 관리자 세션 정보가 아니면
		if (session == null || !"admin".equals(session.getAttribute("sessionLevel"))) {
			System.out.println("관리자 외 접근 제한. index 페이지로 이동");
			req.getRequestDispatcher("/WEB-INF/views/home/index.jsp")
				.forward(req, resp);
			return; // 메인 페이지로 리다이렉트 후 필터 종료
		}
		
		chain.doFilter(request, response); // 관리자이면 각 기능 컨트롤러로 넘기기
	}

}
