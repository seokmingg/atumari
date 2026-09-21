package org.example.atumari.notice.controller;

import java.io.IOException;

import org.example.atumari.notice.dto.NoticeDto;
import org.example.atumari.notice.service.NoticeService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/notice/edit")
public class NoticeEditController extends HttpServlet {

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
            request.getRequestDispatcher("/WEB-INF/views/notice/notice_edit.jsp")
                    .forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");

            noticeService.updateNotice(noticeNo, title, content);
            response.sendRedirect(
                    request.getContextPath() + "/notice/view?noticeNo=" + noticeNo);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            NoticeDto notice = new NoticeDto();
            notice.setNoticeNo(parseNoticeNo(request.getParameter("noticeNo")));
            notice.setTitle(request.getParameter("title"));
            notice.setContent(request.getParameter("content"));

            request.setAttribute("notice", notice);
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/notice/notice_edit.jsp")
                    .forward(request, response);
        }
    }

    private int parseNoticeNo(String noticeNo) {
        try {
            return Integer.parseInt(noticeNo);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
