package org.example.atumari.community.dto;

import java.util.List;

public class CommunityListPageDto {
	private final List<CommunityDto> cmtyList;
    private final int currentPage;
    private final int pageSize;
    private final int totalCount;
    private final int totalPage;
    private final int startPage;
    private final int endPage;
    private final String searchType;
    private final String search;
    
    public CommunityListPageDto(
            List<CommunityDto> cmtyList,
            int currentPage,
            int pageSize,
            int totalCount,
            int totalPage,
            int startPage,
            int endPage,
            String searchType,
            String search){
        this.cmtyList = cmtyList;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.startPage = startPage;
        this.endPage = endPage;
        this.searchType = searchType;
        this.search = search;
    }

	public List<CommunityDto> getCmtyList() {
		return cmtyList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public String getSearchType() {
		return searchType;
	}

	public String getSearch() {
		return search;
	}

}
