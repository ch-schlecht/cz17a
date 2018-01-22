package data.access;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class HibernateUtil {

	private static final Session session;
	private static final SessionFactory sessionFactory;

	static {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			Configuration configuration = new Configuration();
		    configuration.configure("hibernate.cfg.xml");
			sessionFactory = configuration.buildSessionFactory();
			session = sessionFactory.openSession();
		} catch (Throwable ex) {
			System.err.println("SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static Session getSession() {
		return session;
	}

}
