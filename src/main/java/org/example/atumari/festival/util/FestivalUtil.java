package org.example.atumari.festival.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.databind.JsonNode;

public class FestivalUtil {
//매개변수와 지역변수만 사용하는 메서드 → static 가능
//인스턴스 변수를 직접 사용하는 메서드 → 일반적으로 static으로 만들 수 없음
		
		//데이터가 널인지 확인
		public static String safeText(String value) {
		    return value == null ? "" : value;
		}
		
		//필드 존재 및 값이 null체크 int
		public static Integer getIntOrNull(JsonNode node, String fieldName) {
			
			JsonNode valueNode = node.path(fieldName);
			if(!valueNode.isMissingNode() && !valueNode.isNull()) {
				return valueNode.asInt();
			}
			
			return null;
		}
		
		//필드 존재 및 값이 null체크 String
		public static String getStringOrNull(JsonNode node, String fieldName) {
			
			JsonNode valueNode = node.path(fieldName);
			if(!valueNode.isMissingNode() && !valueNode.isNull()) {
				return valueNode.asText();
			}
			
			return null;
		}
		
		//필드 존재 및 값이 null체크 Boolean
		public static Boolean getBooleanOrNull(JsonNode node, String fieldName) {
			
			JsonNode valueNode = node.path(fieldName);
			
			if(!valueNode.isMissingNode() && !valueNode.isNull()) {
				return valueNode.asBoolean();
			}
			
			return null;
		}
		
		
		//계절반환
		public static String getSeasonByDate(LocalDateTime startDateTime) {
			
			if(startDateTime == null) {
				return null;
			}// startDateTime이 null인 경우 getMonthValue() 호출 시
			// NullPointerException이 발생하므로 먼저 null 체크
			int startMonth = startDateTime.getMonthValue();
			
			String season= null;
			
			if(startMonth>=3 && startMonth <=5) {
				season="봄";
			}else if(startMonth>=6 && startMonth <=8) {
				season="여름";
			}else if(startMonth>=9 && startMonth <=11) {
				season="가을";
			}else {
				season="겨울";
			}
			
			
			return season;
		}
		
		//Json 데이터 -> LocalDateTime
		public static LocalDateTime getLocalDateTimeOrNull(JsonNode node, String fieldName) {

		    JsonNode valueNode = node.path(fieldName);

		    if (valueNode.isMissingNode() || valueNode.isNull()) {
		        return null;
		    }

		    String value = valueNode.asText();

		    if (value == null || value.isBlank()) {
		        return null;
		    }

		    try {

		        // 2026-11-01 처럼 날짜만 있는 경우
		        if (value.length() == 10) {
		            LocalDate date = LocalDate.parse(value);

		            // 시간 정보가 없으므로 00:00:00으로 처리
		            return date.atStartOfDay();
		        }

		        // 2026-11-01T18:00:00+09:00 같은 경우
		        OffsetDateTime offsetDateTime = OffsetDateTime.parse(value);

		        return offsetDateTime.toLocalDateTime();

		    } catch (DateTimeParseException e) {

		        System.out.println("날짜 변환 실패: " + value);
		        return null;
		    }
		}
}
