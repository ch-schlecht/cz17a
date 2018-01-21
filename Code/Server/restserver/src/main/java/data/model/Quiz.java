package data.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import data.access.QuestionDAO;

@Entity
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	private int length;
	private int min_participants;
	private int max_participants;
	@OneToMany(mappedBy = "quiz")
	private List<Question> questions;
		
	public Quiz() {}
	
	public Quiz(String title, int length, int min_participants, int max_participants) {
		this.title = title;
		this.length = length;
		this.min_participants = min_participants;
		this.max_participants = max_participants;
		questions = new ArrayList<Question>();
	}
	
	public List<Question> generate_random_questions(String topic){
		QuestionDAO questionDao = new QuestionDAO();
		Question question;
		while(questions.size() < length) {
			int i = questions.size() + 1;
			question = questionDao.getQuestion(i); //CS: might go wrong, since id_s might not be in order from start, should get the complete list of question and then sort out which fit the topic 
			if(questions.contains(question) == false) {
				if(question.getTopic().equals(topic)) {
					questions.add(question);

				}
			}
		}
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

	public List<Question> getQuestions() {
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
