package org.example.atumari.notice.controller;

import java.io.IOException;
import java.util.List;

import org.example.atumari.notice.service.NoticeService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/notice/write")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 10L * 1024 * 1024,
        maxRequestSize = 30L * 1024 * 1024
)
public class NoticeWriteController extends HttpServlet {

    private final NoticeService noticeService = new NoticeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/notice/notice_write.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String authorEmail = (String) request.getSession().getAttribute("sessionEmail");

        try {
            List<Part> files = request.getParts().stream()
                    .filter(part -> "files".equals(part.getName()))
                    .filter(part -> part.getSubmittedFileName() != null)
                    .filter(part -> !part.getSubmittedFileName().isBlank())
                    .toList();
            noticeService.createNotice(title, content, authorEmail, files);
            response.sendRedirect(request.getContextPath() + "/notice");
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("title", title);
            request.setAttribute("content", content);
            request.getRequestDispatcher("/WEB-INF/views/notice/notice_write.jsp")
                    .forward(request, response);
        }
    }

}
