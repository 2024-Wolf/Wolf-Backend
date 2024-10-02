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
</head>

<body>
	<!-- 헤더 -->
	<%@ include file="components/header.jsp" %>
		<div class="mainContents">
			<!-- 사이드바 -->
			<%@ include file="components/sidebar.jsp" %>
				<div class="infoCard">
					<h2 class="title">FAQ 수정</h2>
					<form method="get" action="/faqDetail" onsubmit="alert('수정완료');" class="inputSection scrollArea">
						<!-- 등록일 필드 -->
						<jsp:include page="components/inputDate/registrationDate.jsp">
							<jsp:param name="registrationDate" value="2024-09-29" />
						</jsp:include>

						<!-- 등록자 필드 -->
						<jsp:include page="components/inputText/registrarName.jsp">
							<jsp:param name="registrarName" value="우두머리 늑대" />
						</jsp:include>

						<!-- 카테고리 필드 -->
						<jsp:include page="components/select/faqCategory.jsp">
							<jsp:param name="faqCategory" value="계정" />
						</jsp:include>

						<!-- 제목 입력 필드 -->
						<jsp:include page="components/inputText/title.jsp">
							<jsp:param name="title" value="회원가입은 어떻게 하나요?" />
						</jsp:include>

						<!-- 내용 입력 필드 -->
						<jsp:include page="components/textarea/content.jsp">
							<jsp:param name="content" value="로그인 화면의 “구글로 로그인하기”를 클릭하신 후, 회원가입 절차를 진행하시면 됩니다!" />
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