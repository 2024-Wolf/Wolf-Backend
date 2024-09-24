<%@ page contentType="text/html; charset=UTF-8" %>
    <link rel="stylesheet" type="text/css" href="/resources/css/sidebar.css">
    <nav class="sidebar">
        <a class="sidebar-item <%= request.getRequestURI().equals(" /notice") ? "active" : "" %>"
            href="/notice">공지사항</a>
        <a class="sidebar-item <%= request.getRequestURI().equals(" /faq") ? "active" : "" %>" href="/faq">FAQ</a>
        <a class="sidebar-item <%= request.getRequestURI().equals(" /user") ? "active" : "" %>" href="/user">회원</a>
        <a class="sidebar-item <%= request.getRequestURI().equals(" /group") ? "active" : "" %>" href="/group">그룹</a>
        <a class="sidebar-item <%= request.getRequestURI().equals(" /challenge") ? "active" : "" %>"
            href="/challenge">챌린지</a>
        <a class="sidebar-item <%= request.getRequestURI() %>" href="/report">신고</a>
        <a class="sidebar-item <%= request.getRequestURI() %>" href="/auth">인증</a>
    </nav>