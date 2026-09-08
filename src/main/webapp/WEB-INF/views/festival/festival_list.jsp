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

        <h1>${regionName}の祭り</h1>

        <p>
            ${regionName}で開催される祭りをご紹介します。
        </p>

    </div>


    <!-- =========================
         SEARCH / FILTER
    ========================== -->

    <div class="festival-top">


        <!-- TOTAL -->

        <p class="festival-count">

            全 <strong>${festivalList.size()}</strong> 件

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

    <c:forEach var="festival" items="${festivalList}">

        <a href="<%=request.getContextPath()%>/festival/view?festival_no=${festival.festival_no}"
           class="festival-item">

            <!-- IMAGE -->
            <div class="festival-image">

                <img src="${festival.image_url}"
                     alt="${festival.festival_name}">

            </div>


            <!-- CONTENT -->
            <div class="festival-content">

                <!-- CATEGORY -->
                <span class="festival-category">
                    ${festival.season}
                </span>


                <!-- TITLE -->
                <h2>
                    ${festival.festival_name}
                </h2>


                <!-- DESCRIPTION -->
                <p class="festival-description">
                    ${festival.summary}
                </p>


                <!-- INFO -->
                <div class="festival-info">

                    <span class="festival-location">
                        ${regionName}
                    </span>


                    <span class="festival-date">

                       ${festival.dateRange}

                    </span>

                </div>

            </div>


            <!-- ARROW -->
            <div class="festival-arrow">
                →
            </div>

        </a>

    </c:forEach>

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
