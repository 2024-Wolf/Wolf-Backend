<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="inputGroup">
    <label class="subtitle" for="groupMember">그룹원</label>
    <input class="textContent input" type="text" name="groupMember" id="groupMember"
        value="<%= request.getParameter("groupMember") %>" disabled>
</div>