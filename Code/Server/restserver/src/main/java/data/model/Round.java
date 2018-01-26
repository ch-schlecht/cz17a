package data.model;

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
public class Round {
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
	@Transient
	private List<Player> players = new ArrayList<Player>();
	
	/**
	 * Default Constructor
	 */
	public Round() {}
	
	/**
	 * Constructor with start/end dates and score
	 * @param start Date of start
	 * @param end	Date of end
	 * @param maxScore maximum score
	 */
	public Round(Calendar start, Calendar end, int maxScore) {
		this.start = start;
		this.end = end;
		this.maxScore = maxScore;
	}
	
	/**
	 * Constructor with questions and players
	 * @param questions List of Questions
	 * @param players	List of Players
	 */
	public Round(List<Question> questions, ArrayList<Player> players) {
		this.questions = questions;
		this.players = players;
	}
	
	/**
	 * adds a playedQuestion Object to the List
	 * @param playedQuestion playedQuestion Object
	 */
	public void addPlayedQuestion(PlayedQuestion playedQuestion) {
		playedQuestion = new PlayedQuestion(); //remove this in future since instanciated object will be given as parameters,
											   //but for now doesnt matter since function is not in use yet
		//playedQuestions.add(playedQuestion);
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

}
