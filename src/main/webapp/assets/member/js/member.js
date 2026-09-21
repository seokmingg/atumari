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

// refactor: 전화번호 형식 세부 검증 추가 -> 마이페이지 정보 수정
function checkTelValid() {
    let userInput = document.querySelector("#tel").value;
	
	if (userInput.trim() == "") return true; // 공백 제가한 입력값이 비어 있으면 허용(전화번호는 필수 잆력값 아님)
	
    const result = /^0\d{1,2}-\d{3,4}-\d{4}$/.test(userInput); // 전화번호 정규식 기본 패턴(하이픈 포함)
    if (!result) {
      alert("有効な電話番号を入力してください。"); // 하이픈 포함되어 있지 않으면
      return false;
    }
     
    return true;
    
}

// refactor: 이름 형식 세부 검증 추가 -> 회원가입, 마이페이지 정보 수정
function checkNameValid() {
    let userInput = document.querySelector("#userName").value;
    const result = /^[\p{L} ・]+$/u.test(userInput); // 이름 정규식 기본 패턴 -> 유니코드 문자만 입력 가능(영문자, 한글, 히라가나, 가타카나, 한자 등)
    if (!result) {
      alert("お名前は文字（ひらがな、カタカナ、漢字、アルファベットなど）だけご入力いただけます。");
      return false;
    }
     
    return true;
    
}