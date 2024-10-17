<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="registrarName">등록자</label>
    <input class="textContent input" type="text" name="author" id="registrarName"
        value="<%= request.getParameter("author") %>"
		disabled >
</div>