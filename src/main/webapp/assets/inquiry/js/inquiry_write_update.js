document.addEventListener("DOMContentLoaded", function () {

    /* =========================
       공개 / 비공개
    ========================== */

    const publicRadios =
        document.querySelectorAll('input[name="isPublic"]');

    const passwordArea =
        document.getElementById("passwordArea");

    const passwordInput =
        document.getElementById("inquiryPassword");

    publicRadios.forEach(function (radio) {

        radio.addEventListener("change", function () {

            if (this.value === "0") {
                passwordArea.classList.add("show");
                passwordInput.required = true;

            } else {
                passwordArea.classList.remove("show");
                passwordInput.required = false;
                passwordInput.value = "";
            }

        });

    });


    /* =========================
       메일 알림
    ========================== */

    const emailRadios =
        document.querySelectorAll('input[name="emailNotify"]');

    const emailArea =
        document.getElementById("emailArea");

    const emailInput =
        document.getElementById("email");

    emailRadios.forEach(function (radio) {

        radio.addEventListener("change", function () {

            if (this.value === "1") {
                emailArea.classList.add("show");
                emailInput.required = true;

            } else {
                emailArea.classList.remove("show");
                emailInput.required = false;
                emailInput.value = "";
            }

        });

    });


    /* =========================
       첨부파일
    ========================== */

    const fileInput =
        document.getElementById("inquiryFile");

    const fileName =
        document.getElementById("fileName");

    if (fileInput && fileName) {

        fileInput.addEventListener("change", function () {
			/* addEventListener 어떤 일이 발생하면 이 코드를 실행해라 
			해당 함수에서는 change이니 fileInput의 값이 변경되면 실행해라*/

            if (this.files.length > 0) {
                fileName.textContent = this.files[0].name;
				//this는 fileInput,  this.files.length는 선택한 파일 개수 의미 
				//<input type="file">은 보통 파일 하나만 선택하므로 첫번째 파일만 가져옴

            } else {
                fileName.textContent = "選択されていません";
            }

        });

    }

	
});