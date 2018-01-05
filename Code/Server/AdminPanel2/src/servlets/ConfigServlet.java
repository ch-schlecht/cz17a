package servlets;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.ServerManager;

/**
 * Servlet implementation class ConfigServlet
 * @author Michael
 * @version 1.0
 */
@WebServlet("/Config")
public class ConfigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	ServerManager serverManager = new ServerManager();
	
	/**
	 * POST Methode 
	 * @since 1.0
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if(request.getSession().getAttribute("user_id") != null){ //check if session is set
			int user_id = (int)request.getSession().getAttribute("user_id"); //get ID from session 
			if(serverManager.isAdmin(user_id)) { //check if user is admin

				int ID = 0; //TODO QUIZ-SETTING ID (at the moment only on config exist)
				//params of quiz
				int length = Integer.parseInt(request.getParameter("length"));				
				int min = Integer.parseInt(request.getParameter("min"));
				int max = Integer.parseInt(request.getParameter("max"));
				
				//sql statement to update ID = 0
				serverManager.update("UPDATE Quiz SET length = "+length+", min_participants = "+min+", max_participants = "+max+" WHERE ID = 0 ");

				//forward to sucess.jsp
				request.setAttribute("msg","Changed config successfully");
				request.getRequestDispatcher("/success.jsp").forward(request, response);
			
			}
			else {//not admin
				request.setAttribute("msg","Your not logged in as admin!");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			
			}
			
		}else {//no session
			request.setAttribute("msg","Your not logged in!");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}
