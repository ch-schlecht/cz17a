package data.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

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
public class Round {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Calendar start;
	@Column(name = "end_time")
	private Calendar end;
	private int max_score;
	@ManyToOne
	@JoinColumn(name = "winner", nullable = false)
	private Player winner;
	@OneToMany(mappedBy = "round")
	private List<PlayedQuestion> played_questions = new ArrayList<PlayedQuestion>();
	@OneToMany(mappedBy = "round")
	private List<Participation> participations = new ArrayList<Participation>();
	
	@Transient
	private List<Question> questions;
	@Transient
	private ArrayList<Player> players = new ArrayList<Player>();
	@Transient
	private Question randomQuestion;
	
	public Round() {}
	
	public Round(Calendar start, Calendar end, int max_score) {
		this.start = start;
		this.end = end;
		this.max_score = max_score;
	}
	
	public Round(List<Question> questions, ArrayList<Player> players) {
		this.questions = questions;
		this.players = players;
	}
	
	public void play_question() {
		
	}
	
	private void pick_question() {
		randomQuestion = questions.get(new Random().nextInt(questions.size())); //random question is chosen from the question list
		questions.remove(randomQuestion); //question gets removed to prevent it from appearing more than once
	}
	
	public void addPlayedQuestion(PlayedQuestion played_question) {
		played_question = new PlayedQuestion();
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

	public int getMax_score() {
		return max_score;
	}

	public void setMax_score(int max_score) {
		this.max_score = max_score;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

}
