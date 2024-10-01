<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="challengeName">챌린지 이름</label>
    <input class="textContent input" type="text" name="challengeName" id="challengeName"
	placeholder="챌린지 이름을 입력하세요" value="<%= request.getParameter("challengeName") %>" 
<%= request.getRequestURI().contains("Detail") ? "disabled" : "required" %>>
</div>