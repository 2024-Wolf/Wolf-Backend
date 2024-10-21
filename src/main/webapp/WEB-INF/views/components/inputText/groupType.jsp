<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String groupType = request.getParameter("groupType");
//    String challengeName = request.getParameter("challengeName");
    String challengeCount = request.getParameter("challengeCount");

    // 입력 유효성 검사
    if (groupType == null || challengeCount == null) {
        // 기본값 또는 오류 메시지 처리
        groupType = "";
//        challengeName = "";
        challengeCount = "";
    }
%>

<div class="inputGroup">
    <label class="subtitle" for="groupType">구분</label>
    <input class="textContent input" type="text" name="groupType" id="groupType"
        value="<%= groupType %>" disabled>
</div>

<%
    if("STUDY".equals(groupType)) {
%>
<%--<!-- 진행했던 챌린지 입력 필드 -->--%>
<%--<jsp:include page="../inputText/challengeName.jsp">--%>
<%--    <jsp:param name="challengeName" value="<%= challengeName %>" />--%>
<%--</jsp:include>--%>

<!-- 챌린지 횟수 입력 필드 -->
<jsp:include page="../inputText/challengeCount.jsp">
    <jsp:param name="challengeCount" value="<%= challengeCount %>" />
</jsp:include>
<%
    }
%>
