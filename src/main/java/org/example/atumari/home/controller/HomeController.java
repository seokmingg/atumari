package org.example.atumari.home.controller;

import java.io.IOException;
import java.util.List;

import org.example.atumari.festival.dto.FestivalDto;
import org.example.atumari.festival.service.FestivalCardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({ "", "/home" })
public class HomeController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		FestivalCardService service = new FestivalCardService();

		List<FestivalDto> festivalList = service.getThisMonthFestivalList();

		request.setAttribute("festivalList", festivalList);

		request.getRequestDispatcher("/WEB-INF/views/home/index.jsp").forward(request, response);
	}
}