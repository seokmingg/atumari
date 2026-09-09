package org.example.atumari.festival.controller;

import java.io.IOException;
import java.util.List;

import org.example.atumari.festival.dto.FestivalDto;
import org.example.atumari.festival.service.FestivalListService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/festival/list")
public class ListController extends HttpServlet {

    private FestivalListService FestivalListService;

    @Override
    public void init() throws ServletException {
    	FestivalListService = new FestivalListService();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String region = request.getParameter("region");

        // Service를 통해 지역명과 축제 목록 조회
        String regionName =
        		FestivalListService.getRegionName(region);

        List<FestivalDto> list =
        		FestivalListService.getFestivalList(region);

        
        request.setAttribute("regionName", regionName);
        request.setAttribute("festivalList", list);

        request.getRequestDispatcher(
                "/WEB-INF/views/festival/festival_list.jsp"
        ).forward(request, response);
    }
}