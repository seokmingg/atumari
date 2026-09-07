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
    
    <script src="<%=request.getContextPath()%>/assets/member/js/signup.js"></script>

    
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
<script type="text/javascript">
	function goSignup() {
		if (checkEmpty(signup.userName, "お名前を入力してください。")) return;
		if (checkEmpty(signup.email, "メールアドレスを入力してください。")) return;
		if (checkEmpty(signup.password, "パスワードを入力してください。")) return;
		
		// 비밀번호 글자 수 검증(min: 8, max: 20)
		if (signup.password.value.length < 8 || signup.password.value.length > 20) {
			alert("パスワードは8桁以上20桁以下で入力してください。");
			signup.password.focus();
			return;
		}
		
		if (checkEmpty(signup.passwordConfirm, "もう一度パスワードを入力してください。")) return;
		
		// 입력받은 두 비밀번호 값이 일치하지 않으면
		if (signup.password.value != signup.passwordConfirm.value) {
			alert("同じパスワードを入力してください。");
			signup.passwordConfirm.focus();
			return;
		}
		
		// 이용규약 체크박스 체크 여부 검증
		if (!signup.agree.checked) {
			alert("利用規約とプライバシーポリシーに同意してください。");
			return;
		}
		
		signup.submit();
		
	}
</script>

            <form name="signup" action="javascript:goSignup()" method="post">


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

                    <!-- TODO. 이메일 형식 상세 검증 구현 -->
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
<script type="text/javascript">
	// 이메일 형식 세부 검증
	// TODO. 이벤트 체크해서 수정
	document.querySelector("#email").addEventListener("click", function () {
	    let userInput = document.querySelector("#email").value;
	    const result = /^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/.test(userInput);   // 이메일 정규식 기본 패턴
	    if (!result) {
	      alert("이메일 주소 형식이 올바르지 않습니다!");
	    } else {
	      alert("이메일 주소 형식이 올바릅니다!");  // 실무에서는 쓸일 없겠지만, 확인용으로
	    }
	  });
</script>
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
