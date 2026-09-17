<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><c:out value="${notice.title}"/> | ATSUMARI</title>
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
        </div>

        <article class="notice-view">
            <header class="notice-view-header">
                <h2><c:out value="${notice.title}"/></h2>
                <div class="notice-view-meta">
                    <span><c:out value="${notice.authorName}"/></span>
                    <span>
                        <fmt:formatDate value="${notice.createdAt}"
                                        pattern="yyyy.MM.dd HH:mm"/>
                    </span>
                </div>
            </header>

            <div class="notice-view-content"><c:out value="${notice.content}"/></div>
        </article>

        <div class="notice-view-actions">
            <a class="write-button"
               href="${pageContext.request.contextPath}/notice">一覧へ</a>
        </div>
    </div>
</main>

<footer class="footer">
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</footer>

</body>
</html>
