package data.access;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import data.model.PasswordCode;
import data.model.Question;

public class PasswordCodeDAO {

	public void addPasswordCode(PasswordCode pwc) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction ts = session.beginTransaction();
		session.save(pwc);
		ts.commit();
		session.close();
	}
	
	public boolean authCode(String mail, String code) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("select q from PasswordCode where q.email = :email and q.code = :code");
		query.setParameter("email", mail);
		query.setParameter("code", code);
		PasswordCode pwc = (PasswordCode) query.uniqueResult();
		session.close();
		
		if(pwc == null) {
			return false;
		}
		return true;
	}
	
}
