package cz17a.gamification.gameserver;

import java.util.ArrayList;
import java.util.Calendar;

public class Round {
	private int ID;
	private Calendar start;
	private Calendar end;
	private int max_score;
	private ArrayList<Question> questions;
	
	public Round() {}
	
	public Round(Calendar start, Calendar end, int max_score, Player winner) {
		this.start = start;
		this.end = end;
		this.max_score = max_score;
		winner = null; //default
	}
	
	public void play_question() {
		
	}
	
	private void pick_question() {
		
	}
	
	public void addPlayedQuestion(PlayedQuestion played_question) {
		played_question = new PlayedQuestion();
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Calendar getStart() {
		return start;
	}

	public void setStart(Calendar start) {
		this.start = start;
	}

	public Calendar getEnd() {
		return end;
	}

	public void setEnd(Calendar end) {
		this.end = end;
	}

	public int getMax_score() {
		return max_score;
	}

	public void setMax_score(int max_score) {
		this.max_score = max_score;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
	
	
	

}
