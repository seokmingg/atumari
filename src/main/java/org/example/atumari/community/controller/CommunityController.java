package org.example.atumari.community.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/community", "/community/view"})
public class CommunityController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String view = "/community/view".equals(request.getServletPath())
                ? "/WEB-INF/views/community/view.jsp"
                : "/WEB-INF/views/community/list.jsp";

        request.getRequestDispatcher(view)
                .forward(request, response);
    }
}
