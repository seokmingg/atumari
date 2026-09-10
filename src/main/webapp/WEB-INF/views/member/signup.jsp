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
    <script src="<%=request.getContextPath()%>/assets/member/js/jquery-1.8.1.min.js"></script>

    
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

            <form name="signup" id="signup" action="/signup" method="post">

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

                <!-- パスワード -->

                <div class="input-group">

                    <label for="password">
                        パスワード（8~20文字）
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
<!-- JavaScript -->
<script type="text/javascript">
	/*
	** refactor: id 값에 해당하는 form을 받아, submit할시(회원등록 버튼을 클릭하거나 엔터키 입력시) 입력값 검증하도록 수정 -> 이벤트 핸들러 활용
	** TODO. 이미 회원가입 되어있는 이메일 입력받을시 검증 후 submit 막기(알럿) 추가
	*/
	document.querySelector("#signup").addEventListener("submit", function(event) {
	
	    if (checkEmpty(signup.email, "メールアドレスを入力してください。")) {
	    	signup.email.focus();
	        event.preventDefault();
	        return;
	    }
	    
	    if (checkEmpty(signup.userName, "お名前を入力してください。")) {
	    	signup.userName.focus();
	        event.preventDefault();　// 이벤트 리스너 실행는 유지하되 이벤트 동작을 막음
	        return;
	    }
	
	    if (checkEmpty(signup.password, "パスワードを入力してください。")) {
	    	signup.password.focus();
	        event.preventDefault();
	        return;
	    }
	
	    if (signup.password.value.length < 8 ||
	        signup.password.value.length > 20) {
	
	        alert("パスワードは8文字以上20文字以下で入力してください。");
	        signup.password.focus();
	
	        event.preventDefault();
	        return;
	    }
	
	    if (checkEmpty(signup.passwordConfirm,
	                  "もう一度パスワードを入力してください。")) {
	    	signup.passwordConfirm.focus();
	        event.preventDefault();
	        return;
	    }
	
	    if (signup.password.value != signup.passwordConfirm.value) {
	
	        alert("同じパスワードを入力してください。");
	        signup.passwordConfirm.focus();
	
	        event.preventDefault();
	        return;
	    }
	
	    if (!signup.agree.checked) {
	
	        alert("利用規約とプライバシーポリシーに同意してください.");
	
	        event.preventDefault();
	        return;
	    }
	
	    if (!checkEmailValid()) {
	
	        event.preventDefault();
	        return;
	    }
	    
	    checkDuplicateEmail();
	
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
	
	// 이메일 중복 검증
	function checkDuplicateEmail() {
		//if (checkEmpty(mem.t_id, "아이디를 먼저 입력해주세요.")) return;
		
		let email = signup.email.value;
		
		$.ajax({
		type :"POST",
		url : "<%=request.getContextPath()%>/checkemail",
		data: "email="+email,
		async: false,
		dataType : "text",
		error : () => {
			alert('통신 실패!!!!!');
		},
		success : (data) => {
			let result = $.trim(data); // alert 창 공백 제거(제이쿼리)
			//signup.t_id_check.value = result;
			alert("=="+result+"==");
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
