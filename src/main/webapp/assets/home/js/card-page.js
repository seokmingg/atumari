/**
 *
 */


/* =========================
   COMMON SLIDER
========================= */

function createSlider(sliderSelector, listSelector, prevSelector, nextSelector, cardSelector) {

    const slider = document.querySelector(sliderSelector);
    const list = document.querySelector(listSelector);
    const prev = document.querySelector(prevSelector);
    const next = document.querySelector(nextSelector);
    const cards = document.querySelectorAll(cardSelector);


    /* 요소가 없으면 실행하지 않음 */

    if (!slider || !list || !prev || !next || cards.length === 0) {
        return;
    }


    let currentIndex = 0;


    /* =========================
       현재 화면에서 보이는 카드 개수
    ========================= */

    function getVisibleCount() {

        if (window.innerWidth <= 600) {

            return 1;

        }

        if (window.innerWidth <= 900) {

            return 2;

        }

        return 3;

    }


    /* =========================
       슬라이드 이동
    ========================= */

    function moveSlider() {

        const visibleCount = getVisibleCount();

        let maxIndex =
            cards.length - visibleCount;


        /* 카드가 화면보다 적은 경우 */

        if (maxIndex < 0) {

            maxIndex = 0;

        }


        /* 화면 크기 변경 시 범위 조정 */

        if (currentIndex > maxIndex) {

            currentIndex = maxIndex;

        }

        if (currentIndex < 0) {

            currentIndex = 0;

        }


        /* 카드 실제 너비 */

        const cardWidth =
            cards[0].getBoundingClientRect().width;


        /* CSS의 실제 gap 값 */

        const style =
            window.getComputedStyle(list);

        const gap =
            parseFloat(style.gap) || 0;


        /* 이동 거리 */

        const moveDistance =
            (cardWidth + gap) * currentIndex;


        list.style.transform =
            `translateX(-${moveDistance}px)`;

    }


    /* =========================
       NEXT
    ========================= */

    next.addEventListener("click", function () {

        const visibleCount =
            getVisibleCount();

        const maxIndex =
            Math.max(cards.length - visibleCount, 0);


        if (currentIndex < maxIndex) {

            currentIndex++;

            moveSlider();

        }

    });


    /* =========================
       PREVIOUS
    ========================= */

    prev.addEventListener("click", function () {

        if (currentIndex > 0) {

            currentIndex--;

            moveSlider();

        }

    });


    /* =========================
       화면 크기 변경
    ========================= */

    window.addEventListener("resize", function () {

        moveSlider();

    });


    /* =========================
       초기 실행
    ========================= */

    moveSlider();

}


/* =========================================================
   ARTICLE SLIDER
========================================================= */

createSlider(
    ".article-slider",
    ".article-list",
    ".article-prev",
    ".article-next",
    ".article-card"
);


/* =========================================================
   FESTIVAL SLIDER
========================================================= */

createSlider(
    ".festival-slider",
    ".festival-list",
    ".festival-prev",
    ".festival-next",
    ".festival-card"
);