package org.example.atumari.festival.util;

import java.util.HashMap;
import java.util.Map;

public class PrefectureMap {
	
	//지역명을 지역번호로 치환
	public static Integer getPrefectureNo(String prefectureName) {
		Map<String, Integer> prefectureMap = 
				PrefectureMap.getPrefectureMap();
		
		Integer prefecture_no = prefectureMap.get(prefectureName);
		//Map.get() 자체도 해당 Key가 없으면 null을 반환
		return prefecture_no;
	}
	
	//도도부현의 각 지역의 이름과 번호를 묶어 저장한 Map
	//공식 표준인 JIS 도도부현 코드를 참고해 번호를 매김
	public static Map<String, Integer> getPrefectureMap(){
		Map<String,Integer> prefectureMap = new HashMap<>();
		

		// 北海道 홋카이도(1)
		prefectureMap.put("北海道", 1);       // 홋카이도

		// 東北 도호쿠(6)
		prefectureMap.put("青森県", 2);       // 아오모리현
		prefectureMap.put("岩手県", 3);       // 이와테현
		prefectureMap.put("宮城県", 4);       // 미야기현
		prefectureMap.put("秋田県", 5);       // 아키타현
		prefectureMap.put("山形県", 6);       // 야마가타현
		prefectureMap.put("福島県", 7);       // 후쿠시마현

		// 関東 간토(7)
		prefectureMap.put("茨城県", 8);       // 이바라키현
		prefectureMap.put("栃木県", 9);       // 도치기현
		prefectureMap.put("群馬県", 10);      // 군마현
		prefectureMap.put("埼玉県", 11);      // 사이타마현
		prefectureMap.put("千葉県", 12);      // 지바현
		prefectureMap.put("東京都", 13);      // 도쿄도
		prefectureMap.put("神奈川県", 14);    // 가나가와현

		// 中部 주부(9)
		prefectureMap.put("新潟県", 15);      // 니가타현
		prefectureMap.put("富山県", 16);      // 도야마현
		prefectureMap.put("石川県", 17);      // 이시카와현
		prefectureMap.put("福井県", 18);      // 후쿠이현
		prefectureMap.put("山梨県", 19);      // 야마나시현
		prefectureMap.put("長野県", 20);      // 나가노현
		prefectureMap.put("岐阜県", 21);      // 기후현
		prefectureMap.put("静岡県", 22);      // 시즈오카현
		prefectureMap.put("愛知県", 23);      // 아이치현

		// 近畿 긴키(7)
		prefectureMap.put("三重県", 24);      // 미에현
		prefectureMap.put("滋賀県", 25);      // 시가현
		prefectureMap.put("京都府", 26);      // 교토부
		prefectureMap.put("大阪府", 27);      // 오사카부
		prefectureMap.put("兵庫県", 28);      // 효고현
		prefectureMap.put("奈良県", 29);      // 나라현
		prefectureMap.put("和歌山県", 30);    // 와카야마현

		// 中国 주고쿠(5)
		prefectureMap.put("鳥取県", 31);      // 돗토리현
		prefectureMap.put("島根県", 32);      // 시마네현
		prefectureMap.put("岡山県", 33);      // 오카야마현
		prefectureMap.put("広島県", 34);      // 히로시마현
		prefectureMap.put("山口県", 35);      // 야마구치현

		// 四国 시코쿠(4)
		prefectureMap.put("徳島県", 36);      // 도쿠시마현
		prefectureMap.put("香川県", 37);      // 가가와현
		prefectureMap.put("愛媛県", 38);      // 에히메현
		prefectureMap.put("高知県", 39);      // 고치현

		// 九州・沖縄 규슈・오키나와(8)
		prefectureMap.put("福岡県", 40);      // 후쿠오카현
		prefectureMap.put("佐賀県", 41);      // 사가현
		prefectureMap.put("長崎県", 42);      // 나가사키현
		prefectureMap.put("熊本県", 43);      // 구마모토현
		prefectureMap.put("大分県", 44);      // 오이타현
		prefectureMap.put("宮崎県", 45);      // 미야자키현
		prefectureMap.put("鹿児島県", 46);    // 가고시마현
		prefectureMap.put("沖縄県", 47);      // 오키나와현
		
		return prefectureMap;
	}
}
