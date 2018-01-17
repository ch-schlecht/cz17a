 package application;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.logging.Level;

import model.Answer;
import model.HibernateUtil;
import model.Question;
import server.LogManager;

/**
 * Class for Connecting and Communicate with Database
 * @author Michael Fritz
 * @version 1.3
 */
public class ServerManager {
	
	Connection con = null;
	
	//Data for Connection
	String db = "jdbc:postgresql://localhost:5432/cz17a";
	String user = "cz17a";
	String pw = "roletran";
	
	//Vars for ID's
	int highest_IDq;
	int highest_IDa;
	
	/**
	 * Constroctor
	 * Connect to Server
	 * Load last IDS (number of Entrys)
	 * @since 1.0
	 */
	public ServerManager(){
		connect();
		highest_IDq = countTable("question"); //load last ID of question
		highest_IDa = countTable("answer"); //load last ID of answer
	}
	
	/**
	 * Connect to Server
	 * Inits the Connection {@link #con}
	 * @return success
	 * @since 1.0
	 */
	public boolean connect(){
		 try {
	         Class.forName("org.postgresql.Driver");	//use psql-driver
	         con = DriverManager.getConnection(db,user,pw); //set Connector for db
	        // LogManager.getLogger().info("Opened connection to "+user+"@"+db+" successfully");
	         System.out.println("Opened connection to "+user+"@"+db+" successfully"); 
	         return true;
	      } catch (Exception e) {
	    	// LogManager.getLogger().log(Level.SEVERE, "Unable to connect to Server",e);
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         return false;
	      }
	}
	
	/**
	 * Inserts Questions+Answers into Database
	 * 
	 * FORMAT OF STRING
	 * responsse_time;question;dynamic_diff;static_diff;topic;question1(true);question2;question3;question4;...
	 * 
	 * 
	 * @param data An Coloumn of Data
	 * @return success
	 * @since 1.0
	 * @deprecated 1.3
	 */
	public boolean insert(String[] data){
		
		if(con == null) {
			connect();
		}
		
		try {
			Statement stmt = con.createStatement(); //create Statement
			 
			highest_IDq++; //get next (free) ID
			
			float response_time = Float.parseFloat(data[0].replaceAll("﻿","")); //
			float dynamic_diff = Float.parseFloat(data[2]);
			float static_diff = Float.parseFloat(data[3]);
			
			//SQL Statement to insert Question
			String sql = "INSERT INTO QUESTION(ID,response_time,questioning,dynamic_difficulty,static_difficulty,topic,quiz_id)"
					+"VALUES ("+highest_IDq+","+response_time+",'"+data[1]+"',"+dynamic_diff+","+static_diff+",'"+data[4]+"',0);"; //TODO
			stmt.executeUpdate(sql);
			
			boolean typ = true;
			
			for(int i = 5; i < data.length; i++){  //Every Entry after 5 (id=4) is an Answer

				highest_IDa++; //next Free ID for answers
				//SQL Statement to insert Answers
				sql = "INSERT INTO ANSWER(question_id,ID,Typ,Inhalt)"
					+ "VALUES ("+highest_IDq+","+highest_IDa+","+typ+",'"+data[i]+"')"; //TODO question_id
				stmt.executeUpdate(sql);
				typ = false; //only first is true
			}
			
			stmt.close(); //close statement
			//LogManager.getLogger().info("Sucess: Added Data to Database");
			return true;
		} catch (SQLException e) {
			//LogManager.getLogger().log(Level.WARNING, "Cannot Insert Data into Database", e);
		} catch(NumberFormatException e) {
			//LogManager.getLogger().log(Level.WARNING, "Wrong Input Data: String cannot convert to Integer", e);
		}
		return false;
	}
	
	

	/**
	 * Inserts Questions+Answers into Database
	 * 
	 * FORMAT OF STRING
	 * responsse_time;question;dynamic_diff;static_diff;topic;question1(true);question2;question3;question4;...
	 * 
	 * 
	 * @param data An Coloumn of Data
	 * @return success
	 * @since 1.3
	 */
	public boolean insertHibernate(String[] data){
	
		 
		highest_IDq++; //get next (free) ID
		
		float response_time = Float.parseFloat(data[0].replaceAll("﻿","")); //
		int dynamic_diff = Integer.parseInt(data[2]);
		int static_diff = Integer.parseInt(data[3]);
		String questioning = data[1];
		String topic = data[4];
		
		
		HibernateUtil.getSession().beginTransaction();
		
		Question question = new Question();
		question.setID(highest_IDq);
		question.setDynamic_difficulty(dynamic_diff);
		question.setStatic_difficulty(static_diff);
		question.setQuestioning(questioning);
		question.setQuiz_id(0); //TODO
		question.setResponse_time(response_time);
		question.setTopic(topic);
		
		HibernateUtil.getSession().save(question);

		boolean typ = true;
		
		for(int i = 5; i < data.length; i++){  //Every Entry after 5 (id=4) is an Answer

			highest_IDa++; //next Free ID for answers

			Answer answer = new Answer();
			answer.setID(highest_IDa);
			answer.setQuestion_id(highest_IDq);
			answer.setType(typ);
			
			HibernateUtil.getSession().save(answer);
			
			typ = false; //only first is true
		}

		
		
			
		HibernateUtil.getSession().getTransaction().commit();
	
		return true;
		
	}
		
	
	
	/**
	 * Counts Entrys in spezified table
	 * @param table_name name of table to be counted
	 * @return number of entrys
	 * @since 1.0
	 */
	public int countTable(String table_name){
		Statement stmt;
		ResultSet ret = null; //For SQL-Query result
		int result = -1; //return value --> -1 == ERROR
		
		if(con == null) {
			connect();
		}
		
		try {
			stmt = con.createStatement();
			String sql = "SELECT COUNT(*) AS total FROM "+table_name; //Query for Counting Entrys

			ret = stmt.executeQuery(sql); //get resultset
			
			 while(ret.next()){
				    result = ret.getInt("total"); //turn resultSet into one Integer
			 }
			
			stmt.close(); //close Statement
		} catch (SQLException e) {
			//LogManager.getLogger().log(Level.SEVERE, "Unable to Count Table size",e);
			e.printStackTrace();
		}
		
		return result;
		
		
		
	}
	
	/**
	 * Check if user with ID is admin
	 * @param ID of user
	 * @return if admin
	 * @since 1.1
	 */
	public boolean isAdmin(int ID) {


		ResultSet set;
		boolean admin = false;
		
		if(con == null) {
			connect();
		}
		
		try {
			Statement stmt = con.createStatement(); //create Statement
			
			//SQL Statement to insert Question
			String sql = "SELECT admin FROM users WHERE ID = "+ID;
			set = stmt.executeQuery(sql);
			while(set.next()) {
				admin = set.getBoolean("admin");
				
			}
			set.close();
			stmt.close(); //close statement
			return admin;
		} catch (SQLException e) {
			//LogManager.getLogger().log(Level.SEVERE, "Unable to identify User-rank", e);
			e.printStackTrace();
			return admin;
		}


		
		
	}

	
	
	/**
	 * Inserts Questions+Answers into Database
	 * @param data An Coloumn of Data
	 * @return success
	 * @since 1.0
	 */
	public void update(String sql){
		if(con == null) {
			connect();
		}
		
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * SQL Query with return
	 * @param sql statemnet
	 * @return answer
	 * @since 1.1
	 */
	public  Statement query(String sql) {
		
		if(con == null) {
			connect();
		}
		
		ResultSet ret = null;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ret = stmt.executeQuery(sql); 
			//stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stmt;
	}

	
	
	/**
	 * Function to Authentificate user
	 * @param user username
	 * @param pw   password as plain-text
	 * @param extra AND sql statements
	 * @return ID of user
	 * @since 1.2
	 */
	public int auth(String user, String pw, String extra) {

		if(con == null) {
			connect();
		}
		
		ResultSet set;
		byte[] salt = null;
		int id = -1;
		
		try {
			Statement stmt = con.createStatement(); //create Statement
			
			//SQL Statement to get Salt
			String sql = "SELECT salt FROM users WHERE nickname = '"+user+"'";
			set = stmt.executeQuery(sql);
		
			
			while(set.next()) {
				if(set.getString("salt") != null)
				salt = PasswordManager.StringToByte(set.getString("salt").trim());	
			}
			
			if(salt == null) { //user dosnt exist
				return -1;
			}
					
			pw = PasswordManager.hash(pw, salt);
			
			//SQL Statement to get ID if user and pw +  admin is right
			sql = "SELECT id FROM users WHERE nickname = '"+user+"' AND password = '"+pw+"' "+extra;
			set = stmt.executeQuery(sql);
			while(set.next()) {
				id = set.getInt("ID");	
			}
			set.close();
			stmt.close(); //close statement
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			return id;
		}

	}
	
	
	/**
	 * Close Connection to Server
	 * @return success
	 * @since 1.0
	 */
	public boolean disconnect(){
		try {
			con.close(); //close Connection
			return true;
		} catch (SQLException e) {
			System.out.println("Failed to Close Connection to: "+db);
		}
		return false;
	}
	
}
