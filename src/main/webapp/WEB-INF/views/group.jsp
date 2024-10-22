<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <link rel="stylesheet" type="text/css" href="/resources/css/pagination.css">
</head>

<body>
    <!-- 헤더 -->
    <%@ include file="components/header.jsp" %>
        <div class="mainContents">
            <!-- 사이드바 -->
            <%@ include file="components/sidebar.jsp" %>
                <main class="infoCard">
                    <h2 class="title">그룹</h2>
                    <div class="scrollContainer">
                        <!-- 스크롤 안내 문구 (스크린 width: 929.33px 일 때 나타남) -->
                        <%@ include file="components/scrollDescription.jsp" %>
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
<%--                                            <th>결제 중인 챌린지</th>--%>
<%--                                            <th>결제 현황</th>--%>
                                            <th>챌린지 여부</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!-- 그룹 테이블 tr -->
                                        <c:forEach var="group" items="${groups}">
                                            <jsp:include page="components/table/groupTableTr.jsp">
                                                <jsp:param name="group_id" value="${group.id()}" />
                                                <jsp:param name="group_title" value="${group.name()}" />
                                                <jsp:param name="group_type" value="${group.type()}" />
                                                <jsp:param name="start_date" value="${group.startDate()}" />
                                                <jsp:param name="end_date" value="${group.endDate()}" />
                                                <jsp:param name="member_cnt" value="${group.memberCount()}" />
                                                <jsp:param name="challenge_status" value="${group.challengeStatus()}" />
                                            </jsp:include>
                                        </c:forEach>

                                    </tbody>
                                </table>
                            </div>
                            <!-- 스크롤 안내 문구 (스크린 width: 929.33px 일 때 나타남) -->
                            <%@ include file="components/scrollDescription.jsp" %>
                        <!-- 페이지네이션 -->
						<jsp:include page="components/pagination.jsp">
							<jsp:param name="totalPages" value="${page.totalPages()}" />
							<jsp:param name="currentPage" value="${page.currentPage()}" />
							<jsp:param name="size" value="${page.size()}" />
							<jsp:param name="isPageFirst" value="${page.isPageFirst()}" />
							<jsp:param name="isPageLast" value="${page.isPageLast()}" />
						</jsp:include>
                    </div>
                </main>
        </div>
        <!-- 푸터 -->
        <%@ include file="components/footer.jsp" %>
</body>

</html>