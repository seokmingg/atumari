/**
 * 
 */
document.addEventListener("DOMContentLoaded", function() {

    const urlParams = new URLSearchParams(location.search);

    const region = urlParams.get("region");
    const prefectureNo = urlParams.get("prefecture_no");


    // ========================================
    // 도도부현 버튼
    // ========================================

    const buttons =
        document.querySelectorAll("[data-prefecture-no]");

    buttons.forEach(function(button) {

        const buttonNo =
            button.getAttribute("data-prefecture-no");

        // 현재 선택된 도도부현
        if (prefectureNo === buttonNo) {
            button.classList.add("active");
        }

        button.addEventListener("click", function() {

            location.href =
                "/festival/list"
                + "?region=" + region
                + "&prefecture_no=" + buttonNo;

        });

    });


    // ========================================
    // すべて 버튼
    // ========================================

    const allButton =
        document.querySelector(".prefecture-all");

    if (allButton) {

        // prefecture_no가 없으면 すべて 선택
        if (!prefectureNo) {
            allButton.classList.add("active");
        }

        allButton.addEventListener("click", function() {

            location.href =
                "/festival/list"
                + "?region=" + region;

        });

    }

});