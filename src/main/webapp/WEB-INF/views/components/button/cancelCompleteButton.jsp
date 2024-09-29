<%@ page contentType="text/html; charset=UTF-8" %>
<% 
    String currentUrl = request.getRequestURI(); // 현재 URL을 가져옴
    String redirectUrl = ""; // 빈 문자열로 초기화

    if (currentUrl.startsWith("/WEB-INF/views/notice")) {
        redirectUrl = "/noticeCreate";
    } else if (currentUrl.startsWith("/WEB-INF/views/faq")) {
        redirectUrl = "/faqCreate";
    } else if (currentUrl.startsWith("/WEB-INF/views/challenge")) {
        redirectUrl = "/challengeCreate";
    } else if (currentUrl.startsWith("/WEB-INF/views/user")) {
		redirectUrl = "/user";
	} else if (currentUrl.startsWith("/WEB-INF/views/report")) {
		redirectUrl = "/report";
	} else {
        redirectUrl = "/";
    }
    request.setAttribute("redirectUrl", redirectUrl);
%>
				    <div class="buttonContainer">
				        <button type="button" class="formButton linePurpleButton" onclick="window.history.back()">취소</button>
				        <button type="submit" class="formButton darkBackgroundButton 
						onclick="if ('${redirectUrl}' !== '') { location.href='${redirectUrl}'; } else { alert('유효한 URL이 아닙니다.'); }">
							완료</button>
				    </div>
