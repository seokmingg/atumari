/**
 * 
 */
document.addEventListener("DOMContentLoaded", function() {

    const searchSelect = document.getElementById("searchSelect");
    const searchInput = document.getElementById("searchInput");
    const searchButton = document.getElementById("searchButton");

    searchButton.addEventListener("click", function() {

        const select = searchSelect.value;
        const search = searchInput.value.trim();

        const params = new URLSearchParams(location.search);

        // 검색 조건
        params.set("select", select);

        if (search !== "") {
            params.set("search", search);
        } else {
            params.delete("search");
        }

        // 검색하면 항상 1페이지부터
        params.set("page", "1");

        location.href =
            location.pathname + "?" + params.toString();

    });

    // 엔터 검색
    searchInput.addEventListener("keydown", function(e) {

        if (e.key === "Enter") {
            searchButton.click();
        }

    });

});