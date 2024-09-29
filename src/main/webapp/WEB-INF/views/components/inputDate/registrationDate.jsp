<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
                <div class="inputGroup">
				    <label class="subtitle" for="registrationDate">등록일</label>
				    <input class="textContent input" type="date" name="registrationDate" id="registrationDate"
				        value="<%= request.getParameter("registrationDate") %>"
						disabled />
				</div>