<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="data.access.PasswordCodeDAO" %>
<%
String code = request.getParameter("code");
String email = request.getParameter("email");

PasswordCodeDAO dao = new PasswordCodeDAO();

if(!dao.authCode(email, code)){
	request.setAttribute("msg","Invalid Code");
	request.getRequestDispatcher("/login.jsp").forward(request, response);
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forgot Password</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<form>
		//TODO
	</form>
</body>
</html>