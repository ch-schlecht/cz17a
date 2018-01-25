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
	
	public Answer getAnswer(int id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("select a from Answer a where a.id = :id");
		query.setParameter("id", id);
		Answer answer = (Answer)query.uniqueResult();
		session.close();
		return answer;	
	}

}
