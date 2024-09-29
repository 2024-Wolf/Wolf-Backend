<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <%@ include file="components/header.jsp" %>
    <div class="mainContents">
        <%@ include file="components/sidebar.jsp" %>
        <div class="infoCard">
            <h2 class="title">공지사항 작성</h2>
            <form method="get" action="/notice" onsubmit="alert('작성완료');" enctype="multipart/form-data" class="inputSection scrollArea">
				<%-- 등록일 필드 --%>
				<jsp:include page="components/inputDate/registrationDate.jsp" >
					<jsp:param name="registrationDate" value="2024-09-29" />
				</jsp:include>

				<%-- 등록자 필드 --%>
				<jsp:include page="components/inputText/registrarName.jsp" >
					<jsp:param name="registrarName" value="우두머리 늑대" />
				</jsp:include>
				
				<%-- 제목 입력 필드 --%>
				<jsp:include page="components/inputText/title.jsp" >
					<jsp:param name="title" value="" />
				</jsp:include>

				<%-- 내용 입력 필드 --%>
				<jsp:include page="components/textarea/content.jsp" >
					<jsp:param name="content" value="" />
				</jsp:include>
				
				<%-- 첨부파일 입력 필드 --%>
				<jsp:include page="components/inputFile/inputFile.jsp" >
					<jsp:param name="inputFile" value="" />
				</jsp:include>

				<%-- 취소 & 완료(submit) 버튼 --%>
				<jsp:include page="components/button/cancelCompleteButton.jsp" />
            </form>
        </div>
    </div>
    <%@ include file="components/footer.jsp" %>

</body>

</html>
