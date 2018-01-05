<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
if(session.getAttribute("user_id") == null){
	request.setAttribute("msg","Your not logged in!");
	request.getRequestDispatcher("/login.jsp").forward(request, response);
}
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Admin-Panel</title>
	<link rel="stylesheet" href="style.css">
</head>
<body>
<jsp:include page="header.inc.jsp" />
	<h1>Admin-Panel</h1>
	
		<form method="POST" action="Config" class="center border">
        	Length: <input type="text" name="length"/><br/> <!-- TODO load Data -->
        	min particians: <input type="text" name="min"/><br/>
            max particians: <input type="text" name="max"/><br/>
            <input type="submit" value="config"/>
       	</form>


</body>
</html>