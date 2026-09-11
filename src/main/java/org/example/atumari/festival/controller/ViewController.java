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
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String festivalNo =
                request.getParameter("festival_no");

        if (festivalNo == null || festivalNo.isEmpty()) {

            response.sendRedirect(
                request.getContextPath() + "/festival/list"
            );

            return;
        }

        int festivalNoValue =
                Integer.parseInt(festivalNo);

        FestivalViewService service =
                new FestivalViewService();

        FestivalDto festival =
                service.getFestivalView(festivalNoValue);

        request.setAttribute("festival", festival);

        request.getRequestDispatcher(
            "/WEB-INF/views/festival/festival_view.jsp"
        ).forward(request, response);
    }

}