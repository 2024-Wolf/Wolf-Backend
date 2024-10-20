<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>WOLF 관리자 페이지</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
		<link rel="stylesheet" href="/resources/css/globalstyle.css">
		<link rel="stylesheet" href="/resources/css/mainContents.css">
	</head>

	<body>
		<!-- 헤더 -->
		<%@ include file="components/header.jsp" %>
			<div class="mainContents">
				<!-- 사이드바 -->
				<%@ include file="components/sidebar.jsp" %>
					<div class="infoCard">
						<h2 class="title">인증 정보</h2>
						<form method="POST" action="/admin/challenges/verification/${verification.id()}" onsubmit="console.log('폼 제출: ', this); alert('저장완료');" class="inputSection scrollArea">

							<!-- 인증일 입력 필드 -->
							<jsp:include page="components/inputDate/authDate.jsp">
								<jsp:param name="authDate" value="${verification.createdAt()}" />
							</jsp:include>

							<input type="hidden" name="challengePostId" id="challengePostId" value="${verification.challengePostId()}">

							<input type="hidden" name="groupPostId" id="groupPostId" value="${verification.groupId()}">

							<!-- 닉네임 입력 필드 -->
							<jsp:include page="components/inputText/nickname.jsp">
								<jsp:param name="nickname" value="${verification.userNickname()}" />
							</jsp:include>

							<!-- 챌린지 이름 입력 필드 -->
							<jsp:include page="components/inputText/challengeName.jsp">
								<jsp:param name="title" value="${verification.title()}" />
							</jsp:include>

							<!-- 내용 입력 필드 -->
							<jsp:include page="components/textarea/content.jsp">
								<jsp:param name="content" value="${verification.verificationContent()}" />
							</jsp:include>

							<hr style="border: 1px solid var(--black200);">
							<!-- 인증 상태 입력 필드 -->
							<jsp:include page="components/select/authStatus.jsp">
								<jsp:param name="status" value="${verification.isVerified()}" />
							</jsp:include>

							<input type="hidden" name="_method" value="PUT">
							<!-- 취소 & 완료(submit) 버튼 -->
							<jsp:include page="components/button/cancelCompleteButton.jsp" />
						</form>
					</div>
			</div>
			<!-- 푸터 -->
			<%@ include file="components/footer.jsp" %>
	</body>

	</html>