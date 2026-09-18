/**
 * Atumari Team Project
 * Member - 회원가입 페이지에 사용하는 js
 */
// 공백 체크
function checkEmpty(obj,msg){
	if(obj.value == ""){
		alert(msg);
		obj.focus();
		return true;
	} else {
		return false;
	}
}

// 이메일 형식 세부 검증 -> 회원가입, 로그인
function checkEmailValid() {
    let userInput = document.querySelector("#email").value;
    const result = /^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/.test(userInput); // 이메일 정규식 기본 패턴
    if (!result) {
      alert("有効なメールアドレスを入力してください。");
      return false;
    }
     
    return true;
    
}