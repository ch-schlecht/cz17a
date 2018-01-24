package data.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import data.access.QuestionDAO;

@Entity
@XmlRootElement
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	private int length;
	private int minParticipants;
	private int maxParticipants;
	@OneToMany(mappedBy = "quiz")
	private List<Question> questions;
		
	public Quiz() {}
	
	public Quiz(String title, int length, int minParticipants, int maxParticipants) {
		this.title = title;
		this.length = length;
		this.minParticipants = minParticipants;
		this.maxParticipants = maxParticipants;
		questions = new ArrayList<Question>();
	}
	
	public List<Question> getRandomQuestions() {
		//For AP1 return just the question(s), we have
		return questions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getMinParticipants() {
		return minParticipants;
	}

	public void setMinParticipants(int minParticipants) {
		this.minParticipants = minParticipants;
	}

	public int getMaxParticipants() {
		return maxParticipants;
	}

	public void setMaxParticipants(int maxParticipants) {
		this.maxParticipants = maxParticipants;
	}

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
