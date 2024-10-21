<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle">기간</label>
    <div class="nowrapLeftContainer">
        <input class="textContent input" type="date" name="registrationDate"
            id="startDate" value="<%= request.getParameter("startDate") %>"
<%= request.getRequestURI().contains("Detail") ? "disabled" : "required" %>>
        ~
        <input class="textContent input" type="date" name="deadline" id="endDate"
            value="<%= request.getParameter("endDate") %>"
<%= request.getRequestURI().contains("Detail") ? "disabled" : "required" %>>
    </div>
</div>