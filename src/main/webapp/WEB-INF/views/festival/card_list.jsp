<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="ja">

<head>

<meta charset="UTF-8">

<meta http-equiv="Content-Language" content="ja">

<meta name="viewport"
      content="width=device-width, initial-scale=1.0">

<title>Region | ATSUMARI</title>

<link rel="stylesheet"
      href="<%=request.getContextPath()%>/assets/festival/css/region_list.css">

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

        <span>REGION</span>

        <h1>地域から探す</h1>

        <p>
            日本全国の地域から、お祭りを探すことができます。
        </p>

    </div>


    <!-- =========================
         REGION GRID
    ========================== -->

    <div class="region-grid">


        <!-- =========================
             HOKKAIDO
        ========================== -->

        <a href="<%=request.getContextPath()%>/festival/list?region=hokkaido"
           class="region-card">

            <img src="<%=request.getContextPath()%>/assets/festival/images/region/hokkaido.jpg"
                 alt="北海道">

            <div class="region-overlay">

                <span>HOKKAIDO</span>

                <strong>北海道</strong>

            </div>

        </a>


        <!-- =========================
             TOHOKU
        ========================== -->

        <a href="<%=request.getContextPath()%>/festival/list?region=tohoku"
           class="region-card">

            <img src="<%=request.getContextPath()%>/assets/festival/images/region/tohoku.jpg"
                 alt="東北">

            <div class="region-overlay">

                <span>TOHOKU</span>

                <strong>東北</strong>

            </div>

        </a>


        <!-- =========================
             KANTO
        ========================== -->

        <a href="<%=request.getContextPath()%>/festival/list?region=kanto"
           class="region-card">

            <img src="<%=request.getContextPath()%>/assets/festival/images/region/kanto.jpg"
                 alt="関東">

            <div class="region-overlay">

                <span>KANTO</span>

                <strong>関東</strong>

            </div>

        </a>


        <!-- =========================
             CHUBU
        ========================== -->

        <a href="<%=request.getContextPath()%>/festival/list?region=chubu"
           class="region-card">

            <img src="<%=request.getContextPath()%>/assets/festival/images/region/chubu.jpg"
                 alt="中部">

            <div class="region-overlay">

                <span>CHUBU</span>

                <strong>中部</strong>

            </div>

        </a>


        <!-- =========================
             KINKI
        ========================== -->

        <a href="<%=request.getContextPath()%>/festival/list?region=kinki"
           class="region-card">

            <img src="<%=request.getContextPath()%>/assets/festival/images/region/kinki.jpg"
                 alt="近畿">

            <div class="region-overlay">

                <span>KINKI</span>

                <strong>近畿</strong>

            </div>

        </a>


        <!-- =========================
             CHUGOKU
        ========================== -->

        <a href="<%=request.getContextPath()%>/festival/list?region=chugoku"
           class="region-card">

            <img src="<%=request.getContextPath()%>/assets/festival/images/region/chugoku.jpg"
                 alt="中国">

            <div class="region-overlay">

                <span>CHUGOKU</span>

                <strong>中国</strong>

            </div>

        </a>


        <!-- =========================
             SHIKOKU
        ========================== -->

        <a href="<%=request.getContextPath()%>/festival/list?region=shikoku"
           class="region-card">

            <img src="<%=request.getContextPath()%>/assets/festival/images/region/shikoku.jpg"
                 alt="四国">

            <div class="region-overlay">

                <span>SHIKOKU</span>

                <strong>四国</strong>

            </div>

        </a>


        <!-- =========================
             KYUSHU OKINAWA
        ========================== -->

        <a href="<%=request.getContextPath()%>/festival/list?region=kyushu-okinawa"
           class="region-card">

            <img src="<%=request.getContextPath()%>/assets/festival/images/region/kyushu-okinawa.jpg"
                 alt="九州・沖縄">

            <div class="region-overlay">

                <span>KYUSHU・OKINAWA</span>

                <strong>九州・沖縄</strong>

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
