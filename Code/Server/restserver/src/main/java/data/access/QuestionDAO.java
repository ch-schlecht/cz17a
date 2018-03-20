package data.access;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import data.model.Question;

public class QuestionDAO {

	/**
	 * Gets a question by ID
	 * @param id
	 * @return Question
	 */
	public Question getQuestion(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("select q from Question q where q.id = :id");
		query.setParameter("id", id);
		Question question = (Question) query.uniqueResult();
		System.out.println(question == null);
		session.close();
		return question;
	}
	
	/**
	 * Gets a List of all questions
	 * @return List<Question>
	 */
	public List<Question> getQuestions(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("Select q from Question q");
		List<Question> questions = query.list();
		session.close();
		return questions;
	}
	
	/**
	 * Gets a list of question corresponding to the given topic
	 * @param topic
	 * @return List<Question>
	 */
	@SuppressWarnings("unchecked")
	public List<Question> getQuestionsByTopic(String topic){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("Select q from Question q where q.topic = :topic");
		query.setParameter("topic", topic);
		List<Question> questions = query.list();
		session.close();
		return questions;
	}
	
	/**
	 * Adds a question to DB
	 * @param question
	 */
	public void addQuestion(Question question) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.save(question);
		transaction.commit();
		session.close();
	}
        
        public void updateDynamicDifficulty(int id, int dynamicDifficulty) {
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("Update Question set dynamic_difficulty = :dynamicDifficulty where id = :id");
                query.setParameter("dynamicDifficulty", dynamicDifficulty);
                query.setParameter("id", id);
                session.close();
        }        
}
