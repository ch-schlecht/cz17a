package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	private int length;
	private int min_participants;
	private int max_participants;
	@OneToMany(mappedBy = "quiz_id")
	private ArrayList<Question> questions;
	@OneToMany(mappedBy = "quiz")
	private List<Participation> participations = new ArrayList<Participation>();
	
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

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
	public void addQuestion(Question question) {
		question.setQuiz(this);
		this.questions.add(question);
	}

}
