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
						<h2 class="title">회원 정보</h2>
						<form method="get" action="/user" onsubmit="alert('저장완료');" class="inputSection scrollArea">
							<!-- 가입일 입력 필드 -->
							<jsp:include page="components/inputDate/userCreatedDate.jsp">
								<jsp:param name="userCreatedDate" value="2024-09-19" />
							</jsp:include>

							<!-- 닉네임 입력 필드 -->
							<jsp:include page="components/inputText/nickname.jsp">
								<jsp:param name="nickname" value="니유비 늑대" />
							</jsp:include>

							<!-- 직무 입력 필드 -->
							<jsp:include page="components/inputText/jobTitle.jsp">
								<jsp:param name="jobTitle" value="백엔드 엔지니어" />
							</jsp:include>

							<!-- 소속 입력 필드 -->
							<jsp:include page="components/inputText/organization.jsp">
								<jsp:param name="organization" value="LG CNS" />
							</jsp:include>

							<!-- 경력 입력 필드 -->
							<jsp:include page="components/inputText/experience.jsp">
								<jsp:param name="experience" value="3년" />
							</jsp:include>

							<!-- 스터디 입력 필드 -->
							<jsp:include page="components/inputRange/studyRatio.jsp">
								<jsp:param name="studyRatio" value="90" />
							</jsp:include>

							<!-- 프로젝트 입력 필드 -->
							<jsp:include page="components/inputRange/projectRatio.jsp">
								<jsp:param name="projectRatio" value="50" />
							</jsp:include>

							<!-- 챌린지 입력 필드 -->
							<jsp:include page="components/inputRange/challengeRatio.jsp">
								<jsp:param name="challengeRatio" value="100" />
							</jsp:include>

							<!-- 신고 입력 필드 -->
							<jsp:include page="components/inputRange/reportRatio.jsp">
								<jsp:param name="reportRatio" value="70" />
							</jsp:include>

							<!-- 활동지표 입력 필드 -->
							<jsp:include page="components/inputRange/activityRatio.jsp">
								<jsp:param name="good" value="5" />
								<jsp:param name="soso" value="85" />
								<jsp:param name="bad" value="10" />
							</jsp:include>

							<!-- 자기 소개 입력 필드 -->
							<jsp:include page="components/textarea/introduction.jsp">
								<jsp:param name="introduction"
									value="안녕하세요. 저는 나유비 늑대 입니다. 현재는 LG CNS에서 백엔드 엔지니어로서 일하고 있지만, 프론트 개발도 공부해서 풀스택 개발자가 되는 것이 꿈입니다!" />
							</jsp:include>

							<hr style="border: 1px solid var(--black200);">

							<!-- 정지 상태 입력 필드 -->
							<jsp:include page="components/select/userStatus.jsp">
								<jsp:param name="userStatus" value="정지 상태" />
								<jsp:param name="RemainingTime" value="1일 12시간 남음" />
							</jsp:include>

							<!-- 대상 제재 입력 필드 -->
							<jsp:include page="components/button/warningStop.jsp" />

							<!-- 신고 처리 내용 입력 필드 -->
							<jsp:include page="components/textarea/reportProcessing.jsp">
								<jsp:param name="reportProcessing" value="" />
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