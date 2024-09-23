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
                    <h2 class="content-title">공지사항 작성</h2>
                </div>
                <div class="content-main">
                    <form class="form-container">
                        <div class="form-group">
                            <label for="notice-title" class="form-label">제목</label>
                            <input id="notice-title" type="text" class="form-input" value="울프 서비스가 시작되었습니다." aria-label="공지사항 제목">
                        </div>
                        <div class="form-group">
                            <label for="notice-content" class="form-label">내용</label>
                            <textarea id="notice-content" class="form-input form-textarea" aria-label="공지사항 내용">지금 2조에서 울프 서비스 개발을 완료했습니다. 많은 관심 부탁드립니다.</textarea>
                        </div>
                        <div class="form-group file-input-wrapper">
                            <label for="file-upload" class="form-label">첨부파일</label>
                            <input type="file" id="file-upload" class="visually-hidden" aria-label="파일 업로드">
                        </div>
                        <div class="form-actions">
                            <button type="button" class="cancel-btn" onclick="location.href='/notice'">취소</button>
                            <button type="submit" class="submit-btn" onclick="location.href='/notice'">완료</button>
                        </div>
                    </form>
                </div>
            </section>
        </main>
        <%@ include file="footer.jsp" %>
    </div>
</body>
</html>