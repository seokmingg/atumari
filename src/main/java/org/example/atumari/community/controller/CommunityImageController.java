package org.example.atumari.community.controller;

import java.io.IOException;

import org.example.atumari.community.dto.CommunityFileDto;
import org.example.atumari.community.service.CommunityService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

@WebServlet("/community/file/image")
public class CommunityImageController extends HttpServlet {

    private final CommunityService service = new CommunityService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            long fileNo = Long.parseLong(
                    request.getParameter("fileNo"));

            CommunityFileDto file =
                    service.getCommunityFile(fileNo);

            if (file == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            ResponseBytes<GetObjectResponse> data =
                    service.downloadCommunityFile(file);

            String contentType = data.response().contentType();

            if (contentType == null ||
                !contentType.toLowerCase().startsWith("image/")) {
                response.sendError(
                        HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
                return;
            }

            response.setContentType(contentType);
            response.setContentLength(data.asByteArray().length);
            response.setHeader("X-Content-Type-Options", "nosniff");
            response.getOutputStream().write(data.asByteArray());

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}