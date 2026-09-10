package org.example.atumari.festival.dto;

public class PrefectureDto {
	
	private Integer prefecture_no;
    private String prefecture_name, region_name;
    
	public PrefectureDto(Integer prefecture_no, String prefecture_name, String region_name) {
		this.prefecture_no = prefecture_no;
		this.prefecture_name = prefecture_name;
		this.region_name = region_name;
	}

	public Integer getPrefecture_no() {
		return prefecture_no;
	}

	public String getPrefecture_name() {
		return prefecture_name;
	}

	public String getRegion_name() {
		return region_name;
	}
	
	
    
}
