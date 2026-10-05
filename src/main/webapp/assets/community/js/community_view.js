/**
 * 
 */
// 공백 체크
function checkEmpty(obj,msg){
	if(obj.value === ""){
		alert(msg);
		obj.focus();
		return true;
	} else {
		return false;
	}
}

//대댓글 입력창 생성. 
document.addEventListener("DOMContentLoaded", function () {

    const replyButtons = document.querySelectorAll(".reply-button");

    replyButtons.forEach(function (button) {

        button.addEventListener("click", function () {
			//댓글 번호
            const comment_no = this.dataset.commentNo;
            // 현재 댓글
            const commentItem = this.closest(".comment-item");
			// 게시글 번호
			const cmty_no = this.dataset.cmtyNo;
			// 게시글 번호
			const contextPath = this.dataset.contextPath;

            // 이미 답글 입력창이 있으면 제거
            const existingForm = commentItem.querySelector(".reply-form");

            if (existingForm) {
                existingForm.remove();
                return;
            }

            // 답글 입력창 생성
            const form = document.createElement("form");

            form.className = "reply-form";
            form.method = "post";
            form.action = `${contextPath}/community/view`;

            form.innerHTML = `
				<input type="hidden" name="cmty_no" value="${cmty_no}">
                <input type="hidden" name="parent_no" value="${comment_no}">

                <div class="comment-write">
                    <textarea name="content"
                              placeholder="コメントを入力してください。"></textarea>

                    <div class="comment-write-bottom">
                        <span>
                            他のユーザーを尊重するコメントをお願いします。
                        </span>

                        <button type="submit"
                                class="community-comment-submit">
                            コメントする
                        </button>
                    </div>
                </div>
            `;

            // 부모 댓글 내부에 추가
            commentItem.querySelector(".comment-main").appendChild(form);
        });

    });

});