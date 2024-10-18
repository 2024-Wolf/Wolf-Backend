<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="challengeAwardContent">보상</label>
    <textarea class="textareaContent textarea" name="awardContent" id="challengeAwardContent" cols="30" rows="10"
	placeholder="챌린지 보상 내용을 입력하세요"
        <%= request.getRequestURI().contains("Detail") ? "disabled" : "required" %>
		><%= request.getParameter("awardContent") %></textarea>
</div>