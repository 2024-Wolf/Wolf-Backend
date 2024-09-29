<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="userCreatedDate">가입일</label>
    <input class="textContent input" type="date" name="userCreatedDate" id="userCreatedDate" value="<%= request.getParameter("userCreatedDate") %>" disabled>
</div>
