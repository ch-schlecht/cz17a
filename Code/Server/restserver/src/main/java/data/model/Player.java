package data.model;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity(name="Player")
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
	
	public Player() {}
	
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
	 * @param question Playedquestion that is to be added
	 */
	public void addPlayedQuestion(PlayedQuestion question) {
		this.playedQuestions.add(question);
	}
	/**
	 * adds a game round to the list of participation of the player
	 * @param round participation
	 */
	public void addRound(Participation round) {
		this.playedRounds.add(round);
	}
	/**
	 * Returns all played questions
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
		return 0.0; //TODO
	}
	
	public Quiz bestQuiz() {
		return null; //TODO
	}
	
	public double averageAnswerTime() {
		return 0.0; //TODO
	}
	
	public double averageScore() {
		return 0.0; //TODO
	}
	
	public int maxScore() {
		int maxScore = 0;
		for(Participation p : playedRounds) {
			maxScore = Math.max(maxScore, p.getScore());
		}
		return maxScore;
	}
	
	public int alltimeScore() {
		int alltimeScore = 0;
		for(Participation p : playedRounds) {
			alltimeScore +=  p.getScore();
		}
		return alltimeScore;
	}
	
	public double rightAnswersRatio() {
		int rightAnswersCount = 0;
		for(PlayedQuestion p : playedQuestions) {
			if(p.getIsCorrect()) {
				rightAnswersCount++;
			}
		}
		double rightAnswersRatio = rightAnswersCount / playedQuestions.size();
		return rightAnswersRatio;
	}
}
