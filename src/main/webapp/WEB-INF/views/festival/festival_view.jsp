<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="ja">

<head>

<meta charset="UTF-8">

<meta http-equiv="Content-Language" content="ja">

<meta name="viewport"
      content="width=device-width, initial-scale=1.0">

<title>Festival | ATSUMARI</title>

<link rel="stylesheet"
      href="<%=request.getContextPath()%>/assets/festival/css/festival_view.css">
      
<link rel="stylesheet"
      href="<%=request.getContextPath()%>/assets/festival/css/festival_review.css">

</head>


<body>

<!-- =========================
     HEADER
========================== -->

<%@ include file="/WEB-INF/views/common/header.jsp" %>


<!-- =========================
     FESTIVAL VIEW
========================== -->

<main class="festival-view-page">

<div class="festival-view-inner">


    <!-- =========================
         BACK
    ========================== -->

    <div class="festival-back">

       <c:choose>

	    <c:when test="${type == 'region'}">
	
	        <a href="${pageContext.request.contextPath}/festival/list?type=region&region=${region}">
	            ← 一覧へ戻る
	        </a>
	
	    </c:when>
	
	
	    <c:when test="${type == 'season'}">
	
	        <a href="${pageContext.request.contextPath}/festival/list?type=season&season=${season}">
	            ← 一覧へ戻る
	        </a>
	
	    </c:when>
	
	
	    <c:when test="${type == 'month'}">
	
	        <a href="${pageContext.request.contextPath}/festival/list?type=month&year=${year}&month=${month}">
	            ← 一覧へ戻る
	        </a>
	
	    </c:when>
	    
	    <c:when test="${type == 'date'}">

		    <a href="${pageContext.request.contextPath}/home/search?keyword=${keyword}&startDate=${startDate}&endDate=${endDate}">
		        ← 一覧へ戻る
		    </a>
		
		</c:when>

</c:choose>

    </div>


    <!-- =========================
         FESTIVAL HEADER
    ========================== -->

    <section class="festival-view-header">


        <!-- CATEGORY -->

        <span class="festival-season">

            ${festival.season}

        </span>


        <!-- TITLE -->

        <h1>

            ${festival.festival_name}

        </h1>


        <!-- BASIC INFO -->

        <div class="festival-basic-info">


            <!-- DATE -->

            <div class="festival-basic-item">

                <span class="info-label">

                    開催期間

                </span>

                <strong>

                    ${festival.dateRange}

                </strong>

            </div>


            <!-- LOCATION -->

            <div class="festival-basic-item">

                <span class="info-label">

                    開催地域

                </span>

                <strong>

                    ${festival.prefecture_name}

                </strong>

            </div>


            <!-- PLACE -->

            <div class="festival-basic-item">

                <span class="info-label">

                    開催場所

                </span>

                <strong>

                    ${festival.venue_name}

                </strong>

            </div>


        </div>


    </section>


    <!-- =========================
         MAIN IMAGE
    ========================== -->

    <section class="festival-main-image">

        <img src="${festival.image_url}"
             alt="${festival.festival_name}">

    </section>


    <!-- =========================
         FESTIVAL CONTENT
    ========================== -->

    <section class="festival-detail-content">


        <!-- =========================
             INTRODUCTION
        ========================== -->

        <div class="festival-content-section">

            <span class="section-label">

                ABOUT FESTIVAL

            </span>


            <h2>

                ${festival.festival_name}について

            </h2>


            <p>

                ${festival.summary}

            </p>

        </div>


        <!-- =========================
             ACCESS
        ========================== -->

        <div class="festival-content-section">

            <span class="section-label">

                ACCESS

            </span>


            <h2>

                開催場所・アクセス

            </h2>


            <div class="festival-information">


                <!-- VENUE -->

                <div class="information-row">

                    <span>

                        開催場所

                    </span>

                    <strong>

                        ${festival.venue_name}

                    </strong>

                </div>


                <!-- ADDRESS -->

                <div class="information-row">

                    <span>

                        住所

                    </span>

                    <strong>

                        ${festival.venue_address}

                    </strong>

                </div>


                <!-- ACCESS -->

                <div class="information-row">

                    <span>

                        アクセス

                    </span>

                    <strong>

                        ${festival.access_info}

                    </strong>

                </div>


            </div>

        </div>


        <!-- =========================
             EVENT INFORMATION
        ========================== -->

        <div class="festival-content-section">

            <span class="section-label">

                INFORMATION

            </span>


            <h2>

                開催情報

            </h2>


            <div class="festival-information">


                <!-- FESTIVAL NAME -->

                <div class="information-row">

                    <span>

                        祭り名

                    </span>

                    <strong>

                        ${festival.festival_name}

                    </strong>

                </div>


                <!-- DATE -->

                <div class="information-row">

                    <span>

                        開催期間

                    </span>

                    <strong>

                        ${festival.dateRange}

                    </strong>

                </div>


                <!-- PREFECTURE -->

                <div class="information-row">

                    <span>

                        都道府県

                    </span>

                    <strong>

                        ${festival.prefecture_name}

                    </strong>

                </div>


                <!-- VENUE -->

                <div class="information-row">

                    <span>

                        開催場所

                    </span>

                    <strong>

                        ${festival.venue_name}

                    </strong>

                </div>


                <!-- ORGANIZER -->

                <div class="information-row">

                    <span>

                        主催

                    </span>

                    <strong>

                        ${festival.organizer}

                    </strong>

                </div>


                <!-- PRICE -->

                <div class="information-row">

                    <span>

                        料金

                    </span>

                    <strong>

                        ${festival.price_text}

                    </strong>

                </div>


            </div>

        </div>


        <!-- =========================
             OFFICIAL SITE
        ========================== -->

        <div class="festival-content-section">

            <span class="section-label">

                OFFICIAL SITE

            </span>


            <h2>

                公式情報

            </h2>


            <div class="festival-information">


                <!-- EXTERNAL URL -->

                <div class="information-row">

                    <span>

                        公式サイト

                    </span>

                    <strong>

                        <a href="${festival.external_url}"
                           target="_blank">

                            公式サイトを見る

                        </a>

                    </strong>

                </div>


                <!-- IMAGE SOURCE -->

                <div class="information-row">

                    <span>

                        画像出典

                    </span>

                    <strong>

                        ${festival.image_source}

                    </strong>

                </div>


            </div>

        </div>


    </section>
    
    <!-- =========================
         LIST REVIEW
    ========================== -->
<section class="festival-review">

<c:if test="${not empty msg}">
    <script>
        alert("${msg}");
    </script>
</c:if>

    <div class="festival-review-header">
	    <h2>
	        レビュー
	        <span class="festival-review-count">
	            レビュー件数：${reviewCount}件
	        </span>
	    </h2>
	</div>


    <!-- 리뷰 작성 -->

<c:if test="${not empty sessionScope.sessionId}">    
    
<form
    action="<%=request.getContextPath()%>/festival/review"
    method="post"
    class="festival-review-write">
    
    <input type="hidden" name="type" value="${type}">
	<input type="hidden" name="keyword" value="${keyword}">
	<input type="hidden" name="startDate" value="${startDate}">
	<input type="hidden" name="endDate" value="${endDate}">

    <input
        type="hidden"
        name="action"
        value="write">

    <input
        type="hidden"
        name="festival_no"
        value="${festival.festival_no}">
        
    <textarea
        name="content"
        maxlength="2000"
        placeholder="お祭りの感想を書いてください。"
        required></textarea>

    <div class="festival-review-write-bottom">

        <span class="festival-review-write-guide">
            ※ レビュー는 2000文字以内で入力してください。
        </span>

        <button type="submit">
            レビューを書く
        </button>

    </div>

</form>

</c:if>

<c:if test="${empty sessionScope.sessionId}">

    <p class="festival-review-login-guide">
        レビューを書くにはログインしてください。
    </p>

</c:if>

    <!-- 리뷰 목록 -->
    <div class="festival-review-list" id="review-list">

  <c:forEach var="review" items="${reviewList}">

    <article class="festival-review-item">

        <div class="festival-review-user">

            <strong>${review.name}</strong>

            <span>
            
                <c:choose>

			        <c:when test="${not empty review.updated_date}">
			            ${fn:replace(review.updated_date, 'T', ' ')}
			            <span class="festival-review-edited">
			                編集済み
			            </span>
			        </c:when>
			
			        <c:otherwise>
			            ${fn:replace(review.created_date, 'T', ' ')}
			        </c:otherwise>
			
			    </c:choose>
			    
            </span>

        </div>


        <!-- 리뷰 내용 -->
        <p class="festival-review-content"
    		id="review-content-${review.review_no}">${review.content}</p>
   


        <!-- 수정 폼 -->
        <form
            action="${pageContext.request.contextPath}/festival/review"
            method="post"
            class="festival-review-edit-form"
            id="review-edit-${review.review_no}"
            style="display: none;">

            <input
                type="hidden"
                name="action"
                value="update">

            <input
                type="hidden"
                name="review_no"
                value="${review.review_no}">

            <input
                type="hidden"
                name="festival_no"
                value="${festival.festival_no}">

            <input
                type="hidden"
                name="type"
                value="${type}">

            <input
                type="hidden"
                name="keyword"
                value="${keyword}">

            <input
                type="hidden"
                name="startDate"
                value="${startDate}">

            <input
                type="hidden"
                name="endDate"
                value="${endDate}">

            <textarea
                name="content"
                maxlength="2000"
                required>${review.content}</textarea>

            <div class="festival-review-edit-actions">

                <button type="submit">
                    修整
                </button>

                <button
                    type="button"
                    onclick="cancelReviewEdit(${review.review_no})">
                    キャンセル
                </button>

            </div>

        </form>


        <!-- 본인 리뷰일 경우만 표시 -->
<c:if test="${sessionScope.sessionId == review.member_id || sessionScope.sessionLevel == 'admin'}">

    <div class="festival-review-actions">

        <button
            type="button"
            onclick="editReview(${review.review_no})">
            編集
        </button>

        <form
		    action="${pageContext.request.contextPath}/festival/review"
		    method="post"
		    style="display: inline;"
		    onsubmit="return confirmDeleteReview();">

            <input
                type="hidden"
                name="action"
                value="delete">

            <input
                type="hidden"
                name="review_no"
                value="${review.review_no}">

            <input
                type="hidden"
                name="festival_no"
                value="${festival.festival_no}">

            <input
                type="hidden"
                name="type"
                value="${type}">

            <input
                type="hidden"
                name="keyword"
                value="${keyword}">

            <input
                type="hidden"
                name="startDate"
                value="${startDate}">

            <input
                type="hidden"
                name="endDate"
                value="${endDate}">

            <button type="submit">
                削除
            </button>

        </form>

    </div>

</c:if>

    </article>

</c:forEach>


        <c:if test="${empty reviewList}">

            <div class="festival-review-empty">
                まだレビューがありません。
            </div>

        </c:if>

    </div>
    
    <!-- =========================
         리스트 불러오는중..
    ========================== -->
    <div
	    id="review-loading"
	    class="festival-review-loading"
	    style="display: none;">
	    レビューを読み込み中...
	</div>

</section>


    <!-- =========================
         LIST BUTTON
    ========================== -->

    <div class="festival-view-bottom">

 <c:choose>

	    <c:when test="${type == 'region'}">
	
	        <a href="${pageContext.request.contextPath}/festival/list?type=region&region=${region}" class="festival-list-button">
	            ← 一覧へ戻る
	        </a>
	
	    </c:when>
	
	
	    <c:when test="${type == 'season'}">
	
	        <a href="${pageContext.request.contextPath}/festival/list?type=season&season=${season}" class="festival-list-button">
	            ← 一覧へ戻る
	        </a>
	
	    </c:when>
	
	
	    <c:when test="${type == 'month'}">
	    
	        <a href="${pageContext.request.contextPath}/festival/list?type=month&year=${year}&month=${month}" class="festival-list-button">
	            ← 一覧へ戻る
	        </a>
	
	    </c:when>
	    
	     <c:when test="${type == 'date'}">

		    <a href="${pageContext.request.contextPath}/home/search?keyword=${keyword}&startDate=${startDate}&endDate=${endDate}" class="festival-list-button">
		        ← 一覧へ戻る
		    </a>
		
		</c:when>

</c:choose>
    </div>


</div>

</main>


<!-- =========================
     FOOTER
========================== -->

<footer class="footer">

<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</footer>

<script>
    const festivalNo = ${festival.festival_no};
    const contextPath = '${pageContext.request.contextPath}';
    const sessionMemberId =
        ${empty sessionScope.sessionId ? 'null' : sessionScope.sessionId};
    const sessionLevel =
        '${empty sessionScope.sessionLevel ? "" : sessionScope.sessionLevel}';
        
    const reviewCount =
        ${reviewCount};

    const reviewPageSize =
        ${reviewPageSize};
        
</script>

<script src="${pageContext.request.contextPath}/assets/festival/js/festival_review.js"></script>
<script src="${pageContext.request.contextPath}/assets/festival/js/festival_review_scroll.js"></script>
</body>

</html>