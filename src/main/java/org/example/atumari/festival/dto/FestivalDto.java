package org.example.atumari.festival.dto;


import java.time.LocalDateTime;

public class FestivalDto {
	
	private Integer festival_no, festival_id, prefecture_no; 
	// festival_no, festival_id는 null 상태를 구분할 수 있도록 Integer 사용
	// festival_id는 DB에서 NOT NULL 제약조건을 설정하여 null 저장 방지

	// prefecture_no는 지역 판별이 불가능한 경우가 있으므로
	// null을 허용하기 위해 Integer 사용
	private String festival_name, summary, venue_name, venue_address,
			access_info, image_url, organizer, price_text, external_url,
			image_source,season;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	//날짜 계산을 좀 더 쉽게 하기 위해 해당 타입설정
	private Boolean price_free;
	
	
	// DB SELECT용
	


	

	// API → DB INSERT용
	public FestivalDto(Integer festival_id, Integer prefecture_no, String festival_name, String summary,
			String venue_name, String venue_address, String access_info, String image_url, String organizer,
			String price_text, String external_url, String image_source, String season, LocalDateTime startDateTime,
			LocalDateTime endDateTime, Boolean price_free) {
		super();
		this.festival_id = festival_id;
		this.prefecture_no = prefecture_no;
		this.festival_name = festival_name;
		this.summary = summary;
		this.venue_name = venue_name;
		this.venue_address = venue_address;
		this.access_info = access_info;
		this.image_url = image_url;
		this.organizer = organizer;
		this.price_text = price_text;
		this.external_url = external_url;
		this.image_source = image_source;
		this.season = season;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.price_free = price_free;
	}




	public Integer getFestival_no() {
		return festival_no;
	}
	
	public Integer getFestival_id() {
		return festival_id;
	}
	public Integer getPrefecture_no() {
		return prefecture_no;
	}
	public String getFestival_name() {
		return festival_name;
	}
	public String getSummary() {
		return summary;
	}
	public String getVenue_name() {
		return venue_name;
	}
	public String getVenue_address() {
		return venue_address;
	}
	public String getAccess_info() {
		return access_info;
	}
	public String getImage_url() {
		return image_url;
	}
	public String getOrganizer() {
		return organizer;
	}
	public String getPrice_text() {
		return price_text;
	}
	public String getExternal_url() {
		return external_url;
	}
	public String getImage_source() {
		return image_source;
	}
	public String getSeason() {
		return season;
	}
	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}
	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}
	public Boolean getPrice_free() {
		return price_free;
	}
	
	
	
	
	
	
	

	
	
}
