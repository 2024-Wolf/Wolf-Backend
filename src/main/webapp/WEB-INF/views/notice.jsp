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
    <link rel="stylesheet" type="text/css" href="/resources/css/notice.css">

</head>
<body>
    <div class="admin-main">
        <header class="header">
        </header>
        
        <main class="main-content">
            <nav class="sidebar">
                <a href="/notice" class="sidebar-item active">공지사항</a>
                <a href="/faq" class="sidebar-item">FAQ</a>
                <a href="/user" class="sidebar-item">회원</a>
                <a href="/group" class="sidebar-item">그룹</a>
                <a href="/challenge" class="sidebar-item">챌린지</a>
                <a href="/report" class="sidebar-item">신고</a>
                <a href="/auth" class="sidebar-item">인증</a>
            </nav>
        
            <section class="content-area">
                <div class="content-header">
                    <h2 class="content-title">공지사항</h2>
                    <button class="create-btn" onclick="location.href='/noticeCreate'">작성하기</button>
                </div>
                <div class="content-main">
                    <table class="notice-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>제목</th>
                                <th>등록자</th>
                                <th>등록일</th>
                            </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>5</td>
                            <td class="title-cell"><a href="/noticeDetail">제목입니다. 제목입니다. 제목입니다. 제목입니다. 제목입니다. 제목입니다. 제목입니다.</a></td>
                            <td>등록자</td>
                            <td>24.09.12</td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td class="title-cell"><a href="/noticeDetail">제목입니다. 제목입니다. 제목입니다. 제목입니다. 제목입니다. 제목입니다. 제목입니다.</a></td>
                            <td>정명주</td>
                            <td>24.09.12</td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td class="title-cell"><a href="/noticeDetail">제목입니다. 제목입니다. 제목입니다. 제목입니다. 제목입니다. 제목입니다. 제목입니다.</a></td>
                            <td>정명주</td>
                            <td>24.09.12</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td class="title-cell"><a href="/noticeDetail">제목입니다. 제목입니다. 제목입니다. 제목입니다. 제목입니다. 제목입니다. 제목입니다.</a></td>
                            <td>정명주</td>
                            <td>24.09.12</td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td class="title-cell"><a href="/noticeDetail">제목입니다. 제목입니다. 제목입니다. 제목입니다. 제목입니다. 제목입니다. 제목입니다.</a></td>
                            <td>정명주</td>
                            <td>24.09.12</td>
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
