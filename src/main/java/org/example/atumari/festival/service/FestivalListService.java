package org.example.atumari.festival.service;

import java.util.List;

import org.example.atumari.festival.dao.FestivalDao;
import org.example.atumari.festival.dto.FestivalDto;

public class FestivalListService {

    private FestivalDao festivalDao;

    public FestivalListService() {
        festivalDao = new FestivalDao();
    }

    // 지역 코드 → 일본어 지역명 변환
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
    
 // 계절 → 일본어 계절명 변환
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

    // 지역별 축제 목록 조회
    public List<FestivalDto> getFestivalList(String region) {

        String regionName = getRegionName(region);

        List<FestivalDto> list = festivalDao.getFestivalList(regionName);
                
        for (FestivalDto festival : list) {

            festival.setSeason(
                    getSeasonName(festival.getSeason())
            );
        }

        return list;
        
    }
}