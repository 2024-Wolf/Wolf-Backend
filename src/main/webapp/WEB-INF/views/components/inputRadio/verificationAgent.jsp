<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
	<label class="subtitle" for="verificationAgent">인증주체</label>
	<div class="nowrapLeftContainer">
		<input id="system" type="radio" name="verificationAgent" value="시스템" 
		<%= "시스템".equals(request.getParameter("verificationAgent")) ? "checked" : "checked" %>
		<%= request.getRequestURI().contains("Detail") ? "disabled" : "required" %>>
		<label for="system" class="textContent">시스템</label>

		<input id="manager" type="radio" name="verificationAgent" value="관리자" 
		<%= "관리자".equals(request.getParameter("verificationAgent")) ? "checked" : "" %>
		<%= request.getRequestURI().contains("Detail") ? "disabled" : "required" %>>
		<label for="manager" class="textContent">관리자</label>
	</div>
</div>
