<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="reporter">신고자</label>
    <input class="textContent input" type="text" name="reporter" id="reporter" 
	value="<%= request.getParameter("reporter") %>" disabled>
</div>