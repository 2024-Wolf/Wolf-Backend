<%@ page contentType="text/html; charset=UTF-8" %>
<div class="inputGroup">
    <label class="subtitle">첨부파일</label>
    <div class="fileGroup">
		<%
			String inputFile = request.getParameter("inputFile");
			if (!request.getRequestURI().contains("Detail")) {
		%> 
		<input class="textContent" type="file" name="addFile" id="addFile" style="margin-top: 10px;"
		    accept="image/jpeg, image/png" multiple 
			<%= request.getRequestURI().contains("Detail") ? "disabled" : (request.getRequestURI().contains("notice") ? "" : "required") %> 
			/>
		<%
			}
		%> 

        <div id="imageContainer" class="inputGroup imagePlaceholder">
			<%
				if (inputFile == null || inputFile.isEmpty()) {
			%> 
			<%
								if (request.getRequestURI().contains("challenge")) {
						%> 
									<div class="imageWrapper">
										<input type="radio" id="defaultImg1" name="defaultImg" value="defaultImg1" checked />
										<div class="imgText">
											<div onclick="document.getElementById('defaultImg1').checked = true;" class="fileName">[챌린지] 기본 이미지</div>
											<img onclick="document.getElementById('defaultImg1').checked = true;" src="/resources/img/thumbnail_challenge1.png"/>
										</div>
									</div>
						<%
								}
								if (request.getRequestURI().contains("notice")) {
						%>
									<div class="imageWrapper">
									    <input type="radio" id="defaultImg2" name="defaultImg" value="defaultImg2" checked />
									    <div class="imgText">
									        <div onclick="document.getElementById('defaultImg2').checked = true;" class="fileName">[프로젝트] 기본 이미지</div>
									        <img onclick="document.getElementById('defaultImg2').checked = true;" src="/resources/img/thumbnail_project1.png"/>
									    </div>
									</div>
									<div class="imageWrapper">
									    <input type="radio" id="defaultImg3" name="defaultImg" value="defaultImg3" />
									    <div class="imgText">
									        <div onclick="document.getElementById('defaultImg3').checked = true;" class="fileName">[스터디] 기본 이미지</div>
									        <img onclick="document.getElementById('defaultImg3').checked = true;" src="/resources/img/thumbnail_study1.png"/>
									    </div>
									</div>
						<%
								}
						%>	
						<%
							} else {
						%>
								<div class="imageWrapper">
									<div class="imgText">
										<div  class="fileName">이미지</div>
										<img  src="<%= inputFile %>"/>
									</div>
								</div>
			<%	
				}
			%>
		</div>
		
    </div>
</div>

<script>
    const imageContainer = document.getElementById('imageContainer');
    const fileInput = document.getElementById('addFile');
	let selectedFiles = [];
    const allowedFormats = ['image/jpeg', 'image/png']; // 허용된 파일 형식
	

    fileInput.addEventListener('change', function (event) {
		selectedFiles = [];
        const files = Array.from(event.target.files);
        const invalidFiles = files.filter(file => {
            // MIME 타입이 허용되지 않거나, 확장자가 jfif, pjpeg, pjp인 파일은 제외하기 필터링
            const isJfif = file.name.toLowerCase().endsWith('.jfif');
            const isPjpeg = file.name.toLowerCase().endsWith('.pjpeg');
            const isPjp = file.name.toLowerCase().endsWith('.pjp');
            return !allowedFormats.includes(file.type) || isJfif || isPjpeg || isPjp;
        });

        if (invalidFiles.length > 0) {
            alert('허용되지 않는 파일 형식입니다 (첨부 가능 형식: jpeg, jpg, png)');
            fileInput.value = '';
            return; // 초기화하지 않고 단순히 메시지 출력
        }

        selectedFiles.push(...files); // 기존 파일에 새로 선택한 파일 추가
        renderImages(); // 이미지를 렌더링
    });
	
	const deleteButton = document.createElement('button');
	deleteButton.classList.add('sideButton', 'alarmButton', 'linePurpleButton');
	deleteButton.innerHTML = `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
	    <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5M8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5m3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0" />
	</svg><span class="innerText">전체 삭제</span>`;
	
	// "모두 삭제" 버튼 클릭 시 모든 파일 제거
	deleteButton.addEventListener('click', function () {
	    selectedFiles = []; // 선택된 파일 배열 초기화
	    fileInput.value = ''; // input 파일 필드 초기화
	    imageContainer.innerHTML = ''; // 이미지 리스트 초기화
	});

    function renderImages() {
        imageContainer.innerHTML = ''; // 이전 내용 초기화
		imageContainer.appendChild(deleteButton);
        
        selectedFiles.forEach((file, index) => {
            const reader = new FileReader();
            
            reader.onload = function (e) {
                const imgAllDiv = document.createElement('div');
                imgAllDiv.classList.add('imageWrapper');

                const imgDiv = document.createElement('div');
                imgDiv.classList.add('imgText');

                const img = document.createElement('img');
                img.src = e.target.result;

                const fileNameElement = document.createElement('div');
                fileNameElement.classList.add('fileName');
                fileNameElement.textContent = file.name;
				
                imgDiv.appendChild(fileNameElement);
                imgDiv.appendChild(img);
                imgAllDiv.appendChild(imgDiv);
                imageContainer.appendChild(imgAllDiv);
            };

            reader.readAsDataURL(file);
        });
    }
</script>
