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
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int responseTime;
	private String questioning;
	private int dynamicDifficulty;
	private int staticDifficulty;
	private String topic;
	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
	private List<Answer> answers;
	@ManyToOne
	@JoinColumn(name = "quiz_id")
	private Quiz quiz;
	@OneToMany(mappedBy = "question")
	private List<PlayedQuestion> played_question = new ArrayList<PlayedQuestion>();
	
	
	public Question() {}
	
	public Question(int response_time, String questioning, int dynamicDifficulty, int staticDifficulty, String topic) {
		this.responseTime = response_time;
		this.questioning = questioning;
		this.dynamicDifficulty = dynamicDifficulty;
		this.staticDifficulty = staticDifficulty;
		this.topic = topic;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(int responseTime) {
		this.responseTime = responseTime;
	}

	public String getQuestioning() {
		return questioning;
	}

	public void setQuestioning(String questioning) {
		this.questioning = questioning;
	}

	public int getDynamicDifficulty() {
		return dynamicDifficulty;
	}

	public void setDynamicDifficulty(int dynamicDifficulty) {
		this.dynamicDifficulty = dynamicDifficulty;
	}

	public int getStaticDifficulty() {
		return staticDifficulty;
	}

	public void setStaticDifficulty(int staticDifficulty) {
		this.staticDifficulty = staticDifficulty;
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
	/**
	 * Adds an Answer to the List of Answers of this question
	 * @param answer Answer Object
	 */
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
	
	public List<PlayedQuestion> getPlayedQuestion() {
		return played_question;
	}

	public void setPlayedQuestion(List<PlayedQuestion> playedQuestion) {
		this.played_question = playedQuestion;
	}
	/**
	 * Adds a PlayedQuestion to the List
	 * @param playedQuestion PlayedQuestion Object
	 */
	public void addPlayedQuestion(PlayedQuestion playedQuestion) {
		this.played_question.add(playedQuestion);
	}
}
