<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL -->
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<!DOCTYPE html>
<html lang="ja">

<head>

    <meta charset="UTF-8">
	<meta http-equiv="Content-Language" content="ja">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>マイページ | ATSUMARI</title>

    

    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/member/css/my-info.css">

</head>

<body>


<!-- HEADER -->

<%@ include file="/WEB-INF/views/common/header.jsp" %>



<!-- MYPAGE -->

<main class="mypage">

    <div class="mypage-inner">


        <div class="mypage-title">

            <span>MY PAGE</span>

            <h1>マイページ</h1>

            <p>
                会員情報をご確認いただけます。
            </p>

        </div>



        <section class="member-info">

            <h2>会員情報</h2>


            <div class="info-row">

                <span>名前</span>

                <strong>
                	${myInfo.getName()}
                </strong>

            </div>


            <div class="info-row">

                <span>メールアドレス</span>

                <strong>
                    ${myInfo.getEmail()}
                </strong>

            </div>


            <div class="info-row">

                <span>電話番号</span>

                <strong>
                    ${myInfo.getTel()}
                </strong>
             
            </div>
            
            <div class="info-row">

                <span>ニックネーム</span>

                <strong>
                	${myInfo.getNickname()}
                </strong>

            </div>


            <div class="info-row">

                <span>登録日</span>

                <strong>
                    ${myInfo.getReg_date()}
                </strong>

            </div>
            
            <c:if test="${not empty myInfo.getModify_date()}">
	            <div class="info-row">
	
	                <span>修正日</span>
	
	                <strong>
	                    ${myInfo.getModify_date()}
	                </strong>
	
	            </div>
			</c:if>

            <div class="info-button">

                <a href="<%=request.getContextPath()%>/my-info/modify">
                    会員情報を編集
                </a>

            </div>

        </section>


    </div>

</main>



<!-- FOOTER -->

<footer class="footer">

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>

</footer>


</body>
</html>
