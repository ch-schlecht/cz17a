package data.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Player extends User {
	private double playtimeInMinutes;
	@OneToMany(mappedBy = "player")
	private List<Participation> played_rounds = new ArrayList<Participation>();
	@OneToMany(mappedBy = "player")
	private List<PlayedQuestion> played_questions = new ArrayList<PlayedQuestion>();
	@OneToMany(mappedBy = "winner")
	private List<Round> winned_rounds;
	
	public Player() {}
	
	public Player(String mail, String nickname, String password) {
		this.mail = mail;
		this.nickname = nickname;
		this.password = password;
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
		this.played_questions.add(question);
	}
	/**
	 * adds a game round to the list of participation of the player
	 * @param round participation
	 */
	public void addRound(Participation round) {
		this.played_rounds.add(round);
	}
	/**
	 * Returns all played questions
	 * @return List of played questions in that PlayedQuestion
	 */
	public List<PlayedQuestion> getPlayedQuestion() {
		return played_questions;
	}

}
