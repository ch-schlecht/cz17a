package data.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "Played_Question")
public class PlayedQuestion implements Serializable {
	@Id
	@ManyToOne
	private Question question;
	@Id
	@ManyToOne
	private Round round;
	@Id
	@ManyToOne
	private Player player;
	
	private boolean is_jackpot;
	private boolean is_correct;
	private int score;
	private double speed_in_seconds;
	
	public PlayedQuestion() {}
	
	public PlayedQuestion(Question question, Round round, Player player) {
		this.question = question;
		this.round = round;
		this.player = player;
	}
	
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public Round getRound() {
		return round;
	}
	public void setRound(Round round) {
		this.round = round;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public boolean isIs_jackpot() {
		return is_jackpot;
	}
	public void setIs_jackpot(boolean is_jackpot) {
		this.is_jackpot = is_jackpot;
	}
	public boolean isIs_correct() {
		return is_correct;
	}
	public void setIs_correct(boolean is_correct) {
		this.is_correct = is_correct;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public double getSpeed_in_seconds() {
		return speed_in_seconds;
	}
	public void setSpeed_in_seconds(double speed_in_seconds) {
		this.speed_in_seconds = speed_in_seconds;
	}
	
	

}
