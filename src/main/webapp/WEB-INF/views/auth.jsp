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
                                <th>상태</th>
                                <th>성공</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>2</td>
                                <td>늑대소녀</td>
                                <td>야 너도 자격증 딸 수 있어!</td>
                                <td>늑대들</td>
                                <td>2024.09.13</td>
                                <td>성공</td>
                                <td><button class="btn1" disabled>성공 처리</button></td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td>늑대소년</td>
                                <td>야 너도 자격증 딸 수 있어!</td>
                                <td>늑대들</td>
                                <td>2024.09.13</td>
                                <td>실패</td>
                                <td><button class="btn1">성공 처리</button></td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>늑대소녀</td>
                                <td>야 너도 자격증 딸 수 있어!</td>
                                <td>늑대들</td>
                                <td>2024.09.13</td>
                                <td>성공</td>
                                <td><button class="btn1" disabled>성공 처리</button></td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td>늑대소년</td>
                                <td>야 너도 자격증 딸 수 있어!</td>
                                <td>늑대들</td>
                                <td>2024.09.13</td>
                                <td>실패</td>
                                <td><button class="btn1">성공 처리</button></td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>늑대소녀</td>
                                <td>야 너도 자격증 딸 수 있어!</td>
                                <td>늑대들</td>
                                <td>2024.09.13</td>
                                <td>성공</td>
                                <td><button class="btn1" disabled>성공 처리</button></td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td>늑대소년</td>
                                <td>야 너도 자격증 딸 수 있어!</td>
                                <td>늑대들</td>
                                <td>2024.09.13</td>
                                <td>실패</td>
                                <td><button class="btn1">성공 처리</button></td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>늑대소녀</td>
                                <td>야 너도 자격증 딸 수 있어!</td>
                                <td>늑대들</td>
                                <td>2024.09.13</td>
                                <td>성공</td>
                                <td><button class="btn1" disabled>성공 처리</button></td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td>늑대소년</td>
                                <td>야 너도 자격증 딸 수 있어!</td>
                                <td>늑대들</td>
                                <td>2024.09.13</td>
                                <td>실패</td>
                                <td><button class="btn1">성공 처리</button></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
        </div>
        <%@ include file="footer.jsp" %>
</body>

</html>