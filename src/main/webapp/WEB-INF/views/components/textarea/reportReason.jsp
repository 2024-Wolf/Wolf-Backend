<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="reportReason">신고 사유</label>
    <textarea class="textareaContent textarea" name="reportReason" id="reportReason" cols="30" rows="10"
        disabled><%= request.getParameter("reportReason") %></textarea>
</div>