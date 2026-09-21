package org.example.atumari.notice.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.example.atumari.notice.dto.NoticeFileDto;
import org.example.atumari.notice.service.NoticeService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

@WebServlet("/notice/file/download")
public class NoticeFileDownloadController extends HttpServlet {

    private final NoticeService noticeService = new NoticeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int fileNo = Integer.parseInt(request.getParameter("fileNo"));
            NoticeFileDto file = noticeService.getNoticeFile(fileNo);
            if (file == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            ResponseBytes<GetObjectResponse> object = noticeService.downloadNoticeFile(file);
            String contentType = object.response().contentType();
            response.setContentType(contentType == null ? "application/octet-stream" : contentType);
            response.setContentLength(object.asByteArray().length);
            String encodedName = URLEncoder.encode(
                    file.getOriginalFileName(), StandardCharsets.UTF_8).replace("+", "%20");
            response.setHeader(
                    "Content-Disposition",
                    "attachment; filename*=UTF-8''" + encodedName
            );
            response.getOutputStream().write(object.asByteArray());
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
