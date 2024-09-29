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
    } else {
        redirectUrl = "/";
    }
    request.setAttribute("redirectUrl", redirectUrl);
%>

<span class="buttonSideContainer">
    <button type="button" class="formButton darkBackgroundButton"
        onclick="if ('${redirectUrl}' !== '') { location.href='${redirectUrl}'; } else { alert('유효한 URL이 아닙니다.'); }">
        작성하기
    </button>
</span>
