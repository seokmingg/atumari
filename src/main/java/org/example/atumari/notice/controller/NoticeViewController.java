package org.example.atumari.notice.controller;

import java.io.IOException;

import org.example.atumari.notice.dto.NoticeDto;
import org.example.atumari.notice.service.NoticeService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/notice/view")
public class NoticeViewController extends HttpServlet {

    private final NoticeService noticeService = new NoticeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
            NoticeDto notice = noticeService.getNotice(noticeNo);

            if (notice == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            request.setAttribute("notice", notice);
            request.getRequestDispatcher("/WEB-INF/views/notice/notice_view.jsp")
                    .forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
