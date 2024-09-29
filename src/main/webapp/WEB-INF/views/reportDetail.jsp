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
                        <h2 class="title">신고 정보</h2>
						<form method="get" action="/report" onsubmit="alert('저장완료');" class="inputSection scrollArea">
							<%-- 신고일 입력 필드 --%>
							<jsp:include page="components/inputDate/reportDate.jsp" >
								<jsp:param name="reportDate" value="2024-09-29" />
							</jsp:include>

							<%-- 신고자 입력 필드 --%>
							<jsp:include page="components/inputText/reporter.jsp" >
								<jsp:param name="reporter" value="말단 늑대" />
							</jsp:include>
																				
							<%-- 신고 사유 입력 필드 --%>
							<jsp:include page="components/textarea/reportReason.jsp" >
								<jsp:param name="reportReason" value="그룹에 들어가고 싶어서 질의 글을 남겼는데, 제 스펙으로 어딜 감히 넘보냐면서 욕설을 사용했습니다." />
							</jsp:include>
							
							<%-- 신고 카테고리 입력 필드 --%>
							<jsp:include page="components/select/reportCategory.jsp" >
								<jsp:param name="reportCategory" value="폭력적인 행동" />
							</jsp:include>
							
							<%-- 신고 구분 & 신고 대상 & 신고 댓글 입력 필드 --%>
							<jsp:include page="components/inputRadio/reportType.jsp" >
								<jsp:param name="reportType" value="댓글" />
								<jsp:param name="reportTarget" value="사수 늑대" />
								<jsp:param name="reportComment" value="#욕설#아, 너는 우리 무리에 안어울려 #욕설#.  #욕설#이는 기초부터 쌓고 와라" />
							</jsp:include>
							
							<hr style="border: 1px solid var(--black200);">
							
							<%-- 신고 상태 입력 필드 --%>
							<jsp:include page="components/select/reportStatus.jsp" >
								<jsp:param name="reportStatus" value="처리 접수" />
							</jsp:include>
							
							<%-- 대상 제재 입력 필드 --%>
							<jsp:include page="components/button/warningStop.jsp" />
							
							<%-- 신고 처리 내용 입력 필드 --%>
							<jsp:include page="components/textarea/reportProcessing.jsp" >
								<jsp:param name="reportProcessing" value="" />
							</jsp:include>
							
							<%-- 취소 & 완료(submit) 버튼 --%>
							<jsp:include page="components/button/cancelCompleteButton.jsp" />
						</form>
                    </div>
            </div>
            <%@ include file="components/footer.jsp" %>
    </body>

    </html>