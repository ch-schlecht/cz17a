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
	<h1>Admin-Panel</h1>
	
	<div id="msg" class="center">
		<font color="red">
		${msg}
		</font>
	</div>
	
		<form method="POST" action="Login" class="center border">
        	Login: <input type="text" name="login"/><br/>
        	Password: <input type="password" name="password"/><br/>
            <input type="submit" value="enter"/>
       	</form>


</body>
</html>