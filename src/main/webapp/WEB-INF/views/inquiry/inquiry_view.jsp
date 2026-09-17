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
        <h2>祭り情報についてお問い合わせします</h2>
      </div>
      <span class="status-badge status-completed">回答完了</span>
    </div>
  </div>
  <div class="detail-meta">
    <span>作成者 kim123</span>
    <span>作成日 2026.09.08</span>
    <span>公開設定 公開</span>
  </div>
  
    <!-- 첨부파일 -->
<div class="detail-file">

    <div class="detail-label">添付ファイル</div>

    <div class="detail-file-list">

        <a href="#" class="detail-file-item">
            <img
                src="${pageContext.request.contextPath}/assets/inquiry/images/icon_file.svg"
                alt="添付ファイル"
                class="detail-file-icon">

            <span>festival_schedule.png</span>
        </a>

    </div>

</div>
  <div class="detail-content">
    青森ねぶた祭の開催日程についてお問い合わせします。<br><br>
    現在サイトに表示されている日程と公式サイトの日程が異なるようです。<br>
    ご確認をお願いいたします。
  </div>
</section>

<section class="answer-card">
  <div class="answer-title">管理者回答</div>
  <div class="answer-body">
    お問い合わせありがとうございます。<br><br>
    確認したところ、掲載情報に誤りがありましたので修正いたしました。<br>
    ご連絡いただきありがとうございました。
  </div>
</section>

<div class="detail-actions">
  <a class="secondary-button" href="${pageContext.request.contextPath}/inquiry/list">一覧へ</a>
  <div class="right">
    <a class="primary-button" href="${pageContext.request.contextPath}/inquiry/update">修正</a>
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