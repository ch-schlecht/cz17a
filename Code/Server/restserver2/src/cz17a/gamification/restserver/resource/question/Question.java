package cz17a.gamification.restserver.resource.question;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Question Class
 * @author Michael
 * @version 1.0
 */
@XmlRootElement
public class Question {

	int ID;
	float response_time;
	String questioning;
	int dynamic_difficulty;
	int static_difficulty;
	String topic;
	int quiz_id;
	
	/**
	 * normal contructor for XML-Generion
	 * @since 1.0
	 */
	public Question() {
		
	}
	
	/**
	 * Constructor
	 * @param ID 
	 * @param response_time
	 * @param questioning
	 * @param dynamic_difficulty
	 * @param static_difficulty
	 * @param topic
	 * @param quiz_id
	 * @since 1.0
	 */
	
	public Question(int ID, float response_time, String questioning, int dynamic_difficulty, int static_difficulty,String topic, int quiz_id) {
		this.ID = ID;
		this.response_time = response_time;
		this.questioning = questioning;
		this.dynamic_difficulty = dynamic_difficulty;
		this.static_difficulty = static_difficulty;
		this.topic = topic;
		this.quiz_id = quiz_id;
	}
	
	
	//GETTER AND SETTER
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public float getResponse_time() {
		return response_time;
	}
	public void setResponse_time(float response_time) {
		this.response_time = response_time;
	}
	public String getQuestioning() {
		return questioning;
	}
	public void setQuestioning(String questioning) {
		this.questioning = questioning;
	}
	public int getDynamic_difficulty() {
		return dynamic_difficulty;
	}
	public void setDynamic_difficulty(int dynamic_difficulty) {
		this.dynamic_difficulty = dynamic_difficulty;
	}
	public int getStatic_difficulty() {
		return static_difficulty;
	}
	public void setStatic_difficulty(int static_difficulty) {
		this.static_difficulty = static_difficulty;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public int getQuiz_id() {
		return quiz_id;
	}
	public void setQuiz_id(int quiz_id) {
		this.quiz_id = quiz_id;
	}
	
	
}
