<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>お問い合わせ詳細 | ATSUMARI</title>
<link rel="stylesheet"
      href="<%=request.getContextPath()%>/assets/inquiry/css/inquiry_common.css">

<link rel="stylesheet"
      href="<%=request.getContextPath()%>/assets/inquiry/css/inquiry_view.css">
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
<div class="board-inner">
<div class="board-title">
<span>INQUIRY</span>
<h1>お問い合わせ詳細</h1>
<p>お問い合わせ内容と管理者回答をご確認ください。</p>
</div>

<section class="detail-card">
  <div class="detail-header">
    <div class="detail-header-top">
      <div>
        <div class="detail-label">タイトル</div>
        <h2>${inquiryDto.title}</h2>
      </div>
      <span class="status-badge status-completed">${inquiryDto.status}</span>
    </div>
  </div>
  <div class="detail-meta">
    <span>作成者 ${inquiryDto.writer}</span>
    <span> 作成日時 ${inquiryDto.formattedCreatedDateTime}</span>
    <span>公開設定 ${inquiryDto.isPublic() ? '公開' : '非公開'}</span>
  </div>
  
    <!-- 첨부파일 -->
<div class="detail-file">

    <div class="detail-label">添付ファイル</div>
<c:forEach var="file" items="${fileDtos}">
    <div class="detail-file-list">

        <a href="#" class="detail-file-item">
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

<section class="answer-card">
  <div class="answer-title">管理者回答</div>
  <div class="answer-body">
   <c:choose>
   		<%-- 답변 완료 --%>
   		<c:when test="${inquiryDto.answer_content eq 'COMPLETED'}">
   			${inquiryDto.answer_content}
   		</c:when>
   		
   		<%-- 답변 대기 --%>
   		<c:otherwise>
                まだ回答は登録されていません。
        </c:otherwise>
   </c:choose>
  </div>
</section>

<div class="detail-actions">
  <a class="secondary-button" href="${pageContext.request.contextPath}/inquiry/list">一覧へ</a>
  <div class="right">
    <a class="primary-button" href="${pageContext.request.contextPath}/inquiry/update?inquiryNo=${inquiryDto.inquiry_no}">修正</a>
    <button class="danger-button" onclick="confirm('削除しますか？')">削除</button>
  </div>
</div>

</div>
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