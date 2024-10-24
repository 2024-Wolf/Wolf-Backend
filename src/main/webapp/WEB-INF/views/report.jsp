<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <h2 class="title">신고</h2>
                    <div class="scrollContainer">
                        <!-- 스크롤 안내 문구 (스크린 width: 929.33px 일 때 나타남) -->
                        <%@ include file="components/scrollDescription.jsp" %>
                            <div class="scrollArea">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>신고자</th>
                                            <th>신고 내용</th>
                                            <th>신고 대상</th>
                                            <th>신고일</th>
                                            <th>상태</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="report" items="${reports}">
                                            <!-- 신고 테이블 tr -->
                                            <jsp:include page="components/table/reportTableTr.jsp">
                                                <jsp:param name="report_id" value="${report.id()}" />
                                                <jsp:param name="reporter_id" value="${report.reporter()}" />
                                                <jsp:param name="report_content" value="${report.content()}" />
                                                <jsp:param name="reported_user_id" value="${report.target()}" />
                                                <jsp:param name="report_date" value="${report.createdAt()}" />
                                                <jsp:param name="is_solved" value="${report.isProcessed()}" />
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