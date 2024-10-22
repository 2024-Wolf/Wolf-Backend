<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // 파라미터를 Integer, Boolean 타입으로 변환하고 request 속성에 저장
    int totalPages = Integer.parseInt(request.getParameter("totalPages"));
    int currentPage = Integer.parseInt(request.getParameter("currentPage"));
    int size = Integer.parseInt(request.getParameter("size"));
    boolean isPageFirst = Boolean.parseBoolean(request.getParameter("isPageFirst"));
    boolean isPageLast = Boolean.parseBoolean(request.getParameter("isPageLast"));
%>

<div class="pagination">
    <ul>
        <!-- 이전 페이지 버튼 -->
        <%
            if (!isPageFirst) {
        %>
        <li><a href="?page=<%=currentPage - 1%>&size=<%=size%>">&laquo; 이전</a></li>
        <%
            }
        %>

        <!-- 페이지 번호 -->
        <%
            for (int i = 1; i <= totalPages; i++) {
        %>
        <li>
            <a href="?page=<%=i-1%>&size=<%=size%>" class="<%=currentPage == i ? "active" : ""%>">
                <%=i%>
            </a>
        </li>
        <%
            }
        %>

        <!-- 다음 페이지 버튼 -->
        <%
            if (!isPageLast) {
        %>
        <li><a href="?page=<%=currentPage%>&size=<%=size%>">다음 &raquo;</a></li>
        <%
            }
        %>
    </ul>
</div>
