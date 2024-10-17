<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="ko">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>WOLF 관리자 페이지</title>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
            <link rel="stylesheet" href="/resources/css/globalstyle.css">
            <link rel="stylesheet" href="/resources/css/mainContents.css">
            <link rel="stylesheet" href="/resources/css/login.css">
        </head>

        <body>
            <!-- 헤더 -->
            <%@ include file="components/header.jsp" %>
                <div class="mainContents">
                    <!-- 로그인 컨테이너 -->
                    <div class="loginContainer">
                        <div class="loginCard">
                            <div style="text-align: center;">
                                <h2 class="loginLogo">WOLF</h2>
                                <p class="loginDescription">
                                    <span>관리자 페이지에</span>
                                    <span>&nbsp오신 것을</span>
                                    <span>&nbsp환영합니다.</span>
                                </p>
                            </div>
                            <form class="loginForm" action="/admin/auth/login" method="post">
                                <input id="username" type="text" class="loginInput loginDescription" placeholder="아이디"
                                    aria-label="아이디" name="username">
                                <input id="password" type="password" class="loginInput loginDescription"
                                    placeholder="비밀번호" aria-label="비밀번호" name="password">
                                <button type="submit" class="loginButton darkBackgroundButton">로그인</button>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- 푸터 -->
                <%@ include file="components/footer.jsp" %>
        </body>

        </html>