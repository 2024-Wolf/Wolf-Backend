<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="userStatus">상태</label>
	<div class="nowrapLeftContainer">
        <select class="textContent input" name="userStatus" id="userStatus" value="<%= request.getParameter("userStatus") %>" disabled>
			<option value="suspended" <%= "SUSPENDED".equals(request.getParameter("userStatus")) ? "selected" : "" %>
				>3일 정지 상태</option>
			<option value="stop" <%= "BANNED".equals(request.getParameter("userStatus"))  ||
									"WITHDRAWN".equals(request.getParameter("userStatus"))? "selected" : "" %>
				>정지 상태</option>
			<option value="active" <%= "ACTIVE".equals(request.getParameter("userStatus")) ? "selected" : "" %>
				>활성 상태</option>
        </select>
		<input class="textContent input" type="text" name="RemainingTime" id="RemainingTime" value="<%= request.getParameter("RemainingTime") %>" disabled>
	</div>
</div>
