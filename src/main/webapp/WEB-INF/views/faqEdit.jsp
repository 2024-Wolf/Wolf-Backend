<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> -->
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WOLF 관리자 페이지</title>
    <link rel="stylesheet" href="/resources/css/globalstyle.css">
    <link rel="stylesheet" href="/resources/css/mainContents.css">
</head>

<body>
    <%@ include file="header.jsp" %>
        <div class="mainContents">
            <%@ include file="sidebar.jsp" %>
                <div class="infoCard">
                    <h2 class="title">FAQ 수정</h2>
                    <form method="get" action="/faqDetail" onsubmit="alert('수정완료');" class="inputSection scrollArea">
                        <div class="inputGroup">
                            <label class="subtitle" for="auth-date">등록일</label>
                            <input class="textContent input" type="date" name="auth-date" id="auth-date"
                                value="2024-09-24" disabled>
                        </div>
                        <div class="inputGroup">
                            <label class="subtitle" for="username">등록자</label>
                            <input class="textContent input" type="text" name="username" id="username" value="우두머리 늑대"
                                disabled>
                        </div>
                        <div class="inputGroup">
                            <label class="subtitle" for="challenge">제목</label>
                            <input class="textContent input" type="text" name="challenge" id="challenge"
                                value="회원가입은 어떻게 하나요?" required>
                        </div>
                        <div class="inputGroup">
                            <label class="subtitle" for="content">내용</label>
                            <textarea class="textContent textarea" name="content" id="content" cols="30" rows="10"
                                required>로그인 화면의 “구글로 로그인하기”를 클릭하신 후, 회원가입 절차를 진행하시면 됩니다!</textarea>
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