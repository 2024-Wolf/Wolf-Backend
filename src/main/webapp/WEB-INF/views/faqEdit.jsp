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
    <link rel="stylesheet" type="text/css" href="/resources/css/form.css">

</head>
<body>
    <div class="admin-main">
        <%@ include file="header.jsp" %>
        <main class="main-content">
            <nav class="sidebar">
                <a href="/notice" class="sidebar-item">공지사항</a>
                <a href="/faq" class="sidebar-item active">FAQ</a>
                <a href="/user" class="sidebar-item">회원</a>
                <a href="/group" class="sidebar-item">그룹</a>
                <a href="/challenge" class="sidebar-item">챌린지</a>
                <a href="/report" class="sidebar-item">신고</a>
                <a href="/auth" class="sidebar-item">인증</a>
            </nav>
        
            <section class="content-area">
                <div class="content-header">
                    <h2 class="content-title">FAQ 수정</h2>
                </div>
                <div class="content-main">
                    <form class="form-container">
                        <div class="form-group">
                            <label for="notice-registrant" class="form-label">등록자</label>
                            <input id="notice-registrant" type="text" class="form-input" value="우두머리 늑대" aria-label="공지사항 등록자" disabled>
                        </div>
                        <div class="form-group">
                            <label for="notice-date" class="form-label">등록일</label>
                            <input id="notice-date" type="text" class="form-input" value="2024.09.13" aria-label="공지사항 등록일" disabled>
                        </div>
                        <div class="form-group">
                            <label for="notice-title" class="form-label">제목</label>
                            <input id="notice-title" type="text" class="form-input" value="울프 서비스가 시작되었습니다." aria-label="공지사항 제목">
                        </div>
                        <div class="form-group">
                            <label for="notice-content" class="form-label">내용</label>
                            <textarea id="notice-content" class="form-input form-textarea" aria-label="공지사항 내용">지금 2조에서 울프 서비스 개발을 완료했습니다. 많은 관심 부탁드립니다.</textarea>
                        </div>
                        <div class="form-actions">
                            <button type="button" class="cancel-btn" onclick="location.href='/faqDetail'">취소</button>
                            <button type="submit" class="submit-btn" onclick="location.href='/faqDetail'">완료</button>
                        </div>
                    </form>
                </div>
            </section>
        </main>
        <%@ include file="footer.jsp" %>
    </div>
</body>
</html>