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
    <%@ include file="components/header.jsp" %>
        <div class="mainContents">
            <%@ include file="components/sidebar.jsp" %>
                <div class="infoCard">
                    <h2 class="title">회원</h2>
                    <div class="scrollArea">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>닉네임</th>
                                    <th>직무</th>
                                    <th>소속</th>
                                    <th>경력</th>
                                    <th>가입일</th>
                                </tr>
                            </thead>
                            <tbody>
								<%-- 회원 테이블 tr --%>
								<jsp:include page="components/table/userTableTr.jsp" >
									<jsp:param name="user_id" value="5" />
									<jsp:param name="nickname" value="닉네임" />
									<jsp:param name="job_title" value="풀스택 개발자" />
									<jsp:param name="organization" value="LG CNS" />
									<jsp:param name="experience" value="1년" />
									<jsp:param name="created_date" value="2024.09.19" />
								</jsp:include>
                            </tbody>
                        </table>
                    </div>
                </div>
        </div>
        <%@ include file="components/footer.jsp" %>
</body>

</html>