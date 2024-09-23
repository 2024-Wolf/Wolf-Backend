<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>challengeInfo</title>
    <!-- Kavoon 폰트 -->
    <link href="https://fonts.googleapis.com/css2?family=Kavoon&display=swap" rel="stylesheet">
    <!-- Pretendard 폰트 -->
    <link rel="stylesheet" as="style" crossorigin
        href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.8/dist/web/static/pretendard.css" />
    <!-- 글로벌스타일.css -->
    <link rel="stylesheet" href="globalstyle.css">
    <!-- 사이드바.css -->
    <link rel="stylesheet" href="sidebar.css">
    <!-- mainContents.css -->
    <link rel="stylesheet" href="mainContents.css">
</head>

<body>
    <header style="margin: 0 auto; width: 100%; height: 175px; background-color: var(--violet200);">
    </header>
    <div class="mainContents">
        <div class="sidebar">
            <a class="sidebar-item" href="faqInfo.html">FAQ</a>
            <a class="sidebar-item" href="noticeInfo.html">공지사항</a>
            <a class="sidebar-item" href="userInfo.html">회원</a>
            <a class="sidebar-item" href="groupInfo.html">그룹</a>
            <a class="sidebar-item" href="challengeInfo.html">챌린지</a>
            <a class="sidebar-item" href="reportInfo.html">신고</a>
            <a class="sidebar-item" href="authInfo.html">인증</a>
        </div>
        <div class="infoCard">
            <div class="inputGroup">
                <h2 class="title">챌린지 정보</h2>
                <span class="buttonSideContainer">
                    <button class="formButton linePurpleButton">취소</button>
                    <button class="formButton darkBackgroundButton">완료</button>
                </span>
            </div>
            <div class="inputSection">
                <div class="inputGroup">
                    <label class="subtitle" for="auth">인증주체</label>
                    <input class="textContent input" type="radio" name="auth" id="system">
                    <label for="system">시스템</label>
                    <input class="textContent input" type="radio" name="auth" id="manager">
                    <label for="manager">관리자</label>
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="auth-date">기간</label>
                    <input class="textContent input" type="date" name="auth-date" id="auth-date" required>
                    ~
                    <input class="textContent input" type="date" name="auth-date" id="auth-date" required>
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="username">이름</label>
                    <input class="textContent input" type="text" name="username" id="username" required>
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="challenge">내용</label>
                    <input class="textContent input" type="text" name="challenge" id="challenge">
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="content">유의사항</label>
                    <textarea class="textContent textarea" name="content" id="content" cols="30" rows="10"></textarea>
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="content">보상</label>
                    <textarea class="textContent textarea" name="content" id="content" cols="30" rows="10"></textarea>
                </div>
                <div class="inputGroup imagePlaceholder">
                    <img src="path/to/image.jpg" alt="임시 이미지" style="max-width: 100%; max-height: 100%;">
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="content">첨부파일</label>
                    <input class="textContent" type="file" name="username" id="username" required>
                </div>
            </div>
        </div>
    </div>
    <footer style="margin: 0 auto; width: 100%; height: 175px; background-color: var(--violet200);">
    </footer>
</body>

</html>