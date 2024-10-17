<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
	<label class="subtitle" for="title">제목</label>
	<input class="textContent input" type="text" name="question" id="title"
	value="<%= request.getParameter("question") %>"
	placeholder="제목을 입력하세요"
	<%= request.getRequestURI().contains("Detail") ? "disabled" : "required" %>>
</div>