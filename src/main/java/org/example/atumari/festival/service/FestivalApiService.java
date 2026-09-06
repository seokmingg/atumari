package org.example.atumari.festival.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.example.atumari.common.database.DBConnection;
import org.example.atumari.festival.dao.FestivalDao;
import org.example.atumari.festival.dto.FestivalDto;
import org.example.atumari.festival.util.FestivalUtil;
import org.example.atumari.festival.util.PrefectureDetector;
import org.example.atumari.festival.util.PrefectureMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FestivalApiService {
	public static void main(String[] args) {
		
		String apiUrl = "https://owned-media-production.up.railway.app/api/sites/event/articles/?lang=ja&page_size=300&type=event&category=festivals";
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(apiUrl))
				.GET()
				.build();
		
		HttpResponse<String> response;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			String json = response.body();
			
			
			ObjectMapper mapper = new ObjectMapper();
			try {
				List<FestivalDto> dtos = new ArrayList<>();
				
				JsonNode root = mapper.readTree(json);
				
				
				FestivalDao dao = new FestivalDao();
				//축제 고유번호, 이름, 설명
				JsonNode results = root.get("results");
				for(JsonNode festival : results) {
					
					//축제 API ID
					Integer festival_id = FestivalUtil.getIntOrNull(festival, "id");
					
					
					//축제명
					String festival_name = FestivalUtil.getStringOrNull(festival, "title");
			
					
					//축제 설명
					String summary = FestivalUtil.getStringOrNull(festival, "summary");
				
				
					JsonNode structured = festival.path("structured");
					
					//도시
					String city = FestivalUtil.getStringOrNull(structured,"city");
					
					JsonNode image = structured.path("image");
					//대표 이미지
					String image_url = FestivalUtil.getStringOrNull(image, "url");
					//이미지 원본 출처
					String image_source = FestivalUtil.getStringOrNull(image, "source");
					
					
					JsonNode venue = structured.path("venue");
					//개최장소
					String venue_name = FestivalUtil.getStringOrNull(venue, "name");
					//주소
					String venue_address = FestivalUtil.getStringOrNull(venue, "address");
					
					//접근방법, 교통편
					String access_info = FestivalUtil.getStringOrNull(structured, "access");
					
					
					JsonNode ticket = structured.path("ticket");
					//가격 무료 여부
					Boolean price_free = FestivalUtil.getBooleanOrNull(ticket, "free");
					//boolean이 아닌 참조형을 쓴 이유? 만일 null이 들어가있을 경우 false로 체크되어 정보없음(null)-> 유료(false)로 표시될 수 있음
					
					
					JsonNode datetime = structured.path("datetime");
					//행사 일시
					LocalDateTime startDateTime =
					        FestivalUtil.getLocalDateTimeOrNull(datetime, "start");

					LocalDateTime endDateTime =
					        FestivalUtil.getLocalDateTimeOrNull(datetime, "end");
					//String start_dateTime = structured.get("datetime").get("start").asText();
					//같은 중첩객체에 여러 값을 꺼내는 경우는 중각 객체를 변수에 담는게 좋음, 한 객체에서 값을 하나만 꺼낼 경우 그냥 바로 위처럼 한줄로 작성도 Ok 
					
					//주최사
					String organizer = FestivalUtil.getStringOrNull(structured, "organizer");
					
					//가격 설명 텍스트
					String price_text = FestivalUtil.getStringOrNull(structured, "price_text");
					
					//행사 링크
					String external_url = FestivalUtil.getStringOrNull(structured, "external_url");
					
					//계절
					String season = FestivalUtil.getSeasonByDate(startDateTime);
					
					//지역분류
					String locationText = FestivalUtil.safeText(festival_name)
											+ FestivalUtil.safeText(summary) 
											+ FestivalUtil.safeText(city) 
											+ FestivalUtil.safeText(venue_name) 
											+ FestivalUtil.safeText(venue_address);
					
					String prefecture_name = PrefectureDetector.findPrefecture(locationText);
					Integer prefecture_no = PrefectureMap.getPrefectureNo(prefecture_name);
					
					
					
					FestivalDto dto = new FestivalDto(festival_id, prefecture_no, festival_name,
											summary, venue_name, venue_address, access_info,
											image_url, organizer, price_text, external_url,
											image_source, season, startDateTime, endDateTime, price_free);
					
							
					
					dtos.add(dto);
					
					
					}
					
		
				dao.saveFestivals(dtos);
	
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
