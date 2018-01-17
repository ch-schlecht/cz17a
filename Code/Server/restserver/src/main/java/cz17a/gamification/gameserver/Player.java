package cz17a.gamification.gameserver;

public class Player extends User{
	private double playtime_in_minutes;
	
	public Player() {}
	
	public Player(int id, String email, String nickname, String password) {
		super(id, email, nickname, password);
	}

	//Getters and Setters
	public double getPlaytime_in_minutes() {
		return playtime_in_minutes;
	}

	public void setPlaytime_in_minutes(double playtime_in_minutes) {
		this.playtime_in_minutes = playtime_in_minutes;
	}

}
