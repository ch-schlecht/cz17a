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
		Player player = (Player)query.uniqueResult();
		session.close();
		return player;
	}
	
	public List<Player> getPlayers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Player");
		List<Player> players =  query.list();
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
}
