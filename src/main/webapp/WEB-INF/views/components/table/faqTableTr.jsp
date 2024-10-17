<%@ page contentType="text/html; charset=UTF-8" %>
<script>

</script>
	<tr>
		<td><a href="/admin/faqs/<%= request.getParameter("faq_id") %>" class="aLink"><%= request.getParameter("faq_id") %></a></td>
		<td><a href="/admin/faqs/<%= request.getParameter("faq_id") %>" class="aLink"><%= request.getParameter("category_id") %></a></td>
		<td><a href="/admin/faqs/<%= request.getParameter("faq_id") %>" class="aLink"><%= request.getParameter("question") %></a></td>
		<td><a href="/admin/faqs/<%= request.getParameter("faq_id") %>" class="aLink"><%= request.getParameter("author_id") %></a></td>
		<td><a href="/admin/faqs/<%= request.getParameter("faq_id") %>" class="aLink"><%= request.getParameter("created_date") %></a></td>
	</tr>