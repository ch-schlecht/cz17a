package data.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Player extends User {
	@Column(name = "playtime_in_minutes")
	private double playtimeInMinutes;
	@OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Participation> playedRounds = new ArrayList<Participation>();
	@OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PlayedQuestion> playedQuestions = new ArrayList<PlayedQuestion>();
	@OneToMany(mappedBy = "winner")
	private List<Round> winnedRounds;
	
	/**
	 * Default Constructor
	 */
	public Player() {}
	
	/**
	 * Standard Constructor
	 * @param mail String of email
	 * @param nickname String of nickname
	 * @param password String of password
	 */
	public Player(String mail, String nickname, String password) {
		this.mail = mail;
		this.nickname = nickname;
		this.password = password; //TODO hash this password
	}

	//Getters and Setters
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

}
