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
                                <th>상태</th>
                                <th>처리</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>5</td>
                                <td>늑대소년</td>
                                <td>스터디 무단 추방</td>
                                <td>늑대소녀</td>
                                <td>2024.09.13</td>
                                <td>처리 필요</td>
                                <td><button class="btn1">처리하기</button></td>
                            </tr>
                            <tr>
                                <td>4</td>
                                <td>늑대소년</td>
                                <td>스터디 무단 추방</td>
                                <td>늑대소녀</td>
                                <td>2024.09.13</td>
                                <td>처리 필요</td>
                                <td><button class="btn1">처리하기</button></td>
                            </tr>
                            <tr>
                                <td>3</td>
                                <td>늑대소년</td>
                                <td>스터디 무단 추방</td>
                                <td>늑대소녀</td>
                                <td>2024.09.13</td>
                                <td>처리 필요</td>
                                <td><button class="btn1">처리하기</button></td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>늑대소년</td>
                                <td>스터디 무단 추방</td>
                                <td>늑대소녀</td>
                                <td>2024.09.13</td>
                                <td>처리 필요</td>
                                <td><button class="btn1">처리하기</button></td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td>늑대소년</td>
                                <td>스터디 무단 추방</td>
                                <td>늑대소녀</td>
                                <td>2024.09.13</td>
                                <td>처리 필요</td>
                                <td><button class="btn1">처리하기</button></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
        </div>
        <%@ include file="footer.jsp" %>
</body>

</html>