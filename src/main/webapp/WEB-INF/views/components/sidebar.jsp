	<%@ page contentType="text/html; charset=UTF-8" %>
	<link rel="stylesheet" type="text/css" href="/resources/css/sidebar.css">
	<nav class="sidebar">
	    <a class="sidebar-item <%= request.getRequestURI().startsWith("/WEB-INF/views/notice") ? "active" : "" %>"
	       href="/admin/notices">공지사항</a>
	    <a class="sidebar-item <%= request.getRequestURI().startsWith("/WEB-INF/views/faq") ? "active" : "" %>"
	       href="/admin/faqs">FAQ</a>
	    <a class="sidebar-item <%= request.getRequestURI().startsWith("/WEB-INF/views/user") ? "active" : "" %>"
	       href="/admin/users">회원</a>
	    <a class="sidebar-item <%= request.getRequestURI().startsWith("/WEB-INF/views/group") ? "active" : "" %>"
	       href="/admin/groups">그룹</a>
	    <a class="sidebar-item <%= request.getRequestURI().startsWith("/WEB-INF/views/challenge") ? "active" : "" %>"
	       href="/admin/challenges">챌린지</a>
	    <a class="sidebar-item <%= request.getRequestURI().startsWith("/WEB-INF/views/report") ? "active" : "" %>"
	       href="/admin/reports">신고</a>
	    <a class="sidebar-item <%= request.getRequestURI().startsWith("/WEB-INF/views/auth") ? "active" : "" %>"
	       href="/admin/challenges/verifications">인증</a>
	</nav>
	<div class="sidebarSection"></div>
