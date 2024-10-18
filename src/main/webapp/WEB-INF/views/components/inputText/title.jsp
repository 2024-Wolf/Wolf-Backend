<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
	<label class="subtitle" for="title">제목</label>
	<input class="textContent input" type="text" name="title" id="title"
	value="<%= request.getRequestURI().contains("faq") ? request.getParameter("question") : request.getParameter("title") %>"
	placeholder="제목을 입력하세요"
	<%= request.getRequestURI().contains("Detail") ? "disabled" : "required" %>>
</div>