package org.example.atumari.community.dto;

public class CommunityFileDto {
	private Long file_no;
    private Long cmty_no;
    private String original_file_name;
    private String save_file_name;
    
    //저장 dto
	public CommunityFileDto(Long file_no, Long cmty_no, String original_file_name, String save_file_name) {
		this.file_no = file_no;
		this.cmty_no = cmty_no;
		this.original_file_name = original_file_name;
		this.save_file_name = save_file_name;
	}

	public Long getFile_no() {
		return file_no;
	}

	public Long getCmty_no() {
		return cmty_no;
	}

	public String getOriginal_file_name() {
		return original_file_name;
	}

	public String getSave_file_name() {
		return save_file_name;
	}
    
    
}
