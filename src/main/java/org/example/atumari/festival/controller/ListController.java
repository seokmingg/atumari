package org.example.atumari.festival.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.atumari.festival.dto.FestivalDto;
import org.example.atumari.festival.dto.PrefectureDto;
import org.example.atumari.festival.service.FestivalListService;

@WebServlet("/festival/list")
public class ListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ========================================
		// 1. 지역 코드
		// ========================================

		String region = request.getParameter("region");

		if (region == null || region.isEmpty()) {
			region = "hokkaido";
		}

		// ========================================
		// 2. 도도부현
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
		//  검색
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

		// ========================================
		// 5. Service
		// ========================================

		FestivalListService service = new FestivalListService();

		// ========================================
		// 6. 축제 목록
		// ========================================

		List<FestivalDto> festivalList = service.getFestivalList(region, prefectureNo,
																select, search,page, pageSize);

		// ========================================
		// 7. 전체 축제 개수
		// ========================================

		int totalCount = service.getFestivalTotalCount(region, prefectureNo, select, search);

		// ========================================
		// 8. 전체 페이지 수
		// ========================================

		int totalPage = service.getTotalPage(region, prefectureNo, select, search, pageSize);

		// ========================================
		// 9. 도도부현 목록
		// ========================================

		List<PrefectureDto> prefectureList = service.getPrefectureList(region);
		
		
		// ========================================
		// 10. JSP에 값 전달
		// ========================================

		request.setAttribute("region", region);
		request.setAttribute("regionName", service.getRegionName(region));

		request.setAttribute("festivalList", festivalList);
		request.setAttribute("prefectureList", prefectureList);

		request.setAttribute("currentPage", page);
		request.setAttribute("pageSize", pageSize);

		request.setAttribute("totalCount", totalCount);
		request.setAttribute("totalPage", totalPage);
		
		request.setAttribute("select", select);
		request.setAttribute("search", search);

		// ========================================
		// 11. JSP 이동
		// ========================================

		request.getRequestDispatcher("/WEB-INF/views/festival/festival_list.jsp")
		.forward(request, response);
	}
}