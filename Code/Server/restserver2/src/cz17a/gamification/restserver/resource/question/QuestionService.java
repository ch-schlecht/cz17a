package cz17a.gamification.restserver.resource.question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cz17a.gamification.restserver.sql.ServerManager;
/**
 * Service with all Methods for the QuestionResource
 * @author Michael
 * @version 1.1
 */
public class QuestionService {

	/**
	 * Build an Question out of an ResultSet
	 * @param set return from sql-query
	 * @return Question
	 * @since 1.1
	 */
	public Question buildQuestion(ResultSet set) {
		
		try {
			return new Question(set.getInt(1),set.getFloat(2),set.getString(3),set.getInt(4),set.getInt(5),set.getString(6),set.getInt(7));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Return all Questions 
	 * @return list of all questions
	 * @since 1.0
	 */
	public List<Question> getAllQuestions(){
		List<Question> list = new ArrayList<>();
		
		Statement stmt;
		ResultSet set;
	
		try {
			stmt = ServerManager.query("SELECT * From question");
			set = stmt.getResultSet();
			while(set.next()) {
				list.add(buildQuestion(set));
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	/**
	 * Get all Questions with specfic topic
	 * @param topic to search for
	 * @return list of all questions with topic
	 * @since 1.1
	 */
	public List<Question> getAllQuestionsByTopic(String topic){
		
	List<Question> list = new ArrayList<>();
		
		Statement stmt;
		ResultSet set;
	
		try {
			stmt = ServerManager.query("SELECT * From question WHERE topic = '"+topic+"'");
			set = stmt.getResultSet();
			while(set.next()) {
				list.add(buildQuestion(set));
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * Get all Questions filtert
	 * @param filter sql query after WHERE
	 * @return all Questions who fit the criteriums
	 * @since 1.1
	 */
	public List<Question> getAllQuestionsByFilter(String filter){
		
	List<Question> list = new ArrayList<>();
		
		Statement stmt;
		ResultSet set;
	
		try {
			stmt = ServerManager.query("SELECT * From question WHERE "+filter);
			set = stmt.getResultSet();
			while(set.next()) {
				list.add(buildQuestion(set));
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	
	/**
	 * Get one specified Question
	 * @param id of requested Question
	 * @return Question with id
	 * @since 1.0
	 */
	public Question getQuestion(int id) {
	
		Question res = null;
		Statement stmt;
		ResultSet set;
	
		try {
			stmt = ServerManager.query("SELECT * From question WHERE ID = "+id);
			set = stmt.getResultSet();
			while(set.next()) {
				res = new Question(set.getInt(1),set.getFloat(2),set.getString(3),set.getInt(4),set.getInt(5),set.getString(6),set.getInt(7));
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return res;
	}
	
	/**
	 * Add Question to Database
	 * @param question Question to Add
	 * @return added question
	 * @since 1.0
	 */
	public Question addQuestion(Question question) {
		//generate ID
		int ID = ServerManager.countTable("question")+1;
		ServerManager.update("INSERT INTO question(id,response_time,questioning,dynamic_difficulty,static_difficulty,topic,quiz_id) VALUES ("+ID+","+question.response_time+","+question.questioning+","+question.dynamic_difficulty+","+question.static_difficulty+","+question.topic+","+question.quiz_id+")");
		return question;
	}
	
	/**
	 * Update Qestion
	 * @param question to update
	 * @return updatet question
	 * @since 1.1
	 */
	public Question updateQuestion(Question question) {	
		ServerManager.update("UPDATE Question SET response_time = "+question.response_time+", questioning = '"+question.questioning+"', dynamic_difficulty = "+question.dynamic_difficulty+", static_difficulty = "+question.static_difficulty+", topic = '"+question.topic+"', quiz_id = "+question.quiz_id+" WHERE ID = "+question.getID());
		return question; //TODO get from Server?
	}
	
	/**
	 * remove Question
	 * @param id of question to be removed
	 * @return removed question
	 * @since 1.2
	 */
	public Question removeQuestion(int id) {
		
		ServerManager.update("DELETE FROM question WHERE ID = "+id);
		
		return null; //TODO
	}
	
}
