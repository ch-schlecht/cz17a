package data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Question implements Cloneable, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "response_time")
	private int responseTime;

	@Column(name = "dynamic_difficulty")
	private int dynamicDifficulty;

	@Column(name = "static_difficulty")
	private int staticDifficulty;
	
	private int worth;
	private String topic;
	private String questioning;

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Answer> answers = new ArrayList<Answer>();

	@ManyToOne
	@JoinColumn(name = "quiz_id")
	private Quiz quiz;

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PlayedQuestion> playedQuestions = new ArrayList<PlayedQuestion>();

	/**
	 * Default Constructor
	 */
	public Question() {
	}

	/**
	 * Copy Constructor
	 * 
	 * @param q
	 *            Question Object to copy
	 */
	public Question(Question q) {
		this.id = q.getId();
		this.responseTime = q.getResponseTime();
		this.questioning = q.getQuestioning();
		this.dynamicDifficulty = q.getDynamicDifficulty();
		this.staticDifficulty = q.getStaticDifficulty();
		this.worth = q.getWorth();
		this.topic = q.getTopic();
		this.quiz = new Quiz(q.getQuiz());
	}

	/**
	 * Standard Constructor
	 * 
	 * @param response_time
	 *            response time as Integer
	 * @param questioning
	 *            String of the question
	 * @param dynamicDifficulty
	 *            dynamic difficulty as Integer
	 * @param staticDifficulty
	 *            static difficulty as Integer
	 * @param topic
	 *            String of topic of the question
	 */
	public Question(int response_time, String questioning, int dynamicDifficulty, int staticDifficulty, String topic, int worth) {
		this.responseTime = response_time;
		this.questioning = questioning;
		this.dynamicDifficulty = dynamicDifficulty;
		this.staticDifficulty = staticDifficulty;
		this.topic = topic;
		this.worth = worth;
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

	public int getWorth() {
		return worth;
	}

	public void setWorth(int worth) {
		this.worth = worth;
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
		for (Answer a : answers) {
			a.setQuestion(this);
		}
		this.answers = answers;
	}

	/**
	 * Adds an Answer to the List of Answers of this question
	 * 
	 * @param answer
	 *            Answer Object
	 */
	public void addAnswer(Answer answer) {
		if (answers.contains(answer) == false) {
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
	 * 
	 * @param playedQuestion
	 *            PlayedQuestion Object
	 */
	public void addPlayedQuestion(PlayedQuestion playedQuestion) {
		this.playedQuestions.add(playedQuestion);
	}
}
