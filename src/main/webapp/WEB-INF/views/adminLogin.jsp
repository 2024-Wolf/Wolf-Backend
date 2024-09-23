<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="ko">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-widt h, initial-scale=1.0">
            <title>WOLF 관리자 페이지</title>
            <link rel="stylesheet" href="/resources/css/globalstyle.css">
            <link rel="stylesheet" href="/resources/css/mainContents.css">
            <link rel="stylesheet" href="/resources/css/login.css">
        </head>

        <body>
            <%@ include file="header.jsp" %>
                <div class="mainContents">
                    <div class="loginContainer">
                        <div class="loginCard">
                            <div>
                                <h2 class="loginLogo">WOLF</h2>
                                <p class="loginDescription">WOLF 관리자 페이지 입니다.</p>
                            </div>
                            <form class="loginForm" action="notice" method="get">
                                <input id="username" type="text" class="loginInput textContent" placeholder="아이디"
                                    aria-label="아이디" name="username">
                                <input id="password" type="password" class="loginInput textContent" placeholder="비밀번호"
                                    aria-label="비밀번호" name="password">
                                <button type="submit" class="loginButton darkBackgroundButton">로그인</button>
                            </form>
                        </div>
                    </div>
                </div>
                <%@ include file="footer.jsp" %>
        </body>

        </html>