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


    <span class="buttonSideContainer">

    </span>

    <body>
        <%@ include file="header.jsp" %>
            <div class="mainContents">
                <%@ include file="sidebar.jsp" %>
                    <div class="infoCard">
                        <div class="inputGroup">
                            <h2 class="title">공지사항 정보</h2>
                            <span class="buttonSideContainer">
                                <button class="formButton linePurpleButton"
                                    onclick="location.href='/noticeEdit'">수정</button>
                                <button class="formButton darkBackgroundButton"
                                    onclick="alert('삭제완료'); location.href='/notice'">삭제</button>
                                <button class="formButton linePurpleButton"
                                    onclick="location.href='/notice'">이전</button>
                            </span>
                        </div>
                        <div class="inputSection">
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
                            <div class="inputGroup imagePlaceholder">
                                <img src="path/to/image.jpg" alt="임시 이미지" style="max-width: 100%; max-height: 100%;">
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="content">첨부파일</label>
                                <input class="textContent" type="file" name="username" id="username" disabled>
                            </div>
                        </div>
                    </div>
            </div>
            <%@ include file="footer.jsp" %>
    </body>

    </html>