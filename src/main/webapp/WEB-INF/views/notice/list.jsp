<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="ja">

<head>

<meta charset="UTF-8">

<meta http-equiv="Content-Language" content="ja">

<meta name="viewport"
      content="width=device-width, initial-scale=1.0">

<title>Notice | ATSUMARI</title>

<link rel="stylesheet"
      href="<%=request.getContextPath()%>/assets/notice/css/list.css">

</head>

<body>

<!-- =========================
     HEADER
========================== -->

<%@ include file="/WEB-INF/views/common/header.jsp" %>


<!-- =========================
     BOARD
========================== -->

<main class="board-page">

    <div class="board-inner">


        <!-- =========================
             TITLE
        ========================== -->

        <div class="board-title">

            <span>NOTICE</span>

            <h1>公知事項</h1>

            <p>
                ATSUMARIからのお知らせをご案内します。
            </p>

        </div>


        <!-- =========================
             SEARCH
        ========================== -->

        <div class="board-top">

            <p>
                全 <strong>5</strong> 件
            </p>


            <div class="board-search">

                <select>

                    <option>タイトル</option>

                    <option>内容</option>

                </select>


                <input
                    type="text"
                    placeholder="検索してください">


                <button type="button">
                    検索
                </button>

            </div>

        </div>


        <!-- =========================
             BOARD LIST
        ========================== -->

        <div class="board-list">


            <!-- =========================
                 HEADER
            ========================== -->

            <div class="board-header">

                <!-- 번호 영역 -->

                <span class="board-no"></span>


                <!-- 제목 -->

                <span class="board-subject">
                    タイトル
                </span>


                <!-- 내용 -->

                <span class="board-content">
                    内容
                </span>


                <!-- 작성일 -->

                <span class="board-date">
                    作成日
                </span>

            </div>


            <!-- =========================
                 NOTICE 01
            ========================== -->

            <div class="board-item">


                <!-- LIST ROW -->

                <div class="board-row">

                    <!-- 번호 영역 -->

                    <span class="board-no"></span>


                    <!-- 제목 -->

                    <span class="board-subject">
                        ATSUMARIサイトオープンのお知らせ
                    </span>


                    <!-- 내용 -->

                    <span class="board-content">
                        日本の祭り情報サイト「ATSUMARI」をオープンしました。
                    </span>


                    <!-- 작성일 -->

                    <span class="board-date">
                        2026.08.24
                    </span>

                </div>


                <!-- DETAIL -->

                <div class="board-detail">

                    <div class="board-detail-inner">


                        <!-- 상세 제목 -->

                        <div class="detail-title">

                            ATSUMARIサイトオープンのお知らせ

                        </div>


                        <!-- 상세 내용 -->

                        <div class="detail-content">

                            こんにちは。ATSUMARIです。<br><br>

                            日本全国の祭り情報をより便利に確認できるよう、
                            日本の祭り情報サイト「ATSUMARI」をオープンしました。<br><br>

                            今後もより便利なサービスを提供できるよう、
                            サイトの改善と情報の充実に努めてまいります。

                        </div>


                        <!-- 작성일 -->

                        <div class="detail-date">

                            2026.08.24

                        </div>


                    </div>

                </div>

            </div>


            <!-- =========================
                 NOTICE 02
            ========================== -->

            <div class="board-item">


                <div class="board-row">

                    <span class="board-no"></span>


                    <span class="board-subject">

                        サイトメンテナンスのお知らせ

                    </span>


                    <span class="board-content">

                        サイトメンテナンスの実施についてお知らせします。

                    </span>


                    <span class="board-date">

                        2026.08.23

                    </span>

                </div>


                <!-- DETAIL -->

                <div class="board-detail">

                    <div class="board-detail-inner">


                        <div class="detail-title">

                            サイトメンテナンスのお知らせ

                        </div>


                        <div class="detail-content">

                            より安定したサービスを提供するため、
                            サイトメンテナンスを実施いたします。<br><br>

                            メンテナンス中は一部のサービスを
                            ご利用いただけない場合があります。

                        </div>


                        <div class="detail-date">

                            2026.08.23

                        </div>


                    </div>

                </div>

            </div>


            <!-- =========================
                 NOTICE 03
            ========================== -->

            <div class="board-item">


                <div class="board-row">

                    <span class="board-no"></span>


                    <span class="board-subject">

                        祭り情報を更新しました

                    </span>


                    <span class="board-content">

                        全国の祭り情報を追加・更新しました。

                    </span>


                    <span class="board-date">

                        2026.08.22

                    </span>

                </div>


                <!-- DETAIL -->

                <div class="board-detail">

                    <div class="board-detail-inner">


                        <div class="detail-title">

                            祭り情報を更新しました

                        </div>


                        <div class="detail-content">

                            全国各地で開催される祭りの情報を追加しました。<br><br>

                            開催日程や地域などの情報を
                            順次更新していく予定です。

                        </div>


                        <div class="detail-date">

                            2026.08.22

                        </div>


                    </div>

                </div>

            </div>


            <!-- =========================
                 NOTICE 04
            ========================== -->

            <div class="board-item">


                <div class="board-row">

                    <span class="board-no"></span>


                    <span class="board-subject">

                        サービス利用に関するお知らせ

                    </span>


                    <span class="board-content">

                        ATSUMARIのサービス利用に関するご案内です。

                    </span>


                    <span class="board-date">

                        2026.08.21

                    </span>

                </div>


                <!-- DETAIL -->

                <div class="board-detail">

                    <div class="board-detail-inner">


                        <div class="detail-title">

                            サービス利用に関するお知らせ

                        </div>


                        <div class="detail-content">

                            ATSUMARIでは日本全国の祭り情報を提供しています。<br><br>

                            より快適にサービスをご利用いただくため、
                            定期的にサイトの改善を行っています。

                        </div>


                        <div class="detail-date">

                            2026.08.21

                        </div>


                    </div>

                </div>

            </div>


            <!-- =========================
                 NOTICE 05
            ========================== -->

            <div class="board-item">


                <div class="board-row">

                    <span class="board-no"></span>


                    <span class="board-subject">

                        サイト利用規約について

                    </span>


                    <span class="board-content">

                        ATSUMARIの利用規約を更新しました。

                    </span>


                    <span class="board-date">

                        2026.08.20

                    </span>

                </div>


                <!-- DETAIL -->

                <div class="board-detail">

                    <div class="board-detail-inner">


                        <div class="detail-title">

                            サイト利用規約について

                        </div>


                        <div class="detail-content">

                            ATSUMARIのサービス利用規約を更新しました。<br><br>

                            サービスをご利用の際は、
                            最新の利用規約をご確認ください。

                        </div>


                        <div class="detail-date">

                            2026.08.20

                        </div>


                    </div>

                </div>

            </div>


        </div>


        <!-- =========================
             PAGING
        ========================== -->

        <div class="board-pagination">

            <a href="#" class="page-prev">
                ←
            </a>

            <a href="#" class="active">
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

            <a href="#" class="page-next">
                →
            </a>

        </div>


        <!-- =========================
             WRITE
        ========================== -->

        <div class="board-write">

            <a href="#"
               class="write-button">

                投稿する

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


<!-- =========================
     SCRIPT
========================== -->

<script src="<%=request.getContextPath()%>/assets/notice/js/board-row.js"></script>

</body>

</html>
