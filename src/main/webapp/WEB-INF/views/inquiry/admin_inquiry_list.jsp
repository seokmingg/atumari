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
  <div class="summary-box"><span>全お問い合わせ</span><strong>${allInquiryCount}</strong></div>
  <div class="summary-box"><span>回答待ち</span><strong>${waitingInquiryCount}</strong></div>
  <div class="summary-box"><span>回答完了</span><strong>${completedInquiryCount}</strong></div>
</div>

<div class="board-top">
  <p>全 <strong>${totalCount}</strong> 件</p>
 
  <!-- 검색란 -->
    <form action="${pageContext.request.contextPath}/inquiry/admin/list" method="get">
    
    <input type="hidden" name="status" value="${status}">
    
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

<div class="board-filter">
	<a href="${pageContext.request.contextPath}/inquiry/admin/list" 
	class="${empty status ? 'active' : ''}">
		すべて
	</a>
	 <a href="${pageContext.request.contextPath}/inquiry/admin/list?status=WAITING"
	 class="${status eq 'WAITING' ? 'active' : ''}">
		回答待ち
	 </a>
	 <a href="${pageContext.request.contextPath}/inquiry/admin/list?status=COMPLETED"
	 class="${status eq 'COMPLETED' ? 'active' : ''}">
	 	回答完了
	 </a>
</div>

<div class="board-list">
  <div class="board-header">
    <div class="board-cell board-no">No.</div>
    <div class="board-cell board-subject">タイトル</div>
    <div class="board-cell board-file">添付</div>
    <div class="board-cell board-writer">作成者</div>
    <div class="board-cell board-public">公開設定</div>
    <div class="board-cell board-status">状態</div>
    <div class="board-cell board-date">作成日</div>
  </div>
  
  <c:forEach var="inquiry" items="${inquiryList}">
  <div class="board-row" data-status="waiting">
    <div class="board-cell board-no">${inquiry.inquiry_no}</div>
    <div class="board-cell board-subject"><a href="${pageContext.request.contextPath}/inquiry/admin/view?inquiryNo=${inquiry.inquiry_no}">${inquiry.title}</a></div>
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
    <div class="board-cell board-public">
    ${inquiry.isPublic() ? '公開' : '非公開'}
	</div>
    <div class="board-cell board-status">
	    <span class="status-badge ${inquiry.status eq 'COMPLETED' ? 'status-completed':'status-waiting'}">
	    			${inquiry.status}
	    </span>
    </div>
    <div class="board-cell board-date">${inquiry.formattedCreatedDate}</div>
  </div>
  </c:forEach>
</div>

<div class="board-pagination">

<!-- 이전 페이지 -->
	<c:if test="${page>1}">
		<a href="${pageContext.request.contextPath}/inquiry/admin/list?page=${page - 1}">
			←		
		</a>
	</c:if>
	
<!-- 페이지 번호 -->
	<c:forEach var="i" begin="1" end="${totalPages}">
		<c:if test="${i == page}">
			<a class= "active"
				href="${pageContext.request.contextPath}/inquiry/admin/list?page=${i}">
				${i}
			</a>
		</c:if>
		
		<c:if test="${i != page}">
			<a href="${pageContext.request.contextPath}/inquiry/admin/list?page=${i}">
				${i}
			</a>
		</c:if>
	</c:forEach>

<!-- 다음 페이지 -->
    <c:if test="${page < totalPages}">
        <a href="${pageContext.request.contextPath}/inquiry/admin/list?page=${page + 1}">
            →
        </a>
    </c:if>	
	
</div>

</div>
</main>
<!-- =========================
     FOOTER
========================== -->

<footer class="footer">

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>

</footer>



</body>
</html>