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
                    <h2 class="title">신고</h2>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>신고자</th>
                                <th>신고 내용</th>
                                <th>신고 대상</th>
                                <th>신고일</th>
                                <th>신고 상태</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><a href="/reportDetail" class="aLink">5</a></td>
                                <td><a href="/reportDetail" class="aLink">늑대소년</a></td>
                                <td><a href="/reportDetail" class="aLink">스터디 무단 추방</a></td>
                                <td><a href="/reportDetail" class="aLink">늑대소녀</a></td>
                                <td><a href="/reportDetail" class="aLink">2024.09.13</a></td>
                                <td><button class="btn1" onclick="location.href='/reportInsert'">접수</button></td>
                            </tr>
                            <tr>
                                <td><a href="/reportDetail" class="aLink">4</a></td>
                                <td><a href="/reportDetail" class="aLink">늑대소년</a></td>
                                <td><a href="/reportDetail" class="aLink">스터디 무단 추방</a></td>
                                <td><a href="/reportDetail" class="aLink">늑대소녀</a></td>
                                <td><a href="/reportDetail" class="aLink">2024.09.13</a></td>
                                <td><button class="btn1" onclick="location.href='/reportInsert'" disabled>완료</button>
                                </td>
                            </tr>
                            <tr>
                                <td><a href="/reportDetail" class="aLink">3</a></td>
                                <td><a href="/reportDetail" class="aLink">늑대소년</a></td>
                                <td><a href="/reportDetail" class="aLink">스터디 무단 추방</a></td>
                                <td><a href="/reportDetail" class="aLink">늑대소녀</a></td>
                                <td><a href="/reportDetail" class="aLink">2024.09.13</a></td>
                                <td><button class="btn1" onclick="location.href='/reportInsert'">접수</button></td>
                            </tr>
                            <tr>
                                <td><a href="/reportDetail" class="aLink">2</a></td>
                                <td><a href="/reportDetail" class="aLink">늑대소년</a></td>
                                <td><a href="/reportDetail" class="aLink">스터디 무단 추방</a></td>
                                <td><a href="/reportDetail" class="aLink">늑대소녀</a></td>
                                <td><a href="/reportDetail" class="aLink">2024.09.13</a></td>
                                <td><button class="btn1" onclick="location.href='/reportInsert'" disabled>완료</button>
                                </td>
                            </tr>
                            <tr>
                                <td><a href="/reportDetail" class="aLink">1</a></td>
                                <td><a href="/reportDetail" class="aLink">늑대소년</a></td>
                                <td><a href="/reportDetail" class="aLink">스터디 무단 추방</a></td>
                                <td><a href="/reportDetail" class="aLink">늑대소녀</a></td>
                                <td><a href="/reportDetail" class="aLink">2024.09.13</a></td>
                                <td><button class="btn1" onclick="location.href='/reportInsert'">접수</button></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
        </div>
        <%@ include file="footer.jsp" %>
</body>

</html>