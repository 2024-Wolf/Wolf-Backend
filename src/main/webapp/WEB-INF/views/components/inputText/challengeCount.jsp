<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="challengeCount">참가 챌린지</label>
    <input class="textContent input" type="text" name="challengeCount"
        id="challengeCount" value="<%= request.getParameter("challengeCount") %>" disabled>
</div>