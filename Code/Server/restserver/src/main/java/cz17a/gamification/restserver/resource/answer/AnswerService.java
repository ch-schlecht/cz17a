package cz17a.gamification.restserver.resource.answer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import cz17a.gamification.restserver.resource.question.Question;
import cz17a.gamification.restserver.sql.ServerManager;
import access.HibernateUtil;
/**
 * Methods called by AnswerResource requests
 * @author Michael
 * @version 1.2
 */
public class AnswerService {

	public Answer buildAnswer(ResultSet set) {
		try {
			return new Answer(set.getInt("question_id"),set.getInt("ID"),set.getString("inhalt"),set.getBoolean("typ"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Get all Answers
	 * @return list of answers
	 * @since 1.0
	 * @deprecated 1.3
	 */
	public List<Answer> getAllAnswers(){
		ArrayList<Answer> list = new ArrayList<>();
		

		Statement stmt;
		ResultSet set;
	
		try {
			stmt = ServerManager.query("SELECT * From answer");
			set = stmt.getResultSet();
			while(set.next()) {
				list.add(buildAnswer(set)); //TODO
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return list;
	}

	@SuppressWarnings("unchecked")
	/**
	 * Get all Answers with Hibernate
	 * @return list of answers
	 * @since 1.3
	 */
	public List<Answer> getAllAnswersHibernate(){
		return HibernateUtil.getSession().createQuery("from Answer").list();
	}
	
	
	/**
	 * Getting an specific answer (by ID)
	 * @param ID of answer
	 * @return answer with ID
	 * @since 1.0
	 * @deprecated 1.3
	 */
	public Answer getAnswer(int ID) {

		Answer ret = null;
		
		Statement stmt;
		ResultSet set;
	
		try {
			stmt = ServerManager.query("SELECT * From answer WHERE ID="+ID);
			set = stmt.getResultSet();
			while(set.next()) {
				ret = buildAnswer(set);
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		
		
		return ret;
	}


	/**
	 * Getting an specific answer (by ID) with Hibernate
	 * @param ID of answer
	 * @return answer with ID
	 * @since 1.3
	 */
	public Answer getAnswerHibernate(int ID) {
		return new Answer();
	}
	
	/**
	 * Get all Answers with an question_id
	 * @param ID question_id
	 * @return list of answer for question with ID
	 * @since 1.1
	 * @deprecated 1.3
	 */
	public List<Answer> getAnswerByQuestion(int ID) {

		
		ArrayList ret = new ArrayList<Answer>();
		
		Statement stmt;
		ResultSet set;
	
		try {
			stmt = ServerManager.query("SELECT * From answer WHERE question_id="+ID);
			set = stmt.getResultSet();
			while(set.next()) {
				ret.add(buildAnswer(set)); 
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return ret;
	}


	public List<Answer> getAnswerByQuestionHibernate(int ID){
		String hql = "FROM Answer A WHERE A.question_id = "+ID;
		Query query = HibernateUtil.getSession().createQuery(hql);
		List results = query.list();
		return results; //HibernateUtil.getSession(); //TODO
	}
	
	
	/**
	 * Add an Answer
	 * @param answer
	 * @return added Answer
	 * @since 1.0
	 * @deprecated 1.1 user should'nt add Answers
	 */
	public Answer addAnswer(Answer answer) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Add an Answer with Hibernate
	 * @param answer
	 * @return added Answer
	 * @since 1.3
	 */
	public Answer addAnswerHibernate(Answer answer) {
		// TODO Auto-generated method stub
		HibernateUtil.getSession().beginTransaction();
		HibernateUtil.getSession().save(answer);
		HibernateUtil.getSession().getTransaction().commit();
		return answer;
	}
	
	
}
