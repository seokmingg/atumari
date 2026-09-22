/**
 * 
 */
/* =========================
   FESTIVAL SEARCH
========================= */

document.addEventListener("DOMContentLoaded", function () {

    /* =========================
       ELEMENT
    ========================= */

    const dateSearch =
        document.querySelector(".festival-date-search");

    const dateButton =
        document.querySelector(".festival-date-button");

    const datePanel =
        document.querySelector(".festival-date-panel");

    const prev =
        document.querySelector(".calendar-prev");

    const next =
        document.querySelector(".calendar-next");

    const searchButton =
        document.querySelector("#searchButton");

    const searchInput =
        document.querySelector("#searchInput");
		
	const resetButton =
	    document.querySelector(".calendar-reset");


    /* =========================
       DATE
    ========================= */

    let festivalStartDate = null;

    let festivalEndDate = null;

    const today = new Date();

    let festivalCurrentYear =
        today.getFullYear();

    let festivalCurrentMonth =
        today.getMonth();


    /* =========================
       FORMAT
    ========================= */

    function formatFestivalDate(date) {

        return `${date.getFullYear()}-${String(
            date.getMonth() + 1
        ).padStart(2, "0")}-${String(
            date.getDate()
        ).padStart(2, "0")}`;

    }


    function formatFestivalDisplayDate(date) {

        return `${date.getMonth() + 1}月${date.getDate()}日`;

    }


    /* =========================
       PARSE URL DATE
    ========================= */

    function parseFestivalDate(value) {

        if (!value) {
            return null;
        }

        const parts =
            value.split("-").map(Number);

        if (parts.length !== 3) {
            return null;
        }

        const date =
            new Date(
                parts[0],
                parts[1] - 1,
                parts[2]
            );

        return isNaN(date.getTime())
            ? null
            : date;

    }


    /* =========================
       INITIAL DATE
    ========================= */

    const urlParams =
        new URLSearchParams(
            window.location.search
        );

    const urlStartDate =
        urlParams.get("startDate");

    const urlEndDate =
        urlParams.get("endDate");


    if (urlStartDate) {

        festivalStartDate =
            parseFestivalDate(
                urlStartDate
            );

    }


    if (urlEndDate) {

        festivalEndDate =
            parseFestivalDate(
                urlEndDate
            );

    }


    /* =========================
       INITIAL CALENDAR MONTH
    ========================= */

    if (festivalStartDate) {

        festivalCurrentYear =
            festivalStartDate.getFullYear();

        festivalCurrentMonth =
            festivalStartDate.getMonth();

    }


    /* =========================
       OPEN CALENDAR
    ========================= */

    function openFestivalCalendar() {

        if (!dateSearch) {
            return;
        }

        dateSearch.classList.add("open");

    }


    /* =========================
       CLOSE CALENDAR
    ========================= */

    function closeFestivalCalendar() {

        if (!dateSearch) {
            return;
        }

        dateSearch.classList.remove("open");

    }


    /* =========================
       UPDATE DATE
    ========================= */

    function updateFestivalDate() {

        const start =
            document.querySelector(
                ".festival-date-range .date-start"
            );

        const end =
            document.querySelector(
                ".festival-date-range .date-end"
            );


        if (!start || !end) {
            return;
        }


        if (
            festivalStartDate &&
            !festivalEndDate
        ) {

            start.textContent =
                formatFestivalDisplayDate(
                    festivalStartDate
                );

            end.textContent = "";

            return;

        }


        if (
            festivalStartDate &&
            festivalEndDate
        ) {

            start.textContent =
                formatFestivalDisplayDate(
                    festivalStartDate
                );

            end.textContent =
                formatFestivalDisplayDate(
                    festivalEndDate
                );

            return;

        }


        start.textContent =
            "日付を選択";

        end.textContent = "";

    }


    /* =========================
       RENDER CALENDAR
    ========================= */

    function renderFestivalCalendar() {

        const days =
            document.querySelector(
                ".festival-date-panel .calendar-days"
            );

        const title =
            document.querySelector(
                ".festival-date-panel .calendar-title"
            );


        if (!days || !title) {
            return;
        }


        days.innerHTML = "";


        title.textContent =
            `${festivalCurrentYear}年 ${festivalCurrentMonth + 1}月`;


        const firstDay =
            new Date(
                festivalCurrentYear,
                festivalCurrentMonth,
                1
            ).getDay();


        const lastDate =
            new Date(
                festivalCurrentYear,
                festivalCurrentMonth + 1,
                0
            ).getDate();


        /* =========================
           EMPTY
        ========================= */

        for (
            let i = 0;
            i < firstDay;
            i++
        ) {

            const empty =
                document.createElement("span");

            days.appendChild(empty);

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


            const buttonDate =
                new Date(
                    festivalCurrentYear,
                    festivalCurrentMonth,
                    day
                );


            /* =========================
               SELECTED
            ========================= */

            if (
                festivalStartDate &&
                buttonDate.getTime() ===
                festivalStartDate.getTime()
            ) {

                button.classList.add(
                    "selected"
                );

            }


            if (
                festivalEndDate &&
                buttonDate.getTime() ===
                festivalEndDate.getTime()
            ) {

                button.classList.add(
                    "selected"
                );

            }


            /* =========================
               RANGE
            ========================= */

            if (
                festivalStartDate &&
                festivalEndDate &&
                buttonDate >
                festivalStartDate &&
                buttonDate <
                festivalEndDate
            ) {

                button.classList.add(
                    "range"
                );

            }


            /* =========================
               CLICK
            ========================= */

            button.addEventListener(
                "click",
                function (event) {

                    event.preventDefault();

                    event.stopPropagation();


                    /* FIRST */

                    if (
                        !festivalStartDate ||
                        festivalEndDate
                    ) {

                        festivalStartDate =
                            new Date(buttonDate);

                        festivalEndDate = null;

                    }


                    /* SECOND */

                    else {

                        if (
                            buttonDate <
                            festivalStartDate
                        ) {

                            festivalEndDate =
                                new Date(
                                    festivalStartDate
                                );

                            festivalStartDate =
                                new Date(
                                    buttonDate
                                );

                        }
                        else {

                            festivalEndDate =
                                new Date(
                                    buttonDate
                                );

                        }

                    }


                    updateFestivalDate();

                    renderFestivalCalendar();


                    console.log(
                        "축제 시작일:",
                        formatFestivalDate(
                            festivalStartDate
                        )
                    );


                    if (festivalEndDate) {

                        console.log(
                            "축제 종료일:",
                            formatFestivalDate(
                                festivalEndDate
                            )
                        );

                    }

                }
            );


            days.appendChild(button);

        }

    }


    /* =========================
       DATE BUTTON
    ========================= */

    if (dateButton) {

        dateButton.addEventListener(
            "click",
            function (event) {

                event.preventDefault();

                event.stopPropagation();


                if (
                    dateSearch &&
                    dateSearch.classList.contains(
                        "open"
                    )
                ) {

                    closeFestivalCalendar();

                }
                else {

                    openFestivalCalendar();

                }

            }
        );

    }


    /* =========================
       PREVIOUS
    ========================= */

    if (prev) {

        prev.addEventListener(
            "click",
            function (event) {

                event.preventDefault();

                event.stopPropagation();


                festivalCurrentMonth--;


                if (
                    festivalCurrentMonth < 0
                ) {

                    festivalCurrentMonth = 11;

                    festivalCurrentYear--;

                }


                renderFestivalCalendar();

            }
        );

    }


    /* =========================
       NEXT
    ========================= */

    if (next) {

        next.addEventListener(
            "click",
            function (event) {

                event.preventDefault();

                event.stopPropagation();


                festivalCurrentMonth++;


                if (
                    festivalCurrentMonth > 11
                ) {

                    festivalCurrentMonth = 0;

                    festivalCurrentYear++;

                }


                renderFestivalCalendar();

            }
        );

    }


    /* =========================
       OUTSIDE CLICK
    ========================= */

    document.addEventListener(
        "click",
        function (event) {

            if (
                dateSearch &&
                dateSearch.contains(
                    event.target
                )
            ) {

                return;

            }


            closeFestivalCalendar();

        }
    );
	
	/* =========================
	   RESET
	========================= */

	function resetFestivalCalendar() {

	    festivalStartDate = null;
	    festivalEndDate = null;

	    updateFestivalDate();
	    renderFestivalCalendar();

	}
	
	if (resetButton) {

	    resetButton.addEventListener(
	        "click",
	        function (event) {

	            event.preventDefault();
	            event.stopPropagation();

	            resetFestivalCalendar();

	        }
	    );

	}


	/* =========================
	   SEARCH
	========================= */

	if (searchButton) {

	    searchButton.addEventListener(
	        "click",
	        function (event) {

	            event.preventDefault();

	            const keyword =
	                searchInput
	                    ? searchInput.value.trim()
	                    : "";

	            const params =
	                new URLSearchParams();

	            /* KEYWORD */

	            if (keyword) {

	                params.set(
	                    "keyword",
	                    keyword
	                );

	            }

	            /* START DATE */

	            if (festivalStartDate) {

	                params.set(
	                    "startDate",
	                    formatFestivalDate(
	                        festivalStartDate
	                    )
	                );

	            }

	            /* END DATE */

	            if (festivalEndDate) {

	                params.set(
	                    "endDate",
	                    formatFestivalDate(
	                        festivalEndDate
	                    )
	                );

	            }

	            const query =
	                params.toString();

	            window.location.href =
	                contextPath +
	                "/home/search" +
	                (query ? `?${query}` : "");

	        }
	    );

	}



    /* =========================
       INITIALIZE
    ========================= */

    updateFestivalDate();

    renderFestivalCalendar();

});
