package data.access;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import data.model.Question;

public class QuestionDAO {
	
	public Question getQuestion(int id) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("select q from Question q where q.id = :id");
		query.setParameter("id", id);
		Question question = (Question) query.uniqueResult();
		return question;
		
	}
	
	public List<Question> getQuestions(){
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Question");
		List<Question> questions = query.list();
		session.close();
		return questions;
		
	}
	
	public void addQuestion(Question question) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.save(question);
		transaction.commit();
		session.close();
	}

}
