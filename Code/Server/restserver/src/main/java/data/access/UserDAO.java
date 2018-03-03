package data.access;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import data.model.User;

/**
 * DAO Class for User
 * 
 * @author cz17a
 * @version 1.1
 * @category DAO
 */

public class UserDAO {

	/**
	 * Gets a User from DataBase by ID
	 * 
	 * @param id
	 *            ID of the User
	 * @return User Object
	 */
	public User getUser(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("Select u from Person u where u.id = :id");
		query.setParameter("id", id);
		User user = (User) query.uniqueResult();
		session.close();
		return user;
	}

	/**
	 * Overloaded function, gets an User by nickname (since nicknames have to be
	 * unique) from DataBase
	 * 
	 * @param nickname
	 *            String of nickname
	 * @return User Object
	 */
	public User getUser(String nickname) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("Select u from Person u where u.nickname = :nickname");
		query.setParameter("nickname", nickname);
		User user = (User) query.uniqueResult();
		session.close();
		return user;
	}

	/**
	 * Gets an User by email from DataBase
	 * 
	 * @param email
	 *            String of email
	 * @return User Object
	 * @since 1.1
	 */
	public User getUserByMail(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("Select u from Person u where u.mail = :mail");
		query.setParameter("mail", email);
		User user = (User) query.uniqueResult();
		session.close();
		return user;
	}

	/**
	 * checks if Username already exists
	 * @param name
	 * @return true or false
	 */
	public boolean usernameExist(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("Select u from Person u where u.nickname = :nickname");
		query.setParameter("nickname", name);
		User user = (User) query.uniqueResult();
		session.close();
		if (user != null) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * checks if email already exists
	 * @param email
	 * @return true or false
	 */
	public boolean emailExist(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("Select u from Person u where u.mail = :mail");
		query.setParameter("mail", email);
		User user = (User) query.uniqueResult();
		session.close();
		if (user != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * checks if password already exists
	 * @param password
	 * @return true or false
	 */
	public boolean passwordExist(String password) {
		User user;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("Select p from Person p where p.password = :password");
		query.setParameter("password", password);
		user = (User) query.uniqueResult();
		session.close();
		if (user != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gets a List of all Users
	 * 
	 * @return List of User Objects
	 */
	public List<User> getUsers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Person");
		List<User> users = query.list();
		session.close();
		return users;
	}

	/**
	 * Adds a User to DB
	 * 
	 * @param user
	 *            User Object
	 */
	public void addUser(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction ts = session.beginTransaction();
		session.save(user);
		ts.commit();
		session.close();
	}

	/**
	 * removes a user from the DB
	 * 
	 * @param id
	 *            ID of the User
	 */
	public void removeUser(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("Delete from Person where id = :id");
		query.setParameter("id", id).executeUpdate();
		session.close();
	}
}