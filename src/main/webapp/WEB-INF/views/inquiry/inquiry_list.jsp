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
  <p>全 <strong>${totalCount}</strong> 件</p>
  
  <!-- 검색란 -->
  <form action="${pageContext.request.contextPath}/inquiry/list" method="get">
  <div class="board-search">
    <select name="searchType">
    	<option value="title" <c:if test="${searchType eq 'title'}"> selected </c:if>>タイトル</option>
    	<option value="writer" <c:if test="${searchType eq 'writer'}"> selected </c:if>>作成者</option>
    </select>
    <input id="searchKeyword" name="keyword" value="${keyword}" type="text" placeholder="検索してください">
    <button id="searchBtn" type="submit">検索</button>
  </div>
  </form>
</div>

<div class="board-list">
  <div class="board-header">
    <div class="board-cell board-no">No.</div>
    <div class="board-cell board-subject">タイトル</div>
    <div class="board-cell board-file">添付</div>
    <div class="board-cell board-writer">作成者</div>
    <div class="board-cell board-status">状態</div>
    <div class="board-cell board-date">作成日</div>
  </div>

<c:forEach var="inquiry" items="${inquiryList}">
  <div class="board-row">
    <div class="board-cell board-no">${inquiry.inquiry_no}</div>
    <div class="board-cell board-subject"><a href="${pageContext.request.contextPath}/inquiry/view?inquiryNo=${inquiry.inquiry_no}">${inquiry.title}</a></div>
     <!-- 첨부파일 -->
        <div class="board-cell board-file">
         <c:if test="${inquiry.fileIs}">
           <span class="file-info">
                <img
                    src="${pageContext.request.contextPath}/assets/inquiry/images/icon_file.svg"
                    alt="添付"
                    class="file-icon"
                >
            </span>
            </c:if>
        </div>
      
    <div class="board-cell board-writer">${inquiry.writer}</div>
    <div class="board-cell board-status"><span class="status-badge status-completed">${inquiry.status}</span></div>
    <div class="board-cell board-date"> ${inquiry.formattedCreatedDate}</div>
  </div>
</c:forEach>
</div>

<div class="board-pagination">

<!-- 이전 페이지 -->
	<c:if test="${page>1}">
		<a href="${pageContext.request.contextPath}/inquiry/list?page=${page - 1}">
			←		
		</a>
	</c:if>
	
<!-- 페이지 번호 -->
	<c:forEach var="i" begin="1" end="${totalPages}">
		<c:if test="${i == page}">
			<a class= "active"
				href="${pageContext.request.contextPath}/inquiry/list?page=${i}">
				${i}
			</a>
		</c:if>
		
		<c:if test="${i != page}">
			<a href="${pageContext.request.contextPath}/inquiry/list?page=${i}">
				${i}
			</a>
		</c:if>
	</c:forEach>

<!-- 다음 페이지 -->
    <c:if test="${page < totalPages}">
        <a href="${pageContext.request.contextPath}/inquiry/list?page=${page + 1}">
            →
        </a>
    </c:if>	
	
</div>

<div class="board-write">
  <a class="write-button" href="${pageContext.request.contextPath}/inquiry/admin/list">管理者ページ</a>
  <a class="write-button" href="${pageContext.request.contextPath}/inquiry/write">お問い合わせを書く</a>
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