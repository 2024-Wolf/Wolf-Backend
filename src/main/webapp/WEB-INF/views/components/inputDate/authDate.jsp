<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="authDate">인증일</label>
    <input class="textContent input" type="date" name="createdAt" id="authDate" value="<%= request.getParameter("authDate") %>" readonly>
</div>