package data.access;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import data.model.Answer;

public class AnswerDAO {

	public List<Answer> getAnswers(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Answer");
		List<Answer> answers = query.list();
		session.close();
		return answers;
		
	}

}
