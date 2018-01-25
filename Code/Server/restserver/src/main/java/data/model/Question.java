package data.model;

import java.util.ArrayList;
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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
public class Question implements Cloneable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "response_time")
	private int responseTime;
	
	@Column(name = "dynamic_difficulty")
	private int dynamicDifficulty;
	
	@Column(name = "static_difficulty")
	private int staticDifficulty;
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
	private List<Answer> answers = new ArrayList<Answer>();
	
	@ManyToOne
	@JoinColumn(name = "quiz_id")
	private Quiz quiz;
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PlayedQuestion> playedQuestions = new ArrayList<PlayedQuestion>();
	
	private String topic;
	private String questioning;
	
	public Question() {}
	
	public Question(int response_time, String questioning, int dynamicDifficulty, int staticDifficulty, String topic) {
		this.responseTime = response_time;
		this.questioning = questioning;
		this.dynamicDifficulty = dynamicDifficulty;
		this.staticDifficulty = staticDifficulty;
		this.topic = topic;
	}

	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@XmlElement
	public int getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(int responseTime) {
		this.responseTime = responseTime;
	}

	@XmlElement
	public String getQuestioning() {
		return questioning;
	}

	public void setQuestioning(String questioning) {
		this.questioning = questioning;
	}

	@XmlElement
	public int getDynamicDifficulty() {
		return dynamicDifficulty;
	}

	public void setDynamicDifficulty(int dynamicDifficulty) {
		this.dynamicDifficulty = dynamicDifficulty;
	}

	@XmlElement
	public int getStaticDifficulty() {
		return staticDifficulty;
	}

	public void setStaticDifficulty(int staticDifficulty) {
		this.staticDifficulty = staticDifficulty;
	}

	@XmlElement
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	@XmlElement
	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	/**
	 * Adds an Answer to the List of Answers of this question
	 * @param answer Answer Object
	 */
	public void addAnswer(Answer answer) {
		if(answers.contains(answer) == false) {
			this.answers.add(answer);
		}
		answer.setQuestion(this);
	}

	@XmlTransient
	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	
	@XmlTransient
	public List<PlayedQuestion> getPlayedQuestion() {
		return playedQuestions;
	}

	public void setPlayedQuestion(List<PlayedQuestion> playedQuestion) {
		this.playedQuestions = playedQuestion;
	}
	/**
	 * Adds a PlayedQuestion to the List
	 * @param playedQuestion PlayedQuestion Object
	 */
	public void addPlayedQuestion(PlayedQuestion playedQuestion) {
		this.playedQuestions.add(playedQuestion);
	}
}
