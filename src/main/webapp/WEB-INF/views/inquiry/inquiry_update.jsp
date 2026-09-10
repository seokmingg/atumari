<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>お問い合わせ修正 | ATSUMARI</title>

<link rel="stylesheet"
      href="<%=request.getContextPath()%>/assets/inquiry/css/inquiry_common.css">

<link rel="stylesheet"
      href="<%=request.getContextPath()%>/assets/inquiry/css/inquiry_write_update.css">
</head>

<script src="${pageContext.request.contextPath}/assets/inquiry/js/inquiry_write_update.js"></script>

<body>

<!-- header -->

<%@ include file="/WEB-INF/views/common/header.jsp" %>


<!-- board -->

<main class="board-page">

<div class="board-inner">

<div class="board-title">
    <span>INQUIRY</span>
    <h1>お問い合わせ修正</h1>
    <p>登録したお問い合わせ内容を修正します。</p>
</div>


<form class="form-card">

    <!-- 제목 -->
    <div class="form-row">
        <div class="form-label">タイトル</div>

        <div class="form-field">
            <input type="text"
                   name="title"
                   value="祭り情報についてお問い合わせします">
        </div>
    </div>


   <!-- 작성자 -->
    <div class="form-row">
        <div class="form-label">作成者</div>

        <div class="form-field">
            <input type="text"
                   name="writer"
                   value="kim123">
        </div>
    </div>

    <!-- 공개 설정 -->
    <div class="form-row">

        <div class="form-label">公開設定</div>

        <div class="form-field radio-field">

            <label>
                <input type="radio"
                       name="isPublic"
                       value="1"
                       checked>
                公開
            </label>

            <label>
                <input type="radio"
                       name="isPublic"
                       value="0">
                非公開
            </label>
			<div class="form-help">
               *非公開を選択した場合、他のユーザーからはお問い合わせ内容を閲覧できません。
            </div>
        </div>
        
    </div>


    <!-- 비밀번호 -->
    <div class="form-row hidden-row" id="passwordArea">

        <div class="form-label">パスワード</div>

        <div class="form-field">

            <input type="password"
                   id="inquiryPassword"
                   name="inquiryPassword"
                   maxlength="4"
                   inputmode="numeric"
                   placeholder="4桁のパスワードを入力">

            <div class="form-help">
                *非公開のお問い合わせを確認する際に使用します。
            </div>

        </div>
    </div>


    <!-- 메일 알림 -->
    <div class="form-row">

        <div class="form-label">回答通知</div>

        <div class="form-field radio-field">

            <label>
                <input type="radio"
                       name="emailNotify"
                       value="1">
                メールで受け取る
            </label>

            <label>
                <input type="radio"
                       name="emailNotify"
                       value="0"
                       checked>
                受け取らない
            </label>

        </div>
    </div>


    <!-- 이메일 -->
    <div class="form-row hidden-row" id="emailArea">

        <div class="form-label">メールアドレス</div>

        <div class="form-field">

            <input type="email"
                   id="email"
                   name="email"
                   placeholder="example@email.com">

            <div class="form-help">
                *回答が登録された際に通知メールを送信します。
            </div>

        </div>
    </div>
	<!-- 첨부파일 -->
	
	<div class="form-row">
	
	    <div class="form-label">添付ファイル</div>
	
	    <div class="form-field">
	
	        <div class="file-upload-area">
	
	            <label for="inquiryFile" class="file-select-button">
	                ファイルを選択
	            </label>
	
	            <input type="file"
	                   id="inquiryFile"
	                   name="inquiryFile"
	                   class="file-input">
	
	            <span id="fileName" class="file-name">
	                選択されていません
	            </span>
	
	        </div>
	
	        <div class="form-help">
	            *画像または文書ファイルを添付できます。
	        </div>
	
	    </div>
	
	</div>

    <!-- 문의 내용 -->
    <div class="form-row">

        <div class="form-label">お問い合わせ内容</div>

        <div class="form-field">

            <textarea name="content">青森ねぶた祭の開催日程についてお問い合わせします。
現在サイトに表示されている日程と公式サイトの日程が異なるようです。
ご確認をお願いいたします。</textarea>

        </div>
    </div>

</form>


<div class="form-actions">

    <a class="secondary-button"
       href="${pageContext.request.contextPath}/inquiry/view">
        キャンセル
    </a>

    <div class="right">
        <button type="submit"
                class="primary-button">
            修正する
        </button>
    </div>

</div>


</div>
</main>


<!--footer -->

<footer class="footer">
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</footer>


</body>
</html>