package servlets;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import application.CSVIO;
import application.ServerManager;

/**
 * Servlet implementation class ExecServlet
 */
@WebServlet("/Exec")
public class ExecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	final String UPLOAD_DIRECTORY = "files"; //TODO

	ServerManager serverManager = new ServerManager();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//only if logged in TODO
		
		if(request.getSession().getAttribute("user_id") != null){
			int user_id = (int)request.getSession().getAttribute("user_id");
			//check if ID is admin
			if(serverManager.isAdmin(user_id)) {


				BufferedReader br = null;
				
				if (ServletFileUpload.isMultipartContent(request)) {
				    ServletFileUpload fileUpload = new ServletFileUpload();
				    FileItemIterator items;
					try {
						items = fileUpload.getItemIterator(request);
						 while (items.hasNext()) {
						        FileItemStream item = items.next();
						        if (!item.isFormField()) {
						           
						            br = new BufferedReader(new InputStreamReader(item.openStream()));
						            CSVIO io = new CSVIO(br);
						    		ServerManager manager = new ServerManager(); //create ServerManager
									
									for(String[] data :io.read()){ //for every row
										manager.insert(data); //insert data in database
									}
								
									manager.disconnect(); //disconnect from server
							
						        }
						    }
					} catch (FileUploadException e) {
						response.sendRedirect("fail.jsp");
						e.printStackTrace();
					}

				
			}
			   response.sendRedirect("sucess.jsp");
			}  
			else {

				request.setAttribute("msg","Your not logged in as admin!");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
			  

			
			
		}
		else {

			request.setAttribute("msg","Your not logged in!");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
			}


}
