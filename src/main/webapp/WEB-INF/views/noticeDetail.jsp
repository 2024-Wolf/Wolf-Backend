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
        <%@ include file="components/header.jsp" %>
            <div class="mainContents">
                <%@ include file="components/sidebar.jsp" %>
                    <div class="infoCard">
						<div class="titleInputGroup">
						    <h2 class="title">공지사항 정보</h2>
							<%-- 이전 버튼 --%>
							<jsp:include page="components/button/backButton.jsp" />
						</div>
						<%-- 수정 및 삭제 버튼 --%>
						<jsp:include page="components/button/editDeleteButton.jsp" />
                        <div class="inputSection scrollArea">
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
								<jsp:param name="title" value="울프 서비스가 시작되었습니다." />
							</jsp:include>

							<%-- 내용 입력 필드 --%>
							<jsp:include page="components/textarea/content.jsp" >
								<jsp:param name="content" value="지금 2조에서 울프 서비스 개발을 완료했습니다. 많은 관심 부탁드립니다." />
							</jsp:include>

							<%-- 첨부파일 입력 필드 --%>
							<jsp:include page="components/inputFile/inputFile.jsp" >
								<jsp:param name="inputFile" value="/resources/img/thumbnail_challenge1.png" />
							</jsp:include>
                        </div>
                    </div>
            </div>
            <%@ include file="components/footer.jsp" %>
    </body>

    </html>