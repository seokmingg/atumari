/**
 * 
 */

    const boardRows = document.querySelectorAll(".board-row");

    boardRows.forEach(function(row) {

        row.addEventListener("click", function() {

            const currentItem =
                this.closest(".board-item");


            /* 다른 공지 닫기 */

            document
                .querySelectorAll(".board-item")
                .forEach(function(item) {

                    if (item !== currentItem) {

                        item.classList.remove("active");

                    }

                });


            /* 현재 공지 열기 / 닫기 */

            currentItem.classList.toggle("active");

        });

    });

