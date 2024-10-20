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
		<script>
			console.log(document.getElementsByName("title"))
		</script>
	</head>

	<body>
		<!-- 헤더 -->
		<%@ include file="components/header.jsp" %>
			<div class="mainContents">
				<!-- 사이드바 -->
				<%@ include file="components/sidebar.jsp" %>
					<div class="infoCard">
						<h2 class="title">챌린지 수정</h2>
						<form method="POST" action="/admin/challenges/${challenge.challengePostId}" onsubmit="alert('수정완료');"
							class="inputSection scrollArea">
							<!-- 인증 주체 입력 필드 -->
							<jsp:include page="components/inputRadio/verificationAgent.jsp" />

							<!-- 챌린지 기간 입력 필드 -->
							<jsp:include page="components/inputDate/startEndDate.jsp">
								<jsp:param name="registrationDate" value="${challenge.registrationDate}" />
								<jsp:param name="deadline" value="${challenge.deadline}" />
							</jsp:include>

							<!-- 챌린지 이름 입력 필드 -->
							<jsp:include page="components/inputText/challengeName.jsp">
								<jsp:param name="title" value="${challenge.title}" />
							</jsp:include>

							<!-- 챌린지 내용 필드 -->
							<jsp:include page="components/textarea/content.jsp">
								<jsp:param name="content" value="${challenge.content}" />
							</jsp:include>

							<!-- 유의사항 입력 필드 -->
							<jsp:include page="components/textarea/challengeWarning.jsp">
								<jsp:param name="manner" value="${challenge.manner}" />
							</jsp:include>


							<!-- 보상 입력 필드 -->
							<jsp:include page="components/textarea/challengeAwardContent.jsp">
								<jsp:param name="awardContent" value="${challenge.awardContent}" />
							</jsp:include>

							<!-- 첨부파일 입력 필드 -->
							<jsp:include page="components/inputFile/inputFile.jsp">
								<jsp:param name="inputFile" value="/resources/img/thumbnail_challenge1.png" />
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