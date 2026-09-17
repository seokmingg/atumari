<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Notice | ATSUMARI</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/notice/css/list.css">
</head>
<body>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<main class="board-page">
    <div class="board-inner">

        <div class="board-title">
            <span>NOTICE</span>
            <h1>お知らせ</h1>
            <p>ATSUMARIからのお知らせをご案内します。</p>
        </div>

        <div class="board-top">
            <p>全 <strong>${noticePage.totalCount}</strong> 件</p>

            <form class="board-search" method="get"
                  action="${pageContext.request.contextPath}/notice">
                <select name="searchType">
                    <option value="title"
                        ${noticePage.searchType eq 'title' ? 'selected' : ''}>タイトル</option>
                    <option value="content"
                        ${noticePage.searchType eq 'content' ? 'selected' : ''}>内容</option>
                </select>
                <input type="text" name="keyword"
                       value="<c:out value='${noticePage.keyword}'/>"
                       placeholder="検索してください">
                <button type="submit">検索</button>
            </form>
        </div>

        <div class="board-list">
            <div class="board-header">
                <span class="board-no">No.</span>
                <span class="board-subject">タイトル</span>
                <span class="board-content">内容</span>
                <span class="board-date">作成日</span>
            </div>

            <c:choose>
                <c:when test="${not empty noticePage.noticeList}">
                    <c:forEach var="notice" items="${noticePage.noticeList}" varStatus="status">
                        <a class="board-row board-row-link"
                           href="${pageContext.request.contextPath}/notice/view?noticeNo=${notice.noticeNo}">
                            <span class="board-no">
                                ${noticePage.totalCount
                                  - ((noticePage.currentPage - 1) * noticePage.pageSize)
                                  - status.index}
                            </span>
                            <span class="board-subject">
                                <c:out value="${notice.title}"/>
                            </span>
                            <span class="board-content">
                                <c:out value="${notice.summary}"/>
                            </span>
                            <span class="board-date">
                                <fmt:formatDate value="${notice.createdAt}"
                                                pattern="yyyy.MM.dd"/>
                            </span>
                        </a>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="board-empty">登録されたお知らせがありません。</div>
                </c:otherwise>
            </c:choose>
        </div>

        <c:if test="${noticePage.totalPage > 1}">
            <div class="board-pagination">
                <c:if test="${noticePage.startPage > 1}">
                    <c:url var="previousPageUrl" value="/notice">
                        <c:param name="page" value="${noticePage.startPage - 1}"/>
                        <c:param name="searchType" value="${noticePage.searchType}"/>
                        <c:param name="keyword" value="${noticePage.keyword}"/>
                    </c:url>
                    <a href="${previousPageUrl}" class="page-prev">←</a>
                </c:if>

                <c:forEach var="pageNumber"
                           begin="${noticePage.startPage}"
                           end="${noticePage.endPage}">
                    <c:url var="pageUrl" value="/notice">
                        <c:param name="page" value="${pageNumber}"/>
                        <c:param name="searchType" value="${noticePage.searchType}"/>
                        <c:param name="keyword" value="${noticePage.keyword}"/>
                    </c:url>
                    <a href="${pageUrl}"
                       class="${pageNumber eq noticePage.currentPage ? 'active' : ''}">
                        ${pageNumber}
                    </a>
                </c:forEach>

                <c:if test="${noticePage.endPage < noticePage.totalPage}">
                    <c:url var="nextPageUrl" value="/notice">
                        <c:param name="page" value="${noticePage.endPage + 1}"/>
                        <c:param name="searchType" value="${noticePage.searchType}"/>
                        <c:param name="keyword" value="${noticePage.keyword}"/>
                    </c:url>
                    <a href="${nextPageUrl}" class="page-next">→</a>
                </c:if>
            </div>
        </c:if>

        <c:if test="${sessionScope.sessionLevel eq 'admin'}">
            <div class="board-write">
                <a href="${pageContext.request.contextPath}/notice/write"
                   class="write-button">お知らせを書く</a>
            </div>
        </c:if>

    </div>
</main>

<footer class="footer">
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</footer>

</body>
</html>
