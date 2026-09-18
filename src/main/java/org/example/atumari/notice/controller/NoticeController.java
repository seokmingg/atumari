package org.example.atumari.notice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.example.atumari.notice.dto.NoticeListPageDto;
import org.example.atumari.notice.service.NoticeService;

@WebServlet("/notice")
public class NoticeController extends HttpServlet {

    private final NoticeService noticeService = new NoticeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int currentPage = parsePage(request.getParameter("page"));
        String searchType = request.getParameter("searchType");
        String keyword = request.getParameter("keyword");

        NoticeListPageDto noticePage = noticeService.getNoticePage(
                currentPage, searchType, keyword);

        request.setAttribute("noticePage", noticePage);

        request.getRequestDispatcher("/WEB-INF/views/notice/notice_list.jsp")
                .forward(request, response);
    }

    private int parsePage(String pageValue) {
        try {
            return Math.max(1, Integer.parseInt(pageValue));
        } catch (NumberFormatException e) {
            return 1;
        }
    }
}
