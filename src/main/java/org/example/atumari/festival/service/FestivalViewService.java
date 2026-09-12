package org.example.atumari.festival.service;

import org.example.atumari.festival.dao.FestivalDao;
import org.example.atumari.festival.dto.FestivalDto;

public class FestivalViewService {

    private FestivalDao festivalDao;

    public FestivalViewService() {

        festivalDao = new FestivalDao();

    }

    public FestivalDto getFestivalView(int festivalNo) {

        FestivalDto festival =
                festivalDao.getFestivalView(festivalNo);

        if (festival != null) {

            festival.setSeason(
                getSeasonName(festival.getSeason())
            );

        }

        return festival;
    }


    private String getSeasonName(String season) {

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

}