/**
 * HERO SEARCH
 */

document.addEventListener("DOMContentLoaded", function () {

    /* =========================
       ELEMENT
    ========================= */

    const keywordTrigger =
        document.querySelector(".hero-keyword");

    const dateButton =
        document.querySelector(".hero-date");

    const searchPanel =
        document.querySelector(".hero-search-panel");

    const keywordPanel =
        document.querySelector(".keyword-panel");

    const datePanel =
        document.querySelector(".date-panel");

    const searchWrap =
        document.querySelector(".hero-search-wrap");

    const calendarDays =
        document.querySelector(".calendar-days");

    const calendarTitle =
        document.querySelector(".calendar-title");

    const prevButton =
        document.querySelector(".calendar-prev");

    const nextButton =
        document.querySelector(".calendar-next");


    /* =========================
       PANEL OPEN
    ========================= */

    function openPanel(panel) {

        if (!searchPanel || !panel) return;

        searchPanel.classList.add("open");

        keywordPanel.style.display = "none";
        datePanel.style.display = "none";

        panel.style.display = "flex";
    }


    /* =========================
       FESTIVAL CLICK
    ========================= */

    if (keywordTrigger) {

        keywordTrigger.addEventListener(
            "click",
            function (event) {

                event.preventDefault();
                event.stopPropagation();

                openPanel(keywordPanel);
            }
        );
    }


    /* =========================
       DATE CLICK
    ========================= */

    if (dateButton) {

        dateButton.addEventListener(
            "click",
            function (event) {

                event.preventDefault();
                event.stopPropagation();

                openPanel(datePanel);
            }
        );
    }


    /* =========================
       DATE RANGE
    ========================= */

    let startDate = null;
    let endDate = null;


    /* =========================
       TODAY
    ========================= */

    const today = new Date();

    let currentYear =
        today.getFullYear();

    let currentMonth =
        today.getMonth();


    /* =========================
       DATE FORMAT
    ========================= */

    function formatDate(date) {

        return `${date.getFullYear()}-${String(
            date.getMonth() + 1
        ).padStart(2, "0")}-${String(
            date.getDate()
        ).padStart(2, "0")}`;

    }


    function formatDisplayDate(date) {

        return `${date.getMonth() + 1}月 ${date.getDate()}日`;

    }


    /* =========================
       UPDATE SEARCH BAR
    ========================= */

	function updateDateButton() {

	    const dateStart =
	        document.querySelector(".date-start");

	    const dateEnd =
	        document.querySelector(".date-end");

	    if (!dateStart || !dateEnd) return;


	    /* 시작 날짜만 선택 */

	    if (startDate && !endDate) {

	        dateStart.textContent =
	            formatDisplayDate(startDate);

	        dateEnd.textContent =
	            "日付を選択";

	    }


	    /* 시작 + 종료 날짜 */

	    if (startDate && endDate) {

	        dateStart.textContent =
	            formatDisplayDate(startDate);

	        dateEnd.textContent =
	            formatDisplayDate(endDate);

	    }


	    /* 아무것도 선택하지 않음 */

	    if (!startDate) {

	        dateStart.textContent =
	            "日付を選択";

	        dateEnd.textContent =
	            "";

	    }

	}


    /* =========================
       RENDER CALENDAR
    ========================= */

    function renderCalendar() {

        if (!calendarDays || !calendarTitle) return;


        calendarDays.innerHTML = "";


        calendarTitle.textContent =
            `${currentYear}年 ${currentMonth + 1}月`;


        /* =========================
           FIRST DAY
        ========================= */

        const firstDay =
            new Date(
                currentYear,
                currentMonth,
                1
            ).getDay();


        /* =========================
           LAST DATE
        ========================= */

        const lastDate =
            new Date(
                currentYear,
                currentMonth + 1,
                0
            ).getDate();


        /* =========================
           EMPTY DAYS
        ========================= */

        for (
            let i = 0;
            i < firstDay;
            i++
        ) {

            const empty =
                document.createElement("span");

            calendarDays.appendChild(empty);

        }


        /* =========================
           DAYS
        ========================= */

        for (
            let day = 1;
            day <= lastDate;
            day++
        ) {

            const button =
                document.createElement("button");


            button.type = "button";

            button.textContent = day;


            /* =========================
               BUTTON DATE
            ========================= */

            const buttonDate =
                new Date(
                    currentYear,
                    currentMonth,
                    day
                );


            /* =========================
               SELECTED START DATE
            ========================= */

            if (
                startDate &&
                buttonDate.getTime() ===
                startDate.getTime()
            ) {

                button.classList.add("selected");

            }


            /* =========================
               SELECTED END DATE
            ========================= */

            if (
                endDate &&
                buttonDate.getTime() ===
                endDate.getTime()
            ) {

                button.classList.add("selected");

            }


            /* =========================
               RANGE
            ========================= */

            if (
                startDate &&
                endDate &&
                buttonDate > startDate &&
                buttonDate < endDate
            ) {

                button.classList.add("range");

            }


            /* =========================
               DATE CLICK
            ========================= */

            button.addEventListener(
                "click",
                function (event) {

                    /*
                     * 중요
                     * 달력 클릭으로
                     * 패널이 닫히지 않게 함
                     */

                    event.preventDefault();
                    event.stopPropagation();


                    /* =========================
                       FIRST CLICK
                    ========================= */

                    if (!startDate || endDate) {

                        startDate =
                            new Date(buttonDate);

                        endDate = null;

                    }


                    /* =========================
                       SECOND CLICK
                    ========================= */

                    else {

                        if (
                            buttonDate < startDate
                        ) {

                            endDate =
                                new Date(startDate);

                            startDate =
                                new Date(buttonDate);

                        } else {

                            endDate =
                                new Date(buttonDate);

                        }

                    }


                    /* =========================
                       UPDATE SEARCH BAR
                    ========================= */

                    updateDateButton();


                    /* =========================
                       RENDER
                    ========================= */

                    renderCalendar();


                    /* =========================
                       LOG
                    ========================= */

                    console.log(
                        "축제 시작일:",
                        formatDate(startDate)
                    );


                    if (endDate) {

                        console.log(
                            "축제 종료일:",
                            formatDate(endDate)
                        );

                    }

                }
            );


            calendarDays.appendChild(button);

        }

    }


    /* =========================
       PREVIOUS MONTH
    ========================= */

    if (prevButton) {

        prevButton.addEventListener(
            "click",
            function (event) {

                event.preventDefault();
                event.stopPropagation();


                currentMonth--;


                if (currentMonth < 0) {

                    currentMonth = 11;
                    currentYear--;

                }


                renderCalendar();

            }
        );

    }


    /* =========================
       NEXT MONTH
    ========================= */

    if (nextButton) {

        nextButton.addEventListener(
            "click",
            function (event) {

                event.preventDefault();
                event.stopPropagation();


                currentMonth++;


                if (currentMonth > 11) {

                    currentMonth = 0;
                    currentYear++;

                }


                renderCalendar();

            }
        );

    }


    /* =========================
       PANEL CLOSE
    ========================= */

    document.addEventListener(
        "click",
        function (event) {

            /*
             * 검색바와 패널 내부를 클릭하면
             * 절대 닫지 않음
             */

            if (
                searchWrap &&
                searchWrap.contains(event.target)
            ) {

                return;

            }


            /*
             * 검색 영역 바깥 클릭
             * 패널 닫기
             */

            if (searchPanel) {

                searchPanel.classList.remove("open");

            }

        }
    );


    /* =========================
       FIRST RENDER
    ========================= */

    renderCalendar();

});