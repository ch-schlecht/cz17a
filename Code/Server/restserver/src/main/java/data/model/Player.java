package data.model;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity(name = "Player")
public class Player extends User implements Serializable {
	@Column(name = "playtime_in_minutes")
	private double playtimeInMinutes;
	@OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Participation> playedRounds = new ArrayList<Participation>();
	@OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PlayedQuestion> playedQuestions = new ArrayList<PlayedQuestion>();
	@OneToMany(mappedBy = "winner")
	private List<Round> winnedRounds;
	@Transient
	private InetAddress IPAddress;
	@Transient
	private Integer port;

	public Player() {
	}

	public Player(String mail, String nickname, String password) {
		this.mail = mail;
		this.nickname = nickname;
		this.password = password;
	}

	public double getPlaytimeInMinutes() {
		return playtimeInMinutes;
	}

	public void setPlaytimeInMinutes(double playtimeInMinutes) {
		this.playtimeInMinutes = playtimeInMinutes;
	}

	/**
	 * adds a played question to List of played questions of the player
	 * 
	 * @param question
	 *            Playedquestion that is to be added
	 */
	public void addPlayedQuestion(PlayedQuestion question) {
		this.playedQuestions.add(question);
	}

	/**
	 * adds a game round to the list of participation of the player
	 * 
	 * @param round
	 *            participation
	 */
	public void addRound(Participation round) {
		this.playedRounds.add(round);
	}

	/**
	 * Returns all played questions
	 * 
	 * @return List of played questions in that PlayedQuestion
	 */
	public List<PlayedQuestion> getPlayedQuestion() {
		return playedQuestions;
	}

	public InetAddress getIPAddress() {
		return IPAddress;
	}

	public void setIPAddress(InetAddress iPAddress) {
		IPAddress = iPAddress;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public double winnedRoundsRatio() {
		int winnedRoundsCount = 0;
		for (Participation p : playedRounds) {
			if (p.getRound().getWinner().getId() == id) {
				winnedRoundsCount++;
			}
		}
		double winnedRoundsRatio;
		if (playedQuestions.size() != 0) {
			winnedRoundsRatio = winnedRoundsCount / playedRounds.size();
		} else {
			winnedRoundsRatio = 0.0;
		}
		return winnedRoundsRatio;
	}

	public Quiz bestQuiz() {
		Quiz quiz = new Quiz();
		Round round = new Round();
		int maxScore = maxScore();
		for (Participation p : playedRounds) {
			if (maxScore == p.getScore()) {
				round = p.getRound();
			}
		}
		if (!round.getQuestions().isEmpty()) {
			quiz = round.getQuestions().get(0).getQuiz();
		}
		return quiz;
	}

	public String bestTopic() {
		String topic = bestQuiz().getTitle();
		return topic;
	}

	public double averageAnswerTime() {
		double overallAnswerTime = 0;
		for (PlayedQuestion p : playedQuestions) {
			overallAnswerTime += p.getSpeedInSeconds();
		}
		double averageAnswerTime;
		if (playedQuestions.size() != 0) {
			averageAnswerTime = overallAnswerTime / playedQuestions.size();
		} else {
			averageAnswerTime = 0.0;
		}
		return averageAnswerTime;
	}

	public double averageScore() {
		double averageScore = 0;
		double counter = 0;
		for (Participation p : playedRounds) {
			averageScore += p.getScore();
			counter++;
		}
		if (counter != 0) {
			averageScore /= counter;
		} else {
			averageScore = 0.0;
		}
		averageScore /= counter;
		return averageScore;
	}

	public int maxScore() {
		int maxScore = 0;
		for (Participation p : playedRounds) {
			maxScore = Math.max(maxScore, p.getScore());
		}
		return maxScore;
	}

	public int alltimeScore() {
		int alltimeScore = 0;
		for (Participation p : playedRounds) {
			alltimeScore += p.getScore();
		}
		return alltimeScore;
	}

	public double rightAnswersRatio() {
		int rightAnswersCount = 0;
		for (PlayedQuestion p : playedQuestions) {
			if (p.getIsCorrect()) {
				rightAnswersCount++;
			}
		}
		double rightAnswersRatio;
		if (playedQuestions.size() != 0) {
			rightAnswersRatio = rightAnswersCount / playedQuestions.size();
		} else {
			rightAnswersRatio = 0.0;
		}
		return rightAnswersRatio;
	}

	public double playtimePerDay() {
		double playtimePerDay = 0.0;
		GregorianCalendar today = new GregorianCalendar();
		long difference = today.getTimeInMillis() - registration.getTimeInMillis();
		long daysPlayed = TimeUnit.MILLISECONDS.toDays(difference);
		playtimePerDay = playtimeInMinutes / daysPlayed;
		return playtimePerDay;
	}
}
