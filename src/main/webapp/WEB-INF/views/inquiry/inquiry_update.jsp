<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>お問い合わせ修正 | ATSUMARI</title>

<link rel="stylesheet"
      href="<%=request.getContextPath()%>/assets/inquiry/css/inquiry_common.css">

<link rel="stylesheet"
      href="<%=request.getContextPath()%>/assets/inquiry/css/inquiry_write_update.css">
</head>

<script src="${pageContext.request.contextPath}/assets/inquiry/js/inquiry_write_update.js"></script>

<body>

<!-- header -->

<%@ include file="/WEB-INF/views/common/header.jsp" %>


<!-- board -->

<main class="board-page">

<div class="board-inner">

<div class="board-title">
    <span>INQUIRY</span>
    <h1>お問い合わせ修正</h1>
    <p>登録したお問い合わせ内容を修正します。</p>
</div>


<form class="form-card"
      name="update_form"
      action="${pageContext.request.contextPath}/inquiry/update"
      method="post"
      enctype="multipart/form-data"
      onsubmit="return validateForm();">
      <!-- input type="file" 이 있어서 form안에 enctype이 필요 -->

	<input type="hidden" name="inquiryNo" value="${inquiryDto.inquiry_no}">

    <!-- 제목 -->
    <div class="form-row">
        <div class="form-label">タイトル</div>

        <div class="form-field">
            <input type="text"
                   name="title"
                   value="${inquiryDto.title}">
        </div>
    </div>


   <!-- 작성자 -->
    <div class="form-row">
        <div class="form-label">作成者</div>

        <div class="form-field">
            <input type="text" readonly value="${sessionScope.sessionName}">
        </div>
    </div>

    <!-- 공개 설정 -->
    <div class="form-row">

        <div class="form-label">公開設定</div>

        <div class="form-field radio-field">

            <label>
                <input type="radio"
                       name="isPublic"
                       value="1"
                      <c:if test="${inquiryDto.isPublic()}">checked</c:if>>
                公開
            </label>

            <label>
                <input type="radio"
                       name="isPublic"
                       value="0"
                       <c:if test="${not inquiryDto.isPublic()}">checked</c:if>>
                非公開
            </label>
			<div class="form-help">
               *非公開を選択した場合、他のユーザーからはお問い合わせ内容を閲覧できません。
            </div>
        </div>
        
    </div>


    <!-- 메일 알림 -->
    <div class="form-row">

        <div class="form-label">回答通知</div>

        <div class="form-field radio-field">

            <label>
                <input type="radio"
                       name="emailNotify"
                       value="1"
                       <c:if test="${not empty inquiryDto.email}">checked</c:if>
                       >
                メールで受け取る
            </label>

            <label>
                <input type="radio"
                       name="emailNotify"
                       value="0"
                       <c:if test="${empty inquiryDto.email}">checked</c:if>
                       >
                受け取らない
            </label>

        </div>
    </div>


    <!-- 이메일 -->
    <div class="form-row hidden-row" id="emailArea">

        <div class="form-label">メールアドレス</div>

        <div class="form-field">

            <input type="email"
                   id="email"
                   name="email"
                   placeholder="example@email.com"
                   value="${inquiryDto.email}">

            <div class="form-help">
                *回答が登録された際に通知メールを送信します。
            </div>

        </div>
    </div>
	<!-- 첨부파일 -->
	
	<div class="form-row">
	
	    <div class="form-label">添付ファイル</div>
	
	    <div class="form-field">
			
			<!-- 기존 첨부파일 -->
			<c:if test="${not empty fileDtos}">
				<div class="existing-file-list" 
					 id="existingFileList" 
					 data-fie-count="${fileDtos.size()}">
				
					<c:forEach var="file" items="${fileDtos}">
						<div class="existing-file">
							<span>
								${file.original_file_name}
							</span>
							
							<label>
								<input type="checkbox" 
									   name="deleteFileNo" 
									   value="${file.file_no}"
									   class="delete-file-checkbox">
								削除
							</label>
						</div>
					</c:forEach>
					
				</div>
			</c:if>
			
			<!-- 새로운 첨부파일 선택 -->
	        <div class="file-upload-area">
	
	            <label for="inquiryFile" class="file-select-button">
	                ファイルを選択
	            </label>
	
	           <input type="file"
                       id="inquiryFile"
                       name="inquiryFile"
                       class="file-input"
                       accept=".jpg,.jpeg,.png,.pdf,.doc,.docx,.xls,.xlsx"
                       multiple>
                    <!-- multiple을 붙이면 사용자가 파일을 여러 개 선택할 수 있음 --> 
                    
                <span id="fileName" class="file-name">
                    選択されていません
               	</span> 
                
                </div>              
            
            	<!-- 새로 선택한 파일 목록 -->
				<div id="selectedFileList" class="selected-file-list">
				</div>
				
				<!-- 파일 검증 메시지 -->
				<div id="fileError"
				     class="file-error">
				</div>

	
	        <div class="form-help">
	              * 添付可能なファイル：JPG、JPEG、PNG、PDF、DOC、DOCX、XLS、XLSX<br>
  				  （最大3ファイル、1ファイルあたり10MBまで）
	        </div>
	
	    </div>
	
	</div>

    <!-- 문의 내용 -->
    <div class="form-row">

        <div class="form-label">お問い合わせ内容</div>

        <div class="form-field">
			
            <textarea name="content">${inquiryDto.content}</textarea>

        </div>
    </div>




<div class="form-actions">

    <a class="secondary-button"
       href="${pageContext.request.contextPath}/inquiry/view?inquiryNo=${inquiryDto.inquiry_no}">
        キャンセル
    </a>

    <div class="right">
        <button type="submit"
                class="primary-button">
            修正する
        </button>
    </div>

</div>


</div>
</form>
</main>


<!--footer -->

<footer class="footer">
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</footer>


</body>
</html>