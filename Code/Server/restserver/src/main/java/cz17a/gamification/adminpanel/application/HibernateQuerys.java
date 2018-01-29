package cz17a.gamification.adminpanel.application;

import org.hibernate.Query;
import org.hibernate.Session;

import data.access.HibernateUtil;
import data.model.Quiz;

public class HibernateQuerys {

	public static int getAdminID(String login) {
		int id = -1;
		Session sessionH = HibernateUtil.getSessionFactory().openSession();
		Query query = sessionH.createQuery("select id from Admin a where a.nickname = :nickname");
		query.setParameter("nickname", login);
		id = query.getFirstResult();
		sessionH.close();
		return id;
	}
	
	public static boolean isAdmin(int id) {
		int ret = -1;
		Session sessionH = HibernateUtil.getSessionFactory().openSession();
		Query query = sessionH.createQuery("select id from Admin a where a.id = :id");
		query.setParameter("id",id);
		ret = query.getFirstResult();
		sessionH.close();
		if(ret == -1) {
			return false;
		}
		return true;
	}
	
	public static int authAdmin(String login, String password) {
		int id = -1;
		Session sessionH = HibernateUtil.getSessionFactory().openSession();
		Query query = sessionH.createQuery("select id from Admin a where a.nickname = :nickname AND a.password = :pw");
		query.setParameter("nickname", login);
		query.setParameter("pw", password);
		id = query.getFirstResult();
		sessionH.close();
		return id;
	}
	
	
	
}
