<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="faqCategory">카테고리</label>
    <select class="textContent input" name="faqCategory" id="faqCategory""
	<%= request.getRequestURI().contains("Detail") ? "disabled" : "required" %>>
		<option value="account" <%= "계정".equals(request.getParameter("faqCategory")) ? "selected" : "" %>
			>계정</option>
		<option value="study" <%= "스터디".equals(request.getParameter("faqCategory")) ? "selected" : "" %>
			>스터디</option>
		<option value="project" <%= "프로젝트".equals(request.getParameter("faqCategory")) ? "selected" : "" %>
			>프로젝트</option>
		<option value="challenge" <%= "챌린지".equals(request.getParameter("faqCategory")) ? "selected" : "" %>
			>챌린지</option>
		<option value="etc" <%= "etc".equals(request.getParameter("faqCategory")) ? "selected" : "" %>
			>Etc</option>
    </select>
</div>