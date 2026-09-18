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
    public List<FestivalDto> searchFestivalList(
            String keyword,
            LocalDate startDate,
            LocalDate endDate) {

        return dao.searchFestivalList(
                keyword,
                startDate,
                endDate
        );
    }
}