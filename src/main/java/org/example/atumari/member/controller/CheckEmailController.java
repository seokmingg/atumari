package org.example.atumari.member.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import org.example.atumari.member.service.MemberService;

/**
 * Servlet implementation class CheckEmailController
 */
@WebServlet("/checkemail")
public class CheckEmailController extends HttpServlet {
	/*
	 * 회원가입에서 이메일 입력 값 충복 검증하는 컨트롤러
	 * */
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("email");
		
		MemberService service = new MemberService();
		int count = 0;
		count = service.checkDuplicateEmail(email);
		
		if (count == 0) out.print("このメールアドレスはご利用いただけます。あつまりへようこそ！");
		else out.print("このメールアドレスは登録済です。他のメールアドレスを入力してください。");
			
	}

}
