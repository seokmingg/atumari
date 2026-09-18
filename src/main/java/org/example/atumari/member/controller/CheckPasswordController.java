package org.example.atumari.member.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.example.atumari.member.service.MemberService;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * Servlet implementation class CheckPasswordController
 */
@WebServlet("/checkpassword")
public class CheckPasswordController extends HttpServlet {
	/**
	 * 마이페이지 회원 정보 수정시 비밀번호 입력 값 일치하는지 검증하는 컨트롤러
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		MemberService service = new MemberService();
		boolean isEqual = service.checkPassword(email, password);
		
		if (service.checkPassword(email, password)) out.print("会員情報が修正されました。");
		else out.print("パスワードをもう一度確認してください。");
		
	}

}
