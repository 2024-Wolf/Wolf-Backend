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
                        <h2 class="title">챌린지</h2>
                        <span class="buttonSideContainer">
                            <button class="formButton darkBackgroundButton"
                                onclick="location.href='/noticeCreate'">작성하기</button>
                        </span>
                    </div>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>이름</th>
                                <th>인증 주체</th>
                                <th>시작일</th>
                                <th>종료일</th>
                                <th>상태</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>3</td>
                                <td>너도 자격증 딸 수 있어!</td>
                                <td>시스템</td>
                                <td>2024.10.13</td>
                                <td>2025.04.13</td>
                                <td>진행 전</td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>너도 자격증 딸 수 있어!</td>
                                <td>시스템</td>
                                <td>2024.09.13</td>
                                <td>2025.03.13</td>
                                <td>진행 후</td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td>너도 자격증 딸 수 있어!</td>
                                <td>시스템</td>
                                <td>2024.04.13</td>
                                <td>2024.09.13</td>
                                <td>완료</td>
                            </tr>
                            <tr>
                                <td>3</td>
                                <td>너도 자격증 딸 수 있어!</td>
                                <td>시스템</td>
                                <td>2024.10.13</td>
                                <td>2025.04.13</td>
                                <td>진행 전</td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>너도 자격증 딸 수 있어!</td>
                                <td>시스템</td>
                                <td>2024.09.13</td>
                                <td>2025.03.13</td>
                                <td>진행 후</td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td>너도 자격증 딸 수 있어!</td>
                                <td>시스템</td>
                                <td>2024.04.13</td>
                                <td>2024.09.13</td>
                                <td>완료</td>
                            </tr>
                            <tr>
                                <td>3</td>
                                <td>너도 자격증 딸 수 있어!</td>
                                <td>시스템</td>
                                <td>2024.10.13</td>
                                <td>2025.04.13</td>
                                <td>진행 전</td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>너도 자격증 딸 수 있어!</td>
                                <td>시스템</td>
                                <td>2024.09.13</td>
                                <td>2025.03.13</td>
                                <td>진행 후</td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td>너도 자격증 딸 수 있어!</td>
                                <td>시스템</td>
                                <td>2024.04.13</td>
                                <td>2024.09.13</td>
                                <td>완료</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
        </div>
        <%@ include file="footer.jsp" %>
</body>

</html>