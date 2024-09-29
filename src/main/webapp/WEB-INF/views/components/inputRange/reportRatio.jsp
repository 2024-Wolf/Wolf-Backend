<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="challenge">신고</label>
	<div class="buttonLeftContainer">
        <label for="percentage-slider" class="textContent">비율</label>
        <span id="slider-value" class="textContent"><%= request.getParameter("reportRatio") %>%</span>
        <input type="range" id="percentage-slider" name="percentage" min="0" max="100"
            value="<%= request.getParameter("reportRatio") %>" oninput="updateSliderValue(this.value)" disabled />
	</div>
</div>