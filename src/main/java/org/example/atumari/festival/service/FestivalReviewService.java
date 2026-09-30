package org.example.atumari.festival.service;

import java.util.List;

import org.example.atumari.festival.dao.ReviewDao;
import org.example.atumari.festival.dto.ReviewDto;

public class FestivalReviewService {

	private ReviewDao reviewDao;

	public FestivalReviewService() {

		reviewDao = new ReviewDao();

	}

	// 페이징 리뷰 리스트
	public List<ReviewDto> getReviewList(Integer festivalNo, int page, int pageSize) {

		return reviewDao.getReviewList(festivalNo, page, pageSize);
	}

	// 리뷰 전체 개수
	public int getReviewCount(Integer festivalNo) {

		return reviewDao.getReviewCount(festivalNo);
	}

	// 리뷰 작성
	public boolean writeReview(ReviewDto review) {

		return reviewDao.writeReview(review);

	}

	// 리뷰 수정
	public boolean updateReview(ReviewDto review) {

		return reviewDao.updateReview(review);
	}

	// 리뷰 삭제
	public boolean deleteReview(ReviewDto review) {

		return reviewDao.deleteReview(review);
	}
	
	// 관리자 리뷰 수정
	public boolean updateReviewByAdmin(ReviewDto review) {
	    return reviewDao.updateReviewByAdmin(review);
	}

	// 관리자 리뷰 삭제
	public boolean deleteReviewByAdmin(ReviewDto review) {
	    return reviewDao.deleteReviewByAdmin(review);
	}

}
