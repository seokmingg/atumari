/* =========================
   HEADER SCROLL
========================= */

window.addEventListener("scroll", function () {

    const header =
        document.querySelector(".header");

    if (!header) return;

    if (window.scrollY > 0) {

        header.classList.add("scrolled");

    } else {

        header.classList.remove("scrolled");

    }

});


/* =========================
   SEARCH OPEN
========================= */

function openSearch() {

    const header =
        document.querySelector(".header");

    if (!header) return;

    header.classList.add("search-open");

}


/* =========================
   SEARCH CLOSE
========================= */

function closeSearch() {

    const header =
        document.querySelector(".header");

    if (!header) return;

    header.classList.remove("search-open");

    closeCalendar();

}


/* =========================
   DATE
========================= */

let headerStartDate = null;

let headerEndDate = null;

let headerCurrentDate = new Date();

let headerCurrentYear =
    headerCurrentDate.getFullYear();

let headerCurrentMonth =
    headerCurrentDate.getMonth();


/* =========================
   OPEN CALENDAR
========================= */

function openCalendar() {

    const calendar =
        document.querySelector(".header-calendar");

    if (!calendar) return;

    calendar.classList.toggle("open");

}


/* =========================
   CLOSE CALENDAR
========================= */

function closeCalendar() {

    const calendar =
        document.querySelector(".header-calendar");

    if (!calendar) return;

    calendar.classList.remove("open");

}


/* =========================
   FORMAT
========================= */

function formatHeaderDate(date) {

    return `${date.getFullYear()}-${String(

        date.getMonth() + 1

    ).padStart(2, "0")}-${String(

        date.getDate()

    ).padStart(2, "0")}`;

}


function formatHeaderDisplayDate(date) {

    return `${date.getMonth() + 1}月${date.getDate()}日`;

}


/* =========================
   UPDATE DATE
========================= */

function updateHeaderDate() {

    const start =
        document.querySelector(
            ".header-date-start"
        );

    const end =
        document.querySelector(
            ".header-date-end"
        );


    if (!start || !end) return;


    if (headerStartDate && !headerEndDate) {

        start.textContent =
            formatHeaderDisplayDate(
                headerStartDate
            );

        end.textContent = "";

        return;

    }


    if (headerStartDate && headerEndDate) {

        start.textContent =
            formatHeaderDisplayDate(
                headerStartDate
            );

        end.textContent =
            formatHeaderDisplayDate(
                headerEndDate
            );

        return;

    }


    start.textContent = "日付を選択";

    end.textContent = "";

}

/* =========================
   RESET DATE
========================= */

function resetHeaderCalendar() {

    headerStartDate = null;
    headerEndDate = null;

    updateHeaderDate();
    renderHeaderCalendar();

}


/* =========================
   RENDER CALENDAR
========================= */

function renderHeaderCalendar() {

    const days =
        document.querySelector(
            ".header-calendar-days"
        );

    const title =
        document.querySelector(
            ".header-calendar-title"
        );


    if (!days || !title) return;


    days.innerHTML = "";


    title.textContent =
        `${headerCurrentYear}年 ${headerCurrentMonth + 1}月`;


    const firstDay =
        new Date(
            headerCurrentYear,
            headerCurrentMonth,
            1
        ).getDay();


    const lastDate =
        new Date(
            headerCurrentYear,
            headerCurrentMonth + 1,
            0
        ).getDate();


    /* EMPTY */

    for (let i = 0; i < firstDay; i++) {

        const empty =
            document.createElement("span");

        days.appendChild(empty);

    }


    /* DAYS */

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
                headerCurrentYear,
                headerCurrentMonth,
                day
            );


        /* SELECTED */

        if (
            headerStartDate &&
            buttonDate.getTime() ===
            headerStartDate.getTime()
        ) {

            button.classList.add("selected");

        }


        if (
            headerEndDate &&
            buttonDate.getTime() ===
            headerEndDate.getTime()
        ) {

            button.classList.add("selected");

        }


        /* RANGE */

        if (
            headerStartDate &&
            headerEndDate &&
            buttonDate > headerStartDate &&
            buttonDate < headerEndDate
        ) {

            button.classList.add("range");

        }


        /* CLICK */

        button.addEventListener(
            "click",
            function (event) {

                event.stopPropagation();


                /* FIRST */

                if (
                    !headerStartDate ||
                    headerEndDate
                ) {

                    headerStartDate =
                        buttonDate;

                    headerEndDate = null;

                }


                /* SECOND */

                else {

                    if (
                        buttonDate <
                        headerStartDate
                    ) {

                        headerEndDate =
                            headerStartDate;

                        headerStartDate =
                            buttonDate;

                    } else {

                        headerEndDate =
                            buttonDate;

                    }

                }


                updateHeaderDate();

                renderHeaderCalendar();


                console.log(
                    "축제 시작일:",
                    formatHeaderDate(
                        headerStartDate
                    )
                );


                if (headerEndDate) {

                    console.log(
                        "축제 종료일:",
                        formatHeaderDate(
                            headerEndDate
                        )
                    );

                }

            }
        );


        days.appendChild(button);

    }

}


/* =========================
   SEARCH
========================= */

function searchHeaderFestival() {

    const input =
        document.querySelector(
            "#headerSearchInput"
        );

    if (!input) return;


    const keyword =
        input.value.trim();


    const contextPath =
        window.contextPath || "";


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

    if (headerStartDate) {

        params.set(
            "startDate",
            formatHeaderDate(
                headerStartDate
            )
        );

    }


    /* END DATE */

    if (headerEndDate) {

        params.set(
            "endDate",
            formatHeaderDate(
                headerEndDate
            )
        );

    }


    /* SEARCH */

    window.location.href =
        contextPath +
        "/home/search?" +
        params.toString();

}


/* =========================
   PREVIOUS / NEXT / SEARCH
========================= */

document.addEventListener(
    "DOMContentLoaded",
    function () {

        const prev =
            document.querySelector(
                ".header-calendar-prev"
            );

        const next =
            document.querySelector(
                ".header-calendar-next"
            );

        const searchButton =
            document.querySelector(
                "#headerSearchButton"
            );
			
		const resetButton =
		    document.querySelector(
		        ".header-calendar-reset"
		    );


        /* PREVIOUS */

        if (prev) {

            prev.addEventListener(
                "click",
                function (event) {

                    event.stopPropagation();

                    headerCurrentMonth--;


                    if (
                        headerCurrentMonth < 0
                    ) {

                        headerCurrentMonth = 11;

                        headerCurrentYear--;

                    }


                    renderHeaderCalendar();

                }
            );

        }


        /* NEXT */

        if (next) {

            next.addEventListener(
                "click",
                function (event) {

                    event.stopPropagation();

                    headerCurrentMonth++;


                    if (
                        headerCurrentMonth > 11
                    ) {

                        headerCurrentMonth = 0;

                        headerCurrentYear++;

                    }


                    renderHeaderCalendar();

                }
            );

        }


        /* SEARCH */

        if (searchButton) {

            searchButton.addEventListener(
                "click",
                function () {

                    searchHeaderFestival();

                }
            );

        }
		
		/* RESET */

		if (resetButton) {

		    resetButton.addEventListener(
		        "click",
		        function (event) {

		            event.preventDefault();
		            event.stopPropagation();

		            resetHeaderCalendar();

		        }
		    );

		}


        renderHeaderCalendar();

        updateHeaderDate();

    }
);
