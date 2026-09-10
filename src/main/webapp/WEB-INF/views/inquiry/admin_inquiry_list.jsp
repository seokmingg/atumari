<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>お問い合わせ管理 | ATSUMARI</title>
<link rel="stylesheet"
      href="<%=request.getContextPath()%>/assets/inquiry/css/inquiry_common.css">

<link rel="stylesheet"
      href="<%=request.getContextPath()%>/assets/inquiry/css/inquiry_list.css">
      
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
<h1>お問い合わせ管理</h1>
<p>ユーザーからのお問い合わせを確認・管理します。</p>
</div>

<div class="admin-summary">
  <div class="summary-box"><span>全お問い合わせ</span><strong>12</strong></div>
  <div class="summary-box"><span>回答待ち</span><strong>4</strong></div>
  <div class="summary-box"><span>回答完了</span><strong>8</strong></div>
</div>

<div class="board-top">
  <p>全 <strong>12</strong> 件</p>
  <div class="board-search">
    <select>
    <option>タイトル</option>
    <option>作成者</option>
    </select>
    <input id="searchKeyword" type="text" placeholder="検索してください">
    <button id="searchBtn" type="button">検索</button>
  </div>
</div>

<div class="board-filter">
  <button class="active" data-filter="all">すべて</button>
  <button data-filter="waiting">回答待ち</button>
  <button data-filter="completed">回答完了</button>
</div>

<div class="board-list">
  <div class="board-header">
    <div class="board-cell board-no">No.</div>
    <div class="board-cell board-subject">タイトル</div>
    <div class="board-cell board-writer">作成者</div>
    <div class="board-cell board-status">状態</div>
    <div class="board-cell board-date">作成日</div>
  </div>
  <div class="board-row" data-status="waiting">
    <div class="board-cell board-no">12</div>
    <div class="board-cell board-subject"><a href="${pageContext.request.contextPath}/inquiry/admin/view">開催日程について確認をお願いします</a></div>
    <div class="board-cell board-writer">kim123</div>
    <div class="board-cell board-status"><span class="status-badge status-waiting">回答待ち</span></div>
    <div class="board-cell board-date">2026.09.08</div>
  </div>
  <div class="board-row" data-status="completed">
    <div class="board-cell board-no">11</div>
    <div class="board-cell board-subject"><a href="admin_inquiry_detail.html">お気に入り機能について</a></div>
    <div class="board-cell board-writer">park22</div>
    <div class="board-cell board-status"><span class="status-badge status-completed">回答完了</span></div>
    <div class="board-cell board-date">2026.09.07</div>
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