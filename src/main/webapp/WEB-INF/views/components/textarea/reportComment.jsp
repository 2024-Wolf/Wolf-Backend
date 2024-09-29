<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="reportComment">신고 댓글</label>
    <textarea class="textareaContent textarea" name="reportComment" id="reportComment" cols="30" rows="10"
        disabled><%= request.getParameter("reportComment") %></textarea>
</div>