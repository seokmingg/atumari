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
<h1>お問い合わせ修正</h1>
<p>登録したお問い合わせ内容を修正します。</p>
</div>

<form class="form-card">
  <div class="form-row">
    <div class="form-label">タイトル</div>
    <div class="form-field"><input type="text" value="祭り情報についてお問い合わせします"></div>
  </div>
  <div class="form-row">
    <div class="form-label">公開設定</div>
    <div class="form-field">
      <label><input type="radio" name="open" checked> 公開</label>
      &nbsp;&nbsp;
      <label><input type="radio" name="open"> 非公開</label>
    </div>
  </div>
  <div class="form-row">
    <div class="form-label">お問い合わせ内容</div>
    <div class="form-field"><textarea>青森ねぶた祭の開催日程についてお問い合わせします。

現在サイトに表示されている日程と公式サイトの日程が異なるようです。
ご確認をお願いいたします。</textarea></div>
  </div>
</form>

<div class="form-actions">
  <a class="secondary-button" href="inquiry_detail.html">キャンセル</a>
  <div class="right"><a class="primary-button" href="inquiry_detail.html">修正する</a></div>
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