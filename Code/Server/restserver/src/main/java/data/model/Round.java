package data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Round implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Calendar start;
	@Column(name = "end_time")
	private Calendar end;
	@Column(name = "max_score")
	private int maxScore;
	@ManyToOne
	@JoinColumn(name = "winner", nullable = false)
	private Player winner;
	@OneToMany(mappedBy = "round", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PlayedQuestion> playedQuestions = new ArrayList<PlayedQuestion>();
	@OneToMany(mappedBy = "round")
	private List<Participation> participations = new ArrayList<Participation>();
	
	@Transient
	private List<Question> questions;
	
	public Round() {}

	public Round(Calendar start, Calendar end, int maxScore) {
		this.start = start;
		this.end = end;
		this.maxScore = maxScore;
	}
	
	public Round(List<Question> questions, List<Player> players) {
		this.questions = questions;
		for(Player p : players) {
			Participation participation = new Participation(p, this);
			participation.setRank(0);
			participation.setScore(0);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public List<PlayedQuestion> getPlayedQuestions() {
		return playedQuestions;
	}

	public void setPlayedQuestions(List<PlayedQuestion> playedQuestions) {
		this.playedQuestions = playedQuestions;
	}

	public List<Participation> getParticipations() {
		return participations;
	}
	
	public void addPlayedQuestion(PlayedQuestion playedQuestion) {
		playedQuestions.add(playedQuestion);
	}

	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
}
