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
                ${commentList.size()}
            </span>
        </div>
        <!-- =========================
             COMMENT LIST    댓글 글자수제한 450자.
        ========================== -->
        <div class="comment-list">

    <%-- 부모 댓글만 반복 --%>
    <c:forEach var="comment" items="${commentList}">

        <c:if test="${comment.parent_no eq 0}">

            <div class="comment-item">

                <div class="comment-main">

                    <div class="comment-writer">
                        <strong>
                            <c:out value="${comment.member_name}" />
                            <c:if test="${comment.member_email eq cmtydto.getMember_email()}">
                            <span class="comment-author">
							投稿者
                        	</span>
                        	</c:if>
                        </strong>
                    </div>

                    <div class="comment-content">
                                <c:out value="${comment.content}" />
                    </div>

                    <div class="comment-footer">
                        <span class="comment-date">
                            <fmt:formatDate
                                value="${comment.reg_date}"
                                pattern="yyyy-MM-dd HH:mm" />
                        </span>
                        
                        <c:if test="${comment.member_email eq cmtydto.getMember_email()}">
                            <button
                                type="button"
                                class="delete-button">
                                削除
                            </button>
                        </c:if>

                        <c:if test="${comment.is_delete eq 0}">
                            <button
                                type="button"
                                class="reply-button"
                                data-comment-no="${comment.comment_no}"
                                data-cmty-no="${cmtydto.getCmty_no()}"
                                data-context-path="${pageContext.request.contextPath}"
                                >
                                返信
                            </button>
                        </c:if>
                    </div>

                    <!-- 해당 부모 댓글의 대댓글 -->
                    <div class="reply-list">

                        <c:forEach var="reply" items="${commentList}">

                            <c:if test="${reply.parent_no eq comment.comment_no}">

                                <div class="reply-item">

                                    <div class="reply-main">

                                        <div class="reply-writer">
                                            <strong>
                                                <c:out value="${reply.member_name}" />
                                            </strong>
                                            <c:if test="${reply.member_email eq cmtydto.getMember_email()}">
				                            <span class="comment-author">
											投稿者
				                        	</span>
				                        	</c:if>
                                        </div>

                                        <div class="reply-content">
                                                    <c:out value="${reply.content}" />
                                        </div>

                                        <div class="reply-footer">
                                            <span>
                                                <fmt:formatDate
                                                    value="${reply.reg_date}"
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
		
		<input type="hidden" name="cmty_no" value="${cmtydto.getCmty_no()}">
		
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
document.addEventListener("submit", function(event) {

    const form = event.target;

    // 일반 댓글 폼과 답글 폼만 검사
    if (!form.matches(".comment-form, .reply-form")) {
        return;
    }
    
	const content = form.elements["content"];
	
	const sessionEmail = "${sessionScope.sessionEmail}";
	if (!sessionEmail) { 
		event.preventDefault(); 
		alert("ログインしてください。"); 
		if (confirm("ログインしますか？")) {
			location.href = "<%=request.getContextPath()%>/login"; 
		} 
		return; 
	}
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
    <c:if test="${sessionEmail eq cmtydto.getMember_email()}">
    	<a href="<%=request.getContextPath()%>/community/delete?cmtyNo=${cmtydto.getCmty_no()}"
           class="list-button" onclick="return confirm('本当に削除しますか?');">
            削除
        </a>
     	<a href="<%=request.getContextPath()%>/community/update?cmtyNo=${cmtydto.getCmty_no()}"
           class="list-button">
            ポスト修正
        </a>
	</c:if>
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
