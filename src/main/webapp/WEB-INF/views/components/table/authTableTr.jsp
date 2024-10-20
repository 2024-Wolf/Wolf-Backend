<%@ page contentType="text/html; charset=UTF-8" %>
								<tr>
									<td><a href="/admin/challenges/verification/<%=request.getParameter("auth_id")%>" class="aLink"><%= request.getParameter("auth_id") %></a></td>
									<td><a href="/admin/challenges/verification/<%=request.getParameter("auth_id")%>" class="aLink"><%= request.getParameter("nickname") %></a></td>
									<td><a href="/admin/challenges/verification/<%=request.getParameter("auth_id")%>" class="aLink"><%= request.getParameter("challenge_title") %></a></td>
									<td><a href="/admin/challenges/verification/<%=request.getParameter("auth_id")%>" class="aLink"><%= request.getParameter("group_title") %></a></td>
									<td><a href="/admin/challenges/verification/<%=request.getParameter("auth_id")%>" class="aLink"><%= request.getParameter("verification_date") %></a></td>
									<td><a href="/admin/challenges/verification/<%=request.getParameter("auth_id")%>" class="aLink"><%= request.getParameter("auth_status") %></a></td>
								</tr>