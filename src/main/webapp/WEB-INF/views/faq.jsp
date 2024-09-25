<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> -->
<!DOCTYPE html>
<html lang="ko">

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
                        <h2 class="title">FAQ</h2>
                        <span class="buttonSideContainer">
                            <button class="formButton darkBackgroundButton"
                                onclick="location.href='/faqCreate'">작성하기</button>
                        </span>
                    </div>
                    <div class="scrollArea">
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
                                    <td><a href="/faqDetail" class="aLink">7</a></td>
                                    <td><a href="/faqDetail" class="aLink">제목입니다. 제목입니다. 제목입니다. 제목입니다. 제목입니다.
                                            제목입니다. 제목입니다.</a></td>
                                    <td><a href="/faqDetail" class="aLink">등록자</a></td>
                                    <td><a href="/faqDetail" class="aLink">24.09.12</a></td>
                                </tr>
                                <tr>
                                    <td><a href="/faqDetail" class="aLink">6</a></td>
                                    <td><a href="/faqDetail" class="aLink">제목입니다. 제목입니다. 제목입니다. 제목입니다. 제목입니다.
                                            제목입니다. 제목입니다.</a></td>
                                    <td><a href="/faqDetail" class="aLink">등록자</a></td>
                                    <td><a href="/faqDetail" class="aLink">24.09.12</a></td>
                                </tr>
                                <tr>
                                    <td><a href="/faqDetail" class="aLink">5</a></td>
                                    <td><a href="/faqDetail" class="aLink">제목입니다. 제목입니다. 제목입니다. 제목입니다. 제목입니다.
                                            제목입니다. 제목입니다.</a></td>
                                    <td><a href="/faqDetail" class="aLink">등록자</a></td>
                                    <td><a href="/faqDetail" class="aLink">24.09.12</a></td>
                                </tr>
                                <tr>
                                    <td><a href="/faqDetail" class="aLink">4</a></td>
                                    <td><a href="/faqDetail" class="aLink">제목입니다. 제목입니다. 제목입니다. 제목입니다. 제목입니다.
                                            제목입니다. 제목입니다.</a></td>
                                    <td><a href="/faqDetail" class="aLink">등록자</a></td>
                                    <td><a href="/faqDetail" class="aLink">24.09.12</a></td>
                                </tr>
                                <tr>
                                    <td><a href="/faqDetail" class="aLink">3</a></td>
                                    <td><a href="/faqDetail" class="aLink">제목입니다. 제목입니다. 제목입니다. 제목입니다. 제목입니다.
                                            제목입니다. 제목입니다.</a></td>
                                    <td><a href="/faqDetail" class="aLink">등록자</a></td>
                                    <td><a href="/faqDetail" class="aLink">24.09.12</a></td>
                                </tr>
                                <tr>
                                    <td><a href="/faqDetail" class="aLink">2</a></td>
                                    <td><a href="/faqDetail" class="aLink">제목입니다. 제목입니다. 제목입니다. 제목입니다. 제목입니다.
                                            제목입니다. 제목입니다.</a></td>
                                    <td><a href="/faqDetail" class="aLink">등록자</a></td>
                                    <td><a href="/faqDetail" class="aLink">24.09.12</a></td>
                                </tr>
                                <tr>
                                    <td><a href="/faqDetail" class="aLink">1</a></td>
                                    <td><a href="/faqDetail" class="aLink">제목입니다. 제목입니다. 제목입니다. 제목입니다. 제목입니다.
                                            제목입니다. 제목입니다.</a></td>
                                    <td><a href="/faqDetail" class="aLink">등록자</a></td>
                                    <td><a href="/faqDetail" class="aLink">24.09.12</a></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
        </div>
        <%@ include file="footer.jsp" %>
</body>

</html>