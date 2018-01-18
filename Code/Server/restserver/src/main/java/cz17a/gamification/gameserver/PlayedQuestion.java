package cz17a.gamification.gameserver;

public class PlayedQuestion {
	private boolean is_jackpot;
	private boolean is_correct;
	private int score;
	private double speed_in_seconds;
	
	public boolean isIs_jackpot() {
		return is_jackpot;
	}
	public void setIs_jackpot(boolean is_jackpot) {
		this.is_jackpot = is_jackpot;
	}
	public boolean isIs_correct() {
		return is_correct;
	}
	public void setIs_correct(boolean is_correct) {
		this.is_correct = is_correct;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public double getSpeed_in_seconds() {
		return speed_in_seconds;
	}
	public void setSpeed_in_seconds(double speed_in_seconds) {
		this.speed_in_seconds = speed_in_seconds;
	}
	
	

}
