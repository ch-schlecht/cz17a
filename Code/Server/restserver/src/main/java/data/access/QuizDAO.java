package data.access;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import data.model.Question;
import data.model.Quiz;

public class QuizDAO {
	
	/**
	 * gets a quiz by ID
	 * @param id
	 * @return Quiz
	 */
	public Quiz getQuiz(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("select q from Quiz q where q.id = :id");
		query.setParameter("id", id);
		Quiz quiz = (Quiz)query.uniqueResult();
		session.close();
		return quiz;
	}
	
	/**
	 * Gets a quiz by title
	 * @param title
	 * @return Quiz
	 */
	public Quiz getQuiz(String title) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("select q from Quiz q where q.title = :title");
		query.setParameter("title", title);
		Quiz quiz = (Quiz)query.uniqueResult();
		session.close();
		return quiz;
	}
	
	/**
	 * gets a list of all quizzes
	 * @return List<Quiz>
	 */
	public List<Quiz> getQuizzes() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Quiz");
		List<Quiz> quizzes = query.list();
        session.close();
		return quizzes;
	}
	
	/**
	 * adds a quiz to DB
	 * @param quiz
	 */
	public void addQuiz(Quiz quiz) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction ts = session.beginTransaction();
		session.save(quiz);
		ts.commit();
		session.close();
	}
	
	/**
	 * updates an already existing question
	 * @param question
	 */
	public void updateQuestion(Question question) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(question);
		transaction.commit();
		session.close();
	}
}
