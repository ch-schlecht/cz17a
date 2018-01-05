package cz17a.gamification.restserver.resource.answer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cz17a.gamification.restserver.resource.question.Question;
import cz17a.gamification.restserver.sql.ServerManager;
/**
 * Methods called by AnswerResource requests
 * @author Michael
 * @version 1.1
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
	
	/**
	 * Getting an specific answer (by ID)
	 * @param ID of answer
	 * @return answer with ID
	 * @since 1.0
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
	 * Get all Answers with an question_id
	 * @param ID question_id
	 * @return list of answer for question with ID
	 * @since 1.1
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
	
	
}
