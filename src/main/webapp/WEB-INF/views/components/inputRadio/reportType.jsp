<%@ page contentType="text/html; charset=UTF-8" %>
<%
	String reportTarget = request.getParameter("reportTarget");
	String reportComment = request.getParameter("reportComment");
	String reportType = request.getParameter("reportType");
%>
<div class="inputGroup">
    <label class="subtitle" for="reportType">구분</label>
    <div class="nowrapLeftContainer">
        <input id="user" type="radio" name="reportType" value="유저" disabled
        <%= "USER".equals(request.getParameter("reportType")) ? "checked" : "" %>>
        <label for="user" class="textContent">유저</label>

        <input id="group" type="radio" name="reportType" value="그룹" disabled
        <%= "GROUP".equals(request.getParameter("reportType")) ? "checked" : "" %>>
        <label for="group" class="textContent">그룹</label>

        <input id="question" type="radio" name="reportType" value="질문" disabled
        <%= "QUESTION".equals(request.getParameter("reportType")) ? "checked" : "" %>>
        <label for="question" class="textContent">질문</label>

        <input id="comment" type="radio" name="reportType" value="댓글" disabled
            <%= "REPLY".equals(request.getParameter("reportType")) ? "checked" : "" %>>
        <label for="comment" class="textContent">댓글</label>

    </div>
</div>
<%--GROUP, REPLY, QUESTION, USER--%>
<!-- 신고 대상 -->
<jsp:include page="../inputText/reportTarget.jsp">
	<jsp:param name="reportTarget" value="<%= reportTarget %>" />
</jsp:include>

<%
	if("댓글".equals(reportType)) {
%>
<!-- 신고 댓글 -->
<jsp:include page="../textarea/reportComment.jsp">
	<jsp:param name="reportComment" value="<%= reportComment %>" />
</jsp:include>

<%
	}
%>