<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="username">직무</label>
    <input class="textContent input" type="text" name="username" id="username" value="<%= request.getParameter("jobTitle") %>" disabled>
</div>