<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="ja">

<head>

<meta charset="UTF-8">

<meta http-equiv="Content-Language" content="ja">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Festival List | ATSUMARI</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/festival/css/festival_list.css">

</head>


<body>


	<!-- =========================
     HEADER
========================== -->

	<%@ include file="/WEB-INF/views/common/header.jsp"%>


	<!-- =========================
     FESTIVAL LIST
========================== -->

	<main class="festival-page">

		<div class="festival-inner">


			<!-- =========================
             TITLE
        ========================== -->

			<div class="festival-title">

				<a href="<%=request.getContextPath()%>/festival/month/card"
					class="other-region"> ← 月別の選択 </a> <span>FESTIVAL</span>


				<h1>${month}月の祭り</h1>


				<p>${month}月に開催される祭りをご紹介します。</p>

			</div>



			<!-- =========================
             SEARCH
        ========================== -->

			<div class="festival-top">


				<!-- TOTAL -->

				<p class="festival-count">

					全 <strong>${totalCount}</strong> 件

				</p>

			<div class="festival-month-buttons">

	            <c:forEach begin="1" end="12" var="monthNum">
	
	                <a href="${pageContext.request.contextPath}/festival/list?type=month&month=${monthNum}"
	                   class="month-button ${month == monthNum ? 'active' : ''}">
	                    ${monthNum}月
	                </a>
	
	            </c:forEach>

        	</div>

				<!-- SEARCH -->

				<div class="festival-search">

					<select name="select" id="searchSelect">

						<option value="all" ${select == 'all' ? 'selected' : ''}>

							すべて</option>


						<option value="title" ${select == 'title' ? 'selected' : ''}>

							祭り名</option>


						<option value="region" ${select == 'region' ? 'selected' : ''}>

							開催地域</option>

					</select> <input type="text" placeholder="祭りを検索してください" id="searchInput"
						name="search" value="${search}">



					<button type="button" id="searchButton">検索</button>

				</div>


				<script
					src="<%=request.getContextPath()%>/assets/festival/js/festival_search.js"></script>

			</div>



			<!-- =========================
             FESTIVAL LIST
        ========================== -->

			<div class="festival-list">


				<c:forEach var="festival" items="${festivalList}">


					
						<a href="${pageContext.request.contextPath}/festival/view?festival_no=${festival.festival_no}&type=month&month=${month}"
           				   class="festival-item">

						<div class="festival-image">

							<img src="${festival.image_url}" alt="${festival.festival_name}">

						</div> <!-- CONTENT -->

						<div class="festival-content">


							<!-- CATEGORY -->

							<span class="festival-category"> ${festival.season} </span>



							<!-- TITLE -->

							<h2>${festival.festival_name}</h2>



							<!-- DESCRIPTION -->

							<p class="festival-description">${festival.summary}</p>



							<!-- INFO -->

							<div class="festival-info">

								<span class="festival-location">

									${festival.prefecture_name} </span> <span class="festival-date">

									${festival.dateRange} </span>

							</div>

						</div> <!-- ARROW -->

						<div class="festival-arrow">→</div>


					</a>


				</c:forEach>


			</div>



			<!-- =========================
             PAGINATION
        ========================== -->

			<div class="festival-pagination">


				<!-- 이전 -->

				<c:if test="${currentPage > 1}">

					<a
						href="${pageContext.request.contextPath}/festival/list?type=month&month=${param.month}&page=${currentPage - 1}&select=${select}&search=${search}"
						class="page-prev"> ← </a>

				</c:if>



				<!-- 페이지 번호 -->

				<c:forEach begin="1" end="${totalPage}" var="pageNum">


					<a
						href="${pageContext.request.contextPath}/festival/list?type=month&month=${param.month}&page=${pageNum}&select=${select}&search=${search}"
						class="${currentPage == pageNum ? 'active' : ''}"> ${pageNum}

					</a>


				</c:forEach>



				<!-- 다음 -->

				<c:if test="${currentPage < totalPage}">

					<a
						href="${pageContext.request.contextPath}/festival/list?type=month&month=${param.month}&page=${currentPage + 1}&select=${select}&search=${search}"
						class="page-next"> → </a>

				</c:if>


			</div>


		</div>

	</main>



	<!-- =========================
     FOOTER
========================== -->

	<footer class="footer">

		<%@ include file="/WEB-INF/views/common/footer.jsp"%>

	</footer>


</body>

</html>
