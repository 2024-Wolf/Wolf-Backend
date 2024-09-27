<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> -->
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WOLF 관리자 페이지</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <link rel="stylesheet" href="/resources/css/globalstyle.css">
    <link rel="stylesheet" href="/resources/css/mainContents.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/table.css">

</head>

<body>
    <%@ include file="header.jsp" %>
        <div class="mainContents">
            <%@ include file="sidebar.jsp" %>
                <div class="infoCard">
                    <h2 class="title">회원</h2>
					
                    <div class="scrollArea">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>닉네임</th>
                                    <th>직무</th>
                                    <th>소속</th>
                                    <th>경력</th>
                                    <th>가입일</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><a href="/userDetail" class="aLink">5</a></td>
                                    <td><a href="/userDetail" class="aLink">닉네임</a></td>
                                    <td><a href="/userDetail" class="aLink">풀스택 개발자</a></td>
                                    <td><a href="/userDetail" class="aLink">LG CNS</a></td>
                                    <td><a href="/userDetail" class="aLink">1년</a></td>
                                    <td><a href="/userDetail" class="aLink">2024.09.19</a></td>
                                </tr>
                                <tr>
                                    <td><a href="/userDetail" class="aLink">4</a></td>
                                    <td><a href="/userDetail" class="aLink">닉네임</a></td>
                                    <td><a href="/userDetail" class="aLink">풀스택 개발자</a></td>
                                    <td><a href="/userDetail" class="aLink">LG CNS</a></td>
                                    <td><a href="/userDetail" class="aLink">1년</a></td>
                                    <td><a href="/userDetail" class="aLink">2024.09.19</a></td>
                                </tr>
                                <tr>
                                    <td><a href="/userDetail" class="aLink">3</a></td>
                                    <td><a href="/userDetail" class="aLink">닉네임</a></td>
                                    <td><a href="/userDetail" class="aLink">풀스택 개발자</a></td>
                                    <td><a href="/userDetail" class="aLink">LG CNS</a></td>
                                    <td><a href="/userDetail" class="aLink">1년</a></td>
                                    <td><a href="/userDetail" class="aLink">2024.09.19</a></td>
                                </tr>
                                <tr>
                                    <td><a href="/userDetail" class="aLink">2</a></td>
                                    <td><a href="/userDetail" class="aLink">닉네임</a></td>
                                    <td><a href="/userDetail" class="aLink">풀스택 개발자</a></td>
                                    <td><a href="/userDetail" class="aLink">LG CNS</a></td>
                                    <td><a href="/userDetail" class="aLink">1년</a></td>
                                    <td><a href="/userDetail" class="aLink">2024.09.19</a></td>
                                </tr>
                                <tr>
                                    <td><a href="/userDetail" class="aLink">1</a></td>
                                    <td><a href="/userDetail" class="aLink">닉네임</a></td>
                                    <td><a href="/userDetail" class="aLink">풀스택 개발자</a></td>
                                    <td><a href="/userDetail" class="aLink">LG CNS</a></td>
                                    <td><a href="/userDetail" class="aLink">1년</a></td>
                                    <td><a href="/userDetail" class="aLink">2024.09.19</a></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
        </div>
        <%@ include file="footer.jsp" %>
</body>

</html>