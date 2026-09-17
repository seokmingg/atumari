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
                    <span>
                        作成者：<c:out value="${notice.authorName}"/>
                    </span>

                    <div class="notice-view-dates">
                        <span>
                            作成日：
                            <fmt:formatDate value="${notice.createdAt}"
                                            pattern="yyyy.MM.dd HH:mm"/>
                        </span>

                        <c:if test="${not empty notice.updatedAt}">
                            <span>
                                修正日：
                                <fmt:formatDate value="${notice.updatedAt}"
                                                pattern="yyyy.MM.dd HH:mm"/>
                            </span>
                        </c:if>
                    </div>
                </div>
            </header>

            <div class="notice-view-content"><c:out value="${notice.content}"/></div>
        </article>

        <div class="notice-view-actions">
            <a class="write-button"
               href="${pageContext.request.contextPath}/notice">一覧へ</a>

            <c:if test="${sessionScope.sessionLevel eq 'admin'}">
                <a class="write-button"
                   href="${pageContext.request.contextPath}/notice/edit?noticeNo=${notice.noticeNo}">
                    修正する
                </a>

                <form method="post"
                      action="${pageContext.request.contextPath}/notice/delete"
                      onsubmit="return confirm('このお知らせを削除しますか？');">
                    <input type="hidden" name="noticeNo" value="${notice.noticeNo}">
                    <button type="submit" class="notice-delete-button">削除する</button>
                </form>
            </c:if>
        </div>
    </div>
</main>

<footer class="footer">
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</footer>

</body>
</html>
