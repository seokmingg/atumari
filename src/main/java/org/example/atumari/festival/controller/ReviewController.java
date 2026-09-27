package org.example.atumari.festival.controller;

import java.io.IOException;

import org.example.atumari.festival.dto.ReviewDto;
import org.example.atumari.festival.service.FestivalReviewService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/festival/review")
public class ReviewController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        // 작성
        if ("write".equals(action)) {

            String festivalNo = request.getParameter("festival_no");
            String content = request.getParameter("content");

            String type = request.getParameter("type");
            String keyword = request.getParameter("keyword");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");

            ReviewDto review = new ReviewDto();

            review.setFestival_no(Integer.parseInt(festivalNo));
            review.setContent(content);

            HttpSession session = request.getSession();

            Long memberId = (Long) session.getAttribute("sessionId");

            review.setMember_id(memberId);

            FestivalReviewService rservice = new FestivalReviewService();

            boolean result = rservice.writeReview(review);

            if (result) {
                session.setAttribute("msg", "レビューを登録しました。");
            } else {
                session.setAttribute("msg", "レビューの登録に失敗しました。");
            }

            response.sendRedirect(
                request.getContextPath()
                + "/festival/view?festival_no=" + festivalNo
                + "&type=" + type
                + "&keyword=" + keyword
                + "&startDate=" + startDate
                + "&endDate=" + endDate
            );

            return;
        }

        // 수정
        else if ("update".equals(action)) {

            String festivalNo = request.getParameter("festival_no");
            String reviewNo = request.getParameter("review_no");
            String content = request.getParameter("content");

            String type = request.getParameter("type");
            String keyword = request.getParameter("keyword");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");

            HttpSession session = request.getSession();

            Long memberId = (Long) session.getAttribute("sessionId");

            // 현재 로그인한 사용자의 권한
            String sessionLevel =
                    (String) session.getAttribute("sessionLevel");

            ReviewDto review = new ReviewDto();

            review.setReview_no(Long.parseLong(reviewNo));
            review.setFestival_no(Integer.parseInt(festivalNo));
            review.setMember_id(memberId);
            review.setContent(content);

            FestivalReviewService rservice =
                    new FestivalReviewService();

            boolean result;

            // 관리자라면 모든 리뷰 수정 가능
            if ("admin".equals(sessionLevel)) {

                result = rservice.updateReviewByAdmin(review);

            } else {

                // 일반 사용자는 자신의 리뷰만 수정
                result = rservice.updateReview(review);
            }

            if (result) {
                session.setAttribute("msg", "レビューを修正しました。");
            } else {
                session.setAttribute("msg", "レビューの修正に失敗しました。");
            }

            response.sendRedirect(
                request.getContextPath()
                + "/festival/view?festival_no=" + festivalNo
                + "&type=" + type
                + "&keyword=" + keyword
                + "&startDate=" + startDate
                + "&endDate=" + endDate
            );

            return;
        }

        // 삭제
        else if ("delete".equals(action)) {

            String festivalNo = request.getParameter("festival_no");
            String reviewNo = request.getParameter("review_no");

            String type = request.getParameter("type");
            String keyword = request.getParameter("keyword");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");

            HttpSession session = request.getSession();

            Long memberId = (Long) session.getAttribute("sessionId");

            // 현재 로그인한 사용자의 권한
            String sessionLevel =
                    (String) session.getAttribute("sessionLevel");

            ReviewDto review = new ReviewDto();

            review.setReview_no(Long.parseLong(reviewNo));
            review.setMember_id(memberId);

            FestivalReviewService rservice =
                    new FestivalReviewService();

            boolean result;

            // 관리자라면 모든 리뷰 삭제 가능
            if ("admin".equals(sessionLevel)) {

                result = rservice.deleteReviewByAdmin(review);

            } else {

                // 일반 사용자는 자신의 리뷰만 삭제
                result = rservice.deleteReview(review);
            }

            if (result) {
                session.setAttribute("msg", "レビューを削除しました。");
            } else {
                session.setAttribute("msg", "レビューの削除に失敗しました。");
            }

            response.sendRedirect(
                request.getContextPath()
                + "/festival/view?festival_no=" + festivalNo
                + "&type=" + type
                + "&keyword=" + keyword
                + "&startDate=" + startDate
                + "&endDate=" + endDate
            );

            return;
        }
    }
}