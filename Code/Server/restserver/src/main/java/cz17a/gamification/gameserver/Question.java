package cz17a.gamification.gameserver;

public class Question {
	private int ID;
	private int response_time;
	private String questioning;
	private int dynamic_difficulty;
	private int static_difficulty;
	private String topic;
	
	
	public Question() {}
	
	public Question(int response_time, String questioning, int dynamic_difficulty, int static_difficulty, String topic) {
		this.response_time = response_time;
		this.questioning = questioning;
		this.dynamic_difficulty = dynamic_difficulty;
		this.static_difficulty = static_difficulty;
		this.topic = topic;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getResponse_time() {
		return response_time;
	}

	public void setResponse_time(int response_time) {
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
	
	
}
