package cz17a.gamification.adminpanel.servlets;
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

import org.hibernate.Query;
import org.hibernate.Session;

import cz17a.gamification.adminpanel.application.PasswordManager;
import data.access.HibernateUtil;
import data.model.Answer;


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
@Override	
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

			// Get Params from request
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			String salt = "";
			
			
			//Get Salt
			Session sessionS = HibernateUtil.getSessionFactory().openSession();
			Query queryS = sessionS.createQuery("select a.salt from Admin a where a.nickname=:nickname");
			queryS.setParameter("nickname", login);
			if(queryS.uniqueResult() != null) {
				salt= (String) queryS.uniqueResult();
			}
			
			//Hash pw
			password = PasswordManager.hash(password, PasswordManager.StringToByte(salt));
			
			int id = -1;
			
			//Check if user is an Admin and get id
			Session sessionH = HibernateUtil.getSessionFactory().openSession();
			Query query = sessionH.createQuery("select a.id from Admin a where a.nickname=:nickname and a.password=:pw");
			query.setParameter("nickname", login);
			query.setParameter("pw", password);
			
			System.out.println("Query: "+query.getQueryString());
			System.out.println("Result: "+query.getFirstResult());
			
			if(query.uniqueResult() != null) {
				id= (int) query.uniqueResult();
			}
			
			
			sessionH.close();
			
			
			
			
			if(id != -1) { //if admin account exist with this password
			
				System.out.println("[Admin panel]@"+login+" login with "+password+" at ID"+id);
				
				
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
				
				System.out.println("[Admin panel]@"+login+" login failed with "+password);
				
				
				request.setAttribute("msg","Wrong Username or Password"); //message for login.jsp
				request.getRequestDispatcher("/login.jsp").forward(request, response); //go forward to login.jsp
			}
		
	}

	
/**
 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
 */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setAttribute("msg","Please login"); //message for login.jsp
	request.getRequestDispatcher("/login.jsp").forward(request, response); //go forward to login.jsp

}

}
