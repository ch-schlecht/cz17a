package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Player extends User {
	private double playtime_in_minutes;
	@OneToMany(mappedBy = "player")
	private List<Participation> playedRounds = new ArrayList<>();
	@OneToMany(mappedBy = "player")
	private List<PlayedQuestion> played_questions = new ArrayList<>();
	@OneToMany(mappedBy = "winner")
	private ArrayList<Round> winned_rounds;
	
	public Player() {}
	
	public Player(String mail, String nickname, String password) {
		this.mail = mail;
		this.nickname = nickname;
		this.password = password;
	}

	//Getters and Setters
	public double getPlaytime_in_minutes() {
		return playtime_in_minutes;
	}

	public void setPlaytime_in_minutes(double playtime_in_minutes) {
		this.playtime_in_minutes = playtime_in_minutes;
	}

}
