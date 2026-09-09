<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">

<head>
	<meta http-equiv="Content-Language" content="ja">
    <meta charset="UTF-8">

    <title>ログイン | ATSUMARI</title>

    <link rel="stylesheet"
        href="<%=request.getContextPath()%>/assets/member/css/login.css">
      

</head>

<body>

    <!-- HEADER -->

 		<%@ include file="/WEB-INF/views/common/header.jsp" %>



    <!-- LOGIN -->
    <main class="login-page">

        <div class="login-box">

            <div class="login-title">

                <span>WELCOME</span>

                <h1>ログイン</h1>

                <p>
                    ATSUMARIをもっと楽しむために
                </p>

            </div>


            <form action="#" method="post">

                <div class="input-group">

                    <label for="userId">
                        メールアドレス
                    </label>

                    <input
                        type="text"
                        id="userId"
                        name="userId"
                        placeholder="メールアドレスを入力してください">

                </div>


                <div class="input-group">

                    <label for="password">
                        パスワード
                    </label>

                    <input
                        type="password"
                        id="password"
                        name="password"
                        placeholder="パスワードを入力してください">

                </div>


                <div class="login-options">
				<!-- 
                    <label>
                        <input type="checkbox" name="remember">
                        ログイン状態を保持する
                    </label>

                    <a href="#">
                        パスワードを忘れた方(TODO. 여유 생기면 비밀번호 찾기도 구현)
                    </a>

                </div>
                
                 -->


                <button type="submit" class="login-button">
                    ログイン
                </button>

            </form>


            <div class="signup-area">

                <p>
                    まだ会員登録がお済みでない方
                </p>

                <a href="<%=request.getContextPath()%>/signup">
                    新規会員登録
                </a>

            </div>

        </div>

    </main>


    <!-- FOOTER -->
    <footer class="footer">

 		<%@ include file="/WEB-INF/views/common/footer.jsp" %>

	</footer>

</body>

</html>
