<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="faqCategory">카테고리</label>
    <select class="textContent input" name="category" id="faqCategory"
	<%= request.getRequestURI().contains("Detail") ? "disabled" : "required" %>>
		<option value="계정" <%= "계정".equals(request.getParameter("category")) ? "selected" : "" %>
			>계정</option>
		<option value="스터디" <%= "스터디".equals(request.getParameter("category")) ? "selected" : "" %>
			>스터디</option>
		<option value="프로젝트" <%= "프로젝트".equals(request.getParameter("category")) ? "selected" : "" %>
			>프로젝트</option>
		<option value="챌린지" <%= "챌린지".equals(request.getParameter("category")) ? "selected" : "" %>
			>챌린지</option>
		<option value="기타" <%= "etc".equals(request.getParameter("category")) ? "selected" : "" %>
			>Etc</option>
    </select>
</div>