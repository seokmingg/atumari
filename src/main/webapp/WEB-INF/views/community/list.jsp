<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>

<html lang="ja">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Language" content="ja">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Community | ATSUMARI</title>
<link rel="stylesheet"
      href="<%=request.getContextPath()%>/assets/community/css/list.css">
</head>

<body>

<!-- =========================
     HEADER
========================== -->
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!-- =========================
     COMMUNITY PAGE
========================== -->
<main class="community-page">
    <div class="community-inner">
        <!-- =========================
             PAGE TITLE
        ========================== -->
        <div class="community-title">
            <span>COMMUNITY</span>
            <h1>コミュニティ</h1>
            <p>
                お祭りでの思い出を集めましょう！
            </p>
        </div>

        <!-- =========================
             SEARCH AREA
        ========================== -->
		<form name="search">
        <div class="community-search-area">


            <!-- 검색 조건 -->

            <div class="community-search">

                <select name="searchType">
	                <option value="content">　内容　</option>
                    <option value="title">　タイトル　</option>
                    <option value="title_content">　タイトル＋内容　</option>
                    <option value="writer">　投稿者　</option>

                </select>

                <input type="text" placeholder="検索してください" name="search">

                <button type="button"> 検索 </button>

            </div>


            <!-- 한 페이지 게시글 수 -->

            <div class="post-count">

                <span>
                    表示件数
                </span>

                <select name="postCount">
                    <option value="10">　10件　</option>
                    <option value="20"> 20件 </option>
                    <option value="30"> 30件 </option>
                    <option value="50"> 50件 </option>
                </select>

            </div>


        </div>
        
		</form>


        <!-- =========================
             BOARD TOP
        ========================== -->

        <div class="community-top">

            <p>

                全<strong> ${cmtyPage.getTotalCount()} </strong>件

            </p>


            

        </div>



        <!-- =========================
             POPULAR POSTS
        ========================== -->

        <section class="popular-section">


            <div class="popular-title">

                <span>
                    HOT
                </span>

                <h2>
                    人気の投稿
                </h2>

            </div>


            <div class="popular-list">


                <!-- 인기글 1 -->

                <a href="<%=request.getContextPath()%>/community/view"
                   class="popular-item">


                    <div class="popular-number">

                        1

                    </div>


                    <div class="popular-content">

                        <strong>
                            初めて京都の祇園祭に行ってきました！
                        </strong>

                        <span>
                            初めて参加した感想やおすすめの楽しみ方を紹介します。
                        </span>

                    </div>


                    <div class="popular-info">

                        <span>
                            👁 1,248
                        </span>

                        <span>
                            ♥ 86
                        </span>

                    </div>


                </a>



                <!-- 인기글 2 -->

                <a href="<%=request.getContextPath()%>/community/view"
                   class="popular-item">


                    <div class="popular-number">

                        2

                    </div>


                    <div class="popular-content">

                        <strong>
                            東京でおすすめの夏祭りを教えてください
                        </strong>

                        <span>
                            初めて東京の夏祭りに参加する予定です。
                        </span>

                    </div>


                    <div class="popular-info">

                        <span>
                            👁 986
                        </span>

                        <span>
                            ♥ 72
                        </span>

                    </div>


                </a>



                <!-- 인기글 3 -->

                <a href="<%=request.getContextPath()%>/community/view"
                   class="popular-item">


                    <div class="popular-number">

                        3

                    </div>


                    <div class="popular-content">

                        <strong>
                            日本全国のおすすめ祭りをまとめました
                        </strong>

                        <span>
                            実際に参加した祭りを地域別に紹介します。
                        </span>

                    </div>


                    <div class="popular-info">

                        <span>
                            👁 842
                        </span>

                        <span>
                            ♥ 61
                        </span>

                    </div>


                </a>


            </div>


        </section>



        <!-- =========================
             COMMUNITY LIST
        ========================== -->

        <div class="community-list">


            <!-- =========================
                 HEADER
            ========================== -->

            <div class="community-header">


                <span class="list-title">
                    タイトル
                </span>


                <span class="list-writer">
                    投稿者
                </span>


                <span class="list-date">
                    投稿日
                </span>


                <span class="list-view">
                    閲覧
                </span>


                <span class="list-like">
                    いいね
                </span>


            </div>
		 	<c:choose> 
			<c:when test="${not empty cmtyPage.cmtyList}">
			<c:forEach var="cmty" items="${cmtyPage.cmtyList}" varStatus="status">
	            <!-- =========================
	                 POST 
	            ========================== -->
			
	            <a href="<%=request.getContextPath()%>/community/view?cmtyNo=${cmty.cmty_no}"
	               class="community-row">
	
	                <span class="list-title">
	                    <c:out value="${cmty.title}"/>
	                </span>
	
	                <span class="list-writer">
	                    <c:out value="${cmty.member_name}"/>
	                </span>
	
	                <span class="list-date">
	                    <c:out value="${cmty.reg_date}"/>
	                </span>
	                
	                <span class="list-view">
	                    <c:out value="${cmty.hit}"></c:out>
	                </span>
	
	                <span class="list-like">
	                    ♥ 0
	                </span>
	
	
	            </a>
			</c:forEach>
			</c:when>
			 <c:otherwise>
                    <div class="list-empty">検索結果がありません。</div>
                </c:otherwise>
            </c:choose>
        </div>
        
        <div class="community-write">
			<a href="<%=request.getContextPath()%>/community/write"
               class="write-button">

                投稿する

            </a>
		</div>

        <!-- =========================
             PAGINATION
        ========================== -->

 <!--        <div class="community-pagination">
            <a href="#"
               class="page-prev">
                ←
            </a>
            <a href="#"
               class="active">
                1
            </a>
            <a href="#">
                2
            </a>
            <a href="#">
                3
            </a>
            <a href="#">
                4
            </a>
            <a href="#">
                5
            </a>
            <a href="#"
               class="page-next">
                →
            </a>
        </div>
  -->      
        <c:if test="${cmtyPage.totalPage > 1}">
            <div class="community-pagination">
                <c:if test="${cmtyPage.startPage > 1}">
                    <c:url var="previousPageUrl" value="/community">
                        <c:param name="page" value="${cmtyPage.startPage - 1}"/>
                        <c:param name="searchType" value="${cmtyPage.searchType}"/>
                        <c:param name="search" value="${cmtyPage.search}"/>
                    </c:url>
                    <a href="${previousPageUrl}" class="page-prev">←</a>
                </c:if>

                <c:forEach var="pageNumber"
                           begin="${cmtyPage.startPage}"
                           end="${cmtyPage.endPage}">
                    <c:url var="pageUrl" value="/community">
                        <c:param name="page" value="${pageNumber}"/>
                        <c:param name="searchType" value="${cmtyPage.searchType}"/>
                        <c:param name="search" value="${cmtyPage.search}"/>
                    </c:url>
                    <a href="${pageUrl}"
                       class="${pageNumber eq cmtyPage.currentPage ? 'active' : ''}">
                        ${pageNumber}
                    </a>
                </c:forEach>

                <c:if test="${cmtyPage.endPage < cmtyPage.totalPage}">
                    <c:url var="nextPageUrl" value="/community">
                        <c:param name="page" value="${cmtyPage.endPage + 1}"/>
                        <c:param name="searchType" value="${cmtyPage.searchType}"/>
                        <c:param name="search" value="${cmtyPage.search}"/>
                    </c:url>
                    <a href="${nextPageUrl}" class="page-next">→</a>
                </c:if>
            </div>
        </c:if>
        

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
