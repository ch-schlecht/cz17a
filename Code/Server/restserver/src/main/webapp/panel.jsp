<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin-Panel</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<jsp:include page="header.inc.jsp" />
<%
if(session.getAttribute("user_id") == null){
	request.setAttribute("msg","Your not logged in!");
	request.getRequestDispatcher("/login.jsp").forward(request, response);
}
%>

<h1>Admin-Panel</h1>
	  <form method="POST" action="Exec" enctype="multipart/form-data" class="center border">
        File: <input type="file" name="file"/><br/>
        <input type="submit" value="Upload"/>
       </form>
</body>
</html>