package cz17a.gamification.adminpanel.application;

import org.hibernate.Query;
import org.hibernate.Session;

import data.access.HibernateUtil;
import data.model.Quiz;

public class HibernateQuerys {

	public static int getAdminID(String login) {
		int id = -1;
		Session sessionH = HibernateUtil.getSessionFactory().openSession();
		Query query = sessionH.createQuery("select a.id from Admin a where a.nickname = :nickname");
		query.setParameter("nickname", login);
		
		if(query.uniqueResult() != null) {
			id = (int) query.uniqueResult();
		}
		
		sessionH.close();
		return id;
	}
	
	public static boolean isAdmin(int id) {
		Session sessionH = HibernateUtil.getSessionFactory().openSession();
		Query query = sessionH.createQuery("select a.id from Admin a where a.id=:id");
		query.setParameter("id", id);
		
		if(query.uniqueResult() != null) {
			sessionH.close();
			return true;
		}
		sessionH.close();
		return false;
		
		
		
	}
	
	public static int authAdmin(String login, String password) {
		int id = -1;
		Session sessionH = HibernateUtil.getSessionFactory().openSession();
		Query query = sessionH.createQuery("select a.id from Admin a where a.nickname=:nickname and a.password=:pw");
		query.setParameter("nickname", login);
		query.setParameter("pw", password);
		
		System.out.println("Query: "+query.getQueryString());
		System.out.println("Result: "+query.getFirstResult());
		
		if(query.uniqueResult() != null) {
			id= (int) query.uniqueResult();
		}
		sessionH.close();
		return id;
	}
	
	
	
}
