<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String currentUrl = request.getRequestURI(); // 현재 URL을 가져옴
    String redirectUrl = ""; // 빈 문자열로 초기화

    if (currentUrl.startsWith("/WEB-INF/views/notice")) {
        redirectUrl = "/admin/notices";
    } else if (currentUrl.startsWith("/WEB-INF/views/faq")) {
        redirectUrl = "/admin/faqs";
    } else if (currentUrl.startsWith("/WEB-INF/views/challenge")) {
        redirectUrl = "/admin/challenges";
    } else if (currentUrl.startsWith("/WEB-INF/views/group")) {
		redirectUrl = "/admin/groups";
	}
	else {
        redirectUrl = "/";
    }
    request.setAttribute("redirectUrl", redirectUrl);
%>

<button class="sideButton noBackgroundButton" 
        onclick="if ('${redirectUrl}' !== '') { location.href='${redirectUrl}'; } else { alert('유효한 URL이 아닙니다.'); }">
    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-left" viewBox="0 0 16 16">
        <path fill-rule="evenodd" d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0" />
    </svg>
    <span class="innerText">이전</span>
</button>
