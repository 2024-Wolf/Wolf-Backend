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
	<link rel="stylesheet" type="text/css" href="/resources/css/table.css">
</head>

<body>
	<!-- 헤더 -->
	<%@ include file="components/header.jsp" %>
		<div class="mainContents">
			<!-- 사이드바 -->
			<%@ include file="components/sidebar.jsp" %>
				<main class="infoCard">
					<div class="titleInputGroup">
						<h2 class="title">챌린지</h2>
						<!-- 작성하기 버튼 -->
						<jsp:include page="components/button/createButton.jsp" />
					</div>
					<div class="scrollContainer">
						<!-- 스크롤 안내 문구 (스크린 width: 929.33px 일 때 나타남) -->
						<%@ include file="components/scrollDescription.jsp" %>
							<div class="scrollArea">
								<table class="table">
									<thead>
										<tr>
											<th>ID</th>
											<th>이름</th>
											<th>인증 주체</th>
											<th>시작일</th>
											<th>종료일</th>
											<th>상태</th>
										</tr>
									</thead>
									<tbody>
										<!-- 챌린지 테이블 tr -->
										<jsp:include page="components/table/challengeTableTr.jsp">
											<jsp:param name="challenge_post_id" value="8" />
											<jsp:param name="challenge_title" value="너도 자격증 딸 수 있어!" />
											<jsp:param name="verification_agent" value="시스템" />
											<jsp:param name="challenge_startDate" value="2024.10.13" />
											<jsp:param name="challenge_endDate" value="2025.04.13" />
											<jsp:param name="challenge_status" value="진행 후" />
										</jsp:include>
										<jsp:include page="components/table/challengeTableTr.jsp">
											<jsp:param name="challenge_post_id" value="7" />
											<jsp:param name="challenge_title" value="너도 자격증 딸 수 있어!" />
											<jsp:param name="verification_agent" value="시스템" />
											<jsp:param name="challenge_startDate" value="2024.10.13" />
											<jsp:param name="challenge_endDate" value="2025.04.13" />
											<jsp:param name="challenge_status" value="완료" />
										</jsp:include>
										<jsp:include page="components/table/challengeTableTr.jsp">
											<jsp:param name="challenge_post_id" value="6" />
											<jsp:param name="challenge_title" value="너도 자격증 딸 수 있어!" />
											<jsp:param name="verification_agent" value="시스템" />
											<jsp:param name="challenge_startDate" value="2024.10.13" />
											<jsp:param name="challenge_endDate" value="2025.04.13" />
											<jsp:param name="challenge_status" value="진행 전" />
										</jsp:include>
									</tbody>
								</table>
							</div>
							<!-- 스크롤 안내 문구 (스크린 width: 929.33px 일 때 나타남) -->
							<%@ include file="components/scrollDescription.jsp" %>
					</div>
				</main>
		</div>
		<!-- 푸터 -->
		<%@ include file="components/footer.jsp" %>
</body>

</html>