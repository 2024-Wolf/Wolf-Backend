<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="challengeName">챌린지 이름</label>
    <input class="textContent input" type="text" name="title" id="challengeName"
	placeholder="챌린지 이름을 입력하세요" value="<%= request.getParameter("title") %>"
<%= request.getRequestURI().contains("Detail") ? "readonly" : "required" %>>
</div>