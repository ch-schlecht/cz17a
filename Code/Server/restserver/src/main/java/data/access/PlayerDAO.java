package data.access;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import data.model.Player;

public class PlayerDAO {
	
	/**
	 * Gets a Player by ID
	 * @param id
	 * @return Player
	 */
	public Player getPlayer(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("select p from Player p where p.id = :id");
		query.setParameter("id", id);
		Player player = (Player) query.uniqueResult();
		session.close();
		return player;
	}

	/**
	 * Gets a List of all Players
	 * @return List<Player>
	 */
	public List<Player> getPlayers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Player");
		List<Player> players = query.list();
		session.close();
		return players;
	}

	/**
	 * Adds a Player to DB
	 * @param player Player
	 */
	public void addPlayer(Player player) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction ts = session.beginTransaction();
		session.save(player);
		ts.commit();
		session.close();
	}
	/**
	 * Deletes a Player from DB
	 * @param player Player
	 */
	public void deletePlayer(Player player) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction ts = session.beginTransaction();
		session.delete(player);
		ts.commit();
		session.close();
	}

	/**
	 * Gets a Player by email
	 * @param email
	 * @return Player
	 */
	public Player getPlayerByMail(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("Select p from Player p where p.mail = :mail");
		query.setParameter("mail", email);
		Player player = (Player) query.uniqueResult();
		session.close();
		return player;
	}

	/**
	 * Gets a Player by nickname
	 * @param nickname
	 * @return Player
	 */
	public Player getPlayer(String nickname) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("Select p from Player p where p.nickname = :nickname");
		query.setParameter("nickname", nickname);
		Player player = (Player) query.uniqueResult();
		session.close();
		return player;
	}

	/**
	 * checks if a name is already used
	 * @param name
	 * @return true or false
	 */
	public boolean usernameExist(String name) {
		Player player = getPlayer(name);
		if (player != null) {
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
		Player player = getPlayerByMail(email);
		if (player != null) {
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
		Player player;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("Select p from Player p where p.password = :password");
		query.setParameter("password", password);
		player = (Player) query.uniqueResult();
		session.close();
		if (player != null) {
			return true;
		} else {
			return false;
		}
	}
}
