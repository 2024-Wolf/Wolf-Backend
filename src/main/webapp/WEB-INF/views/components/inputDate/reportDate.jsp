<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="reportDate">신고일</label>
    <input class="textContent input" type="date" name="reportDate" id="reportDate"
	value="<%= request.getParameter("reportDate") %>" disabled>
</div>