<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>WOLF 관리자 페이지</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <link rel="stylesheet" href="/resources/css/globalstyle.css">
        <link rel="stylesheet" href="/resources/css/mainContents.css">
    </head>

    <body>
        <%@ include file="header.jsp" %>
            <div class="mainContents">
                <%@ include file="sidebar.jsp" %>
                    <div class="infoCard">
                        <h2 class="title">챌린지 수정</h2>
                        <form method="get" action="/challengeDetail" onsubmit="alert('수정완료');"
                            class="inputSection scrollArea">
                            <div class="inputGroup">
                                <label class="subtitle" for="auth">인증주체</label>
								<div class="nowrapLeftContainer">
								    <input class="" type="radio" name="auth" id="system" required>
								    <label for="system" class="textContent">시스템</label>
								    <input class="" type="radio" name="auth" id="manager" required>
								    <label for="manager" class="textContent">관리자</label>
								</div>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle">기간</label>
								<div class="nowrapLeftContainer">
	                                <input class="textContent input" type="date" name="start-date" id="start-date" required>
	                                ~
	                                <input class="textContent input" type="date" name="end-date" id="end-date" required>
                            	</div>
							</div>
                            <div class="inputGroup">
                                <label class="subtitle" for="username">이름</label>
                                <input class="textContent input" type="text" name="username" id="username" required>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="challenge">내용</label>
                                <input class="textContent input" type="text" name="challenge" id="challenge">
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="content">유의사항</label>
                                <textarea class="textContent textarea" name="content" id="content" cols="30"
                                    rows="10"></textarea>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="content">보상</label>
                                <textarea class="textContent textarea" name="content" id="content" cols="30"
                                    rows="10"></textarea>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="content">첨부파일</label>
                                <div class="fileGroup">
                                    <input class="textContent" type="file" name="username" id="username"
                                        style="margin-top: 10px;">
                                    <div class="inputGroup imagePlaceholder">
                                        <img src="/resources/img/thumbnail_challenge 1.png" alt=""
                                            style="max-width: 100%; max-height: 100%;">
                                    </div>
                                </div>
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