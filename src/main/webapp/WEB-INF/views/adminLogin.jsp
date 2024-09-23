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
            <!--	
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css">
	<link href="https://fonts.googleapis.com/css2?family=Kavoon&display=swap" rel="stylesheet">
	
	-->
            <style>
                .content-area {
                    flex: 1;
                    width: 800px;
                    overflow-y: auto;
                    background-color: var(--black000);
                    padding: 0px 30px;
                }

                .content-header {
                    display: flex;
                    justify-content: space-between;
                    align-items: center;
                    margin-bottom: 50px;
                }

                .content-title {
                    color: #49494a;
                    font-size: 48px;
                    font-weight: 700;
                }

                .content-main {
                    max-height: 500px;
                    /* 원하는 최대 높이 설정 */
                    overflow: auto;
                    box-sizing: border-box;
                    /* 패딩을 포함하여 높이 계산 */
                }

                .create-btn {
                    border-radius: 5px;
                    background-color: var(--violet500);
                    color: var(--black000);
                    border-color: var(--violet500);
                    padding: 10px 50px;
                    font: 14px Pretendard, sans-serif;
                }

                .itle {
                    text-align: center;
                    font: 50px Kavoon, sans-serif;
                    font-weight: lighter;
                    color: var(--violet700);
                }


                .input-field {
                    align-self: stretch;
                    border-radius: 10px;
                    min-height: 30px;
                    padding: 11px 20px;
                    border: 2px solid var(--black200);
                }

                .username-input {
                    background-color: var(--black000);
                }

                .password-input {
                    background-color: var(--black000);
                    margin-top: 10px;
                }

                .login-button {
                    align-self: stretch;
                    border-radius: 10px;
                    width: 388px;
                    height: 54px;
                    color: #fff;
                    border-color: var(--violet500);
                    background-color: var(--violet500);
                    margin: 50px 0px 0px 50px;

                }
            </style>
        </head>

        <body>
            <%@ include file="header.jsp" %>
                <div class="mainContents">
                    <div class="loginContainer">
                        <div class="loginCard">
                            <div>
                                <h2 class="loginLogo">WOLF</h2>
                                <p class="textContent">WOLF 관리자 페이지 입니다.</p>
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
                </div>


                <div class="main-content">

                </div>



                </div>
                </div>
                <%@ include file="footer.jsp" %>
        </body>

        </html>