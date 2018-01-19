package data.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "Quiz_Participation")
public class Participation {
	@Id
	@ManyToOne
	private Player player;
	@Id
	@ManyToOne
	private Quiz quiz;
	private int rank;
	private int score;
	
	public Participation() {}

	public Participation(Player player, Quiz quiz) {
		this.player = player;
		this.quiz = quiz;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

}
