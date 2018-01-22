package data.access;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import data.model.Quiz;

public class QuizDAO {
	public Quiz getQuiz(int id) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("select q from Quiz q where q.id = :id");
		query.setParameter("id", id);
		Quiz quiz = (Quiz)query.uniqueResult();
		return quiz;
	}
	
	public List<Quiz> getQuizzes() {
		Session session = HibernateUtil.getSession();
		Query query = session.createSQLQuery("Select * from Quiz");
		List<Quiz> quizzes = query.list();
        session.close();
		return quizzes;
	}
	
	public void addQuiz(Quiz quiz) {
		Session session = HibernateUtil.getSession();
		Transaction ts = session.beginTransaction();
		session.save(quiz);
		ts.commit();
		session.close();
	}
}
