package org.example.atumari.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


//LocalDateTime을 화면 출력용 문자열로 변환
//2026-09-22T15:35:42 -> 2026-09-22 15:35
public class DateTimeUtil {

	 // 날짜만
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // 날짜 + 시간
    private static final DateTimeFormatter DATETIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public static String formatDate(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        return dateTime.format(DATE_FORMATTER);
    }


    public static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        return dateTime.format(DATETIME_FORMATTER);
    }
	
}
