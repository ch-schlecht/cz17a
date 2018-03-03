package data.access;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import data.model.Answer;
/**
 * DAO Class for Answers
 * @author cz17a
 * @version 1.0
 * @category DAO
 */
public class AnswerDAO {

	/**
	 * gets all answers from DB
	 * @return List<Answer>
	 */
	public List<Answer> getAnswers(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Answer");
		List<Answer> answers = query.list();
		session.close();
		return answers;	
	}
	
	/**
	 * gets an answer by id
	 * @param id
	 * @return Answer Object
	 */
	public Answer getAnswer(int id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("select a from Answer a where a.id = :id");
		query.setParameter("id", id);
		Answer answer = (Answer)query.uniqueResult();
		session.close();
		return answer;	
	}

}
