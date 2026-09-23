document.addEventListener("DOMContentLoaded", function () {


    /* =========================
       메일 알림
    ========================== */

    const emailRadios =
        document.querySelectorAll('input[name="emailNotify"]');

    const emailArea =
        document.getElementById("emailArea");

    const emailInput =
        document.getElementById("email");

		// 이메일 입력 영역 표시/숨김
		function updateEmailArea() {

		    const checkedRadio =
		        document.querySelector(
		            'input[name="emailNotify"]:checked'
		        );

		    if (checkedRadio && checkedRadio.value === "1") {

		        // 이메일 알림 받기
		        emailArea.classList.add("show");
		        emailInput.required = true;

		    } else {

		        // 이메일 알림 받지 않기
		        emailArea.classList.remove("show");
		        emailInput.required = false;

		    }
		}


		// 라디오 버튼 변경 시
		emailRadios.forEach(function (radio) {

		    radio.addEventListener("change", function () {

		        // 받지 않기를 선택하면 기존 이메일 삭제
		        if (this.value === "0") {
		            emailInput.value = "";
		        }

		        updateEmailArea();

		    });

		});


		// ★ 페이지가 처음 열릴 때도 한 번 실행
		updateEmailArea();
	
	/* =========================
	   첨부파일
	========================== */

	const fileInput =
	    document.getElementById("inquiryFile");

	const fileName =
	    document.getElementById("fileName");

	const selectedFileList =
	    document.getElementById("selectedFileList");

	const fileError =
	    document.getElementById("fileError");

	const MAX_FILE_COUNT = 3;
	
	// Update 페이지에 존재하는 기존 첨부파일
	const existingFiles =
	    document.querySelectorAll(".existing-file");

	// 기존 파일 삭제 체크박스
	const deleteFileCheckboxes =
	    document.querySelectorAll(".delete-file-checkbox");

	// 실제 선택된 파일들을 관리
	let selectedFiles = [];

	if (fileInput) {

	    fileInput.addEventListener("change", function () {

	        // 이번에 선택한 새로운 파일
	        const newFiles = Array.from(this.files);
			
			// 삭제되지 않고 남아있는 기존 파일 개수
			const existingFileCount = getCurrentFileCount();

	        // 기존 파일 + 이전에 선택한 새 파일 + 새 파일이 3개를 초과하는지 확인
	        if (
	          	existingFileCount + selectedFiles.length + newFiles.length > MAX_FILE_COUNT
	        ) {

	            fileError.textContent =
	                "添付できるファイルは3件までです。";

	            // 기존 파일 상태 유지
	            updateFileInput();

	            return;
	        }

	        // 정상적인 경우 에러 메시지 삭제
	        fileError.textContent = "";

	        // 기존 파일 목록에 새 파일 추가
	        selectedFiles.push(...newFiles);

	        // 실제 input에도 반영
	        updateFileInput();

	        // 화면에 파일 목록 표시
	        renderFileList();
	    });
	}
	
	/* =========================
	   기존 파일 개수
	========================== */
	function getCurrentFileCount() {

	    // DB에 저장되어 있는 기존 파일 개수
	    const existingFileCount =
	        existingFiles.length;

	    // 삭제하기로 체크한 기존 파일 개수
	    const deleteFileCount =
	        document.querySelectorAll(
	            ".delete-file-checkbox:checked"
	        ).length;

	    // 수정 후에도 남아있을 기존 파일 개수
	    return existingFileCount - deleteFileCount;
	}
	
	/* =========================
	   삭제 체크박스 변경을 고려해 안내 문구 띄우기
	========================== */
	deleteFileCheckboxes.forEach(function (checkbox) {

	    checkbox.addEventListener("change", function () {

	        const existingFileCount =
	            getCurrentFileCount();

	        const totalFileCount =
	            existingFileCount
	            + selectedFiles.length;

				if (totalFileCount > MAX_FILE_COUNT) {

				    fileError.textContent =
				        "添付できるファイルは3件までです。";

				} else {

				    fileError.textContent = "";

				}

	    });

	});

	/* =========================
	   선택된 파일 화면 표시
	========================== */

	function renderFileList() {

	    selectedFileList.innerHTML = "";

	    // 파일이 없는 경우
	    if (selectedFiles.length === 0) {

	        fileName.textContent =
	            "選択されていません";

	        return;
	    }

	    // 선택된 파일 개수 표시
	    fileName.textContent =
	        selectedFiles.length +
	        "個のファイルを選択しました";


	    selectedFiles.forEach(function (file, index) {

	        const fileItem =
	            document.createElement("div");

	        fileItem.classList.add("selected-file-item");


	        // 파일 이름
	        const name =
	            document.createElement("span");

	        name.classList.add("selected-file-name");

	        name.textContent = file.name;


	        // 삭제 버튼
	        const deleteButton =
	            document.createElement("button");

	        deleteButton.type = "button";

	        deleteButton.classList.add(
	            "file-delete-button"
	        );

	        deleteButton.textContent = "×";


	        // 삭제 버튼 클릭
	        deleteButton.addEventListener(
	            "click",
	            function () {

	                removeFile(index);
	            }
	        );


	        fileItem.appendChild(name);

	        fileItem.appendChild(deleteButton);

	        selectedFileList.appendChild(fileItem);
	    });
	}


	/* =========================
	   선택 파일 삭제
	========================== */

	function removeFile(index) {

	    // 배열에서 해당 파일 삭제
	    selectedFiles.splice(index, 1);

	    // 에러 메시지 초기화
	    fileError.textContent = "";

	    // 실제 input에 다시 반영
	    updateFileInput();

	    // 화면 다시 출력
	    renderFileList();
	}


	/* =========================
	   실제 file input 갱신
	========================== */

	function updateFileInput() {

	    const dataTransfer =
	        new DataTransfer();

	    selectedFiles.forEach(function (file) {

	        dataTransfer.items.add(file);
	    });

	    fileInput.files =
	        dataTransfer.files;
	}
});

/* =========================
   입력값 검증
========================== */

function validateForm() {

    const title =
        document.querySelector("[name='title']");

    const writer =
        document.querySelector("[name='writer']");

    const content =
        document.querySelector("[name='content']");


    // 현재 선택된 이메일 알림 값
    const emailNotify =
        document.querySelector(
            "[name='emailNotify']:checked"
        );

    const email =
        document.querySelector("[name='email']");


    /* =========================
       제목
    ========================== */

    if (checkEmpty(
        title,
        "タイトルを入力してください。"
    )) {
        return false;
    }


    /* =========================
       작성자
    ========================== */

    if (checkEmpty(
        writer,
        "お名前を入力してください。"
    )) {
        return false;
    }


    /* =========================
       이메일
    ========================== */

    if (
        emailNotify &&
        emailNotify.value === "1"
    ) {

        if (checkEmpty(
            email,
            "メールアドレスを入力してください。"
        )) {
            return false;
        }

        // 이메일 형식 간단 검증
        if (!email.validity.valid) {

            alert(
                "正しいメールアドレスを入力してください。"
            );

            email.focus();

            return false;
        }
    }


    /* =========================
       문의 내용
    ========================== */

    if (checkEmpty(
        content,
        "お問い合わせ内容を入力してください。"
    )) {
        return false;
    }


    /* =========================
       최종 확인
    ========================== */

	const form =
	    document.querySelector("form");

	if (form.name === "update_form") {

	    return confirmSubmit(
	        "お問い合わせ内容を修正しますか？"
	    );

	}

	return confirmSubmit(
	    "お問い合わせを登録しますか？"
	);
}


/* =========================
   등록 최종 확인
========================== */

function confirmSubmit(msg) {

    return confirm(msg);
}

