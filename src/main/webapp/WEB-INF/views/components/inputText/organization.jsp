<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="username">소속</label>
    <input class="textContent input" type="text" name="username" id="username" value="<%= request.getParameter("organization") %>" disabled>
</div>