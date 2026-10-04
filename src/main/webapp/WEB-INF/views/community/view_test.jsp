<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<!DOCTYPE html>

<html lang="ja">

<head>

<meta charset="UTF-8">
<meta http-equiv="Content-Language" content="ja">
<meta name="viewport"
      content="width=device-width, initial-scale=1.0">
<title>Community | ATSUMARI</title>
<link rel="stylesheet"
      href="<%=request.getContextPath()%>/assets/community/css/view.css">
<script src="<%=request.getContextPath()%>/assets/community/js/community_view.js"></script>
</head>

<body>
<!-- =========================
     HEADER
========================== -->
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!-- =========================
     COMMUNITY VIEW
========================== -->
<main class="community-view-page">
<div class="community-view-inner">
    <!-- =========================
         POST
    ========================== -->
    <article class="community-post">
        <!-- =========================
             POST HEADER
        ========================== -->
        <div class="post-header">
            <!-- TITLE -->
            <h1 class="post-title">
                ${cmtydto.getTitle()}
            </h1>
            <!-- WRITER -->
            <div class="post-writer-area">

                <!-- PROFILE IMAGE -->
<!-- 
                <div class="writer-profile">
                    <img src="<%=request.getContextPath()%>/assets/community/images/profile-default.svg"
                         alt="プロフィール画像">
                </div>
 -->
                <!-- WRITER INFO -->
                <div class="writer-info">
                    <div class="writer-name-area">
                        <strong class="writer-name">
                            ${cmtydto.getMember_name()}
                        </strong>
                        <span class="writer-id">
                            @<c:out value="${fn:substringBefore(cmtydto.getMember_email(), '@')}" />
                        </span>
                    </div>
         
                    <!-- POST INFO -->
                    <div class="post-info">
                        <span>
                            ${cmtydto.getReg_date()}
                        </span>
                        <span class="info-divider">
                            |
                        </span>
                        <span>
                            閲覧 ${cmtydto.getHit()}
                        </span>
                    </div>
                </div>
            </div>
        </div>

        <!-- =========================
             POST BODY
        ========================== -->
        <div class="post-body">
            <!-- POST IMAGE -->
			<c:forEach var="file" items="${cmtyFiles}">
			    <c:if test="${file.original_file_name.matches('(?i).*[.](jpg|jpeg|png|gif|webp)$')}">
			        <img
			            src="${pageContext.request.contextPath}/community/file/image?fileNo=${file.file_no}"
			            alt="커뮤니티 첨부 이미지"
			            style="max-width: 100%; height: auto;">
			    </c:if>
			</c:forEach>

            <!-- POST CONTENT -->

            <div class="post-content">

                <p>
					${cmtydto.getContent()}
                </p>

            </div>
            
            <div class="post-like">

				<button type="button" class="like-button">
				
				    <span class="like-icon">♡</span>
				
				    <span class="like-text">
				        いいね
				    </span>
				
				    <span class="like-count">
				        24
				    </span>
				</button>
			</div>
        </div>
    </article>


    <!-- =========================
         COMMENT SECTION
    ========================== -->

    <section class="comment-section">
        <!-- COMMENT TITLE -->
        <div class="comment-title">
            <h2>
                コメント
            </h2>
            <span>
                3
            </span>
        </div>
        <!-- =========================
             COMMENT LIST    댓글 글자수제한 450자.
        ========================== -->
        <div class="comment-list">

    <%-- 부모 댓글만 반복 --%>
    <c:forEach var="comment" items="${commentList}">

        <c:if test="${empty comment.parentNo}">

            <div class="comment-item">

                <div class="comment-profile">
                    <img
                        src="${pageContext.request.contextPath}/assets/community/images/profile-default.svg"
                        alt="프로필 이미지">
                </div>

                <div class="comment-main">

                    <div class="comment-writer">
                        <strong>
                            <c:out value="${comment.writerName}" />
                        </strong>
                    </div>

                    <div class="comment-content">
                        <c:choose>
                            <c:when test="${comment.isDeleted == 1}">
                                삭제된 댓글입니다.
                            </c:when>
                            <c:otherwise>
                                <c:out value="${comment.content}" />
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <div class="comment-footer">
                        <span class="comment-date">
                            <fmt:formatDate
                                value="${comment.regDate}"
                                pattern="yyyy-MM-dd HH:mm" />
                        </span>

                        <c:if test="${comment.isDeleted == 0}">
                            <button
                                type="button"
                                class="reply-button"
                                data-comment-no="${comment.commentNo}">
                                답글 달기
                            </button>
                        </c:if>
                    </div>

                    <!-- 해당 부모 댓글의 대댓글 -->
                    <div class="reply-list">

                        <c:forEach var="reply" items="${commentList}">

                            <c:if test="${reply.parentNo == comment.commentNo}">

                                <div class="reply-item">

                                    <div class="reply-profile">
                                        <img
                                            src="${pageContext.request.contextPath}/assets/community/images/profile-default.svg"
                                            alt="프로필 이미지">
                                    </div>

                                    <div class="reply-main">

                                        <div class="reply-writer">
                                            <strong>
                                                <c:out value="${reply.writerName}" />
                                            </strong>
                                        </div>

                                        <div class="reply-content">
                                            <c:choose>
                                                <c:when test="${reply.isDeleted == 1}">
                                                    삭제된 댓글입니다.
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value="${reply.content}" />
                                                </c:otherwise>
                                            </c:choose>
                                        </div>

                                        <div class="reply-footer">
                                            <span>
                                                <fmt:formatDate
                                                    value="${reply.regDate}"
                                                    pattern="yyyy-MM-dd HH:mm" />
                                            </span>
                                        </div>

                                    </div>
                                </div>

                            </c:if>

                        </c:forEach>

                    </div>
                </div>
            </div>

        </c:if>
    </c:forEach>

</div>


        <!-- =========================
             COMMENT WRITE
        ========================== -->
		<form class="comment-form"
        		name="comment"
        		method="post"
			    action="${pageContext.request.contextPath}/community/view"
		>
        <div class="comment-write">
        
            <textarea name="content" 
            			placeholder="コメントを入力してください。"></textarea>
		
            <div class="comment-write-bottom">
                <span>
                    他のユーザーを尊重するコメントをお願いします。
                </span>

                <button type="submit"
                        class="community-comment-submit">
                    コメントする
                </button>
            </div>
        </div>
		</form> 
<script>
// 댓글폼 넘기기 전에 공백인지 확인, 공백일 시 알럿창 띄우고 포커스.
document.querySelector(".comment-form").addEventListener("submit", function(event) {
	if (checkEmpty(comment.content, "内容を入力してください。")) {
		comment.content.focus();
        event.preventDefault();
        return;
    }
});
</script>
    </section>
    


    <!-- =========================
         LIST BUTTON
    ========================== -->

    <div class="community-view-bottom">
        <a href="<%=request.getContextPath()%>/community"
           class="list-button">
            一覧へ
        </a>
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
