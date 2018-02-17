package data.access;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import data.model.User;

public class UserDAO {
	
	/**
	 * Gets a User from DataBase by ID
	 * @param id ID of the User
	 * @return User Object
	 */
	public User getUser(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("Select u from User u where u.id = :id");
		query.setParameter("id", id);
		User user = (User) query.uniqueResult();
		session.close();
		return user;
	}
	/**
	 * Overloaded function, gets a User by nickname (since nicknames have to be unique) from DataBase
	 * @param nickname String of nickname
	 * @return User Object
	 */
	public User getUser(String nickname) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("Select u from User u where u.nickname = :nickname");
		query.setParameter("nickname", nickname);
		User user = (User) query.uniqueResult();
		session.close();
		return user;
	}
	/**
	 * Gets a List of all Users
	 * @return List of User Objects
	 */
	public List<User> getUsers(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from User");
		List<User> users = query.list();
		session.close();
		return users;
	}
	/**
	 * Adds a User to DB, but only if the ID and name is not already in the DB
	 * @param user User Object
	 */
	public void addUser(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("Select u.id from User u");
		List<Integer> userIDs = query.list();
		Query query2 = session.createQuery("Select u.nickname from User u");
		List<String> userNames = query.list();
		if(!(userIDs.contains(user.getId())) && !(userNames.contains(user.getNickname()))) { //only add the user if the ID and name is not in the DB
			Transaction ts = session.beginTransaction();
			session.save(user);
			ts.commit();
		}
		session.close();
	}
	/**
	 * counts the number of users in the DB, (used to later generate new ID's for users)
	 * @return Integer Number of users
	 */
	public int countUsers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("Select count(u) from User u");
		int numberOfUsers = (int) query.uniqueResult();
		session.close();
		return numberOfUsers;
	}
	/** 
	 * removes a user from the DB 
	 * @param id ID of the User
	 */ 
	public void removeUser(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("Delete from Users where id = :id");
		query.setParameter("id", id).executeUpdate();
		session.close();
	}
}