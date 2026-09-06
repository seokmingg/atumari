<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="<%=request.getContextPath()%>/assets/common/js/header.js"></script>    
   
<html lang="ja">

<head>
	<meta http-equiv="Content-Language" content="ja">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>ATSUMARI - 日本の祭り情報</title>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/home/css/home.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/common/css/header.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/common/css/common.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/common/css/calendar.css">
    
</head>

<body>

    <!-- =========================
         HEADER
    ========================== -->

<header class="header">

    <!-- =========================
         HEADER TOP
    ========================== -->

    <div class="header-top">

        <!-- LOGO -->
        <a href="<%=request.getContextPath()%>/" class="logo">
            <div class="logo-main">あつまり</div>
            <div class="logo-sub">MATSURI GUIDE</div>
        </a>

        <!-- NAV -->
        <nav class="nav">
            <a href="#festival-section">今月の祭り</a>
            <a href="#article">季節から探す</a>
            <a href="#region">地域から探す</a>
            <a href="<%=request.getContextPath()%>/notices">公知事項</a>
            <a href="<%=request.getContextPath()%>/community">コミュニティ</a>
        </nav>

        <!-- MEMBER -->
        <div class="nav">
            <a href="<%=request.getContextPath()%>/my-info">
                田中 太郎 様
            </a>
        </div>

        <!-- LOGIN -->
        <div class="nav member-nav">
            <a href="<%=request.getContextPath()%>/login">
                ログイン
            </a>

            <span>|</span>

            <a href="<%=request.getContextPath()%>/signup">
                会員登録
            </a>
        </div>

       

</header>


<!-- =========================
     HERO
========================== -->

<section class="hero">

    <script src="<%=request.getContextPath()%>/assets/home/js/search.js"></script>

    <div class="hero-content">

        <!-- =========================
             HERO TEXT
        ========================== -->

        <div class="hero-text">

            <span class="hero-label">
                JAPAN FESTIVAL GUIDE
            </span>

            <h1>
                日本の祭りに、<br>
                出会う旅。
            </h1>

            <p>
                全国各地の祭りから、<br>
                あなたの特別な一日を見つけよう。
            </p>

        </div>


        <!-- =========================
             HERO SEARCH WRAP
        ========================== -->

        <div class="hero-search-wrap">

            <!-- =========================
                 SEARCH BAR
            ========================== -->

            <div class="hero-search">

                <!-- FESTIVAL KEYWORD -->

                <div class="hero-keyword">

                    <span>FESTIVAL</span>

                    <input
                        type="text"
                        placeholder="祭りの名前を検索"
                    >

                </div>


                <!-- DATE -->

                <div class="hero-date-wrap">

                    <button  type="button" class="hero-date">
                       
                        <span>DATE</span>

                         <div class="date-range">

					        <strong class="date-start">日付を選択</strong>
					
					        <span class="date-arrow">→</span>
					
					        <strong class="date-end"></strong>
					
					    </div>


                    </button>

                </div>


                <!-- SEARCH BUTTON -->

                <button
                    type="button"
                    class="hero-search-button"
                >
                    SEARCH
                </button>

            </div>


            <!-- =========================
                 SEARCH PANEL
            ========================== -->

            <div class="hero-search-panel">

                <!-- =========================
                     FESTIVAL PANEL
                ========================== -->

                <div class="search-panel-content keyword-panel">

                    <h3>
                        おすすめの祭り
                    </h3>

                    <div class="keyword-list">

                        <button type="button">
                            青森ねぶた祭
                        </button>

                        <button type="button">
                            京都祇園祭
                        </button>

                        <button type="button">
                            博多祇園山笠
                        </button>

                    </div>


                    <h4>
                        人気のキーワード
                    </h4>

                    <div class="keyword-tags">

                        <button type="button">
                            #夏祭り
                        </button>

                        <button type="button">
                            #花火
                        </button>

                        <button type="button">
                            #伝統祭り
                        </button>

                        <button type="button">
                            #秋祭り
                        </button>

                    </div>

                </div>


                <!-- =========================
                     DATE PANEL
                ========================== -->

                <div class="search-panel-content date-panel">
                
		                 <div class="date-guide">
					        <span>FIND YOUR DATE</span>
					        <h3>日付を<br>選んでください。</h3>
					        <p>
					            欲しい日付を選んだら<br>
					            この日付の祭りの情報を<br>
					            確認することができます。
					        </p>
					    </div>

                    <div class="hero-calendar">

                        <!-- CALENDAR HEADER -->

                        <div class="calendar-header">

                            <button
                                type="button"
                                class="calendar-prev"
                            >
                                ‹
                            </button>

                            <strong class="calendar-title">
                                2026年 8月
                            </strong>

                            <button
                                type="button"
                                class="calendar-next"
                            >
                                ›
                            </button>

                        </div>


                        <!-- WEEK -->

                        <div class="calendar-week">

                            <span>日</span>
                            <span>月</span>
                            <span>火</span>
                            <span>水</span>
                            <span>木</span>
                            <span>金</span>
                            <span>土</span>

                        </div>


                        <!-- DAYS -->

                        <div class="calendar-days"></div>

                    </div>

                </div>

            </div>

        </div>

    </div>

</section>







<!-- =========================
     SEARCH
========================== -->

<section class="search-section">

    <div class="section-inner">

        <h2 class="section-title">
            祭りを探す
        </h2>

        <div class="search-list">

            <a class="search-item" href="">

                <h3>地域から探す</h3>

                <p>
                    全国の祭りを見る
                </p>

            </a>


            <a class="search-item" href="">

                <h3>季節から探す</h3>

                <p>
                    季節ごとの祭りを見る
                </p>

            </a>


            <a class="search-item" href="">

                <h3>キーワードから探す</h3>

                <p>
                    祭りを検索する
                </p>

            </a>

        </div>

    </div>

</section>


    <!-- =========================
         THIS MONTH
    ========================== -->
   <section class="festival-section" id="festival-section">

    <div class="section-inner">

        <h2 class="section-title">
            今月の祭り
        </h2>


        <div class="festival-slider-wrap">

            <button type="button"
                    class="festival-prev"
                    aria-label="Previous">
                ←
            </button>


            <div class="festival-slider">

                <div class="festival-list">


                    <!-- FESTIVAL 01 -->

                    <div class="festival-card">
						<a href="">
                        <img src="<%=request.getContextPath()%>/assets/home/images/festival/festival01.jpg"
                             alt="青森ねぶた祭">
						</a>
                        <div class="festival-info">

                            <h3>青森ねぶた祭</h3>

                            <p>青森県</p>

                        </div>

                    </div>


                    <!-- FESTIVAL 02 -->

                    <div class="festival-card">
						<a href="">
                        <img src="<%=request.getContextPath()%>/assets/home/images/festival/festival02.jpg"
                             alt="阿波おどり">
						</a>
                        <div class="festival-info">

                            <h3>阿波おどり</h3>

                            <p>徳島県</p>

                        </div>

                    </div>


                    <!-- FESTIVAL 03 -->

                    <div class="festival-card">
						<a href="">
                        <img src="<%=request.getContextPath()%>/assets/home/images/festival/festival03.jpg"
                             alt="仙台七夕まつり">
						</a>
                        <div class="festival-info">

                            <h3>仙台七夕まつり</h3>

                            <p>宮城県</p>

                        </div>

                    </div>
                    
                    <div class="festival-card">
						<a href="">
                        <img src="<%=request.getContextPath()%>/assets/home/images/festival/festival04.jpg"
                             alt="仙台七夕まつり">
						</a>
                        <div class="festival-info">

                            <h3>海辺の祭り</h3>

                            <p>沖縄</p>

                        </div>

                    </div>


                </div>

            </div>


            <button type="button"
                    class="festival-next"
                    aria-label="Next">
                →
            </button>

        </div>

    </div>

</section>


     <!-- =========================
         ARTICLES
    ========================== -->
<section class="article-section" id="article">


    <div class="section-inner">

        <h2 class="section-title">
            季節別祭り
        </h2>


        <div class="article-slider-wrap">

            <button type="button"
                    class="article-prev"
                    aria-label="Previous">
                ←
            </button>


            <div class="article-slider">

                <div class="article-list">

                    <article class="article-card">
						<a href="">
	                        <img src="<%=request.getContextPath()%>/assets/home/images/season/spring.jpg"
	                             alt="日本の祭り">
						</a>
                        <div class="article-info">

                            <span>春</span>

                            <h3>
                                春の祭り
                            </h3>

                            <p>
                                春の祭りについて
                            </p>

                        </div>

                    </article>


                    <article class="article-card">
						<a href="">
	                        <img src="<%=request.getContextPath()%>/assets/home/images/season/summer.jpg"
	                             alt="神輿">
						</a>
                        <div class="article-info">

                            <span>夏</span>

                            <h3>
                                夏の祭り
                            </h3>

                            <p>
                                夏の祭りについて
                            </p>

                        </div>

                    </article>


                    <article class="article-card">
						<a href="">
	                        <img src="<%=request.getContextPath()%>/assets/home/images/season/fall.jpg"
	                             alt="祭り">
						</a>
                        <div class="article-info">

                            <span>秋</span>

                            <h3>
                                秋の祭り
                            </h3>

                            <p>
                                秋の祭りについて
                            </p>

                        </div>

                    </article>


                    <article class="article-card">
					<a href="">
                        <img src="<%=request.getContextPath()%>/assets/home/images/season/winter.jpg"
                             alt="冬祭り">
					</a>
                        <div class="article-info">

                            <span>冬</span>

                            <h3>
                                冬の祭り
                            </h3>

                            <p>
                                冬の祭りについて
                            </p>

                        </div>

                    </article>

                </div>

            </div>


            <button type="button"
                    class="article-next"
                    aria-label="Next">
                →
            </button>

        </div>

    </div>

</section>
<script src="<%=request.getContextPath()%>/assets/home/js/card-page.js"></script>



    <!-- =========================
         map
    ========================== -->
    <section class="region-section" id="region">

    <div class="region-content">

    <!-- 왼쪽 설명 -->
    <div class="region-info" id="regionInfo">


        <h3 id="regionName">日本全国</h3>

        <p id="regionDescription">
            日本各地で開催される祭りをご紹介します。
        </p>

    </div>


        <div class="japan-map">
		    <object
		        id="japanMap"
		        type="image/svg+xml"
		        data="<%=request.getContextPath()%>/assets/home/images/map-full.svg">
		    </object>
		</div>
		<script src="<%=request.getContextPath()%>/assets/home/js/map.js"></script>


    </div>

</section>


 


 <!-- =========================
     FOOTER
========================== -->

<footer class="footer">

 <%@ include file="/WEB-INF/views/common/footer.jsp" %>

</footer>


</body>
</html>
