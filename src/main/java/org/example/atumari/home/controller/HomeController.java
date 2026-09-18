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


            HomeSearchService service = new HomeSearchService();

            List<FestivalDto> festivalList =
                    service.searchFestivalList(
                            keyword,
                            startDate,
                            endDate
                    );


            request.setAttribute("festivalList", festivalList);

            request.setAttribute("keyword", keyword);

            request.setAttribute("startDate", startDate);

            request.setAttribute("endDate", endDate);


            request.getRequestDispatcher(
                    "/WEB-INF/views/festival/date_festival_list.jsp"
            ).forward(request, response);

            return;
        }


        // =========================
        // 기존 메인 페이지
        // =========================

        FestivalCardService service = new FestivalCardService();

        List<FestivalDto> festivalList =
                service.getThisMonthFestivalList();

        request.setAttribute("festivalList", festivalList);

        request.getRequestDispatcher(
                "/WEB-INF/views/home/index.jsp"
        ).forward(request, response);

    }

}