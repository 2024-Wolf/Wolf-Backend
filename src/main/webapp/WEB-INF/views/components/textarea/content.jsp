<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

                <div class="inputGroup">
                    <label class="subtitle" for="notice_content">내용</label>
                    <textarea class="textareaContent textarea" name="<%= request.getRequestURI().contains("faq") ? "answer" : "content" %>" id="notice_content" cols="30" rows="10"
					<%= request.getRequestURI().contains("Detail") ? "disabled" : "required" %>
					placeholder="내용을 입력하세요"
					><%= request.getRequestURI().contains("faq") ? request.getParameter("answer") : request.getParameter("content") %></textarea>
                </div>