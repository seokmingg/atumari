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
                            @${cmtydto.getMember_name()}
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
            <!-- =========================
                 COMMENT 
            ========================== -->
            <div class="comment-item">
              <!--   PROFILE   -->
<!--
                <div class="comment-profile">

                    <img src="<%=request.getContextPath()%>/assets/community/images/profile-default.svg"
                         alt="プロフィール画像">
                </div>
-->
                <!-- COMMENT CONTENT -->
                <div class="comment-main">
                    <!-- WRITER -->
                    <div class="comment-writer">
                        <strong>
                            山田花子
                        </strong>
                        <!-- POST WRITER BADGE -->
                        <span class="comment-author">
							投稿者
                        </span>
                    </div>
                    <!-- COMMENT TEXT -->
                    <div class="comment-content">
                        とても素敵なお祭りですね！
                        私も京都のお祭りに行ってみたいです。
                    </div>
                    <!-- COMMENT FOOTER -->
                    <div class="comment-footer">
                        <span class="comment-date">
                            2026-09-04
                        </span>
                        <button type="button"
                                class="reply-button">
                            返信
                        </button>
                    </div>
                </div>
            </div>
            <!-- =========================
                 COMMENT 
            ========================== -->

            <div class="comment-item">

			    <div class="comment-profile">
			        <img src="<%=request.getContextPath()%>/assets/community/images/profile-default.svg"
			             alt="プロフィール画像">
			    </div>
			
			    <div class="comment-main">
			
			        <div class="comment-writer">
			            <strong>山田花子</strong>
			            <span class="comment-author">작성자</span>
			        </div>
			
			        <div class="comment-content">
			            とても素敵なお祭りですね！
			            私も京都のお祭りに行ってみたいです。
			        </div>
			
			        <div class="comment-footer">
			            <span class="comment-date">2026-09-04</span>
			            <button type="button" class="reply-button">
			                답글
			            </button>
			        </div>

	        		<!-- 대댓글 -->
	       	 		<div class="reply-list">
			            <div class="reply-item">
			                <div class="reply-profile">
			                    <img src="<%=request.getContextPath()%>/assets/community/images/profile-default.svg"
			                         alt="プロフィール画像">
			                </div>
			                <div class="reply-main">
			                    <div class="reply-writer">
			                        <strong>田中太郎</strong>
			                        <span class="reply-author">작성자</span>
			                    </div>
			                    <div class="reply-content">
			                        ありがとうございます！
			                        ぜひ一度行ってみてください。
			                    </div>
			                    <div class="reply-footer">
			                        <span>2026-09-04</span>
			                    </div>
			                </div>
			            </div>
		       	 	</div>
		
			        <!-- 대댓글 입력 -->
			        <div class="reply-write">
			            <textarea placeholder="답글을 입력해주세요."></textarea>
			            <div class="reply-write-bottom">
			                <button type="button">등록</button>
			            </div>
			        </div>
			    </div>
			</div>

            <!-- =========================
                 COMMENT 
            ========================== -->
            <div class="comment-item">
                <div class="comment-profile">
                    <img src="<%=request.getContextPath()%>/assets/community/images/profile-default.svg"
                         alt="プロフィール画像">
                </div>
                <div class="comment-main">
                    <div class="comment-writer">
                        <strong>
                            佐藤美咲
                        </strong>
                    </div>
                    <div class="comment-content">
                        京都の夏祭り、私も大好きです。
                        来年はぜひ行ってみたいです！
                    </div>
                    <div class="comment-footer">
                        <span class="comment-date">
                            2026-09-03
                        </span>
                        <button type="button"
                                class="reply-button">
                            返信する
                        </button>
                    </div>
                </div>
            </div>
        </div>


        <!-- =========================
             COMMENT WRITE
        ========================== -->

        <div class="comment-write">
            <textarea
                placeholder="コメントを入力してください。"></textarea>
            <div class="comment-write-bottom">
                <span>
                    他のユーザーを尊重するコメントをお願いします。
                </span>

                <button type="button">
                    コメントする
                </button>
            </div>
        </div>
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
