package data.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int response_time;
	private String questioning;
	private int dynamic_difficulty;
	private int static_difficulty;
	private String topic;
	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
	private List<Answer> answers;
	@ManyToOne
	@JoinColumn(name = "quiz_id")
	private Quiz quiz;
	@OneToMany(mappedBy = "question")
	private List<PlayedQuestion> played_question = new ArrayList<PlayedQuestion>();
	
	
	public Question() {}
	
	public Question(int response_time, String questioning, int dynamic_difficulty, int static_difficulty, String topic) {
		this.response_time = response_time;
		this.questioning = questioning;
		this.dynamic_difficulty = dynamic_difficulty;
		this.static_difficulty = static_difficulty;
		this.topic = topic;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getResponse_time() {
		return response_time;
	}

	public void setResponse_time(int response_time) {
		this.response_time = response_time;
	}

	public String getQuestioning() {
		return questioning;
	}

	public void setQuestioning(String questioning) {
		this.questioning = questioning;
	}

	public int getDynamic_difficulty() {
		return dynamic_difficulty;
	}

	public void setDynamic_difficulty(int dynamic_difficulty) {
		this.dynamic_difficulty = dynamic_difficulty;
	}

	public int getStatic_difficulty() {
		return static_difficulty;
	}

	public void setStatic_difficulty(int static_difficulty) {
		this.static_difficulty = static_difficulty;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<Answer> answers) {
		this.answers = answers;
	}
	
	public void addAnswer(Answer answer) {
		answer.setQuestion(this);
		this.answers.add(answer);
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	
	public List<PlayedQuestion> getPlayed_question() {
		return played_question;
	}

	public void setPlayed_question(List<PlayedQuestion> played_question) {
		this.played_question = played_question;
	}
	
	public void addPlayed_Question(PlayedQuestion played_question) {
		this.played_question.add(played_question);
	}
}
