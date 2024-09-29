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
						    <h2 class="title">그룹 정보</h2>
							<%-- 이전 버튼 --%>
							<jsp:include page="components/button/backButton.jsp" />
						</div>
                        <div class="inputSection scrollArea">
							<%-- 모집 상태 입력 필드 --%>
							<jsp:include page="components/select/recruitmentStatus.jsp">
								<jsp:param name="recruitmentStatus" value="모집 중" />
							</jsp:include>
							
							<%-- 기간 입력 필드 --%>
							<jsp:include page="components/inputDate/startEndDate.jsp">
								<jsp:param name="startDate" value="2024-09-01" />
								<jsp:param name="endDate" value="2024-09-30" />
							</jsp:include>
							
							<%-- 구분 & 진행했던 챌린지 & 챌린지 횟수 입력 필드 --%>
							<jsp:include page="components/inputText/groupType.jsp">
								<jsp:param name="groupType" value="스터디" />
								<jsp:param name="challengeName" value="야, 너도 자격증 딸 수 있어! (정처기)" />
								<jsp:param name="challengeCount" value="50개" />
							</jsp:include>
							
							<%-- 그룹명 입력 필드 --%>
							<jsp:include page="components/inputText/groupName.jsp">
								<jsp:param name="groupName" value="Grooby Room" />
							</jsp:include>

							<%-- 그룹장 입력 필드 --%>
							<jsp:include page="components/inputText/groupLeader.jsp">
								<jsp:param name="groupLeader" value="힙합 늑대" />
							</jsp:include>
							
							<%-- 인원 입력 필드 --%>
							<jsp:include page="components/inputText/numberOfMember.jsp">
								<jsp:param name="numberOfMember" value="5명" />
							</jsp:include>
							
							<%-- 그룹원 입력 필드 --%>
							<jsp:include page="components/inputText/groupMember.jsp">
								<jsp:param name="groupMember" value="늑대1/늑대2" />
							</jsp:include>
							
							<%-- 태그 입력 필드 --%>
							<jsp:include page="components/inputText/tag.jsp">
								<jsp:param name="tag" value="#프론트엔드 #백엔드 #풀스택" />
							</jsp:include>

							<hr style="border: 1px solid var(--black200);">
							
							<%-- 정지 상태 입력 필드 --%>
							<jsp:include page="components/select/userStatus.jsp" >
								<jsp:param name="userStatus" value="활성 상태" />
								<jsp:param name="RemainingTime" value="없음" />
							</jsp:include>

							<%-- 대상 제재 입력 필드 --%>
							<jsp:include page="components/button/warningStop.jsp" />
							
							<%-- 신고 처리 내용 입력 필드 --%>
							<jsp:include page="components/textarea/reportProcessing.jsp" >
								<jsp:param name="reportProcessing" value="" />
							</jsp:include>
                        </div>
                    </div>
            </div>
            <%@ include file="components/footer.jsp" %>
    </body>

    </html>