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
                    <div class="titleInputGroup">
                        <h2 class="title">챌린지</h2>
                        <span class="buttonSideContainer">
                            <button class="formButton darkBackgroundButton"
                                onclick="location.href='/challengeCreate'">작성하기</button>
                        </span>
                    </div>
                    <div class="scrollArea">
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
                                    <td><a href="/challengeDetail" class="aLink">9</a></td>
                                    <td><a href="/challengeDetail" class="aLink">너도 자격증 딸 수 있어!</a></td>
                                    <td><a href="/challengeDetail" class="aLink">시스템</a></td>
                                    <td><a href="/challengeDetail" class="aLink">2024.10.13</a></td>
                                    <td><a href="/challengeDetail" class="aLink">2025.04.13</a></td>
                                    <td><a href="/challengeDetail" class="aLink">진행 전</a></td>
                                </tr>
                                <tr>
                                    <td><a href="/challengeDetail" class="aLink">8</a></td>
                                    <td><a href="/challengeDetail" class="aLink">너도 자격증 딸 수 있어!</a></td>
                                    <td><a href="/challengeDetail" class="aLink">시스템</a></td>
                                    <td><a href="/challengeDetail" class="aLink">2024.10.13</a></td>
                                    <td><a href="/challengeDetail" class="aLink">2025.04.13</a></td>
                                    <td><a href="/challengeDetail" class="aLink">진행 후</a></td>
                                </tr>
                                <tr>
                                    <td><a href="/challengeDetail" class="aLink">7</a></td>
                                    <td><a href="/challengeDetail" class="aLink">너도 자격증 딸 수 있어!</a></td>
                                    <td><a href="/challengeDetail" class="aLink">시스템</a></td>
                                    <td><a href="/challengeDetail" class="aLink">2024.10.13</a></td>
                                    <td><a href="/challengeDetail" class="aLink">2025.04.13</a></td>
                                    <td><a href="/challengeDetail" class="aLink">완료</a></td>
                                </tr>
                                <tr>
                                    <td><a href="/challengeDetail" class="aLink">6</a></td>
                                    <td><a href="/challengeDetail" class="aLink">너도 자격증 딸 수 있어!</a></td>
                                    <td><a href="/challengeDetail" class="aLink">시스템</a></td>
                                    <td><a href="/challengeDetail" class="aLink">2024.10.13</a></td>
                                    <td><a href="/challengeDetail" class="aLink">2025.04.13</a></td>
                                    <td><a href="/challengeDetail" class="aLink">진행 전</a></td>
                                </tr>
                                <tr>
                                    <td><a href="/challengeDetail" class="aLink">5</a></td>
                                    <td><a href="/challengeDetail" class="aLink">너도 자격증 딸 수 있어!</a></td>
                                    <td><a href="/challengeDetail" class="aLink">시스템</a></td>
                                    <td><a href="/challengeDetail" class="aLink">2024.10.13</a></td>
                                    <td><a href="/challengeDetail" class="aLink">2025.04.13</a></td>
                                    <td><a href="/challengeDetail" class="aLink">진행 후</a></td>
                                </tr>
                                <tr>
                                    <td><a href="/challengeDetail" class="aLink">4</a></td>
                                    <td><a href="/challengeDetail" class="aLink">너도 자격증 딸 수 있어!</a></td>
                                    <td><a href="/challengeDetail" class="aLink">시스템</a></td>
                                    <td><a href="/challengeDetail" class="aLink">2024.10.13</a></td>
                                    <td><a href="/challengeDetail" class="aLink">2025.04.13</a></td>
                                    <td><a href="/challengeDetail" class="aLink">완료</a></td>
                                </tr>
                                <tr>
                                    <td><a href="/challengeDetail" class="aLink">3</a></td>
                                    <td><a href="/challengeDetail" class="aLink">너도 자격증 딸 수 있어!</a></td>
                                    <td><a href="/challengeDetail" class="aLink">시스템</a></td>
                                    <td><a href="/challengeDetail" class="aLink">2024.10.13</a></td>
                                    <td><a href="/challengeDetail" class="aLink">2025.04.13</a></td>
                                    <td><a href="/challengeDetail" class="aLink">진행 전</a></td>
                                </tr>
                                <tr>
                                    <td><a href="/challengeDetail" class="aLink">2</a></td>
                                    <td><a href="/challengeDetail" class="aLink">너도 자격증 딸 수 있어!</a></td>
                                    <td><a href="/challengeDetail" class="aLink">시스템</a></td>
                                    <td><a href="/challengeDetail" class="aLink">2024.10.13</a></td>
                                    <td><a href="/challengeDetail" class="aLink">2025.04.13</a></td>
                                    <td><a href="/challengeDetail" class="aLink">진행 후</a></td>
                                </tr>
                                <tr>
                                    <td><a href="/challengeDetail" class="aLink">1</a></td>
                                    <td><a href="/challengeDetail" class="aLink">너도 자격증 딸 수 있어!</a></td>
                                    <td><a href="/challengeDetail" class="aLink">시스템</a></td>
                                    <td><a href="/challengeDetail" class="aLink">2024.10.13</a></td>
                                    <td><a href="/challengeDetail" class="aLink">2025.04.13</a></td>
                                    <td><a href="/challengeDetail" class="aLink">완료</a></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
        </div>
        <%@ include file="footer.jsp" %>
</body>

</html>