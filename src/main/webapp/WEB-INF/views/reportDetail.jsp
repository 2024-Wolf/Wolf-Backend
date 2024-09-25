<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>WOLF 관리자 페이지</title>
        <link rel="stylesheet" href="/resources/css/globalstyle.css">
        <link rel="stylesheet" href="/resources/css/mainContents.css">
    </head>

    <body>
        <%@ include file="header.jsp" %>
            <div class="mainContents">
                <%@ include file="sidebar.jsp" %>
                    <div class="infoCard">
                        <h2 class="title">신고 정보</h2>
                        <div class="inputSection scrollArea">
                            <div class="inputGroup">
                                <label class="subtitle" for="status">상태</label>
                                <select class="textContent input" name="status" id="status">
                                    <option value="success">처리 접수</option>
                                    <option value="failure">처리 완료</option>
                                </select>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="auth-date">신고일</label>
                                <input class="textContent input" type="date" name="auth-date" id="auth-date" disabled>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="username">신고자</label>
                                <input class="textContent input" type="text" name="username" id="username" disabled>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="username">구분</label>
                                <input class="textContent input" type="text" name="username" id="username" disabled>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="content">카테고리</label>
                                <input class="textContent input" type="text" name="username" id="username" disabled>
                            </div>

                            <div class="inputGroup">
                                <label class="subtitle" for="challenge">신고 사유</label>
                                <textarea class="textContent textarea" name="content" id="content" cols="30" rows="10"
                                    disabled></textarea>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="content">신고 대상</label>
                                <input class="textContent input" type="text" name="username" id="username" disabled>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="content">대상 제재</label>
                                <div class="buttonLeftContainer">
                                    <button class="formButton warningButton"
                                        onclick="document.getElementById('checkbox1').checked = !document.getElementById('checkbox1').checked; 
                                              document.getElementById('checkbox2').checked = false; 
                                              document.getElementById('checkbox3').checked = false; 
                                              this.classList.add('checked'); 
                                              document.querySelector('.formButton:not(.checked)').classList.remove('checked');">
                                        경고
                                        <label>
                                            <input type="checkbox" id="checkbox1">
                                        </label>
                                    </button>
                                    <button class="formButton warningButton"
                                        onclick="document.getElementById('checkbox2').checked = !document.getElementById('checkbox2').checked; 
                                              document.getElementById('checkbox1').checked = false; 
                                              document.getElementById('checkbox3').checked = false; 
                                              this.classList.add('checked'); 
                                              document.querySelector('.formButton:not(.checked)').classList.remove('checked');">
                                        3일 정지
                                        <label>
                                            <input type="checkbox" id="checkbox2">
                                        </label>
                                    </button>
                                    <button class="formButton warningRedButton"
                                        onclick="document.getElementById('checkbox3').checked = !document.getElementById('checkbox3').checked; 
                                              document.getElementById('checkbox1').checked = false; 
                                              document.getElementById('checkbox2').checked = false; 
                                              this.classList.add('checked'); 
                                              document.querySelector('.formButton:not(.checked)').classList.remove('checked');">
                                        영구 정지
                                        <label>
                                            <input type="checkbox" id="checkbox3">
                                        </label>
                                        <input type="checkbox" id="checkbox3" style="display: none;">
                                </div>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="content">신고 댓글</label>
                                <textarea class="textContent textarea" name="content" id="content" cols="30" rows="10"
                                    disabled></textarea>
                            </div>
                            <div class="inputGroup">
                                <label class="subtitle" for="content">처리 내용</label>
                                <textarea class="textContent textarea" name="content" id="content" cols="30"
                                    rows="10"></textarea>
                                <button class="formButton linePurpleButton">알림 발송</button>
                            </div>
                            <div class="buttonContainer">
                                <button type="button" class="formButton linePurpleButton"
                                    onclick="window.history.back()">취소</button>
                                <button class="formButton darkBackgroundButton"
                                    onclick="alert('완료'); location.href='/report'">완료</button>
                            </div>
                        </div>
                    </div>
            </div>
            <%@ include file="footer.jsp" %>
    </body>

    </html>