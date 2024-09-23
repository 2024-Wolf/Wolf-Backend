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
                    <h2 class="title">인증</h2>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>회원</th>
                                <th>인증 챌린지</th>
                                <th>그룹</th>
                                <th>인증일</th>
                                <th>인증 상태</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><a href="/authDetail" class="aLink">2</a></td>
                                <td><a href="/authDetail" class="aLink">늑대소녀</a></td>
                                <td><a href="/authDetail" class="aLink">야 너도 자격증 딸 수 있어!</a></td>
                                <td><a href="/authDetail" class="aLink">늑대들</a></td>
                                <td><a href="/authDetail" class="aLink">2024.09.13</a></td>
                                <td><button class="btn1" onclick="location.href='/authInsert'">인증실패</button></td>
                            </tr>
                            <tr>
                                <td><a href="/authDetail" class="aLink">2</a></td>
                                <td><a href="/authDetail" class="aLink">늑대소년</a></td>
                                <td><a href="/authDetail" class="aLink">야 너도 자격증 딸 수 있어!</a></td>
                                <td><a href="/authDetail" class="aLink">늑대들</a></td>
                                <td><a href="/authDetail" class="aLink">2024.09.13</a></td>
                                <td><button class="btn1" onclick="location.href='/authInsert'" disabled>인증성공</button>
                                </td>
                            </tr>
                            <tr>
                                <td><a href="/authDetail" class="aLink">2</a></td>
                                <td><a href="/authDetail" class="aLink">늑대소녀</a></td>
                                <td><a href="/authDetail" class="aLink">야 너도 자격증 딸 수 있어!</a></td>
                                <td><a href="/authDetail" class="aLink">늑대들</a></td>
                                <td><a href="/authDetail" class="aLink">2024.09.13</a></td>
                                <td><button class="btn1" onclick="location.href='/authInsert'">인증실패</button></td>
                            </tr>
                            <tr>
                                <td><a href="/authDetail" class="aLink">2</a></td>
                                <td><a href="/authDetail" class="aLink">늑대소년</a></td>
                                <td><a href="/authDetail" class="aLink">야 너도 자격증 딸 수 있어!</a></td>
                                <td><a href="/authDetail" class="aLink">늑대들</a></td>
                                <td><a href="/authDetail" class="aLink">2024.09.13</a></td>
                                <td><button class="btn1" onclick="location.href='/authInsert'" disabled>인증성공</button>
                                </td>
                            </tr>
                            <tr>
                                <td><a href="/authDetail" class="aLink">2</a></td>
                                <td><a href="/authDetail" class="aLink">늑대소녀</a></td>
                                <td><a href="/authDetail" class="aLink">야 너도 자격증 딸 수 있어!</a></td>
                                <td><a href="/authDetail" class="aLink">늑대들</a></td>
                                <td><a href="/authDetail" class="aLink">2024.09.13</a></td>
                                <td><button class="btn1" onclick="location.href='/authInsert'">인증실패</button></td>
                            </tr>
                            <tr>
                                <td><a href="/authDetail" class="aLink">2</a></td>
                                <td><a href="/authDetail" class="aLink">늑대소년</a></td>
                                <td><a href="/authDetail" class="aLink">야 너도 자격증 딸 수 있어!</a></td>
                                <td><a href="/authDetail" class="aLink">늑대들</a></td>
                                <td><a href="/authDetail" class="aLink">2024.09.13</a></td>
                                <td><button class="btn1" onclick="location.href='/authInsert'" disabled>인증성공</button>
                                </td>
                            </tr>
                            <tr>
                                <td><a href="/authDetail" class="aLink">2</a></td>
                                <td><a href="/authDetail" class="aLink">늑대소녀</a></td>
                                <td><a href="/authDetail" class="aLink">야 너도 자격증 딸 수 있어!</a></td>
                                <td><a href="/authDetail" class="aLink">늑대들</a></td>
                                <td><a href="/authDetail" class="aLink">2024.09.13</a></td>
                                <td><button class="btn1" onclick="location.href='/authInsert'">인증실패</button></td>
                            </tr>
                            <tr>
                                <td><a href="/authDetail" class="aLink">2</a></td>
                                <td><a href="/authDetail" class="aLink">늑대소년</a></td>
                                <td><a href="/authDetail" class="aLink">야 너도 자격증 딸 수 있어!</a></td>
                                <td><a href="/authDetail" class="aLink">늑대들</a></td>
                                <td><a href="/authDetail" class="aLink">2024.09.13</a></td>
                                <td><button class="btn1" onclick="location.href='/authInsert'" disabled>인증성공</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
        </div>
        <%@ include file="footer.jsp" %>
</body>

</html>