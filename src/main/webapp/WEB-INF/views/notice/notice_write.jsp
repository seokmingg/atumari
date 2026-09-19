<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>お知らせを書く | ATSUMARI</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/notice/css/list.css">
</head>
<body>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<main class="board-page">
    <div class="board-inner">
        <div class="board-title">
            <span>NOTICE</span>
            <h1>お知らせを書く</h1>
        </div>

        <form class="notice-write-form" method="post" enctype="multipart/form-data"
              action="${pageContext.request.contextPath}/notice/write">
            <c:if test="${not empty errorMessage}">
                <p class="notice-form-error"><c:out value="${errorMessage}"/></p>
            </c:if>

            <label for="title">タイトル</label>
            <input id="title" type="text" name="title" maxlength="200"
                   value="<c:out value='${title}'/>" required>

            <label for="content">内容</label>
            <textarea id="content" name="content" rows="16"
                      required><c:out value="${content}"/></textarea>

            <label for="files">添付ファイル（最大3個、各10MB）</label>
            <input id="files" type="file" name="files" multiple
                   accept=".jpg,.jpeg,.png,.pdf,.doc,.docx,.xls,.xlsx">

            <div class="notice-form-actions">
                <a href="${pageContext.request.contextPath}/notice">キャンセル</a>
                <button type="submit">登録する</button>
            </div>
        </form>
    </div>
</main>

<footer class="footer">
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</footer>

</body>
</html>
