	<%@ page contentType="text/html; charset=UTF-8" %>
	<link rel="stylesheet" type="text/css" href="/resources/css/sidebar.css">
	<nav class="sidebar">
	    <a class="sidebar-item <%= request.getRequestURI().startsWith("/WEB-INF/views/notice") ? "active" : "" %>"
	       href="/notice">공지사항</a>
	    <a class="sidebar-item <%= request.getRequestURI().startsWith("/WEB-INF/views/faq") ? "active" : "" %>"
	       href="/faq">FAQ</a>
	    <a class="sidebar-item <%= request.getRequestURI().startsWith("/WEB-INF/views/user") ? "active" : "" %>"
	       href="/user">회원</a>
	    <a class="sidebar-item <%= request.getRequestURI().startsWith("/WEB-INF/views/group") ? "active" : "" %>"
	       href="/group">그룹</a>
	    <a class="sidebar-item <%= request.getRequestURI().startsWith("/WEB-INF/views/challenge") ? "active" : "" %>"
	       href="/challenge">챌린지</a>
	    <a class="sidebar-item <%= request.getRequestURI().startsWith("/WEB-INF/views/report") ? "active" : "" %>"
	       href="/report">신고</a>
	    <a class="sidebar-item <%= request.getRequestURI().startsWith("/WEB-INF/views/auth") ? "active" : "" %>"
	       href="/auth">인증</a>
	</nav>
	<div class="sidebarSection"></div>
