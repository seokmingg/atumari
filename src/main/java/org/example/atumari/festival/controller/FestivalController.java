package org.example.atumari.festival.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({
    "/card-list",
    "/festival-list",
    "/festival-view"
})
public class FestivalController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String servletPath = request.getServletPath();
        String view;

        switch (servletPath) {

            case "/card-list":
                view = "/WEB-INF/views/festival/card_list.jsp";
                break;

            case "/festival-list":
                String region = request.getParameter("region");
                request.setAttribute("region", region);

                view = "/WEB-INF/views/festival/festival_list.jsp";
                break;

            case "/festival-view":
                view = "/WEB-INF/views/festival/festival_view.jsp";
                break;

            default:
                view = "/WEB-INF/views/festival/card_list.jsp";
                break;
        }

        request.getRequestDispatcher(view)
               .forward(request, response);
    }
}