/**
 * 
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
// 사진 넣을 때 미리보기
$(function() {

    const inputImage = document.getElementById("input-image");
    const previewImage = document.getElementById("preview-image");
    const cancelButton = document.getElementById("cancel-image");

    // 페이지 처음 로딩할 때 기존 이미지 저장
    const originalImageSrc = previewImage.src;

    // 이미지 선택
    inputImage.addEventListener("change", e => {

        const file = e.target.files[0];

        if (!file) {
            return;
        }

        // 이미지 파일인지 확인
        if (!file.type.startsWith("image/")) {
            alert("이미지 파일만 선택해주세요.");

            // input 초기화
            inputImage.value = "";

            return;
        }

        const reader = new FileReader();

        reader.onload = e => {
            // 기존 사진을 새 사진으로 교체
            previewImage.src = e.target.result;
            previewImage.style.display = "block";
        };

        reader.readAsDataURL(file);
    });


    // 취소 버튼
    cancelButton.addEventListener("click", () => {

        // 기존 사진으로 복구
        previewImage.src = originalImageSrc;

        // 선택했던 파일도 초기화
        inputImage.value = "";

        // 기존 사진이 있었다면 표시
        previewImage.style.display = "block";
    });

});

/* =========================
   community write save
========================= */

