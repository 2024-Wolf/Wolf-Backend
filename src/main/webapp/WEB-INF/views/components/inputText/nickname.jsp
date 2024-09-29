<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="nickname">닉네임</label>
    <input class="textContent input" type="text" name="nickname" id="nickname"
        value="<%= request.getParameter("nickname") %>"
		<%= request.getRequestURI().contains("Detail") ? "disabled" : "required" %>>
</div>