<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
            <div class="inputGroup">
                <h2 class="title">그룹 정보</h2>
                <span class="buttonSideContainer">
                    <button class="formButton linePurpleButton">이전</button>
                </span>
            </div>
            <div class="inputSection">
                <div class="inputGroup">
                    <label class="subtitle" for="auth-date">기간</label>
                    <input class="textContent input" type="date" name="auth-date" id="auth-date" required>
                    ~
                    <input class="textContent input" type="date" name="auth-date" id="auth-date" required>
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="username">그룹명</label>
                    <input class="textContent input" type="text" name="username" id="username" required>
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="challenge">그룹장</label>
                    <input class="textContent input" type="text" name="challenge" id="challenge">
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="content">구분</label>
                    <input class="textContent input" type="text" name="challenge" id="challenge">
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="content">상태</label>
                    <input class="textContent input" type="text" name="challenge" id="challenge">
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="challenge">인원</label>
                    <input class="textContent input" type="text" name="challenge" id="challenge">
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="challenge">태그</label>
                    <input class="textContent input" type="text" name="challenge" id="challenge">
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="challenge">챌린지</label>
                    <input class="textContent input" type="text" name="challenge" id="challenge">
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="challenge">참가 챌린지</label>
                    <input class="textContent input" type="text" name="challenge" id="challenge">
                </div>
            </div>
        </div>
    </div>
<%@ include file="footer.jsp" %>
</body>

</html>