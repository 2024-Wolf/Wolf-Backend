<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String currentUrl = request.getRequestURI(); // 현재 URL을 가져옴
    String redirectUrl = ""; // 빈 문자열로 초기화

    if (currentUrl.startsWith("/WEB-INF/views/challenge/verification")) {
        redirectUrl = "/admin/challenges/verifications";
    } else if (currentUrl.startsWith("/WEB-INF/views/group")) {
        redirectUrl = "/admin/groups";
    } else if (currentUrl.startsWith("/WEB-INF/views/user")) {
        redirectUrl = "/admin/users";
    } else if (currentUrl.startsWith("/WEB-INF/views/report")) {
        redirectUrl = "/admin/reports";
    }
    request.setAttribute("redirectUrl", redirectUrl);
%>
    <div class="buttonContainer">
        <button type="button" class="formButton linePurpleButton" onclick="if ('${redirectUrl}' !== '') { location.href='${redirectUrl}'; } else { window.history.back()}">취소</button>
        <button type="submit" class="formButton darkBackgroundButton"> 완료</button>
    </div>
