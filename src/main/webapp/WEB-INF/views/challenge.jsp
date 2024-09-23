<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> -->
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WOLF 관리자 페이지</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css">
    <link href="https://fonts.googleapis.com/css2?family=Kavoon&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/resources/css/admin.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/sidebar.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/header.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/footer.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/challenge.css">

</head>
<body>
    <div class="admin-main">
        <%@ include file="header.jsp" %>

        <main class="main-content">
            <nav class="sidebar">
                <a href="/notice" class="sidebar-item">공지사항</a>
                <a href="/faq" class="sidebar-item">FAQ</a>
                <a href="/user" class="sidebar-item">회원</a>
                <a href="/group" class="sidebar-item">그룹</a>
                <a href="/challenge" class="sidebar-item active">챌린지</a>
                <a href="/report" class="sidebar-item">신고</a>
                <a href="/auth" class="sidebar-item">인증</a>
            </nav>
        
            <section class="content-area">
                <div class="content-header">
                    <h2 class="content-title">챌린지</h2>
                </div>
                <div class="content-main">
                    <table class="challenge-table">
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
            </section>
        </main>

        <%@ include file="footer.jsp" %>
    </div>
</body>
</html>
