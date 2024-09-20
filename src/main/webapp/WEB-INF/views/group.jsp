<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> -->
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WOLF 관리자 페이지</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css">
    <link href="https://fonts.googleapis.com/css2?family=Kavoon&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sidebar.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/group.css">

</head>
<body>
    <div class="admin-main">
        <header class="header">
        </header>
         
        <main class="main-content">
            <nav class="sidebar">
                <a href="../jsp/notice.jsp" class="sidebar-item">공지사항</a>
                <a href="../jsp/faq.jsp" class="sidebar-item">FAQ</a>
                <a href="../jsp/user.jsp" class="sidebar-item">회원</a>
                <a href="../jsp/group.jsp" class="sidebar-item active">그룹</a>
                <a href="../jsp/challenge.jsp" class="sidebar-item">챌린지</a>
                <a href="../jsp/report.jsp" class="sidebar-item">신고</a>
                <a href="../jsp/auth.jsp" class="sidebar-item">인증</a>
            </nav>
        
            <section class="content-area">
                <div class="content-header">
                    <h2 class="content-title">그룹</h2>
                </div>
                <div class="content-main">
                    <table class="group-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>이름</th>
                                <th>구분</th>
                                <th>시작일</th>
                                <th>종료일</th>
                                <th>인원</th>
                                <th>결제 중인 챌린지</th>
                                <th>결제 현황</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>12</td>
                                <td>늑대들</td>
                                <td>스터디</td>
                                <td>2024.09.13</td>
                                <td>2025.03.13</td>
                                <td>5</td>
                                <td>너도 자격증 딸 수 있어!</td>
                                <td>2 / 5</td>
                            </tr>
                            <tr>
                                <td>11</td>
                                <td>늑대들</td>
                                <td>스터디</td>
                                <td>2024.09.13</td>
                                <td>2025.03.13</td>
                                <td>5</td>
                                <td>너도 자격증 딸 수 있어!</td>
                                <td>2 / 5</td>
                            </tr>
                            <tr>
                                <td>10</td>
                                <td>늑대들</td>
                                <td>스터디</td>
                                <td>2024.09.13</td>
                                <td>2025.03.13</td>
                                <td>5</td>
                                <td>너도 자격증 딸 수 있어!</td>
                                <td>2 / 5</td>
                            </tr>
                            <tr>
                                <td>9</td>
                                <td>늑대들</td>
                                <td>스터디</td>
                                <td>2024.09.13</td>
                                <td>2025.03.13</td>
                                <td>5</td>
                                <td>너도 자격증 딸 수 있어!</td>
                                <td>2 / 5</td>
                            </tr>
                            <tr>
                                <td>8</td>
                                <td>늑대들</td>
                                <td>스터디</td>
                                <td>2024.09.13</td>
                                <td>2025.03.13</td>
                                <td>5</td>
                                <td>너도 자격증 딸 수 있어!</td>
                                <td>2 / 5</td>
                            </tr>
                            <tr>
                                <td>7</td>
                                <td>늑대들</td>
                                <td>스터디</td>
                                <td>2024.09.13</td>
                                <td>2025.03.13</td>
                                <td>5</td>
                                <td>너도 자격증 딸 수 있어!</td>
                                <td>2 / 5</td>
                            </tr>
                            <tr>
                                <td>6</td>
                                <td>늑대들</td>
                                <td>스터디</td>
                                <td>2024.09.13</td>
                                <td>2025.03.13</td>
                                <td>5</td>
                                <td>너도 자격증 딸 수 있어!</td>
                                <td>2 / 5</td>
                            </tr>
                            <tr>
                                <td>5</td>
                                <td>늑대들</td>
                                <td>스터디</td>
                                <td>2024.09.13</td>
                                <td>2025.03.13</td>
                                <td>5</td>
                                <td>너도 자격증 딸 수 있어!</td>
                                <td>2 / 5</td>
                            </tr>
                            <tr>
                                <td>4</td>
                                <td>늑대들</td>
                                <td>스터디</td>
                                <td>2024.09.13</td>
                                <td>2025.03.13</td>
                                <td>5</td>
                                <td>너도 자격증 딸 수 있어!</td>
                                <td>2 / 5</td>
                            </tr>
                            <tr>
                                <td>3</td>
                                <td>늑대들</td>
                                <td>스터디</td>
                                <td>2024.09.13</td>
                                <td>2025.03.13</td>
                                <td>5</td>
                                <td>너도 자격증 딸 수 있어!</td>
                                <td>2 / 5</td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>늑대들</td>
                                <td>스터디</td>
                                <td>2024.09.13</td>
                                <td>2025.03.13</td>
                                <td>5</td>
                                <td>너도 자격증 딸 수 있어!</td>
                                <td>2 / 5</td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td>늑대들</td>
                                <td>스터디</td>
                                <td>2024.09.13</td>
                                <td>2025.03.13</td>
                                <td>5</td>
                                <td>너도 자격증 딸 수 있어!</td>
                                <td>2 / 5</td>
                            </tr>
                            <tr>
                                <td>6</td>
                                <td>늑대들</td>
                                <td>스터디</td>
                                <td>2024.09.13</td>
                                <td>2025.03.13</td>
                                <td>5</td>
                                <td>너도 자격증 딸 수 있어!</td>
                                <td>2 / 5</td>
                            </tr>
                            <tr>
                                <td>5</td>
                                <td>늑대들</td>
                                <td>스터디</td>
                                <td>2024.09.13</td>
                                <td>2025.03.13</td>
                                <td>5</td>
                                <td>너도 자격증 딸 수 있어!</td>
                                <td>2 / 5</td>
                            </tr>
                            <tr>
                                <td>4</td>
                                <td>늑대들</td>
                                <td>스터디</td>
                                <td>2024.09.13</td>
                                <td>2025.03.13</td>
                                <td>5</td>
                                <td>너도 자격증 딸 수 있어!</td>
                                <td>2 / 5</td>
                            </tr>
                            <tr>
                                <td>3</td>
                                <td>늑대들</td>
                                <td>스터디</td>
                                <td>2024.09.13</td>
                                <td>2025.03.13</td>
                                <td>5</td>
                                <td>너도 자격증 딸 수 있어!</td>
                                <td>2 / 5</td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>늑대들</td>
                                <td>스터디</td>
                                <td>2024.09.13</td>
                                <td>2025.03.13</td>
                                <td>5</td>
                                <td>너도 자격증 딸 수 있어!</td>
                                <td>2 / 5</td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td>늑대들</td>
                                <td>스터디</td>
                                <td>2024.09.13</td>
                                <td>2025.03.13</td>
                                <td>5</td>
                                <td>너도 자격증 딸 수 있어!</td>
                                <td>2 / 5</td>
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
