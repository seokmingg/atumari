package org.example.atumari.common.util;

/**
 * 목록 화면에서 공통으로 사용할 페이지네이션 계산 결과입니다.
 *
 * <p>현재 페이지, 전체 페이지, 페이지 번호 범위와
 * DB 조회에 사용할 시작 위치(offset)를 한 번에 계산합니다.</p>
 */
public final class Pagination {

    // 사용자가 요청한 페이지를 실제 존재하는 범위로 보정한 값
    private final int currentPage;
    // 한 페이지에 표시할 게시글 수
    private final int pageSize;
    // 검색 조건에 맞는 전체 게시글 수
    private final int totalCount;
    // 전체 페이지 수
    private final int totalPage;
    // 현재 화면에 표시할 첫 번째 페이지 번호
    private final int startPage;
    // 현재 화면에 표시할 마지막 페이지 번호
    private final int endPage;
    // DB 조회를 시작할 게시글 위치
    private final int offset;

    private Pagination(
            int currentPage,
            int pageSize,
            int totalCount,
            int totalPage,
            int startPage,
            int endPage,
            int offset) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.startPage = startPage;
        this.endPage = endPage;
        this.offset = offset;
    }

    /**
     * 페이지네이션에 필요한 모든 값을 계산합니다.
     *
     * @param requestedPage 사용자가 요청한 페이지 번호
     * @param pageSize 한 페이지에 표시할 게시글 수
     * @param pageGroupSize 한 화면에 표시할 페이지 번호 수
     * @param totalCount 전체 게시글 수
     * @return 계산이 완료된 페이지네이션 정보
     */
    public static Pagination of(
            int requestedPage,
            int pageSize,
            int pageGroupSize,
            int totalCount) {
        // 게시글이 없어도 목록 화면은 1페이지로 처리합니다.
        int totalPage = Math.max(
                1,
                (int) Math.ceil((double) totalCount / pageSize)
        );

        // 1보다 작거나 마지막 페이지보다 큰 요청을 유효한 범위로 보정합니다.
        int currentPage = Math.min(Math.max(1, requestedPage), totalPage);

        // 예: 페이지 번호를 5개씩 표시할 때 7페이지는 6~10 그룹에 속합니다.
        int startPage = ((currentPage - 1) / pageGroupSize) * pageGroupSize + 1;
        int endPage = Math.min(startPage + pageGroupSize - 1, totalPage);

        // SQL의 LIMIT과 OFFSET을 사용할 때 필요한 조회 시작 위치입니다.
        int offset = (currentPage - 1) * pageSize;

        return new Pagination(
                currentPage,
                pageSize,
                totalCount,
                totalPage,
                startPage,
                endPage,
                offset
        );
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

    public int getOffset() {
        return offset;
    }
}
