<%@ page import="java.time.LocalDate" %>
<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> -->
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>WOLF 관리자 페이지</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
	<link rel="stylesheet" href="/resources/css/globalstyle.css">
	<link rel="stylesheet" href="/resources/css/mainContents.css">
	<%
		// 현재 날짜와 시간을 가져오고 원하는 포맷으로 변환
		LocalDate now = LocalDate.now();
	%>
</head>

<body>
	<!-- 헤더 -->
	<%@ include file="components/header.jsp" %>
		<div class="mainContents">
			<!-- 사이드바 -->
			<%@ include file="components/sidebar.jsp" %>
				<div class="infoCard">
					<h2 class="title">챌린지 작성</h2>
					<form method="POST" action="/admin/challenges" onsubmit="alert('작성완료');" class="inputSection scrollArea">
						<!-- 인증 주체 입력 필드 -->
						<jsp:include page="components/inputRadio/verificationAgent.jsp" />

						<!-- 챌린지 기간 입력 필드 -->
						<jsp:include page="components/inputDate/startEndDate.jsp">
							<jsp:param name="registrationDate" value="" />
							<jsp:param name="deadline" value="" />
						</jsp:include>

						<!-- 챌린지 이름 입력 필드 -->
						<jsp:include page="components/inputText/challengeName.jsp">
							<jsp:param name="title" value="" />
						</jsp:include>

						<!-- 챌린지 내용 필드 -->
						<jsp:include page="components/textarea/content.jsp">
							<jsp:param name="content" value="" />
						</jsp:include>

						<!-- 유의사항 입력 필드 -->
						<jsp:include page="components/textarea/challengeWarning.jsp">
							<jsp:param name="manner" value="" />
						</jsp:include>

						<!-- 보상 입력 필드 -->
						<jsp:include page="components/textarea/challengeAwardContent.jsp">
							<jsp:param name="awardContent" value="" />
						</jsp:include>

						<!-- 첨부파일 입력 필드 -->
						<jsp:include page="components/inputFile/inputFile.jsp">
							<jsp:param name="img" value="" />
						</jsp:include>

						<!-- 취소 & 완료(submit) 버튼 -->
						<jsp:include page="components/button/cancelCompleteButton.jsp" />
					</form>
				</div>
		</div>
		<!-- 푸터 -->
		<%@ include file="components/footer.jsp" %>
</body>

</html>