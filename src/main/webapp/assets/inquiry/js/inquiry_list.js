// 비공개 문의글 작성자가 아닌 다른 사람이 클릭시 알림창 띄우기
document.addEventListener("DOMContentLoaded", function () {

    const url = new URL(window.location.href);

    if (url.searchParams.get("error") === "private") {

        alert(
            "非公開のお問い合わせです。作成者本人のみ閲覧できます。"
        );

        // 새로고침 시 알림이 반복되지 않도록 파라미터 제거
        url.searchParams.delete("error");

        history.replaceState(
            null,
            "",
            url.pathname + url.search + url.hash
        );
    }
});