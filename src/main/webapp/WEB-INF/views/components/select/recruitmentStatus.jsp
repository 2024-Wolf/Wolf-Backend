<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="inputGroup">
    <label class="subtitle" for="recruitmentStatus">모집 상태</label>
	<select class="textContent input" name="recruitmentStatus" id="recruitmentStatus" disabled>	
		<option value="account" <%= "모집 중".equals(request.getParameter("recruitmentStatus")) ? "selected" : "" %>
			>모집 중</option>
		<option value="study" <%= "모집 완료".equals(request.getParameter("recruitmentStatus")) ? "selected" : "" %>
			>모집 완료</option>
	</select>
</div>