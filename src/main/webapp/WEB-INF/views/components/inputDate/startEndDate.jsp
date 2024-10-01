<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="groupStartDate">기간</label>
    <div class="nowrapLeftContainer">
        <input class="textContent input" type="date" name="startDate"
            id="startDate" value="<%= request.getParameter("startDate") %>" 
<%= request.getRequestURI().contains("Detail") ? "disabled" : "required" %>>
        ~
        <input class="textContent input" type="date" name="endDate" id="endDate"
            value="<%= request.getParameter("endDate") %>" 
<%= request.getRequestURI().contains("Detail") ? "disabled" : "required" %>>
    </div>
</div>