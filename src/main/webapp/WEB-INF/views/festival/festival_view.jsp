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

        <a href="<%=request.getContextPath()%>/festival/list">

            ← 一覧へ戻る

        </a>

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

                    ${festival.startDateTime}
                    ～
                    ${festival.endDateTime}

                </strong>

            </div>


            <!-- LOCATION -->

            <div class="festival-basic-item">

                <span class="info-label">

                    開催地域

                </span>

                <strong>

                    ${festival.prefecture_no}

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

                        ${festival.startDateTime}
                        ～
                        ${festival.endDateTime}

                    </strong>

                </div>


                <!-- PREFECTURE -->

                <div class="information-row">

                    <span>

                        都道府県

                    </span>

                    <strong>

                        ${festival.prefecture_no}

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
         LIST BUTTON
    ========================== -->

    <div class="festival-view-bottom">

        <a href="<%=request.getContextPath()%>/festival/list"
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