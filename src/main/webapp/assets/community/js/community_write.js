/**
 * 
 */
// 공백 체크
function checkEmpty(obj,msg){
	if(obj.value === ""){
		alert(msg);
		obj.focus();
		return true;
	} else {
		return false;
	}
}

// 사진 넣을 때 미리보기
function setThumbnail(event) {
    var file = event.target.files[0];

    // 파일이 없으면 종료
    if (!file) {
        return;
    }

    var reader = new FileReader();

    reader.onload = function(event) {
        var preview = document.querySelector("#image_preview");

        // 기존 이미지 삭제
        preview.innerHTML = "";

        // 미리보기 영역
        var previewBox = document.createElement("div");
        previewBox.classList.add("image-preview-box");

        // 이미지
        var img = document.createElement("img");
        img.setAttribute("src", event.target.result);
        img.setAttribute("class", "community-preview-image");

        // 삭제 버튼
        var deleteButton = document.createElement("button");
        deleteButton.setAttribute("type", "button");
        deleteButton.classList.add("image-delete-button");
        deleteButton.innerText = "イメージ削除";

        // 삭제 버튼 클릭
        deleteButton.addEventListener("click", function() {
            // 미리보기 삭제
            preview.innerHTML = "";

            // input의 파일 선택값 삭제
            document.querySelector("#community-image").value = "";
			
			// 파일 이름을 원래 문구로 변경
			    var imageName = document.querySelector(".community-image-name");
			    imageName.innerText = "新しいイメージをインプットしてください。";
				
			/*
             * 기존 이미지도 삭제하는 것으로 처리
             *
             * 서버에서 deleteImage = 1을 확인해서
             * 기존 S3 파일과 DB 데이터를 삭제한다.
             */
            document.querySelector("#delete-image").value = "1";
			
        });

        // 추가
        previewBox.appendChild(img);
        previewBox.appendChild(deleteButton);
        preview.appendChild(previewBox);
    };

    reader.readAsDataURL(file);
}
// ======================================================
// 수정 페이지에서 기존 이미지 삭제
// ======================================================
function deleteExistingImage() {

    // 미리보기 영역
    var preview = document.querySelector("#image_preview");

    // 기존 이미지 미리보기 삭제
    preview.innerHTML = "";


    // 기존 이미지 삭제 여부를 1로 변경
    //
    // 서버에서는 이 값을 확인해서
    // 기존 S3 파일과 DB 정보를 삭제하면 됨.
    document.querySelector("#delete-image").value = "1";


    // 파일 이름 문구 변경
    var imageName = document.querySelector(".community-image-name");

    imageName.innerText =
        "新しいイメージをインプットしてください。";
}

