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
                    <h2 class="title">그룹</h2>
                    <div class="scrollArea">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>이름</th>
                                    <th>구분</th>
                                    <th>시작일</th>
                                    <th>종료일</th>
                                    <th>인원</th>
                                    <th>결제 중인 챌린지</th>
                                    <th>결제 현황</th>
                                </tr>
                            </thead>
                            <tbody>
								<%-- 그룹 테이블 tr --%>
								<jsp:include page="components/table/groupTableTr.jsp" >
									<jsp:param name="group_id" value="13" />
									<jsp:param name="group_title" value="늑대들" />
									<jsp:param name="group_type" value="스터디" />
									<jsp:param name="start_date" value="2024.09.13" />
									<jsp:param name="end_date" value="2025.03.13" />
									<jsp:param name="member_cnt" value="5" />
									<jsp:param name="challenge_title" value="너도 자격증 딸 수 있어!" />
									<jsp:param name="pay_cnt" value="2 / 5" />
								</jsp:include>
                            </tbody>
                        </table>
                    </div>
                </div>
        </div>
        <%@ include file="components/footer.jsp" %>
</body>

</html>