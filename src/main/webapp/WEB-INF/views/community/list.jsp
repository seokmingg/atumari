<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="ja">

<head>

<meta charset="UTF-8">

<meta http-equiv="Content-Language" content="ja">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Community | ATSUMARI</title>

<link rel="stylesheet"
      href="<%=request.getContextPath()%>/assets/community/css/list.css">

</head>


<body>


<!-- =========================
     HEADER
========================== -->

<%@ include file="/WEB-INF/views/common/header.jsp" %>


<!-- =========================
     COMMUNITY PAGE
========================== -->

<main class="community-page">

    <div class="community-inner">


        <!-- =========================
             PAGE TITLE
        ========================== -->

        <div class="community-title">

            <span>COMMUNITY</span>

            <h1>コミュニティ</h1>

            <p>
                日本の祭りについて自由に情報を共有しましょう。
            </p>

        </div>



        <!-- =========================
             SEARCH AREA
        ========================== -->

        <div class="community-search-area">


            <!-- 검색 조건 -->

            <div class="community-search">

                <select name="search">

                    <option value="content">
                        内容
                    </option>

                    <option value="title">
                        タイトル
                    </option>

                    <option value="title_content">
                        タイトル＋内容
                    </option>

                    <option value="writer">
                        投稿者
                    </option>

                </select>


                <input
                    type="text"
                    placeholder="検索してください">


                <button type="button">

                    検索

                </button>

            </div>


            <!-- 한 페이지 게시글 수 -->

            <div class="post-count">

                <span>
                    表示件数
                </span>

                <select>

                    <option>
                        10件
                    </option>

                    <option>
                        20件
                    </option>

                    <option>
                        30件
                    </option>

                    <option>
                        50件
                    </option>

                </select>

            </div>


        </div>



        <!-- =========================
             BOARD TOP
        ========================== -->

        <div class="community-top">

            <p>

                全

                <strong>
                    128
                </strong>

                件

            </p>


            

        </div>



        <!-- =========================
             POPULAR POSTS
        ========================== -->

        <section class="popular-section">


            <div class="popular-title">

                <span>
                    HOT
                </span>

                <h2>
                    人気の投稿
                </h2>

            </div>


            <div class="popular-list">


                <!-- 인기글 1 -->

                <a href="<%=request.getContextPath()%>/community/view"
                   class="popular-item">


                    <div class="popular-number">

                        1

                    </div>


                    <div class="popular-content">

                        <strong>
                            初めて京都の祇園祭に行ってきました！
                        </strong>

                        <span>
                            初めて参加した感想やおすすめの楽しみ方を紹介します。
                        </span>

                    </div>


                    <div class="popular-info">

                        <span>
                            👁 1,248
                        </span>

                        <span>
                            ♥ 86
                        </span>

                    </div>


                </a>



                <!-- 인기글 2 -->

                <a href="<%=request.getContextPath()%>/community/view"
                   class="popular-item">


                    <div class="popular-number">

                        2

                    </div>


                    <div class="popular-content">

                        <strong>
                            東京でおすすめの夏祭りを教えてください
                        </strong>

                        <span>
                            初めて東京の夏祭りに参加する予定です。
                        </span>

                    </div>


                    <div class="popular-info">

                        <span>
                            👁 986
                        </span>

                        <span>
                            ♥ 72
                        </span>

                    </div>


                </a>



                <!-- 인기글 3 -->

                <a href="<%=request.getContextPath()%>/community/view"
                   class="popular-item">


                    <div class="popular-number">

                        3

                    </div>


                    <div class="popular-content">

                        <strong>
                            日本全国のおすすめ祭りをまとめました
                        </strong>

                        <span>
                            実際に参加した祭りを地域別に紹介します。
                        </span>

                    </div>


                    <div class="popular-info">

                        <span>
                            👁 842
                        </span>

                        <span>
                            ♥ 61
                        </span>

                    </div>


                </a>


            </div>


        </section>



        <!-- =========================
             COMMUNITY LIST
        ========================== -->

        <div class="community-list">


            <!-- =========================
                 HEADER
            ========================== -->

            <div class="community-header">


                <span class="list-title">
                    タイトル
                </span>


                <span class="list-writer">
                    投稿者
                </span>


                <span class="list-date">
                    投稿日
                </span>


                <span class="list-view">
                    閲覧
                </span>


                <span class="list-like">
                    いいね
                </span>


            </div>



            <!-- =========================
                 POST 01
            ========================== -->

            <a href="<%=request.getContextPath()%>/community/view"
               class="community-row">


                <div class="list-title">

                    初めて日本の祭りに参加しました！

                </div>


                <div class="list-writer">

                    祭り好き

                </div>


                <div class="list-date">

                    2026-09-04

                </div>


                <div class="list-view">

                    328

                </div>


                <div class="list-like">

                    ♥ 24

                </div>


            </a>



            <!-- =========================
                 POST 02
            ========================== -->

            <a href="<%=request.getContextPath()%>/community/view"
               class="community-row">


                <div class="list-title">

                    大阪でおすすめの祭りを教えてください

                </div>


                <div class="list-writer">

                    大阪旅行中

                </div>


                <div class="list-date">

                    2026-09-03

                </div>


                <div class="list-view">

                    217

                </div>


                <div class="list-like">

                    ♥ 18

                </div>


            </a>



            <!-- =========================
                 POST 03
            ========================== -->

            <a href="<%=request.getContextPath()%>/community/view"
               class="community-row">


                <div class="list-title">

                    秋に開催されるおすすめの祭り

                </div>


                <div class="list-writer">

                    秋祭り

                </div>


                <div class="list-date">

                    2026-09-02

                </div>


                <div class="list-view">

                    185

                </div>


                <div class="list-like">

                    ♥ 12

                </div>


            </a>



            <!-- =========================
                 POST 04
            ========================== -->

            <a href="<%=request.getContextPath()%>/community/view"
               class="community-row">


                <div class="list-title">

                    祭りに行くときの服装について

                </div>


                <div class="list-writer">

                    夏休み

                </div>


                <div class="list-date">

                    2026-09-01

                </div>


                <div class="list-view">

                    154

                </div>


                <div class="list-like">

                    ♥ 9

                </div>


            </a>



            <!-- =========================
                 POST 05
            ========================== -->

            <a href="<%=request.getContextPath()%>/community/view"
               class="community-row">


                <div class="list-title">

                    初心者におすすめの日本の祭りはありますか？

                </div>


                <div class="list-writer">

                    日本旅行

                </div>


                <div class="list-date">

                    2026-08-31

                </div>


                <div class="list-view">

                    132

                </div>


                <div class="list-like">

                    ♥ 7

                </div>


            </a>
				
				

        </div>
        
        <div class="community-write">
			<a href="#"
               class="write-button">

                投稿する

            </a>
		</div>

        <!-- =========================
             PAGINATION
        ========================== -->

        <div class="community-pagination">


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


            <a href="#">

                4

            </a>


            <a href="#">

                5

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
