<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>

<html lang="ja">

<head>

<meta charset="UTF-8">

<meta http-equiv="Content-Language" content="ja">

<meta name="viewport"
      content="width=device-width, initial-scale=1.0">

<title>投稿 | ATSUMARI</title>

<link rel="stylesheet"
      href="<%=request.getContextPath()%>/assets/community/css/community_common.css">

<link rel="stylesheet"
      href="<%=request.getContextPath()%>/assets/community/css/write.css">

</head>

<!-- =========================
     HEADER
========================== -->

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<!-- =========================
     COMMUNITY WRITE
========================= -->
<main class="community-write-page">

    <div class="community-write-inner">

        <!-- PAGE TITLE -->
        <div class="community-write-title">
            <span>COMMUNITY</span>
            <h1>投稿</h1>
            <p>新しいポストを作成してください。</p>
        </div>


        <!-- =========================
             WRITE FORM
        ========================= -->
        <form class="community-write-form"
              action="${pageContext.request.contextPath}/community/write"
              method="post"
              enctype="multipart/form-data"
              onsubmit="return confirmSubmit('投稿しますか？')"
              name="cmtywrite">


            <!-- =========================
                 POST HEADER
            ========================= -->
            <div class="community-write-header">

                <div class="community-write-row">

                    <div class="community-write-label">
                        タイトル
                    </div>

                    <div class="community-write-field">
                        <input type="text"
                               name="title"
                               placeholder="タイトルを入力してください。"
                               required>
                    </div>

                </div>


                <div class="community-write-row">

                    <div class="community-write-label">
                        投稿者
                    </div>

                    <div class="community-write-field">
                        <input type="text"
                               name="writer"
                               value="${sessionScope.loginMember.id}"
                               readonly>
                    </div>

                </div>

            </div>


            <!-- =========================
                 POST BODY
            ========================= -->
            <div class="community-write-body">

                <!-- IMAGE -->
                <div class="community-write-row">

                    <div class="community-write-label">
                        イメージ
                    </div>

                    <div class="community-write-field">

                        <div class="community-image-upload">

                            <input type="file"
                                   id="community-image"
                                   name="image"
                                   accept="image/*">

                            <label for="community-image"
                                   class="community-image-button">
                                イメージ選択
                            </label>

                            <span class="community-image-name">
                                新しいイメージをインプットしてください。
                            </span>

                        </div>

                        <p class="community-write-help">
                            投稿するイメージを入れて下さい。
                        </p>

                    </div>

                </div>


                <!-- CONTENT -->
                <div class="community-write-row">

                    <div class="community-write-label">
                        内容
                    </div>

                    <div class="community-write-field">

                        <textarea name="content"
                                  placeholder="内容を入力してください。"
                                  required></textarea>

                    </div>

                </div>

		<!--
                TAG 기능(미사용)
                <div class="community-write-row">

                    <div class="community-write-label">
                        태그
                    </div>

                    <div class="community-write-field">

                        <input type="text"
                               name="tag"
                               placeholder="#태그를 입력해주세요.">

                        <p class="community-write-help">
                            여러 개의 태그는 쉼표(,)로 구분해주세요.
                        </p>

                    </div>

                </div>
		-->
            </div>
            
			<!-- =========================
                 BUTTONS
            ========================= -->
            <div class="community-write-actions">

                <a class="community-write-cancel"
                   href="${pageContext.request.contextPath}/community/list">
                    キャンセル
                </a>

                <button type="submit"
                        class="community-write-submit"
                        >
                    ポストする
                </button>

            </div>
            
        </form>
        
        

    </div>
    


</main>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>


<script>
const imageInput = document.getElementById('community-image');
const imageName = document.querySelector('.community-image-name');

imageInput.addEventListener('change', function() {
    imageName.textContent = this.files.length > 0
        ? this.files[0].name
        : '선택된 이미지가 없습니다.';
});
</script>

</body>
</html>