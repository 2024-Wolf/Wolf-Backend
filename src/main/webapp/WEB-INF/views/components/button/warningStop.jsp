<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="inputGroup">
    <label class="subtitle" for="content">대상 제재</label>
    <div class="buttonLeftContainer">
        <button type="button" class="formButton warningButton"
                onclick="document.getElementById('checkbox1').checked = !document.getElementById('checkbox1').checked;
                  document.getElementById('checkbox2').checked = false;
                  document.getElementById('checkbox3').checked = false;
                  document.getElementById('checkbox4').checked = false;
                  this.classList.add('checked');
                  document.querySelector('.formButton:not(.checked)').classList.remove('checked');">
            제재 없음
            <label>
                <input type="checkbox" id="checkbox1" name="action" value="NOTHING">
            </label>
        </button>
        <button type="button" class="formButton warningButton"
            onclick="document.getElementById('checkbox2').checked = !document.getElementById('checkbox2').checked;
                  document.getElementById('checkbox1').checked = false;
                  document.getElementById('checkbox3').checked = false;
                  document.getElementById('checkbox4').checked = false;
                  this.classList.add('checked'); 
                  document.querySelector('.formButton:not(.checked)').classList.remove('checked');">
            경고
            <label>
                <input type="checkbox" id="checkbox2" name="action" value="WARNING">
            </label>
        </button>
        <button type="button" class="formButton warningButton"
            onclick="document.getElementById('checkbox3').checked = !document.getElementById('checkbox3').checked;
                    document.getElementById('checkbox1').checked = false;
                    document.getElementById('checkbox2').checked = false;
                    document.getElementById('checkbox4').checked = false;
                  this.classList.add('checked'); 
                  document.querySelector('.formButton:not(.checked)').classList.remove('checked');">
            3일 정지
            <label>
                <input type="checkbox" id="checkbox3" name="action" value="SUSPEND">
            </label>
        </button>
        <button type="button" class="formButton warningRedButton"
            onclick="document.getElementById('checkbox4').checked = !document.getElementById('checkbox4').checked;
                  document.getElementById('checkbox1').checked = false;
                  document.getElementById('checkbox2').checked = false;
                  document.getElementById('checkbox3').checked = false;
                  this.classList.add('checked'); 
                  document.querySelector('.formButton:not(.checked)').classList.remove('checked');">
            영구 정지
            <label>
                <input type="checkbox" id="checkbox4" name="action" value="BAN">
            </label>
        </button>
    </div>
</div>