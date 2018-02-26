package cz17a.gamification.adminpanel.servlets;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cz17a.gamification.adminpanel.application.CSVIO;
import cz17a.gamification.adminpanel.application.HibernateQuerys;
import data.access.HibernateUtil;
import data.model.Answer;
import data.model.Question;

/**
 * Servlet implementation class ExecServlet
 */
@WebServlet("/Exec")
public class ExecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	final String UPLOAD_DIRECTORY = "files"; //TODO

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
			if(HibernateQuerys.isAdmin(user_id)) {


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
						            if(item.getName().endsWith(".csv")) {
						    
						                
							            CSVIO io = new CSVIO(br);
							    	
							            //TODO
							          //  int IDanswer = (Integer)HibernateUtil.getSessionFactory().openSession().createQuery("select max(a.id) from Answer a").uniqueResult();
							          //  int IDquestion = (Integer)HibernateUtil.getSessionFactory().openSession().createQuery("select max(q.id) from Question q").uniqueResult();							            //int IDanswer = ((Long)HibernateUtil.getSessionFactory().openSession().createQuery("select count(*) from Answer").uniqueResult()).intValue();;        
							          
							         //   System.out.println("MAX IDq: "+IDquestion);
							          //  System.out.println("MAX IDa: "+IDanswer);
							            
							            //  int IDquestion = ((Long)HibernateUtil.getSessionFactory().openSession().createQuery("select count(*) from Question").uniqueResult()).intValue();;
							            
										for(String[] data :io.read()){ //for every row
										//	IDquestion++;
											Question question = new Question(
													Integer.parseInt(data[0].trim()),
													data[1],
													Integer.parseInt(data[2].trim()),
													Integer.parseInt(data[3].trim()),
													data[4]);
										//	question.setId(IDquestion);
											
											
											Session session = HibernateUtil.getSessionFactory().openSession();
											Transaction ts = session.beginTransaction();
											session.save(question);
											ts.commit();
											session.close();
											
											boolean type = true;
											for(int i = 5; i < data.length; i++) {
												if(!data[i].equals("")) {

											//		IDanswer++;
													Answer answer = new Answer(data[i],type);
												//	answer.setID(IDanswer);
													answer.setQuestion(question);
													
													System.out.println("Answer ID:"+answer.getID());
													
													Session sessionA = HibernateUtil.getSessionFactory().openSession();
													Transaction tsA = sessionA.beginTransaction();
													sessionA.save(answer);
													tsA.commit();
													sessionA.close();
													
												}
												
												type = false;
											}
											
										}
									
								
						            	
						            }
						            else {
						            	//LogManager.getLogger().info("Tried to import not .csv file");
						            	request.getRequestDispatcher("/fail.jsp").forward(request, response);
						            	return;
						            }
						            
						    
						        }
						    }
					} catch (FileUploadException e) {
						//LogManager.getLogger().log(Level.WARNING, "Unable to upload user File", e);
						request.getRequestDispatcher("/fail.jsp").forward(request, response);
						e.printStackTrace();
						return;
					}

				
			}
			   response.sendRedirect("sucess.jsp");
			}  
			else {

				request.setAttribute("msg","Your not logged in as admin!");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}
			  

			
			
		}
		else {

			request.setAttribute("msg","Your not logged in!");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
			}


}
