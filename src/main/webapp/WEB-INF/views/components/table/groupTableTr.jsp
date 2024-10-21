<%@ page contentType="text/html; charset=UTF-8" %>
								<tr>
									<td><a href="/groupDetail" class="aLink"><%= request.getParameter("group_id") %></a></td>
									<td><a href="/groupDetail" class="aLink"><%= request.getParameter("group_title") %></a></td>
									<td><a href="/groupDetail" class="aLink"><%= request.getParameter("group_type") %></a></td>
									<td><a href="/groupDetail" class="aLink"><%= request.getParameter("start_date") %></a></td>
									<td><a href="/groupDetail" class="aLink"><%= request.getParameter("end_date") %></a></td>
									<td><a href="/groupDetail" class="aLink"><%= request.getParameter("member_cnt") %></a></td>
<%--									<td><a href="/groupDetail" class="aLink"><%= request.getParameter("challenge_title") %></a></td>--%>
									<td><a href="/groupDetail" class="aLink"><%= request.getParameter("challenge_status") %></a></td>
								</tr>