package data.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity(name="Quiz")
@XmlRootElement
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "min_participants")
	private int minParticipants;

	@Column(name = "max_participants")
	private int maxParticipants;

	@OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Question> questions = new ArrayList<Question>();

	private String title;
	private int length;

	/**
	 * Default Constructor
	 */
	public Quiz() {
	}

	/**
	 * Copy Constructor
	 * 
	 * @param q
	 *            Quiz Object to be copied
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
	 * 
	 * @param title
	 *            String of title of the quiz
	 * @param length
	 *            number of questions in that quiz
	 * @param minParticipants
	 *            minimum number of participants
	 * @param maxParticipants
	 *            maximum number of participants
	 */
	public Quiz(String title, int length, int minParticipants, int maxParticipants) {
		this.title = title;
		this.length = length;
		this.minParticipants = minParticipants;
		this.maxParticipants = maxParticipants;
		questions = new ArrayList<Question>();
	}

	/**
	 * Produces a list of random questions (amount is equal to the length attribute
	 * of the quiz) associated with the quiz. Questions that have a higher amount of wrong answers are preferred, 
	 * but the choosing process persists to be random.
	 * see git issue for detailed description of the algorithm.
	 * 
	 * @return List of Question
	 */
	public List<Question> getRandomQuestions() {
		/*
		List<Question> chooseList = this.getQuestions();
		List<Question> returnList = new ArrayList<Question>();
		Collections.sort(chooseList);
		outer: while(returnList.size() < this.length) { //do the iteration until we have enough questions
			for(int i = 0; i < chooseList.size(); i++) { //iterate over all questions
				int threshold = (int) Math.random() * 5; //magic number: this is the maximum value for dynamic difficulty, to be changed however we decide
				if(chooseList.get(i).getDynamicDifficulty() > threshold || i == (chooseList.size() - 1) ) { //if the difficulty is greater than the random value OR the question is the last in the list
					if(!returnList.contains(chooseList.get(i))) { //if it was not already taken
						returnList.add(chooseList.get(i)); //then take this question
						continue outer; //once a question was taken, go back to the outer loop, because every iteration should only add one question
					}
				}
			}
		}
		
		return returnList;
		*/
		List<Question> questionList = new ArrayList<Question>();
		for(int i = 0; i < 10; i++) {
			questionList.add(questions.get(i));
		}
		return questionList;
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
		for (Question q : questions) {
			q.setQuiz(this);
		}
		this.questions = questions;
	}

	public void addQuestion(Question question) {
		if (questions.contains(question) != true) {
			question.setQuiz(this);
			this.questions.add(question);
		}
	}

}
