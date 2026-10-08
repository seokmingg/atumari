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
            <h1>ポスト修正</h1>
            <p>ポストを修正してください。</p>
        </div>


        <!-- =========================
             WRITE FORM
        ========================= -->
  		<form class="community-write-form"
			    name="cmtywrite"
			    method="post"
			    action="${pageContext.request.contextPath}/community/update"
			    enctype="multipart/form-data"
	    >
	    
		<input type="hidden" name="cmtyNo" value="${cmtydto.getCmty_no()}">
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
                               value="${cmtydto.getTitle()}"
                        >
                    </div>

                </div>


                <div class="community-write-row">

                    <div class="community-write-label">
                        投稿者
                    </div>

                    <div class="community-write-field">
                        <input type="text"
                               name="writer"
                               value="${sessionScope.sessionName}"
                               readonly
                               style="color:black;"
                               disabled="disabled"
                               >
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

                        <!-- 이미지 업로드 영역 -->
					<div class="community-image-upload">
					
					    <!--
					        파일 선택 input
					
					        수정 페이지에서는 기존 파일을
					        input type="file"에 넣을 수 없기 때문에
					        새로운 파일을 선택할 때만 사용한다.
					    -->
					    <input type="file"
					           id="community-image"
					           name="image"
					           accept="image/*"
					           onchange="setThumbnail(event);">
					    <!-- 파일 선택 버튼 -->
					    <label for="community-image"
					           class="community-image-button">
					        イメージ選択
					    </label>
					    <!--
					        선택한 파일 이름을 표시하는 영역
					    -->
					    <span class="community-image-name">
					
					        <!--
					            기존 이미지가 있으면
					            기존 이미지의 파일명을 보여줄 수도 있음.
					
					            이미지 이름을 따로 관리하지 않는다면
					            아래 기본 문구를 사용하면 됨.
					        -->
					        新しいイメージをインプットしてください。
					    </span>
					
					
					    <p class="community-write-help">
					        一番よく取れた写真を投稿してください。写真は一つだけ添付できます。
					    </p>
					
					</div>
					
					
					<!-- ==================================================
					     사진 미리보기
					     ================================================== -->
					
					<div id="image_preview">
					
					    <!--
					        기존 이미지가 존재하는 경우에만 출력
					
					        fn:trim()을 이용해서
					        null 또는 빈 문자열을 확인할 수 있다.
					    -->
					    <c:forEach var="file" items="${cmtyFiles}"> 
					    <c:if test="${not empty file.file_no}">
							
					        <div class="image-preview-box">
					
					            <!--
					                기존에 저장되어 있는 이미지 출력
					
					                S3 이미지 URL이 cmty.image에 들어있다고 가정
					            -->
					            <img src="${pageContext.request.contextPath}/community/file/image?fileNo=${file.file_no}"
					                 class="community-preview-image"
					                 alt="기존 이미지">
								
					            <!--
					                기존 이미지 삭제 버튼
					                onclick으로 JavaScript의
					                deleteExistingImage() 실행
					            -->
					            <button type="button"
					                    class="image-delete-button"
					                    onclick="deleteExistingImage();">
					                イメージ削除
					            </button>
								<!-- 기존 파일 번호 -->
				                
								
					        </div>
					        
					    </c:if>
					    </c:forEach>
					
					</div>
					
					
					<!--
					    기존 이미지 삭제 여부를 서버에 전달하기 위한 hidden input
					
					    0 = 기존 이미지 유지
					    1 = 기존 이미지 삭제
					
					    수정 페이지에 처음 들어왔을 때는
					    기존 이미지를 유지해야 하므로 0
					-->
					<input type="hidden"
					       id="delete-image"
					       name="deleteImage"
					       value="0">
					<c:if test="${cmtyFiles.size() ne 0}">       
					<input type="text"
	                       name="fileNo"
	                       value="${cmtyFiles.get(0).file_no}">
					</c:if>
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
                                  oninput="autoResize(this)"
                                  >${cmtydto.getContent()}</textarea>

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
                   href="${pageContext.request.contextPath}/community/view?cmtyNo=${cmtydto.getCmty_no()}">
                    キャンセル
                </a>

                <button type="submit"
                        class="community-write-submit"
                        >
                    修正する
                </button>

            </div>
            
        </form>
        
<script>
//폼 넘기기 전에 공백인지 확인, 공백일 시 알럿창 띄우고 포커스.
document.querySelector(".community-write-form").addEventListener("submit", function(event) {

	if (checkEmpty(cmtywrite.title, "タイトル入力してください。")) {
		cmtywrite.title.focus();
        event.preventDefault();
        return;
    }
	if (checkEmpty(cmtywrite.content, "内容を入力してください。")) {
		cmtywrite.content.focus();
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