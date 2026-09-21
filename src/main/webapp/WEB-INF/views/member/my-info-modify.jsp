<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL -->
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
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
          
    <script src="<%=request.getContextPath()%>/assets/member/js/member.js"></script>
	<!-- jQuery -->
    <script src="<%=request.getContextPath()%>/assets/member/js/jquery-1.8.1.min.js"></script>

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

            <form name="modify" id="modify" action="/my-info/modify" method="post">
            
            <!-- getCheckPassword() 비밀번호 값 검증에 사용하는 hidden input -->
            
            <input type="hidden" name="checkPasswordResult" />
            
            	<!-- メール: 이메일은 로그인시 아이디 역할을 하므로 수정 허용하지 않음 -->

                <div class="form-row">

                    <label for="email">
                        メールアドレス
                    </label>

                    ${myInfo.getEmail()}
                     <input
                        type="hidden"
                        id="email"
                        name="email"
                        value="${myInfo.getEmail()}">

                </div>


                <!-- 名前 -->

                <div class="form-row">

                    <label for="userName">
                        名前（必修）
                    </label>

                    <input
                        type="text"
                        id="userName"
                        name="userName"
                        value="${myInfo.getName()}">

                </div>
                
                <!-- 電話番号 -->

                <div class="form-row">

                    <label for="phone">
                        電話番号（任意）
                    </label>
                   
                    <input
                        type="text"
                        id="tel"
                        name="tel"
                        class="tel"
                        value="${myInfo.getTel()}"
                        placeholder="入力例：「070-0000-0000」、「080-000-0000」">
                    <!--    
                    <input
                        type="text"
                        id="tel2"
                        name="tel2"
                        class="tel"
                        value="${myInfo.getTel()}">
                        
                    <input
                        type="text"
                        id="tel3"
                        name="tel3"
                        class="tel"
                        value="${myInfo.getTel()}">
                        
                    -->  

                </div>
                
                
                <!-- ニックネーム -->

                <div class="form-row">

                    <label for="userName">
                        ニックネーム（任意）
                    </label>

                    <input
                       type="text"
                       id="nickname"
                       name="nickname"
                       value="${myInfo.getNickname()}">
                        	
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
                        placeholder="パスワードが一致する場合だけ、会員情報をご変更いただけます">

                </div>

                <!-- BUTTON -->

               <div class="modify-buttons">

				    <button
				        type="submit"
				        class="save-button">
				        変更を保存
				    </button>
				
				<!--  
				    <a href="#"
				       class="delete-button">
				        退会する
				    </a>
				    
				-->
			
				</div>


            </form>
            
<!-- JavaScript -->
<script type="text/javascript">
	document.querySelector("#modify").addEventListener("submit", function(event) {
	    
	    if (checkEmpty(modify.userName, "お名前を入力してください。")) {
	    	modify.userName.focus();
	        event.preventDefault();　// 이벤트 리스너 실행는 유지하되 서브밋 동작 자체를 막음
	        return;
	    }
	    
	
	    if (checkEmpty(modify.password, "パスワードを入力してください。")) {
	    	modify.password.focus();
	        event.preventDefault();
	        return;
	    }
	    
	    if(!checkTelValid()) {
	    	event.preventDefault();
	        return;
	    }
	    
	    getCheckPassword();
	    
	    // 비밀번호가 맞지 않으면 submit 막기
	    if (modify.checkPasswordResult.value == "パスワードをもう一度確認してください。") {
	    	event.preventDefault();
	    	return;
	    }
		
	    // 여기까지 왔다면 정상적으로 form 제출
	});
	
    function getCheckPassword() {
		
    	let email = modify.email.value;
		let password = modify.password.value;
		
		$.ajax({
		type :"POST",
		url : "<%=request.getContextPath()%>/checkpassword",
		data: "email="+email+"&password="+password,
		async: false,
		dataType : "text",
		error : () => {
			alert('問題が発生しました。もう一度確認してください。');
		},
		success : (data) => {
			let result = $.trim(data); // alert 창 공백 제거(제이쿼리)
			modify.checkPasswordResult.value = result; // 전용 인풋에 결괏값 넣기
			alert(result);
		}
	});	
	}
	
</script>

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
