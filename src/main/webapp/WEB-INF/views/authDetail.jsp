<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">    
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>authInfo</title>
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
        <div class="infoCard">
            <h2 class="title">인증 정보</h2>

            <div class="inputSection">
                <div class="inputGroup">
                    <label class="subtitle" for="status">상태</label>
                    <select class="textContent input" name="status" id="status" disabled>
                        <option value="success">인증 성공</option>
                        <option value="failure">인증 실패</option>
                    </select>
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="auth-date">인증일</label>
                    <input class="textContent input" type="date" name="auth-date" id="auth-date" disabled>
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="username">닉네임</label>
                    <input class="textContent input" type="text" name="username" id="username" disabled>
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="challenge">챌린지</label>
                    <input class="textContent input" type="text" name="challenge" id="challenge" disabled>
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="content">내용
                    </label>
                    <textarea class="textContent textarea" name="content" id="content" cols="30" rows="10"
                        disabled></textarea>
                </div>
                <div class="buttonContainer">
                    <button class="formButton linePurpleButton">취소</button>
                    <button class="formButton darkBackgroundButton">완료</button>
                </div>
            </div>
        </div>
    </div>
    <footer style="margin: 0 auto; width: 100%; height: 175px; background-color: var(--violet200);">
    </footer>
</body>

</html>