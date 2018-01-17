package cz17a.gamification.restserver.sql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Class for Connecting and Communicate with Database
 * @author Michael Fritz
 * @version 1.0
 */
public class ServerManager {
	
	
	static Connection con = null;
	
	//Data for Connection
	static String db = "jdbc:postgresql://localhost:5432/cz17a";
	static String user = "cz17a";
	static String pw = "roletran";
	
	
	/**
	 * Connect to Server
	 * Inits the Connection {@link #con}
	 * @return success
	 * @since 1.0
	 */
	public static boolean connect(){
		 try {
	         Class.forName("org.postgresql.Driver");
	         con = DriverManager.getConnection(db,user,pw);
	         System.out.println("Opened connection to "+user+"@"+db+" successfully");
	         return true;
	      } catch (Exception e) {
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         return false;
	      }
	}
	
	
	
	/**
	 * Inserts Questions+Answers into Database
	 * @param data An Coloumn of Data
	 * @return success
	 * @since 1.0
	 */
	public static void update(String sql){
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
	 */
	public static Statement query(String sql) {
		
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
	 * Count Entrys of specified Table
	 * @param table_name name of table to count
	 * @return number of elements in table
	 * @since 1.0
	 */
	public static int countTable(String table_name){
		
		if(con == null) {
			connect();
		}
		
		Statement stmt;
		ResultSet ret = null; //For SQL-Query result
		int result = -1; //return value --> -1 == ERROR
		try {
			stmt = con.createStatement();
			String sql = "SELECT COUNT(*) AS total FROM "+table_name; //Query for Counting Entrys

			ret = stmt.executeQuery(sql); //get resultset
			
			 while(ret.next()){
				    result = ret.getInt("total"); //turn resultSet into one Integer
			 }
			
			stmt.close(); //close Statement
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;	
	}
	
	
	/**
	 * Close Connection to Server
	 * @return success
	 * @since 1.0
	 */
	public static boolean disconnect(){
		
		try {
			con.close(); //close Connection
			return true;
		} catch (SQLException e) {
			System.out.println("Failed to Close Connection to: "+db);
		}
		return false;
	}
	
	
	
}
