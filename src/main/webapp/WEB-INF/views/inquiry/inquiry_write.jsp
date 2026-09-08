<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>お問い合わせ登録 | ATSUMARI</title>
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
<h1>お問い合わせ登録</h1>
<p>お問い合わせ内容をご入力ください。</p>
</div>

<form class="form-card" onsubmit="return confirmSubmit('お問い合わせを登録しますか？')">
  <div class="form-row">
    <div class="form-label">タイトル</div>
    <div class="form-field"><input type="text" placeholder="タイトルを入力してください"></div>
  </div>
  <div class="form-row">
    <div class="form-label">作成者</div>
    <div class="form-field"><input type="text" value="kim123"></div>
  </div>
  <div class="form-row">
    <div class="form-label">メール</div>
    <div class="form-field"><input type="email" placeholder="example@email.com"><div class="form-help">回答通知を受け取るメールアドレスです。</div></div>
  </div>
  <div class="form-row">
    <div class="form-label">公開設定</div>
    <div class="form-field">
      <label><input type="radio" name="open" checked> 公開</label>
      &nbsp;&nbsp;
      <label><input type="radio" name="open"> 非公開</label>
      <div class="form-help">公開を選択すると、会員・非会員を問わず一覧で確認できます。</div>
    </div>
  </div>
  <div class="form-row">
    <div class="form-label">お問い合わせ内容</div>
    <div class="form-field"><textarea placeholder="お問い合わせ内容を入力してください"></textarea></div>
  </div>
</form>

<div class="form-actions">
  <a class="secondary-button" href="inquiry_list.html">一覧へ</a>
  <div class="right">
    <a class="primary-button" href="inquiry_list.html">登録する</a>
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