package data.access;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import data.model.Player;

public class PlayerDAO {
	public Player getPlayer(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("select p from Player p where p.id = :id");
		query.setParameter("id", id);
		Player player = (Player) query.uniqueResult();
		session.close();
		return player;
	}

	public List<Player> getPlayers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Player");
		List<Player> players = query.list();
		session.close();
		return players;
	}

	public void addPlayer(Player player) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction ts = session.beginTransaction();
		session.save(player);
		ts.commit();
		session.close();
	}
	
	public void deletePlayer(Player player) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction ts = session.beginTransaction();
		session.delete(player);
		ts.commit();
		session.close();
	}

	public Player getPlayerByMail(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("Select p from Player p where p.mail = :mail");
		query.setParameter("mail", email);
		Player player = (Player) query.uniqueResult();
		session.close();
		return player;
	}

	public Player getPlayer(String nickname) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("Select p from Player p where p.nickname = :nickname");
		query.setParameter("nickname", nickname);
		Player player = (Player) query.uniqueResult();
		session.close();
		return player;
	}

	public boolean usernameExist(String name) {
		Player player = getPlayer(name);
		if (player != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean emailExist(String email) {
		Player player = getPlayerByMail(email);
		if (player != null) {
			return true;
		} else {
			return false;
		}
	}
	
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
