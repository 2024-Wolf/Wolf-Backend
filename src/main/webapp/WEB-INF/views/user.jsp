<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> -->
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WOLF 관리자 페이지</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css">
    <link href="https://fonts.googleapis.com/css2?family=Kavoon&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="/resources/css/globalstyle.css">
	<link rel="stylesheet" href="/resources/css/mainContents.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/user.css">

</head>
<body>
		<%@ include file="header.jsp" %>
		<div class="mainContents">
			<%@ include file="sidebar.jsp" %>
            <section class="content-area">
                <div class="content-header">
                    <h2 class="content-title">회원</h2>
                </div>
                <div class="content-main">
                    <table class="user-table">
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
                                <td>5</td>
                                <td>닉네임</td>
                                <td>풀스택 개발자</td>
                                <td>LG CNS</td>
                                <td>1년</td>
                                <td>2024.09.19</td>
                            </tr>
                            <tr>
                                <td>4</td>
                                <td>닉네임</td>
                                <td>풀스택 개발자</td>
                                <td>LG CNS</td>
                                <td>1년</td>
                                <td>2024.09.19</td>
                            </tr>
                            <tr>
                                <td>3</td>
                                <td>닉네임</td>
                                <td>풀스택 개발자</td>
                                <td>LG CNS</td>
                                <td>1년</td>
                                <td>2024.09.19</td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>닉네임</td>
                                <td>풀스택 개발자</td>
                                <td>LG CNS</td>
                                <td>1년</td>
                                <td>2024.09.19</td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td>닉네임</td>
                                <td>풀스택 개발자</td>
                                <td>LG CNS</td>
                                <td>1년</td>
                                <td>2024.09.19</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </section>
		</div>
		<%@ include file="footer.jsp" %>
</body>
</html>
