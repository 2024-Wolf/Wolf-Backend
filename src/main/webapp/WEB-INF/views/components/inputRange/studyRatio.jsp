<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="challenge">스터디</label>
	<div class="buttonLeftContainer">
        <label for="percentage-slider" class="textContent">완료</label>
        <span id="slider-value" class="textContent"><%= request.getParameter("studyRatio") %>%</span>
        <input type="range" id="percentage-slider" name="percentage" min="0" max="100"
            value="<%= request.getParameter("studyRatio") %>" oninput="updateSliderValue(this.value)" disabled />
	</div>
</div>