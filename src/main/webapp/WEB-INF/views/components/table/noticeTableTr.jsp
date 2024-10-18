<%@ page contentType="text/html; charset=UTF-8" %>
								<tr>
								    <td><a href="/admin/notices/<%= request.getParameter("notice_id")%>" class="aLink"><%= request.getParameter("notice_id") %></a></td>
								    <td><a href="/admin/notices/<%= request.getParameter("notice_id")%>" class="aLink"><%= request.getParameter("notice_title") %></a></td>
								    <td><a href="/admin/notices/<%= request.getParameter("notice_id")%>" class="aLink"><%= request.getParameter("registrarName") %></a></td>
								    <td><a href="/admin/notices/<%= request.getParameter("notice_id")%>" class="aLink"><%= request.getParameter("registrationDate") %></a></td>
								</tr>