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
	<link rel="stylesheet" href="/resources/css/login.css">
</head>

<body>
	<%@ include file="header.jsp" %>
    <div class="mainContents">
		<%@ include file="sidebar.jsp" %>
        <div class="infoCard">
            <h2 class="title">신고 정보</h2>
            <div class="inputSection">
                <div class="inputGroup">
                    <label class="subtitle" for="status">상태</label>
                    <select class="textContent input" name="status" id="status">
                        <option value="success">처리 필요</option>
                        <option value="failure">처리 완료</option>
                    </select>
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="auth-date">신고일</label>
                    <input class="textContent input" type="date" name="auth-date" id="auth-date" required>
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="username">신고자</label>
                    <input class="textContent input" type="text" name="username" id="username" required>
                </div>

                <div class="inputGroup">
                    <label class="subtitle" for="username">구분</label>
                    <input class="textContent input" type="text" name="username" id="username" required>
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="challenge">신고댓글</label>
                    <textarea class="textContent textarea" name="content" id="content" cols="30"
                        rows="10"></textarea>
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="content">신고대상</label>
                    <input class="textContent input" type="text" name="username" id="username" required>
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="content">카테고리</label>
                    <input class="textContent input" type="text" name="username" id="username" required>
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="content">내용</label>
                    <textarea class="textContent textarea" name="content" id="content" cols="30"
                        rows="10"></textarea>
                </div>

                <div class="buttonContainer">
                    <button class="formButton linePurpleButton">취소</button>
                    <button class="formButton darkBackgroundButton">완료</button>
                </div>
            </div>
        </div>
    </div>
<%@ include file="footer.jsp" %>
</body>

</html>