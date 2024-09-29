<%@ page contentType="text/html; charset=UTF-8" %>
								<tr>
								    <td><a href="/faqDetail" class="aLink"><%= request.getParameter("faq_id") %></a></td>
									<td><a href="/faqDetail" class="aLink"><%= request.getParameter("category_id") %></a></td>
									<td><a href="/faqDetail" class="aLink"><%= request.getParameter("question") %></a></td>
									<td><a href="/faqDetail" class="aLink"><%= request.getParameter("author_id") %></a></td>
									<td><a href="/faqDetail" class="aLink"><%= request.getParameter("created_date") %></a></td>
								</tr>