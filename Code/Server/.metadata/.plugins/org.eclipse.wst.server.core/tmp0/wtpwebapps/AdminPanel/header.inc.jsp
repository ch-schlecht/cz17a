
	<nav class ="navbar">
		<ul>
			<li><a href="panel.jsp">panel</a></li>
			<li><a href="config.jsp">config</a></li>
			<%
			if(session.getAttribute("user_id") != null){
				out.println("<li><a class='last' href='logout.jsp'>logout</a></li>");
			}
			%>
		</ul>
	</nav>