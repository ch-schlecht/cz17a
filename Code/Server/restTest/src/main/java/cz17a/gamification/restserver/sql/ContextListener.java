package cz17a.gamification.restserver.sql;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.HibernateUtil;

public class ContextListener implements ServletContextListener{

	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//ServerManager.connect();
		//Session session = HibernateUtil.getSessionFactory().openSession();
	}


}
