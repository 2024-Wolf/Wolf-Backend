<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
					<h2 class="title">인증</h2>
					<div class="scrollContainer">
						<!-- 스크롤 안내 문구 (스크린 width: 929.33px 일 때 나타남) -->
						<%@ include file="components/scrollDescription.jsp" %>
							<div class="scrollArea">
								<table class="table">
									<thead>
										<tr>
											<th>ID</th>
											<th>회원</th>
											<th>인증 챌린지</th>
											<th>그룹</th>
											<th>인증일</th>
											<th>상태</th>
										</tr>
									</thead>
									<tbody>
										<!-- 인증 테이블 tr -->
										<c:forEach var="verification" items="${verifications}">
											<jsp:include page="components/table/authTableTr.jsp">
												<jsp:param name="auth_id" value="${verification.id()}" />
												<jsp:param name="nickname" value="${verification.userNickname()}" />
												<jsp:param name="challenge_title" value="${verification.title()}" />
												<jsp:param name="group_title" value="${verification.groupTitle()}" />
												<jsp:param name="verification_date" value="${verification.createdAt()}" />
												<jsp:param name="auth_status" value="${verification.isVerified()}" />
											</jsp:include>
										</c:forEach>
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