<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="status">상태</label>
    <select class="textContent input" name="status" id="status">
		<option value="success" <%= "인증 성공".equals(request.getParameter("status")) ? "selected" : "" %>
			>인증 성공</option>
		<option value="failure" <%= "인증 실패".equals(request.getParameter("status")) ? "selected" : "" %>
			>인증 실패</option>
    </select>
</div>