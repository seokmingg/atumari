<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">

<head>

    <meta charset="UTF-8">
	<meta http-equiv="Content-Language" content="ja">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>会員情報の変更 | ATSUMARI</title>

    

    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/member/css/my-info-modify.css">

</head>

<body>


<!-- =========================
     HEADER
========================= -->

<%@ include file="/WEB-INF/views/common/header.jsp" %>



<!-- =========================
     MEMBER MODIFY
========================= -->

<main class="member-modify">

    <div class="member-modify-inner">


        <!-- TITLE -->

        <div class="modify-title">

            <span>MEMBER INFORMATION</span>

            <h1>会員情報の変更</h1>

            <p>
                会員情報を変更することができます。
            </p>

        </div>



        <!-- FORM -->

        <section class="modify-box">

            <form action="#" method="post">


                <!-- 名前 -->

                <div class="form-row">

                    <label for="userName">
                        名前
                    </label>

                    <input
                        type="text"
                        id="userName"
                        name="userName"
                        value="田中 太郎">

                </div>



                <!-- メール -->

                <div class="form-row">

                    <label for="email">
                        メールアドレス
                    </label>

                    <input
                        type="email"
                        id="email"
                        name="email"
                        value="example@email.com">

                </div>



                <!-- 電話番号 -->

                <div class="form-row">

                    <label for="phone">
                        電話番号
                    </label>

                    <input
                        type="text"
                        id="phone"
                        name="phone"
                        value="090-1234-5678">

                </div>



                <!-- 비밀번호 -->

                <div class="form-row">

                    <label for="password">
                        パスワード
                    </label>

                    <input
                        type="password"
                        id="password"
                        name="password"
                        placeholder="変更する場合のみ入力">

                </div>



                <!-- 비밀번호 확인 -->

                <div class="form-row">

                    <label for="passwordConfirm">
                        パスワード確認
                    </label>

                    <input
                        type="password"
                        id="passwordConfirm"
                        name="passwordConfirm"
                        placeholder="もう一度入力してください">

                </div>



                <!-- BUTTON -->

               <div class="modify-buttons">

				    <button
				        type="submit"
				        class="save-button">
				        変更を保存
				    </button>
				
				    <a href="#"
				       class="delete-button">
				        退会する
				    </a>
			
				</div>


            </form>

        </section>


    </div>

</main>



<!-- =========================
     FOOTER
========================= -->

<footer class="footer">

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>

</footer>


</body>
</html>
