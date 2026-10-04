<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>お問い合わせ確認・回答 | ATSUMARI</title>
<link rel="stylesheet"
      href="<%=request.getContextPath()%>/assets/inquiry/css/inquiry_common.css">

<link rel="stylesheet"
      href="<%=request.getContextPath()%>/assets/inquiry/css/inquiry_view.css">
      
<link rel="stylesheet"
      href="<%=request.getContextPath()%>/assets/inquiry/css/admin_inquiry.css">
</head>
<body>
<!-- =========================
     HEADER
========================== -->

<%@ include file="/WEB-INF/views/common/header.jsp" %>


<!-- =========================
     BOARD
========================== -->
<main class="board-page">
<form action="${pageContext.request.contextPath}/inquiry/admin/view"
      method="post">
	
	<input type="hidden" name="inquiryNo" value="${inquiryDto.inquiry_no}">
<div class="board-inner">
<div class="board-title">
<span>INQUIRY</span>
<h1>お問い合わせ確認・回答</h1>
<p>お問い合わせ内容を確認し、回答を登録します。</p>
</div>

<section class="detail-card">
  <div class="detail-header">
    <div class="detail-header-top">
      <div>
        <div class="detail-label">タイトル</div>
        <h2>${inquiryDto.title}</h2>
      </div>
      <span class="status-badge status-waiting">${inquiryDto.status}</span>
    </div>
  </div>
  <div class="detail-meta">
    <span>作成者 ${inquiryDto.writer}</span>
    <span>作成日 ${inquiryDto.formattedCreatedDateTime}</span>
    <span>公開設定 ${inquiryDto.isPublic() ? '公開' : '非公開'}</span>
  </div>
  
  <!-- 첨부파일 -->
<div class="detail-file">

    <div class="detail-label">添付ファイル</div>
<c:forEach var="file" items="${fileDtos}">
    <div class="detail-file-list">

        <a href="${pageContext.request.contextPath}/inquiry/file/download?fileNo=${file.file_no}" class="detail-file-item">
            <img
                src="${pageContext.request.contextPath}/assets/inquiry/images/icon_file.svg"
                alt="添付ファイル"
                class="detail-file-icon">

            <span>${file.original_file_name}</span>
        </a>

    </div>
</c:forEach>

</div>
  
  <div class="detail-content">
	${inquiryDto.content}
  </div>
</section>

<section class="admin-answer-card">
  <div class="answer-title">管理者回答</div>
  
   <%-- 답변 등록 실패 시 오류 메시지 , 저장에 실패했을 때 오류 메시지 확인--%>
    <c:if test="${not empty errorMessage}">
        <div class="form-error" role="alert">
            <c:out value="${errorMessage}" />
        </div>
    </c:if>
  
  <textarea name="answerContent" placeholder="回答内容を入力してください"><c:out value="${inquiryDto.answer_content}" /></textarea>

</section>

<div class="detail-actions">
  <a class="secondary-button" href="${pageContext.request.contextPath}/inquiry/admin/list">一覧へ</a>
  <div class="right">
  <button type="submit" 
  		  class="primary-button"
  		  onclick="return confirm('回答を保存しますか？')">
  		  回答保存
  		  </button>
  <button type="submit" 
  		  class="primary-button answer-delete"
  		  formaction="${pageContext.request.contextPath}/inquiry/admin/answer/delete"
  		  formnovalidate
  		  onclick ="return confirm('回答を削除しますか？');">
  		  回答削除</button>
  </div>
</div>

</div>
</form>
</main>

<!-- =========================
     FOOTER
========================== -->

<footer class="footer">

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>

</footer>


<!-- =========================
     SCRIPT
========================== -->
<script src="inquiry.js"></script>
</body>
</html>