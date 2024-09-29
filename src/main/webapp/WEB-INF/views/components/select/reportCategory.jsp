<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="reportCategory">카테고리</label>
	<select class="textContent input" name="reportCategory" id="reportCategory" disabled>
		<option value="comment" <%= "부적절한 언행".equals(request.getParameter("reportCategory")) ? "selected" : "" %>
			>부적절한 언행</option>
		<option value="action" <%= "폭력적인 행동".equals(request.getParameter("reportCategory")) ? "selected" : "" %>
			>폭력적인 행동</option>
	</select>
</div>