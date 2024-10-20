<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="challengeWarning">유의사항</label>
	<textarea class="textareaContent textarea" name="manner" id="challengeWarning" cols="30" rows="10" placeholder="챌린지 유의사항을 입력하세요"
	    <%= request.getRequestURI().contains("Detail") ? "disabled" : "required" %>
		><%= request.getParameter("manner") %>
	</textarea>
</div>



