<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="challenge">프로젝트</label>
	<div class="buttonLeftContainer">
        <label for="percentage-slider" class="textContent">완료</label>
        <span id="slider-value" class="textContent"><%= request.getParameter("projectRatio") %>%</span>
        <input type="range" id="percentage-slider" name="percentage" min="0" max="100"
            value="<%= request.getParameter("projectRatio") %>" oninput="updateSliderValue(this.value)" disabled />
	</div>
</div>