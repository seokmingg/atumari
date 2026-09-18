<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">

<head>

    <meta charset="UTF-8">
    <meta http-equiv="Content-Language" content="ja">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Festival Search | ATSUMARI</title>

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

                <span>FESTIVAL SEARCH</span>

                <h1>祭りを検索</h1>

                <p>
                    <c:choose>

                        <c:when test="${not empty startDate and not empty endDate}">
                            ${startDate} ～ ${endDate}の祭りをご紹介します。
                        </c:when>

                        <c:when test="${not empty keyword}">
                            「${keyword}」の祭りをご紹介します。
                        </c:when>

                        <c:otherwise>
                            検索結果をご紹介します。
                        </c:otherwise>

                    </c:choose>
                </p>

            </div>



            <!-- =========================
                 SEARCH RESULT INFO
            ========================== -->

            <div class="festival-top">

                <!-- LEFT -->

                <div class="festival-count-area">

                    <p class="festival-count">
                        全 <strong>${festivalList.size()}</strong> 件
                    </p>

                </div>


                <!-- SEARCH -->

                <div class="festival-search">

                    <input
                        type="text"
                        placeholder="祭りを検索してください"
                        id="searchInput"
                        value="${keyword}">

                    <button type="button" id="searchButton">
                        検索
                    </button>

                </div>

            </div>



            <!-- =========================
                 FESTIVAL LIST
            ========================== -->

            <div class="festival-list">

                <c:forEach var="festival" items="${festivalList}">

                    <a
                        href="${pageContext.request.contextPath}/festival/view?festival_no=${festival.festival_no}"
                        class="festival-item">


                        <!-- IMAGE -->

                        <div class="festival-image">

                            <img
                                src="${festival.image_url}"
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
                                    ${festival.prefecture_name}
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


                <!-- =========================
                     NO RESULT
                ========================== -->

                <c:if test="${empty festivalList}">

                    <div class="festival-no-result">

                        <p>検索条件に一致する祭りがありません。</p>

                    </div>

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