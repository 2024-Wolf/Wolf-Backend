<%@ page contentType="text/html; charset=UTF-8" %>
								<tr>
								    <td><a href="/admin/users/<%= request.getParameter("user_id") %>" class="aLink"><%= request.getParameter("user_id") %></a></td>
								    <td><a href="/admin/users/<%= request.getParameter("user_id") %>" class="aLink"><%= request.getParameter("nickname") %></a></td>
								    <td><a href="/admin/users/<%= request.getParameter("user_id") %>" class="aLink"><%= request.getParameter("job_title") %></a></td>
								    <td><a href="/admin/users/<%= request.getParameter("user_id") %>" class="aLink"><%= request.getParameter("organization") %></a></td>
								    <td><a href="/admin/users/<%= request.getParameter("user_id") %>" class="aLink"><%= request.getParameter("experience") %></a></td>
								    <td><a href="/admin/users/<%= request.getParameter("user_id") %>" class="aLink"><%= request.getParameter("created_date") %></a></td>
								</tr>
								