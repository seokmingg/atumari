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

                // 비공개 선택
                passwordArea.classList.add("show");
                passwordInput.required = true;

            } else {

                // 공개 선택
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

                // 이메일 알림 받기
                emailArea.classList.add("show");
                emailInput.required = true;

            } else {

                // 이메일 알림 받지 않기
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

            if (this.files.length > 0) {

                // 여러 파일 선택 시 파일 개수 표시
                if (this.files.length === 1) {

                    fileName.textContent =
                        this.files[0].name;

                } else {

                    fileName.textContent =
                        this.files.length + "個のファイルを選択しました";
                }

            } else {

                fileName.textContent =
                    "選択されていません";
            }
        });
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

    // 현재 선택된 공개/비공개 값
    const isPublic =
        document.querySelector(
            "[name='isPublic']:checked"
        );

    const password =
        document.querySelector("[name='password']");

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
       비공개 비밀번호
    ========================== */

    if (isPublic && isPublic.value === "0") {

        // 빈칸 확인
        if (checkEmpty(
            password,
            "パスワードを入力してください。"
        )) {
            return false;
        }

        // 숫자 4자리인지 확인
        if (!/^\d{4}$/.test(password.value)) {

            alert(
                "パスワードは4桁の数字で入力してください。"
            );

            password.focus();

            return false;
        }
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