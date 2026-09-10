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
        <h2>開催日程について確認をお願いします</h2>
      </div>
      <span class="status-badge status-waiting">回答待ち</span>
    </div>
  </div>
  <div class="detail-meta">
    <span>作成者 kim123</span>
    <span>作成日 2026.09.08</span>
    <span>メール kim123@example.com</span>
    <span>公開設定 公開</span>
  </div>
  
  <!-- 첨부파일 -->
<div class="detail-file">

    <div class="detail-label">添付ファイル</div>

    <div class="detail-file-list">

        <a href="#" class="detail-file-item">
            <img
                src="${pageContext.request.contextPath}/assets/inquiry/images/attach_file.svg"
                alt="添付ファイル"
                class="detail-file-icon">

            <span>festival_schedule.png</span>
        </a>

    </div>

</div>
  
  <div class="detail-content">
    青森ねぶた祭の開催日程についてお問い合わせします。<br><br>
    公式サイトと日程が異なるようです。確認をお願いいたします。
  </div>
</section>

<section class="admin-answer-card">
  <div class="answer-title">管理者回答</div>
  <textarea placeholder="回答内容を入力してください"></textarea>

</section>

<div class="detail-actions">
  <a class="secondary-button" href="admin_inquiry_list.html">一覧へ</a>
  <div class="right"><button class="primary-button" onclick="alert('回答を登録しました。')">回答登録</button></div>
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