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
						<h2 class="title">신고 정보</h2>
						<form method="get" action="/report" onsubmit="alert('저장완료');" class="inputSection scrollArea">
							<!-- 신고일 입력 필드 -->
							<jsp:include page="components/inputDate/reportDate.jsp">
								<jsp:param name="reportDate" value="${report.id()}" />
							</jsp:include>

							<!-- 신고자 입력 필드 -->
							<jsp:include page="components/inputText/reporter.jsp">
								<jsp:param name="reporter" value="${report.reporter()}" />
							</jsp:include>

							<!-- 신고 사유 입력 필드 -->
							<jsp:include page="components/textarea/reportReason.jsp">
								<jsp:param name="reportReason"
									value="${report.content()}" />
							</jsp:include>

							<!-- 신고 카테고리 입력 필드 -->
							<jsp:include page="components/select/reportCategory.jsp">
								<jsp:param name="reportCategory" value="${report.content()}" />
							</jsp:include>

							<!-- 신고 구분 & 신고 대상 & 신고 댓글 입력 필드 -->
							<jsp:include page="components/inputRadio/reportType.jsp">
								<jsp:param name="reportType" value="${report.target()}" />
								<jsp:param name="reportTarget" value="${report.target()}" />
								<jsp:param name="reportComment"
									value="${report.targetContent()}" />
							</jsp:include>

							<hr style="border: 1px solid var(--black200);">

							<!-- 신고 상태 입력 필드 -->
							<jsp:include page="components/select/reportStatus.jsp">
								<jsp:param name="reportStatus" value="${report.isProcessed()}" />
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