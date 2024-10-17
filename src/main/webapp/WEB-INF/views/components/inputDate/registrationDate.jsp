<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
                <div class="inputGroup">
				    <label class="subtitle" for="registrationDate">등록일</label>
				    <input class="textContent input" type="date" name="createdDate" id="registrationDate"
				        value="<%= request.getParameter("createdDate") %>"
						disabled />
				</div>