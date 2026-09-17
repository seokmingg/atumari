package org.example.atumari.festival.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.example.atumari.festival.dto.FestivalDto;
import org.example.atumari.festival.dto.PrefectureDto;
import org.example.atumari.festival.service.FestivalListService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/festival/list")
public class ListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ========================================
		// 1. 구분값
		// ========================================

		String type = request.getParameter("type");

		if (type == null || type.isEmpty()) {

			type = "region";

		}

		// ========================================
		// 2. 검색
		// ========================================

		String select = request.getParameter("select");

		String search = request.getParameter("search");

		// ========================================
		// 3. 페이지
		// ========================================

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

		// ========================================
		// 4. 페이지당 축제 개수
		// ========================================

		int pageSize = 5;
		
		// 페이지 번호 표시 개수
		int pageBlock = 5;

		// ========================================
		// 5. Service
		// ========================================

		FestivalListService service = new FestivalListService();

		// ========================================
		// 6. 공통 변수
		// ========================================

		List<FestivalDto> festivalList = null;

		List<PrefectureDto> prefectureList = null;

		int totalCount = 0;

		int totalPage = 0;

		// ========================================
		// 7. 지역
		// ========================================

		String region = request.getParameter("region");

		if (region == null || region.isEmpty()) {

			region = "hokkaido";

		}

		// ========================================
		// 8. 도도부현
		// ========================================

		String prefectureNoParam = request.getParameter("prefecture_no");

		Integer prefectureNo = null;

		if (prefectureNoParam != null && !prefectureNoParam.isEmpty()) {

			try {

				prefectureNo = Integer.parseInt(prefectureNoParam);

			} catch (NumberFormatException e) {

				prefectureNo = null;

			}
		}

		// ========================================
		// 9. 계절
		// ========================================

		String season = request.getParameter("season");

		if (season == null || season.isEmpty()) {

			season = "봄";

		}

		// ========================================
		// 10. 월
		// ========================================

		String monthParam = request.getParameter("month");

		int month = LocalDate.now().getMonthValue();

		if (monthParam != null && !monthParam.isEmpty()) {

			try {

				month = Integer.parseInt(monthParam);

			} catch (NumberFormatException e) {

				month = LocalDate.now().getMonthValue();

			}
		}

		// 월 범위 확인

		if (month < 1 || month > 12) {

			month = LocalDate.now().getMonthValue();

		}

		// ========================================
		// 11. 월 날짜 계산
		// ========================================

		LocalDate firstDay = null;

		LocalDate nextMonth = null;

		if ("month".equals(type)) {

			int year = LocalDate.now().getYear();

			firstDay = LocalDate.of(year, month, 1);

			nextMonth = firstDay.plusMonths(1);

		}

		// ========================================
		// 12. 잘못된 type 확인
		// ========================================

		if (!"region".equals(type) && !"season".equals(type) && !"month".equals(type)) {

			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 리스트 구분값입니다.");

			return;

		}

		// ========================================
		// 13. 축제 목록
		// 지역 / 계절 / 월 통합
		// ========================================

		festivalList = service.getFestivalList(type, region, season, firstDay, nextMonth, prefectureNo, select, search,
				page, pageSize);

		// ========================================
		// 14. 전체 개수
		// 지역 / 계절 / 월 통합
		// ========================================

		totalCount = service.getFestivalTotalCount(type, region, season, firstDay, nextMonth, prefectureNo, select,
				search);

		// ========================================
		// 15. 전체 페이지
		// ========================================

		totalPage = service.getTotalPage(totalCount, pageSize);
		
		// ========================================
		// 15-1. 페이지 번호 범위
		// ========================================

		int startPage =
		        service.getStartPage(page, pageBlock);

		int endPage =
		        service.getEndPage(page, totalPage, pageBlock);

		// ========================================
		// 16. 지역 데이터
		// ========================================

		if ("region".equals(type)) {

			prefectureList = service.getPrefectureList(region);

			request.setAttribute("region", region);

			request.setAttribute("regionName", service.getRegionName(region));

			request.setAttribute("prefectureList", prefectureList);

		}

		// ========================================
		// 17. 계절 데이터
		// ========================================

		else if ("season".equals(type)) {

			request.setAttribute("season", service.getSeasonName(season));

		}

		// ========================================
		// 18. 월 데이터
		// ========================================

		else if ("month".equals(type)) {

			request.setAttribute("month", month);

		}

		// ========================================
		// 19. 공통 JSP 전달
		// ========================================

		request.setAttribute("type", type);

		request.setAttribute("festivalList", festivalList);

		request.setAttribute("currentPage", page);

		request.setAttribute("pageSize", pageSize);

		request.setAttribute("totalCount", totalCount);

		request.setAttribute("totalPage", totalPage);
		
		request.setAttribute("startPage", startPage);

		request.setAttribute("endPage", endPage);

		request.setAttribute("select", select);

		request.setAttribute("search", search);

		// ========================================
		// 20. JSP 이동
		// ========================================

		if ("region".equals(type)) {

			request.getRequestDispatcher("/WEB-INF/views/festival/region_festival_list.jsp").forward(request, response);

		}

		else if ("season".equals(type)) {

			request.getRequestDispatcher("/WEB-INF/views/festival/season_festival_list.jsp").forward(request, response);

		}

		else if ("month".equals(type)) {

			request.getRequestDispatcher("/WEB-INF/views/festival/month_festival_list.jsp").forward(request, response);

		}

	}

}
