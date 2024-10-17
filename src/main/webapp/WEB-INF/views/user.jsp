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

</head>

<body>
    <!-- 헤더 -->
    <%@ include file="components/header.jsp" %>
        <div class="mainContents">
            <!-- 사이드바 -->
            <%@ include file="components/sidebar.jsp" %>
                <main class="infoCard">
                    <h2 class="title">회원</h2>
                    <div class="scrollContainer">
                        <!-- 스크롤 안내 문구 (스크린 width: 929.33px 일 때 나타남) -->
                        <%@ include file="components/scrollDescription.jsp" %>
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
                                        <c:forEach var="user" items="${users}">
                                            <!-- 회원 테이블 tr -->
                                            <jsp:include page="components/table/userTableTr.jsp">
                                                <jsp:param name="user_id" value="${user.id()}" />
                                                <jsp:param name="nickname" value="${user.nickname()}" />
                                                <jsp:param name="job_title" value="${user.jobTitle()}" />
                                                <jsp:param name="organization" value="${user.organization()}" />
                                                <jsp:param name="experience" value="${user.experience()}" />
                                                <jsp:param name="created_date" value="${user.joinDate()}" />
                                            </jsp:include>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <!-- 스크롤 안내 문구 (스크린 width: 929.33px 일 때 나타남) -->
                            <%@ include file="components/scrollDescription.jsp" %>
                    </div>
                </main>
        </div>
        <!-- 푸터 -->
        <%@ include file="components/footer.jsp" %>
</body>

</html>