package cz17a.gamification.restserver.resource.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cz17a.gamification.restserver.sql.ServerManager;

public class UserService {

	final String TABLE_NAME = "users";
	
	
	public User generateUser(ResultSet set) {
		try {
			return new User(set.getInt("ID"),set.getString("mail"),set.getString("nickname"),set.getString("password"),set.getString("salt"),set.getTimestamp("last_login"),set.getTimestamp("registration"),set.getFloat("playtime"),set.getBoolean("admin"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	
	public List<User> getAllUser(){
		ArrayList<User> res = new ArrayList<>();
		Statement stmt;
		ResultSet set;
	
		try {
			stmt = ServerManager.query("SELECT * From "+TABLE_NAME);
			set = stmt.getResultSet();
			while(set.next()) {
				res.add(generateUser(set));
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public User getUser(int ID) {
		User res = null;
		Statement stmt;
		ResultSet set;		
		
		try {
			stmt = ServerManager.query("SELECT * From "+TABLE_NAME+" WHERE ID = "+ID);
			set = stmt.getResultSet();
			while(set.next()) {
				res = generateUser(set);
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public User getUserByName(String name) {
		User res = null;
		Statement stmt;
		ResultSet set;		
		
		try {
			stmt = ServerManager.query("SELECT * From "+TABLE_NAME+" WHERE nickname = '"+name+"'");
			set = stmt.getResultSet();
			while(set.next()) {
				res = generateUser(set);
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	//TODO
		public User addUser(User user) {
		ServerManager.update("INSERT INTO "+TABLE_NAME+"(id,mail,nickname,password,last_login,registration,playtime,admin,salt) VALUES("+user.getID()+", "+user.getMail()+", "+user.getNickname()+", "+user.getPassword()+", "+user.getLast_login()+", "+user.getRegistation()+", "+user.getPlaytime()+", "+user.getAdmin()+", "+user.getSalt()+")");
		return user; //TODO
	}
	
	
}