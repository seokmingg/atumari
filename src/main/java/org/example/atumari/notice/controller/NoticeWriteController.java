package org.example.atumari.notice.controller;

import java.io.IOException;

import org.example.atumari.notice.service.NoticeService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/notice/write")
public class NoticeWriteController extends HttpServlet {

    private final NoticeService noticeService = new NoticeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!isAdmin(request)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        request.getRequestDispatcher("/WEB-INF/views/notice/notice_write.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!isAdmin(request)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String authorEmail = (String) request.getSession().getAttribute("sessionEmail");

        try {
            noticeService.createNotice(title, content, authorEmail);
            response.sendRedirect(request.getContextPath() + "/notice");
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("title", title);
            request.setAttribute("content", content);
            request.getRequestDispatcher("/WEB-INF/views/notice/notice_write.jsp")
                    .forward(request, response);
        }
    }

    private boolean isAdmin(HttpServletRequest request) {
        Object sessionLevel = request.getSession(false) == null
                ? null
                : request.getSession(false).getAttribute("sessionLevel");
        return "admin".equals(sessionLevel);
    }
}
