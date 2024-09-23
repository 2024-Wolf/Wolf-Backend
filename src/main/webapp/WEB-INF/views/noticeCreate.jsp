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
                    <h2 class="title">공지사항 작성</h2>
                    <form method="get" action="/notice" onsubmit="alert('작성완료');" class="inputSection">
                        <div class="inputGroup">
                            <label class="subtitle" for="challenge">제목</label>
                            <input class="textContent input" type="text" name="challenge" id="challenge" required>
                        </div>
                        <div class="inputGroup">
                            <label class="subtitle" for="content">내용</label>
                            <textarea class="textContent textarea" name="content" id="content" cols="30" rows="10"
                                required></textarea>
                        </div>
                        <div class="inputGroup imagePlaceholder">
                            <img src="path/to/image.jpg" alt="임시 이미지" style="max-width: 100%; max-height: 100%;">
                        </div>
                        <div class="inputGroup">
                            <label class="subtitle" for="content">첨부파일</label>
                            <input class="textContent" type="file" name="username" id="username">
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