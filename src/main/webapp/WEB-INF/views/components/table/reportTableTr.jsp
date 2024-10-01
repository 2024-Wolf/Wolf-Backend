<%@ page contentType="text/html; charset=UTF-8" %>
								<tr>
								    <td><a href="/reportDetail" class="aLink"><%= request.getParameter("report_id") %></a></td>
								    <td><a href="/reportDetail" class="aLink"><%= request.getParameter("reporter_id") %></a></td>
								    <td><a href="/reportDetail" class="aLink"><%= request.getParameter("report_content") %></a></td>
								    <td><a href="/reportDetail" class="aLink"><%= request.getParameter("reported_user_id") %></a></td>
									<td><a href="/reportDetail" class="aLink"><%= request.getParameter("report_date") %></a></td>								    
									<td><button class="btn1"
								            onclick="this.disabled = !this.disabled; this.textContent = '처리 완료';"><%= request.getParameter("is_solved") %></button>
								    </td>
								</tr>