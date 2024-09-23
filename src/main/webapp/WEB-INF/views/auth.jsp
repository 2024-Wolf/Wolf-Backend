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
    <link rel="stylesheet" type="text/css" href="/resources/css/auth.css">

</head>

<body>
    <div class="admin-main">
        <header class="header">
        </header>

        <main class="main-content">
            <%@ include file="sidebar.jsp" %>
                <section class="content-area">
                    <div class="content-header">
                        <h2 class="content-title">인증</h2>
                    </div>
                    <div class="content-main">
                        <table class="auth-table">
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
                </section>
        </main>

        <footer class="footer">
        </footer>
    </div>
</body>

</html>