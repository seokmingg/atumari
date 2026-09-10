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
    
    <script src="<%=request.getContextPath()%>/assets/member/js/signup.js"></script>
	<!-- jQuery -->
    <script src="<%=request.getContextPath()%>/assets/member/js/jquery-1.8.1.min.js"></script>
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


            <form name="login" id="login" action="/login" method="post">

                <div class="input-group">

                    <label for="userId">
                        メールアドレス
                    </label>

                    <input
                        type="text"
                        id="email"
                        name="email"
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


				<!-- 
                <div class="login-options">
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
            <!-- JavaScript -->
<script type="text/javascript">
	// 입력값 공백 체크 -> 이벤트 핸들링
	document.querySelector("#login").addEventListener("submit", function(event) {
	
	    if (checkEmpty(login.email, "メールアドレスを入力してください。")) {
	    	login.email.focus();
	        event.preventDefault(); // 이벤트 리스너 실행는 유지하되 이벤트 동작을 막음
	        return;
	    }
	    
	    if (checkEmpty(login.password, "パスワードを入力してください。")) {
	    	login.password.focus();
	        event.preventDefault();　
	        return;
	    }
	    
	    if (!checkEmailValid()) {
	        event.preventDefault();
	        return;
	    }
	    
	    getLoginResult();
	   
		
	    // 여기까지 왔다면 정상적으로 form 제출
	});
	
	// 이메일 형식 세부 검증
	function checkEmailValid() {
	    let userInput = document.querySelector("#email").value;
	    const result = /^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/.test(userInput); // 이메일 정규식 기본 패턴
	    if (!result) {
	      alert("有効なメールアドレスを入力してください。");
	      return false;
	    }
	     
	    return true;
	    
	}
	
	// 로그인 결과 알럿 반환
	function getLoginResult() {
		
		let email = login.email.value;
		let password = login.password.value;
		
		$.ajax({
		type :"POST",
		url : "<%=request.getContextPath()%>/loginresult",
		data: "email="+email+"&password="+password,
		async: false,
		dataType : "text",
		error : () => {
			alert('통신 실패!!!!!');
		},
		success : (data) => {
			let result = $.trim(data); // alert 창 공백 제거(제이쿼리)
			alert(result);
		}
	});	
	}
	
	/*
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
		
		// 이메일 형식이 올바르지 않으면
		if (!checkEmailValid()) return;
		
		signup.submit();
		
	}
	*/
</script>


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
