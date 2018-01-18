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

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getMin_participants() {
		return min_participants;
	}

	public void setMin_participants(int min_participants) {
		this.min_participants = min_participants;
	}

	public int getMax_participants() {
		return max_participants;
	}

	public void setMax_participants(int max_participants) {
		this.max_participants = max_participants;
	}
	
	

}
