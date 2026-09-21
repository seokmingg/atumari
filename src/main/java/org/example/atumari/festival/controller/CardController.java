package org.example.atumari.festival.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.example.atumari.festival.dto.FestivalDto;
import org.example.atumari.festival.service.FestivalCardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({ "/festival/region/card", "/festival/season/card", "/festival/month/card" })
public class CardController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getServletPath();

		String view;

		// ========================================
		// 지역 카드
		// ========================================

		if ("/festival/region/card".equals(path)) {

			view = "/WEB-INF/views/festival/region_card_list.jsp";

		}

		// ========================================
		// 계절 카드
		// ========================================

		else if ("/festival/season/card".equals(path)) {

			view = "/WEB-INF/views/festival/season_card_list.jsp";

		}

		// ========================================
		// 이번 달 축제 카드
		// ========================================

		else if ("/festival/month/card".equals(path)) {

		    view = "/WEB-INF/views/festival/month_card_list.jsp";

		    FestivalCardService service = new FestivalCardService();

		    int year = LocalDate.now().getYear();
		    int month = LocalDate.now().getMonthValue();

		    List<FestivalDto> festivalList =
		            service.getThisMonthFestivalList();

		    request.setAttribute("festivalList", festivalList);
		    request.setAttribute("year", year);
		    request.setAttribute("month", month);
		}

		// ========================================
		// 잘못된 경로
		// ========================================

		else {

			response.sendError(HttpServletResponse.SC_NOT_FOUND);

			return;
		}

		// ========================================
		// JSP 이동
		// ========================================

		request.getRequestDispatcher(view).forward(request, response);
	}}



