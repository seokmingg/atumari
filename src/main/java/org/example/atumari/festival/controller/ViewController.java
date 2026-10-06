package org.example.atumari.festival.controller;

import java.io.IOException;
import java.util.List;

import org.example.atumari.festival.dto.FestivalDto;
import org.example.atumari.festival.dto.ReviewDto;
import org.example.atumari.festival.service.FestivalReviewService;
import org.example.atumari.festival.service.FestivalViewService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
		String date = request.getParameter("date");
		String year = request.getParameter("year");

		// 홈 날짜 검색값 받기
		String keyword = request.getParameter("keyword");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		FestivalViewService service = new FestivalViewService();
		FestivalReviewService rservice = new FestivalReviewService();

		FestivalDto festival = service.getFestivalView(festivalNoValue);

		final int reviewPageSize = 10;

		List<ReviewDto> reviewList = rservice.getReviewList(festivalNoValue, 1, reviewPageSize);

		int reviewCount = rservice.getReviewCount(festivalNoValue);

		request.setAttribute("festival", festival);
		
		request.setAttribute("reviewCount", reviewCount);
		request.setAttribute("reviewPageSize", reviewPageSize);

		// JSP로 구분값 전달
		request.setAttribute("type", type);
		request.setAttribute("region", region);
		request.setAttribute("season", season);
		request.setAttribute("month", month);
		request.setAttribute("date", date);
		request.setAttribute("year", year);

		// JSP로 홈 검색값 전달
		request.setAttribute("keyword", keyword);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);

		request.setAttribute("reviewList", reviewList);

		HttpSession session = request.getSession();

		String msg = (String) session.getAttribute("msg");

		request.setAttribute("msg", msg);

		session.removeAttribute("msg");

		request.getRequestDispatcher("/WEB-INF/views/festival/festival_view.jsp").forward(request, response);
	}
}