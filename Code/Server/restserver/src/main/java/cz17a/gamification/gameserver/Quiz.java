package cz17a.gamification.gameserver;

import java.util.ArrayList;

public class Quiz {
	private int ID;
	private String title;
	private int length;
	private int min_participants;
	private int max_participants;
	
	public Quiz() {}
	
	public Quiz(String title, int length, int min_participants, int max_participants) {
		this.title = title;
		this.length = length;
		this.min_participants = min_participants;
		this.max_participants = max_participants;
	}
	
	public ArrayList<Question> generate_random_questions(){
		return null; //default
	}

}
