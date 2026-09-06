package org.example.atumari.festival.util;

import java.util.Map;

public class PrefectureDetector {
	
	//위치 문자열을 받아서 동일한 단어가 있으면 지역명으로 값 치환
	public static String findPrefecture(String locationText) {
		
		
		Map<String,String[]> prefectureKeywords = 
				PrefectureKeywords.getPrefectureKeywords();
		
		for (Map.Entry<String, String[]> entry : prefectureKeywords.entrySet()) {
			//entrySet역할 : Map의 Key + Value 한 쌍을 가져옴
			
			String prefectureName = entry.getKey();
			String[] keywords = entry.getValue();
			
			if(containsAny(locationText, keywords)) {
				return prefectureName;
			}
		}
		
		
		return null;
	}
	
	//위치 문자열 널인지 판별후 동일한 문자가 존재하는지 확인 메서드
	private static boolean containsAny(String text, String[] keywords) {
		
		if(text == null) {
			return false;
		}
		
		for(String keyword : keywords) {
			
			if(text.toLowerCase().contains(keyword.toLowerCase())) {
				return true;
				//contains()는 대소문자 구분하기에 소문자로 통일해 비교, 키워드에 영어도 들어있어서 형식통일 필요했음
			}
		}
		
		return false;
		
	}
	


}
