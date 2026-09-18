package org.example.atumari.notice.dto;

import java.util.List;

public class NoticeListPageDto {

    private final List<NoticeDto> noticeList;
    private final int currentPage;
    private final int pageSize;
    private final int totalCount;
    private final int totalPage;
    private final int startPage;
    private final int endPage;
    private final String searchType;
    private final String keyword;

    public NoticeListPageDto(
            List<NoticeDto> noticeList,
            int currentPage,
            int pageSize,
            int totalCount,
            int totalPage,
            int startPage,
            int endPage,
            String searchType,
            String keyword) {
        this.noticeList = noticeList;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.startPage = startPage;
        this.endPage = endPage;
        this.searchType = searchType;
        this.keyword = keyword;
    }

    public List<NoticeDto> getNoticeList() {
        return noticeList;
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

    public String getKeyword() {
        return keyword;
    }
}
