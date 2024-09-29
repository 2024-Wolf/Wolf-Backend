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
						    <h2 class="title">챌린지 정보</h2>
							<%-- 이전 버튼 --%>
							<jsp:include page="components/button/backButton.jsp" />
						</div>
						<%-- 수정 및 삭제 버튼 --%>
						<jsp:include page="components/button/editDeleteButton.jsp" />
						<div class="inputSection scrollArea">
							<%-- 인증 주체 입력 필드 --%>
							<jsp:include page="components/inputRadio/verificationAgent.jsp">
								<jsp:param name="verificationAgent" value="시스템" />
							</jsp:include>
							
							<%-- 챌린지 기간 입력 필드 --%>
							<jsp:include page="components/inputDate/startEndDate.jsp" >
								<jsp:param name="startDate" value="2024-09-01" />
								<jsp:param name="endDate" value="2024-09-30" />
							</jsp:include>

							<%-- 챌린지 이름 입력 필드 --%>
							<jsp:include page="components/inputText/challengeName.jsp" >
								<jsp:param name="challengeName" value="야, 너도 자격증 딸 수 있어! (정처기)" />
							</jsp:include>

							<%-- 챌린지 내용 필드 --%>
							<jsp:include page="components/textarea/content.jsp" >
								<jsp:param name="content" value="기간 내에 자격증을 취득하신 후 자격증 코드를 인증하시면 성공처리 됩니다!" />
							</jsp:include>

							<%-- 유의사항 입력 필드 --%>
							<jsp:include page="components/textarea/challengeWarning.jsp" >
								<jsp:param name="challengeWarning" value="중복 인증은 불가하므로, 반드시 본인 명의의 자격증 취득 내용만 인증하시기 바랍니다.
참가 후 인증하지 않을 시, 인증처리되지 않습니다.
타인의 자격증 코드 도용 시, 챌린지 실패처리 됩니다." />
							</jsp:include>


							<%-- 보상 입력 필드 --%>
							<jsp:include page="components/textarea/challengeAwardContent.jsp" >
								<jsp:param name="challengeAwardContent" value="제출된 금액을 챌린지 성공 인원으로 나눈 금액이 보상으로 지급됩니다.
※ 그룹별로 챌린지에 참가한 인원과 성공한 인원으로 계산됩니다." />
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