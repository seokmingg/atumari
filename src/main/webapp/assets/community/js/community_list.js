/**
 * 
 */
//게시글 표시 개수 바뀔 때 마다 새로고침
document.addEventListener("DOMContentLoaded", function () {

    const postCount = document.querySelector("select[name='postCount']");

    if (postCount) {

        postCount.addEventListener("change", function () {

            const url = new URL(window.location.href);

            // 게시물 표시 개수 변경
            url.searchParams.set("postCount", this.value);

            // 1페이지로 이동
            url.searchParams.set("page", "1");

            // 검색 정보(searchType, search)는 기존 URL 그대로 유지

            // 새로고침
            window.location.href = url.toString();

        });

    }

});