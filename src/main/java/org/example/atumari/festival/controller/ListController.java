package org.example.atumari.festival.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/festival/list"})
    

public class ListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String view = "/WEB-INF/views/festival/festival_list.jsp";

        request.getRequestDispatcher(view)
               .forward(request, response);
    }
}