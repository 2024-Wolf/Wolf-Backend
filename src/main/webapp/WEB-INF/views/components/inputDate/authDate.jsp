<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="authDate">인증일</label>
    <input class="textContent input" type="date" name="authDate" id="authDate" value="<%= request.getParameter("authDate") %>" disabled>
</div>