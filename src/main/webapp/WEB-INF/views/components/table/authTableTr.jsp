<%@ page contentType="text/html; charset=UTF-8" %>
								<tr>
									<td><a href="/authDetail" class="aLink"><%= request.getParameter("auth_id") %></a></td>
									<td><a href="/authDetail" class="aLink"><%= request.getParameter("nickname") %></a></td>
									<td><a href="/authDetail" class="aLink"><%= request.getParameter("challenge_title") %></a></td>
									<td><a href="/authDetail" class="aLink"><%= request.getParameter("group_title") %></a></td>
									<td><a href="/authDetail" class="aLink"><%= request.getParameter("verification_date") %></a></td>							    
									<td><button class="btn1"
									        onclick="this.disabled = !this.disabled; this.textContent = '인증 성공';"><%= request.getParameter("auth_status") %></button>
									</td>
								</tr>