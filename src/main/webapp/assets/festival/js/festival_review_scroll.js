let reviewPage = 1;

let reviewLoading = false;

let reviewHasNext =
    reviewCount > reviewPageSize;


window.addEventListener("scroll", function() {

    // 리뷰가 하나도 없으면 무조건 종료
    if (reviewCount === 0) {
        return;
    }

    // 이미 로딩 중이거나 다음 페이지가 없으면 종료
    if (
        reviewLoading ||
        !reviewHasNext
    ) {
        return;
    }


    const scrollPosition =
        window.innerHeight + window.scrollY;

    const documentHeight =
        document.documentElement.scrollHeight;


    if (
        scrollPosition >=
        documentHeight - 300
    ) {
        loadMoreReviews();
    }

});

function loadMoreReviews() {

    reviewLoading = true;
    reviewPage++;

    const loading =
        document.getElementById("review-loading");

    loading.style.display = "block";

    requestAnimationFrame(function() {

        setTimeout(function() {

            fetch(
                contextPath
                + "/festival/review/list"
                + "?festival_no=" + festivalNo
                + "&page=" + reviewPage
            )

            .then(response => response.json())

            .then(data => {

                const reviewList =
                    document.getElementById("review-list");

                data.reviews.forEach(review => {

                    const article =
                        document.createElement("article");

                    article.className =
                        "festival-review-item";


                    /* =========================
                       USER
                    ========================= */

                    const userDiv =
                        document.createElement("div");

                    userDiv.className =
                        "festival-review-user";


                    const name =
                        document.createElement("strong");

                    name.textContent =
                        review.name;


                    const dateSpan =
                        document.createElement("span");


                    if (review.updated_date) {

                        dateSpan.textContent =
                            review.updated_date.replace(
                                "T",
                                " "
                            );


                        const editedSpan =
                            document.createElement("span");

                        editedSpan.className =
                            "festival-review-edited";

                        editedSpan.textContent =
                            " 編集済み";


                        dateSpan.appendChild(
                            editedSpan
                        );

                    } else {

                        dateSpan.textContent =
                            review.created_date.replace(
                                "T",
                                " "
                            );

                    }


                    userDiv.appendChild(name);
                    userDiv.appendChild(dateSpan);


                    /* =========================
                       CONTENT
                    ========================= */

                    const content =
                        document.createElement("p");

                    content.className =
                        "festival-review-content";

                    content.id =
                        "review-content-"
                        + review.review_no;

                    content.textContent =
                        review.content;


                    /* =========================
                       EDIT FORM
                    ========================= */

                    const editForm =
                        document.createElement("form");

                    editForm.action =
                        contextPath
                        + "/festival/review";

                    editForm.method =
                        "post";

                    editForm.className =
                        "festival-review-edit-form";

                    editForm.id =
                        "review-edit-"
                        + review.review_no;

                    editForm.style.display =
                        "none";


                    const updateAction =
                        document.createElement("input");

                    updateAction.type =
                        "hidden";

                    updateAction.name =
                        "action";

                    updateAction.value =
                        "update";


                    const reviewNoInput =
                        document.createElement("input");

                    reviewNoInput.type =
                        "hidden";

                    reviewNoInput.name =
                        "review_no";

                    reviewNoInput.value =
                        review.review_no;


                    const festivalNoInput =
                        document.createElement("input");

                    festivalNoInput.type =
                        "hidden";

                    festivalNoInput.name =
                        "festival_no";

                    festivalNoInput.value =
                        festivalNo;


                    const editTextarea =
                        document.createElement("textarea");

                    editTextarea.name =
                        "content";

                    editTextarea.maxLength =
                        2000;

                    editTextarea.required =
                        true;

                    editTextarea.value =
                        review.content;


                    const editActions =
                        document.createElement("div");

                    editActions.className =
                        "festival-review-edit-actions";


                    const updateButton =
                        document.createElement("button");

                    updateButton.type =
                        "submit";

                    updateButton.textContent =
                        "修整";


                    const cancelButton =
                        document.createElement("button");

                    cancelButton.type =
                        "button";

                    cancelButton.textContent =
                        "キャンセル";

                    cancelButton.onclick =
                        function() {

                            cancelReviewEdit(
                                review.review_no
                            );

                        };


                    editActions.appendChild(
                        updateButton
                    );

                    editActions.appendChild(
                        cancelButton
                    );


                    editForm.appendChild(
                        updateAction
                    );

                    editForm.appendChild(
                        reviewNoInput
                    );

                    editForm.appendChild(
                        festivalNoInput
                    );

                    editForm.appendChild(
                        editTextarea
                    );

                    editForm.appendChild(
                        editActions
                    );


                    article.appendChild(
                        userDiv
                    );

                    article.appendChild(
                        content
                    );

                    article.appendChild(
                        editForm
                    );


                    /* =========================
                       MY REVIEW ACTION
                    ========================= */

					if (
					    (
					        sessionMemberId !== null &&
					        Number(sessionMemberId) ===
					        Number(review.member_id)
					    )
					    ||
					    sessionLevel === "admin"
					)  {

                        const actions =
                            document.createElement("div");

                        actions.className =
                            "festival-review-actions";


                        const editButton =
                            document.createElement("button");

                        editButton.type =
                            "button";

                        editButton.textContent =
                            "編集";

                        editButton.onclick =
                            function() {

                                editReview(
                                    review.review_no
                                );

                            };


                        const deleteForm =
                            document.createElement("form");

                        deleteForm.action =
                            contextPath
                            + "/festival/review";

                        deleteForm.method =
                            "post";

                        deleteForm.style.display =
                            "inline";

                        deleteForm.onsubmit =
                            function() {

                                return confirmDeleteReview();

                            };


                        const deleteAction =
                            document.createElement("input");

                        deleteAction.type =
                            "hidden";

                        deleteAction.name =
                            "action";

                        deleteAction.value =
                            "delete";


                        const deleteReviewNo =
                            document.createElement("input");

                        deleteReviewNo.type =
                            "hidden";

                        deleteReviewNo.name =
                            "review_no";

                        deleteReviewNo.value =
                            review.review_no;


                        const deleteFestivalNo =
                            document.createElement("input");

                        deleteFestivalNo.type =
                            "hidden";

                        deleteFestivalNo.name =
                            "festival_no";

                        deleteFestivalNo.value =
                            festivalNo;


                        const deleteButton =
                            document.createElement("button");

                        deleteButton.type =
                            "submit";

                        deleteButton.textContent =
                            "削除";


                        deleteForm.appendChild(
                            deleteAction
                        );

                        deleteForm.appendChild(
                            deleteReviewNo
                        );

                        deleteForm.appendChild(
                            deleteFestivalNo
                        );

                        deleteForm.appendChild(
                            deleteButton
                        );


                        actions.appendChild(
                            editButton
                        );

                        actions.appendChild(
                            deleteForm
                        );


                        article.appendChild(
                            actions
                        );

                    }


                    reviewList.appendChild(
                        article
                    );

                });


                reviewHasNext =
                    data.hasNext;


                loading.style.display =
                    "none";

                reviewLoading =
                    false;

            })

            .catch(error => {

                console.error(
                    "리뷰 불러오기 실패:",
                    error
                );

                reviewPage--;

                loading.style.display =
                    "none";

                reviewLoading =
                    false;

            });

        }, 300);

    });

}