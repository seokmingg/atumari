package org.example.atumari.home.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.example.atumari.festival.dto.FestivalDto;
import org.example.atumari.festival.service.FestivalCardService;
import org.example.atumari.home.service.HomeSearchService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({ "", "/home", "/home/search" })
public class HomeController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getServletPath();

		// =========================
		// 검색
		// =========================
		if ("/home/search".equals(path)) {

			String keyword = request.getParameter("keyword");

			String startDateParam = request.getParameter("startDate");

			String endDateParam = request.getParameter("endDate");

			LocalDate startDate = null;
			LocalDate endDate = null;

			if (startDateParam != null && !startDateParam.isEmpty()) {
				startDate = LocalDate.parse(startDateParam);
			}

			if (endDateParam != null && !endDateParam.isEmpty()) {
				endDate = LocalDate.parse(endDateParam);
			}

			// =========================
			// 페이지
			// =========================
			int page = 1;

			String pageParam = request.getParameter("page");

			if (pageParam != null && !pageParam.isEmpty()) {
				try {
					page = Integer.parseInt(pageParam);
				} catch (NumberFormatException e) {
					page = 1;
				}
			}

			if (page < 1) {
				page = 1;
			}

			// =========================
			// 페이지 설정
			// =========================
			int pageSize = 5;
			int pageBlock = 5;

			int start = (page - 1) * pageSize;

			// =========================
			// 검색
			// =========================
			HomeSearchService service = new HomeSearchService();

			List<FestivalDto> festivalList = service.searchFestivalList(keyword, startDate, endDate, start, pageSize);

			// =========================
			// 전체 개수
			// =========================
			int totalCount = service.getFestivalTotalCount(keyword, startDate, endDate);

			// =========================
			// 페이지 계산
			// =========================
			int totalPage = service.getTotalPage(totalCount, pageSize);

			int startPage = service.getStartPage(page, pageBlock);

			int endPage = service.getEndPage(page, totalPage, pageBlock);

			// =========================
			// JSP 전달
			// =========================
			request.setAttribute("festivalList", festivalList);

			request.setAttribute("keyword", keyword);

			request.setAttribute("startDate", startDate);

			request.setAttribute("endDate", endDate);

			request.setAttribute("currentPage", page);

			request.setAttribute("pageSize", pageSize);

			request.setAttribute("totalCount", totalCount);

			request.setAttribute("totalPage", totalPage);

			request.setAttribute("startPage", startPage);

			request.setAttribute("endPage", endPage);

			request.setAttribute("type", "date");

			// =========================
			// 검색 결과 페이지
			// =========================
			request.getRequestDispatcher("/WEB-INF/views/festival/date_festival_list.jsp").forward(request, response);

			return;
		}

		// =========================
		// 기존 메인 페이지
		// =========================
		FestivalCardService service = new FestivalCardService();

		List<FestivalDto> festivalList = service.getThisMonthFestivalList();

		// =========================
		// 다가오는 축제 목록
		// =========================
		HomeSearchService homeService = new HomeSearchService();

		List<FestivalDto> upcomingFestivalList = homeService.getUpcomingFestivalList();

		// =========================
		// JSP 전달
		// =========================
		request.setAttribute("festivalList", festivalList);

		request.setAttribute("upcomingFestivalList", upcomingFestivalList);

		request.getRequestDispatcher("/WEB-INF/views/home/index.jsp").forward(request, response);
	}
}