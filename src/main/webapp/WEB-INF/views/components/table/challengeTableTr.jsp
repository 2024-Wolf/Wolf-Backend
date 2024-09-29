<%@ page contentType="text/html; charset=UTF-8" %>
								<tr>
									<td><a href="/challengeDetail" class="aLink"><%= request.getParameter("challenge_post_id") %></a></td>
									<td><a href="/challengeDetail" class="aLink"><%= request.getParameter("challenge_title") %></a></td>
									<td><a href="/challengeDetail" class="aLink"><%= request.getParameter("verification_agent") %></a></td>
									<td><a href="/challengeDetail" class="aLink"><%= request.getParameter("challenge_startDate") %></a></td>
									<td><a href="/challengeDetail" class="aLink"><%= request.getParameter("challenge_endDate") %></a></td>
									<td><a href="/challengeDetail" class="aLink"><%= request.getParameter("challenge_status") %></a></td>
								</tr>