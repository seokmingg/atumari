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
            <h1>게시글 작성</h1>
            <p>커뮤니티에 새로운 글을 작성해보세요.</p>
        </div>


        <!-- =========================
             WRITE FORM
        ========================= -->
        <form class="community-write-form"
              action="${pageContext.request.contextPath}/community/write"
              method="post"
              enctype="multipart/form-data"
              onsubmit="return confirmSubmit('게시글을 등록하시겠습니까?')">


            <!-- =========================
                 POST HEADER
            ========================= -->
            <div class="community-write-header">

                <div class="community-write-row">

                    <div class="community-write-label">
                        제목
                    </div>

                    <div class="community-write-field">
                        <input type="text"
                               name="title"
                               placeholder="제목을 입력해주세요."
                               required>
                    </div>

                </div>


                <div class="community-write-row">

                    <div class="community-write-label">
                        작성자
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
                        이미지
                    </div>

                    <div class="community-write-field">

                        <div class="community-image-upload">

                            <input type="file"
                                   id="community-image"
                                   name="image"
                                   accept="image/*">

                            <label for="community-image"
                                   class="community-image-button">
                                이미지 선택
                            </label>

                            <span class="community-image-name">
                                선택된 이미지가 없습니다.
                            </span>

                        </div>

                        <p class="community-write-help">
                            게시글에 사용할 이미지를 선택해주세요.
                        </p>

                    </div>

                </div>


                <!-- CONTENT -->
                <div class="community-write-row">

                    <div class="community-write-label">
                        내용
                    </div>

                    <div class="community-write-field">

                        <textarea name="content"
                                  placeholder="내용을 입력해주세요."
                                  required></textarea>

                    </div>

                </div>


                <!-- TAG -->
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

            </div>


            <!-- =========================
                 BUTTONS
            ========================= -->
            <div class="community-write-actions">

                <a class="community-write-cancel"
                   href="${pageContext.request.contextPath}/community/list">
                    취소
                </a>

                <button type="submit"
                        class="community-write-submit">
                    등록하기
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