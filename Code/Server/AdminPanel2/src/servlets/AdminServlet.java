package servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import application.ServerManager;

/**
 * Servlet for Login Funktion
 * @author Michael
 * @version 1.0
 */
@WebServlet("/Login")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * POST funktion
	 * needs login(name) + password
	 * 
	 * session with ID if login success
	 * 
	 * @since 1.0
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServerManager serverManager = new ServerManager(); //for sql-querys

			// Get Params from request
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			
			
			int id = serverManager.auth(login,password,"AND admin = true"); //try to login (and if admin)
			
			if(id != -1) { //if admin account exist with this password
			
				
				HttpSession session = request.getSession(); //create new session
				session.setAttribute("user_id", id);	//ID as session attribute
				//setting session to expiry in 30 mins
				session.setMaxInactiveInterval(30*60);
				Cookie userID = new Cookie("user_id", ""+id); //also setting cookie with ID
				userID.setMaxAge(30*60); //for 30 mins
				
				response.addCookie(userID);				//add Cookie to response
				response.sendRedirect("panel.jsp");		//redirect to panel
			}
			else {	//login is false
				
				request.setAttribute("msg","Wrong Username or Password"); //message for login.jsp
				request.getRequestDispatcher("/login.jsp").forward(request, response); //go forward to login.jsp
			}
		
	}

}
