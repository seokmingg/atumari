<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>お問い合わせ | ATSUMARI</title>
<link rel="stylesheet"
      href="<%=request.getContextPath()%>/assets/inquiry/css/inquiry_common.css">

<link rel="stylesheet"
      href="<%=request.getContextPath()%>/assets/inquiry/css/inquiry_list.css">
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
<h1>お問い合わせ</h1>
<p>公開されているお問い合わせをご案内します。</p>
</div>

<div class="board-top">
  <p>全 <strong>5</strong> 件</p>
  <div class="board-search">
    <select>
    	<option>タイトル</option>
    	<option>作成者</option>
    	<option>会員ID</option>
    </select>
    <input id="searchKeyword" type="text" placeholder="検索してください">
    <button id="searchBtn" type="button">検索</button>
  </div>
</div>

<div class="board-list">
  <div class="board-header">
    <div class="board-cell board-no">No.</div>
    <div class="board-cell board-subject">タイトル</div>
    <div class="board-cell board-writer">作成者</div>
    <div class="board-cell board-status">状態</div>
    <div class="board-cell board-date">作成日</div>
  </div>

  <div class="board-row">
    <div class="board-cell board-no">5</div>
    <div class="board-cell board-subject"><a href="${pageContext.request.contextPath}/inquiry/view">祭り情報についてお問い合わせします</a></div>
    <div class="board-cell board-writer">kim123</div>
    <div class="board-cell board-status"><span class="status-badge status-completed">回答完了</span></div>
    <div class="board-cell board-date">2026.09.08</div>
  </div>

  <div class="board-row">
    <div class="board-cell board-no">4</div>
    <div class="board-cell board-subject"><a href="${pageContext.request.contextPath}/inquiry/view">お気に入り機能について</a></div>
    <div class="board-cell board-writer">park22</div>
    <div class="board-cell board-status"><span class="status-badge status-waiting">回答待ち</span></div>
    <div class="board-cell board-date">2026.09.07</div>
  </div>

  <div class="board-row">
    <div class="board-cell board-no">3</div>
    <div class="board-cell board-subject"><a href="${pageContext.request.contextPath}/inquiry/view">開催日程が違うようです</a></div>
    <div class="board-cell board-writer">guest</div>
    <div class="board-cell board-status"><span class="status-badge status-completed">回答完了</span></div>
    <div class="board-cell board-date">2026.09.06</div>
  </div>
</div>

<div class="board-pagination">
  <a href="#">←</a><a class="active" href="#">1</a><a href="#">2</a><a href="#">3</a><a href="#">→</a>
</div>

<div class="board-write">
  <a class="write-button" href="${pageContext.request.contextPath}/inquiry/admin/list">管理者ページ</a>
</div>
<div class="board-write">
  <a class="write-button" href="${pageContext.request.contextPath}/inquiry/write">お問い合わせを書く</a>
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