<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="ja">

<head>

<meta charset="UTF-8">

<meta http-equiv="Content-Language" content="ja">

<meta name="viewport"
      content="width=device-width, initial-scale=1.0">

<title> Season | ATSUMARI</title>

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

        <span> SEASON</span>

        <h1>季節から探す</h1>

        <p>
            日本の季節から、お祭りを探すことができます。
        </p>

    </div>


    <!-- =========================
         REGION GRID
    ========================== -->

    <div class="region-grid">


        <!-- =========================
             HOKKAIDO
        ========================== -->

        <a href="<%=request.getContextPath()%>/festival/list?type=season&season=봄"
           class="region-card">

            <img src="<%=request.getContextPath()%>/assets/festival/images/season/spring.jpg"
                 alt="春">

            <div class="region-overlay">

                <span>SPRING</span>

                <strong>春</strong>

            </div>

        </a>


        <!-- =========================
             TOHOKU
        ========================== -->

        <a href="<%=request.getContextPath()%>/festival/list?type=season&season=여름"
           class="region-card">

            <img src="<%=request.getContextPath()%>/assets/festival/images/season/summer.jpg"
                 alt="東北">

            <div class="region-overlay">

                <span>SUMMER</span>

                <strong>夏</strong>

            </div>

        </a>


        <!-- =========================
             KANTO
        ========================== -->

        <a href="<%=request.getContextPath()%>/festival/list?type=season&season=가을"
           class="region-card">

            <img src="<%=request.getContextPath()%>/assets/festival/images/season/fall.jpg"
                 alt="関東">

            <div class="region-overlay">

                <span>FALL</span>

                <strong>秋</strong>

            </div>

        </a>


        <!-- =========================
             CHUBU
        ========================== -->

        <a href="<%=request.getContextPath()%>/festival/list?type=season&season=겨울"
           class="region-card">

            <img src="<%=request.getContextPath()%>/assets/festival/images/season/winter.jpg"
                 alt="中部">

            <div class="region-overlay">

                <span> WINTER</span>

                <strong>冬</strong>

            </div>

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
