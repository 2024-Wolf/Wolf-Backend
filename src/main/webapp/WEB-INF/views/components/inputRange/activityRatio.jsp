<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle" for="challenge">활동지표</label>
    <div class="buttonLeftContainer">
        <div class="rangeContainer">

            <label for="percentage-slider" class="textContent">&nbsp<svg
                    xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                    fill="currentColor" class="bi bi-emoji-laughing textContent"
                    viewBox="0 0 16 16">
                    <path
                        d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16" />
                    <path
                        d="M12.331 9.5a1 1 0 0 1 0 1A5 5 0 0 1 8 13a5 5 0 0 1-4.33-2.5A1 1 0 0 1 4.535 9h6.93a1 1 0 0 1 .866.5M7 6.5c0 .828-.448 0-1 0s-1 .828-1 0S5.448 5 6 5s1 .672 1 1.5m4 0c0 .828-.448 0-1 0s-1 .828-1 0S9.448 5 10 5s1 .672 1 1.5" />
                </svg>
                </label>
				  <span id="slider-value" class="textContent"><%= request.getParameter("good") %>%</span>
            <input type="range" id="percentage-slider" name="percentage" min="0" max="100"
                value="<%= request.getParameter("good") %>" oninput="updateSliderValue(this.value)" disabled />
        </div>
        <div class="rangeContainer">
            <label for="percentage-slider" class="textContent">&nbsp<svg
                    xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                    fill="currentColor" class="bi bi-emoji-neutral textContent"
                    viewBox="0 0 16 16">
                    <path
                        d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16" />
                    <path
                        d="M4 10.5a.5.5 0 0 0 .5.5h7a.5.5 0 0 0 0-1h-7a.5.5 0 0 0-.5.5m3-4C7 5.672 6.552 5 6 5s-1 .672-1 1.5S5.448 8 6 8s1-.672 1-1.5m4 0c0-.828-.448-1.5-1-1.5s-1 .672-1 1.5S9.448 8 10 8s1-.672 1-1.5" />
                </svg></label>
				<span id="slider-value" class="textContent"><%= request.getParameter("soso") %>%</span>
            <input type="range" id="percentage-slider" name="percentage" min="0" max="100"
                value="<%= request.getParameter("soso") %>" oninput="updateSliderValue(this.value)" disabled />
        </div>
        <div class="rangeContainer">
            <label for="percentage-slider" class="textContent">&nbsp<svg 
                    xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                    fill="currentColor" class="bi bi-emoji-frown textContent"
                    viewBox="0 0 16 16">
                    <path
                        d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16" />
                    <path
                        d="M4.285 12.433a.5.5 0 0 0 .683-.183A3.5 3.5 0 0 1 8 10.5c1.295 0 2.426.703 3.032 1.75a.5.5 0 0 0 .866-.5A4.5 4.5 0 0 0 8 9.5a4.5 4.5 0 0 0-3.898 2.25.5.5 0 0 0 .183.683M7 6.5C7 7.328 6.552 8 6 8s-1-.672-1-1.5S5.448 5 6 5s1 .672 1 1.5m4 0c0 .828-.448 1.5-1 1.5s-1-.672-1-1.5S9.448 5 10 5s1 .672 1 1.5" />
                </svg></label>
				<span id="slider-value" class="textContent"><%= request.getParameter("bad") %>%</span>
            <input type="range" id="percentage-slider" name="percentage" min="0" max="100"
                value="<%= request.getParameter("bad") %>" oninput="updateSliderValue(this.value)" disabled />
        </div>

    </div>
</div>