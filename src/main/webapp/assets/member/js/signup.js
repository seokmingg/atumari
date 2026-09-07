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