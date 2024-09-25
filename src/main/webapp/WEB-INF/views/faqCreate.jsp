<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> -->
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WOLF 관리자 페이지</title>
    <link rel="stylesheet" href="/resources/css/globalstyle.css">
    <link rel="stylesheet" href="/resources/css/mainContents.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/form.css">

</head>

<body>
    <%@ include file="header.jsp" %>
        <div class="mainContents">
            <%@ include file="sidebar.jsp" %>
                <div class="infoCard">
                    <h2 class="title">FAQ 작성</h2>
                    <form method="get" action="/faq" onsubmit="alert('작성완료');" class="inputSection scrollArea">
                        <div class="inputGroup">
                            <label class="subtitle" for="title">제목</label>
                            <input class="textContent input" type="text" name="title" id="title" required>
                        </div>
                        <div class="inputGroup">
                            <label class="subtitle" for="content">내용</label>
                            <textarea class="textContent textarea" name="content" id="content" cols="30" rows="10"
                                required></textarea>
                        </div>
                        <div class="buttonContainer">
                            <button type="button" class="formButton linePurpleButton"
                                onclick="window.history.back()">취소</button>
                            <button type="submit" class="formButton darkBackgroundButton">완료</button>
                        </div>
                    </form>
                </div>
        </div>
        <%@ include file="footer.jsp" %>
</body>

</html>