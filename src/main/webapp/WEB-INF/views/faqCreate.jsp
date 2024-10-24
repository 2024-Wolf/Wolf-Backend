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
					<h2 class="title">FAQ 작성</h2>
					<form method="POST" action="/admin/faqs" onsubmit="alert('작성완료');" class="inputSection scrollArea">
						<!-- 등록일 필드 -->
						<jsp:include page="components/inputDate/registrationDate.jsp">
							<jsp:param name="createdDate" value="<%= now %>"/>
						</jsp:include>

						<!-- 등록자 필드 -->
						<jsp:include page="components/inputText/registrarName.jsp">
							<jsp:param name="author" value="" />
						</jsp:include>

						<!-- 카테고리 필드 -->
						<jsp:include page="components/select/faqCategory.jsp">
							<jsp:param name="category" value="" />
						</jsp:include>

						<!-- 제목 입력 필드 -->
						<jsp:include page="components/inputText/title.jsp">
							<jsp:param name="question" value="" />
						</jsp:include>

						<!-- 내용 입력 필드 -->
						<jsp:include page="components/textarea/content.jsp">
							<jsp:param name="answer" value="" />
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