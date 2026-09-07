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

    <a href="festival_list.jsp">

        ← 一覧へ戻る

    </a>

</div>


<!-- =========================
     FESTIVAL HEADER
========================== -->

<section class="festival-view-header">


    <!-- CATEGORY -->

    <span class="festival-season">

        WINTER

    </span>


    <!-- TITLE -->

    <h1>

        さっぽろ雪まつり

    </h1>


    <!-- BASIC INFO -->

    <div class="festival-basic-info">


        <!-- DATE -->

        <div class="festival-basic-item">

            <span class="info-label">

                開催期間

            </span>

            <strong>

                2026.02.04 ～ 2026.02.11

            </strong>

        </div>


        <!-- LOCATION -->

        <div class="festival-basic-item">

            <span class="info-label">

                開催地域

            </span>

            <strong>

                北海道・札幌市

            </strong>

        </div>


        <!-- PLACE -->

        <div class="festival-basic-item">

            <span class="info-label">

                開催場所

            </span>

            <strong>

                大通公園・すすきの会場 ほか

            </strong>

        </div>


    </div>


</section>



<!-- =========================
     MAIN IMAGE
========================== -->

<section class="festival-main-image">

    <img src="<%=request.getContextPath()%>/images/festival_01.jpg"
         alt="さっぽろ雪まつり">

</section>



<!-- =========================
     FESTIVAL CONTENT
========================== -->

<section class="festival-detail-content">


    <!-- INTRODUCTION -->

    <div class="festival-content-section">

        <span class="section-label">

            ABOUT FESTIVAL

        </span>


        <h2>

            さっぽろ雪まつりについて

        </h2>


        <p>

            さっぽろ雪まつりは、北海道札幌市で開催される
            日本を代表する冬の祭りです。

            <br><br>

            会場には巨大な雪像や氷像が並び、
            毎年多くの観光客が訪れます。

            <br><br>

            昼間は迫力ある雪像を楽しむことができ、
            夜になるとライトアップによって
            幻想的な景色が広がります。

        </p>

    </div>



    <!-- PROGRAM -->

    <div class="festival-content-section">

        <span class="section-label">

            HIGHLIGHTS

        </span>


        <h2>

            見どころ

        </h2>


        <ul class="festival-highlight-list">

            <li>

                大迫力の巨大雪像

            </li>

            <li>

                夜を彩るライトアップ

            </li>

            <li>

                氷の彫刻が並ぶすすきの会場

            </li>

            <li>

                北海道ならではのグルメ

            </li>

        </ul>

    </div>



    <!-- EVENT INFORMATION -->

    <div class="festival-content-section">

        <span class="section-label">

            INFORMATION

        </span>


        <h2>

            開催情報

        </h2>


        <div class="festival-information">


            <div class="information-row">

                <span>

                    祭り名

                </span>

                <strong>

                    さっぽろ雪まつり

                </strong>

            </div>


            <div class="information-row">

                <span>

                    開催期間

                </span>

                <strong>

                    2026年2月4日 ～ 2月11日

                </strong>

            </div>


            <div class="information-row">

                <span>

                    開催地域

                </span>

                <strong>

                    北海道札幌市

                </strong>

            </div>


            <div class="information-row">

                <span>

                    開催場所

                </span>

                <strong>

                    大通公園・すすきの会場 ほか

                </strong>

            </div>


            <div class="information-row">

                <span>

                    主催

                </span>

                <strong>

                    さっぽろ雪まつり実行委員会

                </strong>

            </div>


        </div>

    </div>


</section>



<!-- =========================
     LIST BUTTON
========================== -->

<div class="festival-view-bottom">

    <a href="festival-list"
       class="festival-list-button">

        祭り一覧へ

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
