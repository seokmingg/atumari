<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="ja">

<head>

<meta charset="UTF-8">

<meta http-equiv="Content-Language" content="ja">

<meta name="viewport"
      content="width=device-width, initial-scale=1.0">

<title> Month | ATSUMARI</title>

<link rel="stylesheet"
      href="<%=request.getContextPath()%>/assets/festival/css/card_list.css">

</head>

<body>

<!-- =========================
     HEADER
========================== -->

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<!-- =========================
     REGION PAGE
========================== -->

<main class="region-page">

<div class="region-inner">


    <!-- =========================
         TITLE
    ========================== -->


    <div class="region-title">

    <span>MONTH</span>

    <div class="title-row">

	        <h1>月別に探してください</h1>
	
	        <a href="${pageContext.request.contextPath}/festival/list?festival_no=${festival.festival_no}&type=month&month=${month}"
	           class="card-list-button">
	            月別の祭り一覧 →
	        </a>
	
	    </div>
	
	    <p>
	        月別に、お祭りを探すことができます。
	    </p>

	</div>


    <!-- =========================
         REGION GRID
    ========================== -->

    <div class="region-grid">


        <!-- =========================
             
        ========================== -->

     <c:forEach var="festival" items="${festivalList}"  begin="0" end="9">
           

	    <a href="${pageContext.request.contextPath}/festival/view?festival_no=${festival.festival_no}&type=month&month=${month}"
	       class="region-card">
	
	        <img src="${pageContext.request.contextPath}${festival.image_url}"
	             alt="${festival.festival_name}">
	
	        <div class="region-overlay">
	
	            <span>${festival.prefecture_name}</span>
	
	            <strong>${festival.festival_name}</strong>
	
	        </div>
	
	    </a>

	</c:forEach>



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
