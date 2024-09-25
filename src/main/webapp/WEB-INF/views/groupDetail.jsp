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
                            <h2 class="title">그룹 정보</h2>
                            <span class="buttonSideContainer">
                                <button class="formButton noBackgroundButton" onclick="location.href='/group'"><svg
                                        xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                        class="bi bi-chevron-left" viewBox="0 0 16 16">
                                        <path fill-rule="evenodd"
                                            d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0" />
                                    </svg>이전</button>
                            </span>
                        </div>
                        <div class="inputSection scrollArea">
                            <div class="inputGroup">
                                <label class="subtitle" for="groupStartDate">기간</label>
                                <input class="textContent input" type="date" name="groupStartDate" id="groupStartDate"
                                    disabled>
                                ~
                                <input class="textContent input" type="date" name="groupEndDate" id="groupEndDate"
                                    disabled>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="challengeType">구분</label>
                                <input class="textContent input" type="text" name="challengeType" id="challengeType"
                                    disabled>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="groupName">그룹명</label>
                                <input class="textContent input" type="text" name="groupName" id="groupName" disabled>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="groupLeader">그룹장</label>
                                <input class="textContent input" type="text" name="groupLeader" id="groupLeader"
                                    disabled>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="participants">인원</label>
                                <input class="textContent input" type="text" name="participants" id="participants"
                                    disabled>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="participants">그룹원</label>
                                <input class="textContent input" type="text" name="participants" id="participants"
                                    disabled>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="status">상태</label>
                                <input class="textContent input" type="text" name="status" id="status" disabled>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="tags">태그</label>
                                <input class="textContent input" type="text" name="tags" id="tags" disabled>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="challengeName">챌린지</label>
                                <input class="textContent input" type="text" name="challengeName" id="challengeName"
                                    disabled>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="participatingChallenge">참가 챌린지</label>
                                <input class="textContent input" type="text" name="participatingChallenge"
                                    id="participatingChallenge" disabled>
                            </div>
                        </div>
                    </div>
            </div>
            <%@ include file="footer.jsp" %>
    </body>

    </html>