<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="groupLeader">그룹장</label>
    <input class="textContent input" type="text" name="groupLeader" id="groupLeader"
        value="<%= request.getParameter("groupLeader") %>" disabled>
</div>