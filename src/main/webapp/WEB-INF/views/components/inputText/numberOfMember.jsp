<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="inputGroup">
    <label class="subtitle" for="numberOfMember">인원</label>
    <input class="textContent input" type="text" name="numberOfMember" id="numberOfMember"
        value="<%= request.getParameter("numberOfMember") %>" disabled>
</div>