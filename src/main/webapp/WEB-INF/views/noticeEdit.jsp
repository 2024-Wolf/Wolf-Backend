<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> -->
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WOLF 관리자 페이지</title>
	<link rel="stylesheet" href="/resources/css/globalstyle.css">
	<link rel="stylesheet" href="/resources/css/mainContents.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/form.css">

</head>
<body>
		<%@ include file="header.jsp" %>
		<div class="mainContents">
			<%@ include file="sidebar.jsp" %>
            <section class="content-area">
                <div class="content-header">
                    <h2 class="content-title">공지사항 수정</h2>
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
                        <div class="form-group file-input-wrapper">
                            <label for="file-upload" class="form-label">첨부파일</label>
                            <input type="file" id="file-upload" class="visually-hidden" aria-label="파일 업로드">
                        </div>
                        <div class="form-actions">
                            <button type="button" class="cancel-btn" onclick="location.href='/noticeDetail'">취소</button>
                            <button type="submit" class="submit-btn" onclick="location.href='/noticeDetail'">완료</button>
                        </div>
                    </form>
                </div>
            </section>
		</div>
		<%@ include file="footer.jsp" %>
</body>
</html>