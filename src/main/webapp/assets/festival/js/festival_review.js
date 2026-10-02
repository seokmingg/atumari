/**
 * 
 */
function editReview(reviewNo) {

    const content = document.getElementById(
        "review-content-" + reviewNo
    );

    const editForm = document.getElementById(
        "review-edit-" + reviewNo
    );

    content.style.display = "none";
    editForm.style.display = "block";
}


function cancelReviewEdit(reviewNo) {

    const content = document.getElementById(
        "review-content-" + reviewNo
    );

    const editForm = document.getElementById(
        "review-edit-" + reviewNo
    );

    content.style.display = "block";
    editForm.style.display = "none";
}

function confirmDeleteReview() {

    return confirm("レビューを削除しますか？");

}