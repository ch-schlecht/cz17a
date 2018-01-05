package cz17a.gamification.restserver.resource.answer;
/**
 * Holding Information about an Answer
 * @author Michael
 * @since 1.0
 */
public class Answer {

	int question_id;
	int ID;
	String content;
	boolean type;
	
	public Answer() {
		
	}
	
	public Answer(int question_id, int ID, String content, boolean type) {
		this.question_id = question_id;
		this.ID = ID;
		this.content = content;
		this.type = type;
	}
	
	
	//GETTER AND SETTER
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}
	
}
