package org.example.atumari.home.service;

import java.time.LocalDate;
import java.util.List;

import org.example.atumari.festival.dto.FestivalDto;
import org.example.atumari.home.dao.HomeDao;

public class HomeSearchService {

	private HomeDao dao;

	public HomeSearchService() {
		dao = new HomeDao();
	}

	// 인덱스 검색
	public List<FestivalDto> searchFestivalList(String keyword, LocalDate startDate, LocalDate endDate, int start,
			int pageSize) {

		List<FestivalDto> list = dao.searchFestivalList(keyword, startDate, endDate, start, pageSize);

		// 계절 한국어 → 일본어 변환
		for (FestivalDto festival : list) {

			festival.setSeason(getSeasonName(festival.getSeason()));
		}

		return list;
	}

	// ========================================
	// 계절 한국어 → 일본어 변환
	// ========================================

	public String getSeasonName(String season) {

		if ("봄".equals(season)) {

			return "春";

		} else if ("여름".equals(season)) {

			return "夏";

		} else if ("가을".equals(season)) {

			return "秋";

		} else if ("겨울".equals(season)) {

			return "冬";
		}

		return season;
	}

	// ========================================
	// 지역 코드 → 일본어 지역명 변환
	// ========================================

	public String getRegionName(String region) {

		switch (region) {

		case "hokkaido":
			return "北海道";

		case "tohoku":
			return "東北";

		case "kanto":
			return "関東";

		case "chubu":
			return "中部";

		case "kinki":
			return "近畿";

		case "chugoku":
			return "中国";

		case "shikoku":
			return "四国";

		case "kyushu-okinawa":
			return "九州";

		default:
			return "";
		}
	}

	// 인덱스 검색 전체 개수
	public int getFestivalTotalCount(String keyword, LocalDate startDate, LocalDate endDate) {

		return dao.getFestivalTotalCount(keyword, startDate, endDate);
	}

	// 전체 페이지 수
	public int getTotalPage(int totalCount, int pageSize) {

		return (int) Math.ceil((double) totalCount / pageSize);
	}

	// 페이지 블록 시작
	public int getStartPage(int currentPage, int pageBlock) {

		return ((currentPage - 1) / pageBlock) * pageBlock + 1;
	}

	// 페이지 블록 끝
	public int getEndPage(int currentPage, int totalPage, int pageBlock) {

		int endPage = getStartPage(currentPage, pageBlock) + pageBlock - 1;

		return Math.min(endPage, totalPage);
	}

	// ========================================
	// 곧 개최되는 축제 목록
	// ========================================

	public List<FestivalDto> getUpcomingFestivalList() {

		List<FestivalDto> festivalList = dao.getUpcomingFestivalList();

		for (FestivalDto festival : festivalList) {

			festival.setSeason(getSeasonName(festival.getSeason()));
		}

		return festivalList;
	}
}