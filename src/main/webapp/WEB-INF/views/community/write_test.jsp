<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
    
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

<script src="<%=request.getContextPath()%>/assets/community/js/community_write.js"></script>

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
              enctype="multipart/form-data"
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
                               value=" ${sessionScope.sessionName}"
                               readonly
                               style="color:black;"
                               disabled="disabled">
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
                                   accept="image/*"
                                   onchange="setThumbnail(event);">

                            <label for="community-image"
                                   class="community-image-button">
                                イメージ選択
                            </label>

                            <span class="community-image-name">
                                新しいイメージをインプットしてください。
                            </span>
                            
                        <p class="community-write-help">
                            一番よく取れた写真を投稿してください。写真は一つだけ添付できます。<br>
                        </p>
                        
                        <!-- 사진 프리뷰 -->
							<div id="image_preview">
							</div>

                    </div>

                	</div>
				
				</div>
<script type="text/javascript">
	function autoResize(textarea) {
	    textarea.style.height = 'auto' // 높이를 자동으로 초기화
	    textarea.style.height = textarea.scrollHeight + 'px' // 스크롤 높이에 맞게 높이 설정
	  }
</script>
                <!-- CONTENT -->
                <div class="community-write-row">

                    <div class="community-write-label">
                        内容
                    </div>

                    <div class="community-write-field">

                        <textarea name="content"
                                  placeholder="内容を入力してください。"
                                  required
                                  oninput="autoResize(this)"
                                  ></textarea>

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
        
<script>
	// 폼 넘기기 전에 공백인지 확인, 공백일 시 알럿창 띄우고 포커스.
	document.querySelector(".community-write-form").addEventListener("submit", function(event) {
	
		if (checkEmpty(cmtywrite.title, "タイトル入力してください。")) {
	    	signup.email.focus();
	        event.preventDefault();
	        return;
	    }
		if (checkEmpty(cmtywrite.content, "メールアドレスを入力してください。")) {
	    	signup.email.focus();
	        event.preventDefault();
	        return;
	    }
		if (checkEmpty(signup.email, "メールアドレスを入力してください。")) {
	    	signup.email.focus();
	        event.preventDefault();
	        return;
	    }
		if (checkEmpty(signup.email, "メールアドレスを入力してください。")) {
	    	signup.email.focus();
	        event.preventDefault();
	        return;
	    }
	
	});
</script>

    </div>
    


</main>


<!-- =========================
     FOOTER
========================== -->

<footer class="footer">

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>

</footer>


<script>
const imageInput = document.getElementById('community-image');
const imageName = document.querySelector('.community-image-name');

imageInput.addEventListener('change', function() {
    imageName.textContent = this.files.length > 0
        ? this.files[0].name
        : 'イメージを選択してください。';
});
</script>

</body>
</html>