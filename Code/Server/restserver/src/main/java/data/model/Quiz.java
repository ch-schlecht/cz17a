package data.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "min_participants")
	private int minParticipants;
	
	@Column(name = "max_participants")
	private int maxParticipants;
	
	@OneToMany(mappedBy = "quiz")
	private List<Question> questions = new ArrayList<Question>();
	
	private String title;
	private int length;
	
	/**
	 * Default Constructor
	 */
	public Quiz() {}
	
	/**
	 * Copy Constructor
	 * @param q Quiz Object to be copied
	 */
	public Quiz(Quiz q) {
		this.id = q.getId();
		this.title = q.getTitle();
		this.length = q.getLength();
		this.minParticipants = q.getMinParticipants();
		this.maxParticipants = q.getMaxParticipants();
	}
	
	/**
	 * Standard Constructor
	 * @param title String of title of the quiz
	 * @param length number of questions in that quiz
	 * @param minParticipants minimum number of participants
	 * @param maxParticipants maximum number of participants
	 */
	public Quiz(String title, int length, int minParticipants, int maxParticipants) {
		this.title = title;
		this.length = length;
		this.minParticipants = minParticipants;
		this.maxParticipants = maxParticipants;
		questions = new ArrayList<Question>();
	}
	/**
	 * Produces a list of random questions (amount is equal to the length attribute of the quiz) associated with the quiz.
	 * AP1: just returns all questions of the quiz since the DB only has test questions
	 * @return List of Question
	 */
	public List<Question> getRandomQuestions() {
		//For AP1 return just the question(s), we have
		return questions;
	}

	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@XmlElement
	public int getMinParticipants() {
		return minParticipants;
	}

	public void setMinParticipants(int minParticipants) {
		this.minParticipants = minParticipants;
	}

	@XmlElement
	public int getMaxParticipants() {
		return maxParticipants;
	}

	public void setMaxParticipants(int maxParticipants) {
		this.maxParticipants = maxParticipants;
	}

	@XmlTransient
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	/**
	 * Adds a question to the quiz
	 * @param question Question Object
	 */
	public void addQuestion(Question question) {
		question.setQuiz(this);
		this.questions.add(question);
	}

}
