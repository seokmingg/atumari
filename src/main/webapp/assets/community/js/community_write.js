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
function setThumbnail(event){
		var reader = new FileReader();
		
		reader.onload = function(event){
			 var preview = document.querySelector("div#image_preview");

		        // 기존 이미지 삭제
		        preview.innerHTML = "";

		        var img = document.createElement("img");
		        img.setAttribute("src", event.target.result);
		        img.setAttribute("class", "col-lg-6");

		        preview.appendChild(img);
		};
		
		reader.readAsDataURL(event.target.files[0]);
	}

/* =========================
   community write save
========================= */

