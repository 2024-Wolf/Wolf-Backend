<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                <div class="inputGroup">
                    <h2 class="title">FAQ 정보</h2>
                    <span class="buttonSideContainer">
                        <button class="formButton linePurpleButton"onclick="location.href='/faqEdit'">수정</button>
                        <button class="formButton darkBackgroundButton">삭제</button>
                    </span>
                </div>
                <div class="inputSection">

                    <div class="inputGroup">
                        <label class="subtitle" for="username">등록자</label>
                        <input class="textContent input" type="text" name="username" id="username" required>
                    </div>
                    <div class="inputGroup">
                        <label class="subtitle" for="auth-date">등록일</label>
                        <input class="textContent input" type="date" name="auth-date" id="auth-date" required>
                    </div>
                    <div class="inputGroup">
                        <label class="subtitle" for="challenge">제목</label>
                        <input class="textContent input" type="text" name="challenge" id="challenge">
                    </div>
                    <div class="inputGroup">
                        <label class="subtitle" for="content">내용</label>
                        <textarea class="textContent input" name="content" id="content" cols="30"
                            rows="10"></textarea>
                    </div>
                </div>
            </div>
        </div>
<%@ include file="footer.jsp" %>
    </body>

    </html>