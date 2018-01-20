package cz17a.gamification.restserver.resource.round;

import java.util.List;

import data.access.HibernateUtil;



public class RoundService {

	@SuppressWarnings("unchecked")
	public static List<Round> getAllRoundsHibernate() {
		return HibernateUtil.getSession().createCriteria(Round.class).list();
	}
	
	public static Round getRoundHibernate(int ID) {
		return (Round) HibernateUtil.getSession().get(Round.class,ID);
	}
	
	
}
