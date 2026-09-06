<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="ja">

<head>

    <meta charset="UTF-8">
	<meta http-equiv="Content-Language" content="ja">
	
    <title>会員登録 | ATSUMARI</title>

    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/member/css/login.css">

    

</head>

<body>


    <!-- HEADER -->

    <%@ include file="/WEB-INF/views/common/header.jsp" %>


    <!-- SIGN UP -->

    <main class="login-page">

        <div class="login-box signup-box">


            <!-- TITLE -->

            <div class="login-title">

                <span>WELCOME</span>

                <h1>会員登録</h1>

                <p>
                    ATSUMARIをもっと楽しむために
                </p>

            </div>


            <form action="#" method="post">


                <!-- 名前 -->

                <div class="input-group">

                    <label for="userName">
                        お名前
                    </label>

                    <input
                        type="text"
                        id="userName"
                        name="userName"
                        placeholder="お名前を入力してください">

                </div>


                <!-- メール -->

                <div class="input-group">

                    <label for="email">
                        メールアドレス
                    </label>

                    <input
                        type="email"
                        id="email"
                        name="email"
                        placeholder="メールアドレスを入力してください">

                </div>


                <!-- パスワード -->

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


                <!-- パスワード確認 -->

                <div class="input-group">

                    <label for="passwordConfirm">
                        パスワード（確認）
                    </label>

                    <input
                        type="password"
                        id="passwordConfirm"
                        name="passwordConfirm"
                        placeholder="もう一度パスワードを入力してください">

                </div>


                <!-- 利用規約 -->

                <div class="signup-agree">

                    <label>

                        <input
                            type="checkbox"
                            name="agree">

                        <span>
                            利用規約とプライバシーポリシーに同意する
                        </span>

                    </label>

                </div>


                <!-- BUTTON -->

                <button
                    type="submit"
                    class="login-button">

                    会員登録

                </button>


            </form>


            <!-- LOGIN LINK -->

            <div class="signup-area">

                <p>
                    すでに会員登録がお済みの方
                </p>

                <a href="<%=request.getContextPath()%>/login">

                    ログイン

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
