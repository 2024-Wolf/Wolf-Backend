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
                        <h2 class="title">인증 정보</h2>
						<form method="get" action="/auth" onsubmit="alert('저장완료');" class="inputSection scrollArea">
							
							<%-- 인증일 입력 필드 --%>
							<jsp:include page="components/inputDate/authDate.jsp" >
								<jsp:param name="authDate" value="2024-09-29" />
							</jsp:include>

							<%-- 닉네임 입력 필드 --%>
							<jsp:include page="components/inputText/nickname.jsp" >
								<jsp:param name="nickname" value="힙합 늑대" />
							</jsp:include>
							
							<%-- 챌린지 이름 입력 필드 --%>
							<jsp:include page="components/inputText/challengeName.jsp" >
								<jsp:param name="challengeName" value="야, 너도 자격증 딸 수 있어! (정처기)" />
							</jsp:include>
							
							<%-- 내용 입력 필드 --%>
							<jsp:include page="components/textarea/content.jsp" >
								<jsp:param name="content" value="인증 기관 : 한국산업인력공단
자격증 코드 : 123123123
인증자 이름 : 김늑대
실패 이유 : 이미 인증된 자격증 코드" />
							</jsp:include>
							
							<hr style="border: 1px solid var(--black200);">
							<%-- 인증 상태 입력 필드 --%>
							<jsp:include page="components/select/authStatus.jsp" >
								<jsp:param name="authStatus" value="인증 성공" />
							</jsp:include>
							
							
							<%-- 취소 & 완료(submit) 버튼 --%>
							<jsp:include page="components/button/cancelCompleteButton.jsp" />
						</form>
                    </div>
            </div>
            <%@ include file="components/footer.jsp" %>
    </body>

    </html>