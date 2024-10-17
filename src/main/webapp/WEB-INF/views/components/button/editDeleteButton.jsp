<%@ page contentType="text/html; charset=UTF-8" %>
<%
	String currentUrl = request.getRequestURI(); // 현재 URL을 가져옴
	String editUrl = ""; // 빈 문자열로 초기화
	String deleteUrl = ""; // 빈 문자열로 초기화

	if (currentUrl.startsWith("/WEB-INF/views/notice")) {
		editUrl = "/admin/notices/noticeEdit/" + request.getParameter("notice_id");
		deleteUrl = "/admin/notices/" + request.getParameter("notice_id");
	} else if (currentUrl.startsWith("/WEB-INF/views/faq")) {
		editUrl = "/admin/faqs/faqEdit/" + request.getParameter("faq_id");
		deleteUrl = "/admin/faqs/" + request.getParameter("faq_id");
	} else if (currentUrl.startsWith("/WEB-INF/views/challenge")) {
		editUrl = "/challengeEdit/" + request.getParameter("challenge_id");
		deleteUrl = "/admin/challenges/" + request.getParameter("challenge_id");
	} else {
		editUrl = "/";
	}

	request.setAttribute("editUrl", editUrl);
	request.setAttribute("deleteUrl", deleteUrl);
%>
<div class="buttonSideContainer">
	<!-- 수정 버튼 -->
	<button class="sideButton linePurpleButton"
			onclick="if ('${editUrl}' !== '') { location.href='${editUrl}'; } else { alert('유효한 URL이 아닙니다.'); }">
		<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square"
			 viewBox="0 0 16 16">
			<path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
			<path fill-rule="evenodd"
				  d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"/>
		</svg>
		<span class="innerText">수정</span>
	</button>

	<!-- 삭제 버튼 -->
	<form action="${deleteUrl}" method="post" style="display:inline;">
		<input type="hidden" name="_method" value="DELETE">
		<button type="submit" class="sideButton darkBackgroundButton"
				onclick="return confirm('정말 삭제하시겠습니까?')">
			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
				 class="bi bi-trash-fill" viewBox="0 0 16 16">
				<path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5M8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5m3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0"/>
			</svg>
			<span class="innerText">삭제</span>
		</button>
	</form>
</div>
