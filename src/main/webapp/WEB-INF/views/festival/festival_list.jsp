<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="ja">

<head>

<meta charset="UTF-8">

<meta http-equiv="Content-Language" content="ja">

<meta name="viewport"
      content="width=device-width, initial-scale=1.0">

<title>Festival List | ATSUMARI</title>

<link rel="stylesheet"
      href="<%=request.getContextPath()%>/assets/festival/css/festival_list.css">

</head>

<body>

<!-- =========================
     HEADER
========================== -->

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<!-- =========================
     FESTIVAL LIST
========================== -->

<main class="festival-page">

<div class="festival-inner">


    <!-- =========================
         TITLE
    ========================== -->

    <div class="festival-title">

        <span>FESTIVAL</span>

        <h1>北海道の祭り</h1>

        <p>
            北海道で開催される祭りをご紹介します。
        </p>

    </div>


    <!-- =========================
         SEARCH / FILTER
    ========================== -->

    <div class="festival-top">


        <!-- TOTAL -->

        <p class="festival-count">

            全 <strong>24</strong> 件

        </p>


        <!-- SEARCH -->

        <div class="festival-search">

            <select>

                <option>すべて</option>

                <option>祭り名</option>

                <option>開催地域</option>

            </select>


            <input type="text"
                   placeholder="祭りを検索してください">


            <button type="button">

                検索

            </button>

        </div>


    </div>


    <!-- =========================
         CATEGORY FILTER
    ========================== -->

    <div class="festival-filter">

        <button type="button"
                class="active">

            すべて

        </button>


        <button type="button">

            札幌市

        </button>


        <button type="button">

            函館市

        </button>


        <button type="button">

            旭川市

        </button>


        <button type="button">

            小樽市

        </button>
        
        <button type="button">

            釧路市

        </button>
        
        <button type="button">

           帯広市

        </button>
        
        <button type="button">

            北見市

        </button>
        
        <button type="button">

            稚内市

        </button>
        
        <button type="button">

            富良野市

        </button>
        
        <button type="button">

            千歳市

        </button>

    </div>


    <!-- =========================
         FESTIVAL LIST
    ========================== -->

    <div class="festival-list">


        <!-- =========================
             FESTIVAL 01
        ========================== -->

        <a href="festival-view"
           class="festival-item">


            <!-- IMAGE -->

            <div class="festival-image">

                <img src="<%=request.getContextPath()%>/images/festival_01.jpg"
                     alt="さっぽろ雪まつり">

            </div>


            <!-- CONTENT -->

            <div class="festival-content">


                <!-- CATEGORY -->

                <span class="festival-category">

                    WINTER

                </span>


                <!-- TITLE -->

                <h2>

                    さっぽろ雪まつり

                </h2>


                <!-- DESCRIPTION -->

                <p class="festival-description">

                    北海道札幌市で開催される日本を代表する冬の祭りです。
                    会場には迫力ある雪像や氷像が並び、
                    多くの観光客が訪れます。

                </p>


                <!-- INFO -->

                <div class="festival-info">


                    <span class="festival-location">

                        北海道・札幌市

                    </span>


                    <span class="festival-date">

                        2026.02.04 ～ 2026.02.11

                    </span>


                </div>


            </div>


            <!-- ARROW -->

            <div class="festival-arrow">

                →

            </div>


        </a>


        <!-- =========================
             FESTIVAL 02
        ========================== -->

        <a href="festival-view"
           class="festival-item">


            <div class="festival-image">

                <img src="<%=request.getContextPath()%>/images/festival_02.jpg"
                     alt="旭川夏まつり">

            </div>


            <div class="festival-content">


                <span class="festival-category">

                    SUMMER

                </span>


                <h2>

                    旭川夏まつり

                </h2>


                <p class="festival-description">

                    北海道旭川市で開催される夏の祭りです。
                    地域ならではのイベントやパレードが行われ、
                    多くの人で賑わいます。

                </p>


                <div class="festival-info">


                    <span class="festival-location">

                        北海道・旭川市

                    </span>


                    <span class="festival-date">

                        2026.07.30 ～ 2026.08.01

                    </span>


                </div>


            </div>


            <div class="festival-arrow">

                →

            </div>


        </a>


        <!-- =========================
             FESTIVAL 03
        ========================== -->

        <a href="festival-view"
           class="festival-item">


            <div class="festival-image">

                <img src="<%=request.getContextPath()%>/images/festival_03.jpg"
                     alt="函館港まつり">

            </div>


            <div class="festival-content">


                <span class="festival-category">

                    SUMMER

                </span>


                <h2>

                    函館港まつり

                </h2>


                <p class="festival-description">

                    函館の夏を彩る代表的な祭りです。
                    華やかなパレードや地域イベントが開催され、
                    多くの人々が集まります。

                </p>


                <div class="festival-info">


                    <span class="festival-location">

                        北海道・函館市

                    </span>


                    <span class="festival-date">

                        2026.08.01 ～ 2026.08.05

                    </span>


                </div>


            </div>


            <div class="festival-arrow">

                →

            </div>


        </a>


        <!-- =========================
             FESTIVAL 04
        ========================== -->

        <a href="festival-view"
           class="festival-item">


            <div class="festival-image">

                <img src="<%=request.getContextPath()%>/images/festival_04.jpg"
                     alt="小樽雪あかりの路">

            </div>


            <div class="festival-content">


                <span class="festival-category">

                    WINTER

                </span>


                <h2>

                    小樽雪あかりの路

                </h2>


                <p class="festival-description">

                    小樽の街並みを温かな灯りが包む冬のイベントです。
                    雪景色と幻想的な光が美しい風景を作り出します。

                </p>


                <div class="festival-info">


                    <span class="festival-location">

                        北海道・小樽市

                    </span>


                    <span class="festival-date">

                        2026.02.07 ～ 2026.02.14

                    </span>


                </div>


            </div>


            <div class="festival-arrow">

                →

            </div>


        </a>


        <!-- =========================
             FESTIVAL 05
        ========================== -->

        <a href="festival-view"
           class="festival-item">


            <div class="festival-image">

                <img src="<%=request.getContextPath()%>/images/festival_05.jpg"
                     alt="YOSAKOIソーラン祭り">

            </div>


            <div class="festival-content">


                <span class="festival-category">

                    SUMMER

                </span>


                <h2>

                    YOSAKOIソーラン祭り

                </h2>


                <p class="festival-description">

                    札幌市で開催される大規模な踊りの祭典です。
                    全国から集まったチームが迫力ある演舞を披露します。

                </p>


                <div class="festival-info">


                    <span class="festival-location">

                        北海道・札幌市

                    </span>


                    <span class="festival-date">

                        2026.06.03 ～ 2026.06.07

                    </span>


                </div>


            </div>


            <div class="festival-arrow">

                →

            </div>


        </a>


    </div>


    <!-- =========================
         PAGINATION
    ========================== -->

    <div class="festival-pagination">

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


        <a href="#"
           class="page-next">

            →

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
