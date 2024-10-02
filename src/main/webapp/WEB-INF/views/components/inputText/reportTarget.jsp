<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="reportTarget">신고 대상</label>
    <input class="textContent input" type="text" name="reportTarget" id="reportTarget" 
	value="<%= request.getParameter("reportTarget") %>" disabled>
</div>