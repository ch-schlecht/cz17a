package data.access;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import data.model.Round;

public class RoundDAO {
	
	/**
	 * gets a round by ID
	 * @param id
	 * @return Round
	 */
	public Round getRound(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("select r from Round p where r.id = :id");
		query.setParameter("id", id);
		Round round = (Round)query.uniqueResult();
		session.close();
		return round;
	}
	
	/**
	 * gets a list of all Rounds
	 * @return List<Round>
	 */
	public List<Round> getPlayers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Round");
		List<Round> rounds =  query.list();
        session.close();
		return rounds;
	}
	
	/**
	 * adds a round to DB
	 * @param round
	 */
	public void addRound(Round round) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction ts = session.beginTransaction();
		session.save(round);
		ts.commit();
		session.close();
	}
}
