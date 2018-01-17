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
}
