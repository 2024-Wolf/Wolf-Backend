<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> -->
<!DOCTYPE html>
<html lang="ko">
<% System.out.println("request.getRequestURI()"); %>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>WOLF 관리자 페이지</title>

        <link rel="stylesheet" href="/resources/css/globalstyle.css">
        <link rel="stylesheet" href="/resources/css/mainContents.css">
        <link rel="stylesheet" type="text/css" href="/resources/css/table.css">

    </head>

    <body>
        <%@ include file="header.jsp" %>
            <div class="mainContents">
                <%@ include file="sidebar.jsp" %>
                    <div class="infoCard">
                        <div class="inputGroup">
                            <h2 class="title">공지사항</h2>
                            <span class="buttonSideContainer">
                                <button class="formButton darkBackgroundButton"
                                    onclick="location.href='/noticeCreate'">작성하기</button>
                            </span>
                        </div>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>제목</th>
                                    <th>등록자</th>
                                    <th>등록일</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>5</td>
                                    <td class="title-cell"><a href="/noticeDetail">제목입니다. 제목입니다. 제목입니다. 제목입니다.
                                            제목입니다.
                                            제목입니다.
                                            제목입니다.</a></td>
                                    <td>등록자</td>
                                    <td>24.09.12</td>
                                </tr>
                                <tr>
                                    <td>4</td>
                                    <td class="title-cell"><a href="/noticeDetail">제목입니다. 제목입니다. 제목입니다. 제목입니다.
                                            제목입니다.
                                            제목입니다.
                                            제목입니다.</a></td>
                                    <td>정명주</td>
                                    <td>24.09.12</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td class="title-cell"><a href="/noticeDetail">제목입니다. 제목입니다. 제목입니다. 제목입니다.
                                            제목입니다.
                                            제목입니다.
                                            제목입니다.</a></td>
                                    <td>정명주</td>
                                    <td>24.09.12</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td class="title-cell"><a href="/noticeDetail">제목입니다. 제목입니다. 제목입니다. 제목입니다.
                                            제목입니다.
                                            제목입니다.
                                            제목입니다.</a></td>
                                    <td>정명주</td>
                                    <td>24.09.12</td>
                                </tr>
                                <tr>
                                    <td>1</td>
                                    <td class="title-cell"><a href="/noticeDetail">제목입니다. 제목입니다. 제목입니다. 제목입니다.
                                            제목입니다.
                                            제목입니다.
                                            제목입니다.</a></td>
                                    <td>정명주</td>
                                    <td>24.09.12</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
            </div>
            <%@ include file="footer.jsp" %>
    </body>

</html>