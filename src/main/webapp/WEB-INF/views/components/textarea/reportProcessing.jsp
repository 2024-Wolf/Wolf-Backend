<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="reportProcessing">처리 내용</label>
    <textarea class="textareaContent textarea" name="reportProcessing" id="reportProcessing" cols="30"
		placeholder="알림 보낼 처리 내용을 입력하세요"
        rows="10"><%= request.getParameter("reportProcessing") %></textarea>
    <button type="button" onClick="alert('알림 발송')" class="alarmButton linePurpleButton" >알림 발송</button>
</div>