<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="status">상태</label>
    <select class="textContent input" name="status" id="status" %>">
		<option value="comment" <%= "false".equals(request.getParameter("reportStatus")) ? "selected" : "" %>
			>처리 접수</option>
		<option value="action" <%= "true".equals(request.getParameter("reportStatus")) ? "selected" : "" %>
			>처리 완료</option>
    </select>
</div>