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
    <link rel="stylesheet" type="text/css" href="/resources/css/report.css">

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
                <a href="/challenge" class="sidebar-item">챌린지</a>
                <a href="/report" class="sidebar-item active">신고</a>
                <a href="/auth" class="sidebar-item">인증</a>
            </nav>
        
            <section class="content-area">
                <div class="content-header">
                    <h2 class="content-title">신고</h2>
                </div>
                <div class="content-main">
                    <table class="report-table">
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
            </section>
        </main>
        <%@ include file="footer.jsp" %>
    </div>
</body>
</html>
