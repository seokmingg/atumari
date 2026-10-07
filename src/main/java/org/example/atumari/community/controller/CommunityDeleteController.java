package org.example.atumari.community.controller;

import java.io.IOException;

import org.example.atumari.community.service.CommunityService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/community/delete")
public class CommunityDeleteController extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        CommunityService communityService = new CommunityService();

        // 게시글 번호
        long cmtyNo;
        try {
            cmtyNo = Long.parseLong(
                    request.getParameter("cmtyNo")
            );
        } catch (NumberFormatException e) {
            response.sendRedirect(
                    request.getContextPath() + "/community/list"
            );
            return;
        }
        // 로그인한 사용자
        String sessionEmail =
                (String) request.getSession()
                        .getAttribute("sessionEmail");
        // 로그인하지 않은 경우
        if (sessionEmail == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        int result = communityService.delete(
                        cmtyNo,
                        sessionEmail
                );
        
        if (result == 1) {
            // 삭제 성공
            response.sendRedirect(request.getContextPath()+ "/community");

        } else {

            // 삭제 실패
            response.sendRedirect(
                    request.getContextPath()
                    + "/community/view?cmtyNo="
                    + cmtyNo
                    + "&error=delete"
            );
        }
    }
}
