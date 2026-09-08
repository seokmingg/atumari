<%@ page pageEncoding="UTF-8"%>
    <link rel="stylesheet"
      href="<%=request.getContextPath()%>/assets/common/css/common_header.css">
       <link rel="stylesheet"
      href="<%=request.getContextPath()%>/assets/common/css/common.css">
      <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/common/css/calendar.css">
      <script src="<%=request.getContextPath()%>/assets/common/js/header.js"></script>
<header class="header">

  <a href="<%=request.getContextPath()%>/" class="logo">

	    <div class="logo-main">あつまり</div>
	    <div class="logo-sub">MATSURI GUIDE</div>

	</a>

        <nav class="nav">
            <a href="#">祭りを探す</a>
            <a href="<%=request.getContextPath()%>/festival/card">地域から探す</a>
            <a href="#">季節から探す</a>
            <a href="<%=request.getContextPath()%>/inquiry/list">お問い合わせ</a>
            <a href="<%=request.getContextPath()%>/community">コミュニティ</a>
        </nav>
        
        <div class="nav">
           <a href="<%=request.getContextPath()%>/my-info"> 田中 太郎 様</a>
         </div>

         <div class="nav member-nav">

		    <a href="<%=request.getContextPath()%>/login">
		        ログイン
		    </a>
		
		    <span>|</span>
		
		    <a href="<%=request.getContextPath()%>/signup">
		        会員登録
		    </a>
		
		</div>
		
		<!-- SEARCH -->
        <button
            type="button"
            class="header-search"
            onclick="openSearch()">
            🔍
        </button>

    </div>


    <!-- =========================
         HEADER SEARCH
    ========================== -->

    <div class="search-header">

        <div class="search-panel">

            <!-- TOP -->
            <div class="search-panel-top">

                <span class="search-panel-title">
                    FESTIVAL SEARCH
                </span>

                <button
                    type="button"
                    class="search-close"
                    onclick="closeSearch()">
                    ×
                </button>

            </div>


            <!-- SEARCH CONTENT -->
            <div class="search-content">

                <!-- KEYWORD -->
                <div class="search-keyword">

                    <label>
                        祭りを探す
                    </label>

                    <input
                        type="text"
                        placeholder="祭りの名前を入力してください">

                </div>


                <!-- DATE -->
                <div class="search-date">

                    <label>
                        開催日
                    </label>

                    <button
                        type="button"
                        class="calendar-button"
                        onclick="openCalendar()">

                        <span class="calendar-plus">＋</span>

                        <div class="header-date-range">

                            <strong class="header-date-start">
                                日付を選択
                            </strong>

                            <span class="date-arrow">
                                →
                            </span>

                            <strong class="header-date-end">
                            </strong>

                        </div>

                    </button>


                    <!-- CALENDAR -->
                    <div class="header-calendar">

                        <div class="calendar-header">

                            <button
                                type="button"
                                class="header-calendar-prev">
                                ‹
                            </button>

                            <strong class="header-calendar-title">
                                2026年 8月
                            </strong>

                            <button
                                type="button"
                                class="header-calendar-next">
                                ›
                            </button>

                        </div>


                        <div class="calendar-week">

                            <span>日</span>
                            <span>月</span>
                            <span>火</span>
                            <span>水</span>
                            <span>木</span>
                            <span>金</span>
                            <span>土</span>

                        </div>


                        <div class="header-calendar-days">
                        </div>

                    </div>

                </div>


                <!-- SEARCH -->
                <button
                    type="button"
                    class="search-submit">

                    検索

                </button>

            </div>

        </div>

    </div>
</header>
