
/* 입력란의 빈칸,빈 문자열 검사후 메세지반환 + 해당 입력란 focus */ 
function checkEmpty(obj, msg) {

    if (obj.value.trim() === "") {
        alert(msg);
        obj.focus();
        return true;
    }

    return false;
}