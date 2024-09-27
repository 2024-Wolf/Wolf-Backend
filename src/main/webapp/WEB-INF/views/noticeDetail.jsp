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
                        <div class="titleInputGroup">
                            <h2 class="title">공지사항 정보</h2>
                            <button class="sideButton noBackgroundButton" onclick="location.href='/notice'"><svg
                                    xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                    class="bi bi-chevron-left" viewBox="0 0 16 16">
                                    <path fill-rule="evenodd"
                                        d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0" />
                                </svg><span class="innerText">이전</span></button>
                        </div>
                        <div class="buttonSideContainer">
                            <button class="sideButton linePurpleButton" onclick="location.href='/noticeEdit'"><svg
                                    xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                    class="bi bi-pencil-square" viewBox="0 0 16 16">
                                    <path
                                        d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
                                    <path fill-rule="evenodd"
                                        d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z" />
                                </svg><span class="innerText">수정</span></button>

                            <button class="sideButton darkBackgroundButton"
                                onclick="alert('삭제완료'); location.href='/notice'"><svg xmlns="http://www.w3.org/2000/svg"
                                    width="16" height="16" fill="currentColor" class="bi bi-trash-fill"
                                    viewBox="0 0 16 16">
                                    <path
                                        d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5M8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5m3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0" />
                                </svg><span class="innerText">삭제</span></button>
                        </div>
                        <div class="inputSection scrollArea">
                            <div class="inputGroup">
                                <label class="subtitle" for="auth-date">등록일</label>
                                <input class="textContent input" type="date" name="auth-date" id="auth-date"
                                    value="2024-09-24" disabled>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="username">등록자</label>
                                <input class="textContent input" type="text" name="username" id="username"
                                    value="우두머리 늑대" disabled>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="challenge">제목</label>
                                <input class="textContent input" type="text" name="challenge" id="challenge"
                                    value="울프 서비스가 시작되었습니다." disabled>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="content">내용</label>
                                <textarea class="textContent textarea" name="content" id="content" cols="30" rows="10"
                                    disabled>지금 2조에서 울프 서비스 개발을 완료했습니다. 많은 관심 부탁드립니다.</textarea>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="content">첨부파일</label>
                                <div class="fileGroup">
                                    <input class="textContent" type="file" name="username" id="username"
                                        style="margin-top: 10px;" disabled>
                                    <div class="inputGroup imagePlaceholder">
                                        <img src="/resources/img/thumbnail_challenge 1.png" alt=""
                                            style="max-width: 100%; max-height: 100%;">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
            </div>
            <%@ include file="footer.jsp" %>
    </body>

    </html>