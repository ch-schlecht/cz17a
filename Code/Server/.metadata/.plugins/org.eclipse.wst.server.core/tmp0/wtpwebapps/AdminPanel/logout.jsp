<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%


if(session.getAttribute("user_id") != null){ //if session is set
	session.removeAttribute("user_id"); //remove user_id
	session.invalidate(); //invalid session
	
	//forward to login.jsp
	request.setAttribute("msg","You have been logged out!");
	request.getRequestDispatcher("/login.jsp").forward(request, response);

}
else{
	//forward to login.jsp
	request.setAttribute("msg","You havn't been logged in!");
	request.getRequestDispatcher("/login.jsp").forward(request, response);
	
}
%>  
