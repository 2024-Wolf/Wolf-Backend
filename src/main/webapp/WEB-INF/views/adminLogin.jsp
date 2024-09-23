<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-widt h, initial-scale=1.0">
    <title>WOLF 관리자 페이지</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css">
    <link href="https://fonts.googleapis.com/css2?family=Kavoon&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/admin.css">
    <link rel="stylesheet" href="/resources/css/header.css">
    <link rel="stylesheet" href="/resources/css/footer.css">
    <link rel="stylesheet" href="/resources/css/login.css">

</head>
<body>
    <div class="admin-main">
        <div class="header">
        </div>
    
        <div class="main-content">
            <div class="login-container">
                <h2 class="main-title">WOLF</h2>
                <p class="description">WOLF 관리자 페이지 입니다.</p>
                <form class="login-form" action="notice" method="get">
                    <div class="input-wrapper">
                        <input id="username" type="text" class="input-field username-input" placeholder="아이디" aria-label="아이디" name="username">
                        
                        <input id="password" type="password" class="input-field password-input" placeholder="비밀번호" aria-label="비밀번호" name="password">
                    </div>
                    <button type="submit" class="login-button">로그인</button>
                </form>
            </div>
        </div>
    
        <div class="footer">
            
        </div>
    </div>
</body>
</html>
