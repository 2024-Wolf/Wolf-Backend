<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WOLF 관리자 페이지</title>
	<link rel="stylesheet" href="/resources/css/globalstyle.css">
	<link rel="stylesheet" href="/resources/css/mainContents.css">
	<link rel="stylesheet" href="/resources/css/login.css">
</head>

<body>
	<%@ include file="header.jsp" %>
    <div class="mainContents">
		<%@ include file="sidebar.jsp" %>
        <div class="infoCard">
            <div class="inputGroup">
                <h2 class="title">회원 정보</h2>
                <span class="buttonSideContainer">
                    <button class="formButton linePurpleButton">이전</button>
                </span>
            </div>
            <div class="inputSection">
                <div class="inputGroup">
                    <label class="subtitle" for="auth-date">가입일</label>
                    <input class="textContent input" type="date" name="auth-date" id="auth-date" disabled>
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="status">상태</label>
                    <select class="textContent input" name="status" id="status">
                        <option value="success">인증 성공</option>
                        <option value="failure">인증 실패</option>
                    </select>
                    <input class="textContent input" type="date" name="auth-date" id="auth-date" disabled>
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="username">닉네임</label>
                    <input class="textContent input" type="text" name="username" id="username" required>
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="username">직무</label>
                    <input class="textContent input" type="text" name="username" id="username" required>
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="username">소속</label>
                    <input class="textContent input" type="text" name="username" id="username" required>
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="username">경력</label>
                    <input class="textContent input" type="text" name="username" id="username" required>
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="challenge">스터디</label>
                    <label for="percentage-slider">완료:</label>
                    <span id="slider-value">50%</span>
                    <input type="range" id="percentage-slider" name="percentage" min="0" max="100" value="50"
                        oninput="updateSliderValue(this.value)" disabled />
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="challenge">프로젝트</label>
                    <label for="percentage-slider">완료:</label>
                    <span id="slider-value">50%</span>
                    <input type="range" id="percentage-slider" name="percentage" min="0" max="100" value="50"
                        oninput="updateSliderValue(this.value)" disabled />
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="challenge">챌린지</label>
                    <label for="percentage-slider">성공:</label>
                    <span id="slider-value">50%</span>
                    <input type="range" id="percentage-slider" name="percentage" min="0" max="100" value="50"
                        oninput="updateSliderValue(this.value)" disabled />
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="challenge">신고</label>
                    <label for="percentage-slider">신고:</label>
                    <span id="slider-value">50%</span>
                    <input type="range" id="percentage-slider" name="percentage" min="0" max="100" value="50"
                        oninput="updateSliderValue(this.value)" disabled />
                    <label for="percentage-slider">피신고:</label>
                    <span id="slider-value">50%</span>
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="challenge">활동지표</label>
                    <label for="percentage-slider"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                            fill="currentColor" class="bi bi-emoji-laughing" viewBox="0 0 16 16">
                            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16" />
                            <path
                                d="M12.331 9.5a1 1 0 0 1 0 1A5 5 0 0 1 8 13a5 5 0 0 1-4.33-2.5A1 1 0 0 1 4.535 9h6.93a1 1 0 0 1 .866.5M7 6.5c0 .828-.448 0-1 0s-1 .828-1 0S5.448 5 6 5s1 .672 1 1.5m4 0c0 .828-.448 0-1 0s-1 .828-1 0S9.448 5 10 5s1 .672 1 1.5" />
                        </svg></label>
                    <span id="slider-value">50%</span>
                    <input type="range" id="percentage-slider" name="percentage" min="0" max="100" value="50"
                        oninput="updateSliderValue(this.value)" disabled />
                    <label for="percentage-slider"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                            fill="currentColor" class="bi bi-emoji-neutral" viewBox="0 0 16 16">
                            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16" />
                            <path
                                d="M4 10.5a.5.5 0 0 0 .5.5h7a.5.5 0 0 0 0-1h-7a.5.5 0 0 0-.5.5m3-4C7 5.672 6.552 5 6 5s-1 .672-1 1.5S5.448 8 6 8s1-.672 1-1.5m4 0c0-.828-.448-1.5-1-1.5s-1 .672-1 1.5S9.448 8 10 8s1-.672 1-1.5" />
                        </svg></label>
                    <span id="slider-value">50%</span>
                    <input type="range" id="percentage-slider" name="percentage" min="0" max="100" value="50"
                        oninput="updateSliderValue(this.value)" disabled />
                    <label for="percentage-slider"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                            fill="currentColor" class="bi bi-emoji-frown" viewBox="0 0 16 16">
                            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16" />
                            <path
                                d="M4.285 12.433a.5.5 0 0 0 .683-.183A3.5 3.5 0 0 1 8 10.5c1.295 0 2.426.703 3.032 1.75a.5.5 0 0 0 .866-.5A4.5 4.5 0 0 0 8 9.5a4.5 4.5 0 0 0-3.898 2.25.5.5 0 0 0 .183.683M7 6.5C7 7.328 6.552 8 6 8s-1-.672-1-1.5S5.448 5 6 5s1 .672 1 1.5m4 0c0 .828-.448 1.5-1 1.5s-1-.672-1-1.5S9.448 5 10 5s1 .672 1 1.5" />
                        </svg></label>
                    <span id="slider-value">50%</span>
                    <input type="range" id="percentage-slider" name="percentage" min="0" max="100" value="50"
                        oninput="updateSliderValue(this.value)" disabled />
                </div>
                <div class="inputGroup">
                    <label class="subtitle" for="content">자기소개
                    </label>
                    <textarea class="textContent textarea" name="content" id="content" cols="30"
                        rows="10"></textarea>
                </div>
            </div>
        </div>
    </div>
		<%@ include file="footer.jsp" %>
</body>

</html>