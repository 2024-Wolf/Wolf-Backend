<%@ page contentType="text/html; charset=UTF-8" %>
								<tr>
								    <td><a href="/admin/reports/<%= request.getParameter("report_id") %>" class="aLink"><%= request.getParameter("report_id") %></a></td>
								    <td><a href="/admin/reports/<%= request.getParameter("report_id") %>" class="aLink"><%= request.getParameter("reporter_id") %></a></td>
								    <td><a href="/admin/reports/<%= request.getParameter("report_id") %>" class="aLink"><%= request.getParameter("report_content") %></a></td>
								    <td><a href="/admin/reports/<%= request.getParameter("report_id") %>" class="aLink"><%= request.getParameter("reported_user_id") %></a></td>
									<td><a href="/admin/reports/<%= request.getParameter("report_id") %>" class="aLink"><%= request.getParameter("report_date") %></a></td>
									<td><button class="btn1"><%= request.getParameter("is_solved").equals("true") ? "처리 완료" : "처리 접수" %></button>
								    </td>
								</tr>