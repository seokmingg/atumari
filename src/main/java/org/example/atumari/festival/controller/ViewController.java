package org.example.atumari.festival.controller;

import java.io.IOException;

import org.example.atumari.festival.dto.FestivalDto;
import org.example.atumari.festival.service.FestivalViewService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/festival/view")
public class ViewController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String festivalNo = request.getParameter("festival_no");

		if (festivalNo == null || festivalNo.isEmpty()) {

			response.sendRedirect(request.getContextPath() + "/festival/list");

			return;
		}

		int festivalNoValue = Integer.parseInt(festivalNo);

		// 목록 구분값 받기
		String type = request.getParameter("type");
		String region = request.getParameter("region");
		String season = request.getParameter("season");
		String month = request.getParameter("month");

		FestivalViewService service = new FestivalViewService();

		FestivalDto festival = service.getFestivalView(festivalNoValue);

		request.setAttribute("festival", festival);

		// JSP로 구분값 전달
		request.setAttribute("type", type);
		request.setAttribute("region", region);
		request.setAttribute("season", season);
		request.setAttribute("month", month);

		request.getRequestDispatcher("/WEB-INF/views/festival/festival_view.jsp").forward(request, response);
	}

}