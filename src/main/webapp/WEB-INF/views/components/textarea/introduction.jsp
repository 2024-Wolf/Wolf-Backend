<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="content">자기소개
    </label>
    <textarea class="textareaContent textarea" name="content" id="content" cols="30" rows="10"
        disabled><%= request.getParameter("introduction") %></textarea>
</div>