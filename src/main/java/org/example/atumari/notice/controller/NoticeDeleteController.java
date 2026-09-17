package org.example.atumari.notice.controller;

import java.io.IOException;

import org.example.atumari.notice.service.NoticeService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/notice/delete")
public class NoticeDeleteController extends HttpServlet {

    private final NoticeService noticeService = new NoticeService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
            noticeService.deleteNotice(noticeNo);
            response.sendRedirect(request.getContextPath() + "/notice");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
