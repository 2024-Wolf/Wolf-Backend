<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                            <h2 class="title">인증 정보</h2>
                            <span class="buttonSideContainer">
                                <button class="formButton noBackgroundButton" onclick="location.href='/auth'"><svg
                                        xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                        class="bi bi-chevron-left" viewBox="0 0 16 16">
                                        <path fill-rule="evenodd"
                                            d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0" />
                                    </svg>이전</button>
                            </span>
                        </div>
                        <div class="inputSection">
                            <div class="inputGroup">
                                <label class="subtitle" for="status">상태</label>
                                <select class="textContent input" name="status" id="status" disabled>
                                    <option value="success">인증 성공</option>
                                    <option value="failure">인증 실패</option>
                                </select>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="auth-date">인증일</label>
                                <input class="textContent input" type="date" name="auth-date" id="auth-date" disabled>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="username">닉네임</label>
                                <input class="textContent input" type="text" name="username" id="username" disabled>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="challenge">챌린지</label>
                                <input class="textContent input" type="text" name="challenge" id="challenge" disabled>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="content">내용
                                </label>
                                <textarea class="textContent textarea" name="content" id="content" cols="30" rows="10"
                                    disabled></textarea>
                            </div>
                            <div class="buttonContainer">
                                <button class="formButton linePurpleButton" onclick="window.history.back()">취소</button>
                                <button class="formButton darkBackgroundButton"
                                    onclick="alert('작성완료'); location.href='/auth'">완료</button>
                            </div>
                        </div>
                    </div>
            </div>
            <%@ include file="footer.jsp" %>
    </body>

    </html>