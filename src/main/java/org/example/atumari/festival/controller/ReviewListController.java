package org.example.atumari.festival.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.atumari.festival.dto.ReviewDto;
import org.example.atumari.festival.service.FestivalReviewService;

import com.nimbusds.jose.shaded.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/festival/review/list")
public class ReviewListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int festivalNo = Integer.parseInt(request.getParameter("festival_no"));

		int page = Integer.parseInt(request.getParameter("page"));

		int pageSize = 10;

		FestivalReviewService service = new FestivalReviewService();

		List<ReviewDto> reviewList = service.getReviewList(festivalNo, page, pageSize);

		int totalCount = service.getReviewCount(festivalNo);

		boolean hasNext = page * pageSize < totalCount;

		// JSON으로 변환할 리뷰 데이터
		List<Map<String, Object>> reviews = new ArrayList<>();

		for (ReviewDto review : reviewList) {

			Map<String, Object> reviewData = new HashMap<>();

			reviewData.put("review_no", review.getReview_no());

			reviewData.put("festival_no", review.getFestival_no());

			reviewData.put("member_id", review.getMember_id());

			reviewData.put("name", review.getName());

			reviewData.put("content", review.getContent());

			// LocalDateTime → String
			if (review.getCreated_date() != null) {

				reviewData.put("created_date", review.getCreated_date().toString());

			} else {

				reviewData.put("created_date", null);
			}

			if (review.getUpdated_date() != null) {

				reviewData.put("updated_date", review.getUpdated_date().toString());

			} else {

				reviewData.put("updated_date", null);
			}

			reviews.add(reviewData);
		}

		// 최종 JSON 데이터
		Map<String, Object> result = new HashMap<>();

		result.put("reviews", reviews);
		result.put("page", page);
		result.put("hasNext", hasNext);

		// JSON 응답
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		Gson gson = new Gson();

		response.getWriter().write(gson.toJson(result));
	}
}