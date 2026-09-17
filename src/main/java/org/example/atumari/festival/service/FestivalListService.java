package org.example.atumari.festival.service;

import java.time.LocalDate;
import java.util.List;

import org.example.atumari.festival.dao.FestivalDao;
import org.example.atumari.festival.dto.FestivalDto;
import org.example.atumari.festival.dto.PrefectureDto;

public class FestivalListService {

	private FestivalDao festivalDao;

	public FestivalListService() {

		festivalDao = new FestivalDao();

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


	// ========================================
	// 계절 → 일본어 계절명 변환
	// ========================================

	public String getSeasonName(String season) {

		switch (season) {

		case "봄":
			return "春";

		case "여름":
			return "夏";

		case "가을":
			return "秋";

		case "겨울":
			return "冬";

		default:
			return "";

		}

	}


	// ========================================
	// 축제 목록 조회
	// 지역 / 계절 / 이번 달 통합
	// 페이지네이션 포함
	// ========================================

	public List<FestivalDto> getFestivalList(
			String type,
			String region,
			String season,
			LocalDate firstDay,
			LocalDate nextMonth,
			Integer prefectureNo,
			String select,
			String search,
			int page,
			int pageSize) {


		// 페이지 시작 / 끝 번호

		int start = (page - 1) * pageSize + 1;

		int end = page * pageSize;


		// 지역 코드 → 일본어 지역명

		String regionName = getRegionName(region);


		// DAO 조회

		List<FestivalDto> list =
				festivalDao.getFestivalList(
						type,
						regionName,
						season,
						firstDay,
						nextMonth,
						prefectureNo,
						select,
						search,
						start,
						end
				);


		// 계절명 일본어 변환

		for (FestivalDto festival : list) {

			festival.setSeason(
					getSeasonName(festival.getSeason())
			);

		}


		return list;

	}


	// ========================================
	// 전체 축제 개수
	// 지역 / 계절 / 이번 달 통합
	// ========================================

	public int getFestivalTotalCount(
			String type,
			String region,
			String season,
			LocalDate firstDay,
			LocalDate nextMonth,
			Integer prefectureNo,
			String select,
			String search) {


		// 지역 코드 → 일본어 지역명

		String regionName = getRegionName(region);


		return festivalDao.getFestivalTotalCount(
				type,
				regionName,
				season,
				firstDay,
				nextMonth,
				prefectureNo,
				select,
				search
		);
		

	}
	


	// ========================================
	// 전체 페이지 수
	// ========================================

	public int getTotalPage(
			int totalCount,
			int pageSize) {

		return (int) Math.ceil(
				(double) totalCount / pageSize
		);

	}
	// ========================================
	// 페이지 번호 시작
	// 5개 단위로 페이지 번호 표시
	// ========================================

	public int getStartPage(
	        int currentPage,
	        int pageBlock) {

	    return ((currentPage - 1) / pageBlock)
	            * pageBlock + 1;

	}


	// ========================================
	// 페이지 번호 끝
	// 5개 단위로 페이지 번호 표시
	// ========================================

	public int getEndPage(
	        int currentPage,
	        int totalPage,
	        int pageBlock) {

	    int endPage =
	            getStartPage(currentPage, pageBlock)
	            + pageBlock - 1;

	    if (endPage > totalPage) {

	        endPage = totalPage;

	    }

	    return endPage;

	}


	// ========================================
	// 지역별 도도부현 목록 조회
	// ========================================

	public List<PrefectureDto> getPrefectureList(
			String region) {


		String regionName =
				getRegionName(region);


		return festivalDao.getPrefectureList(
				regionName
		);

	}

}
