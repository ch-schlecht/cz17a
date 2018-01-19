package cz17a.gamification.gameserver;

import java.util.ArrayList;

import data.model.Player;
import data.model.Quiz;
import data.model.Round;

public class Game {
	private Round round;
	
	public Round getRound() {
		return round;
	}
	public void setRound(Round round) {
		this.round = round;
	}
	public Game(Quiz quiz, ArrayList<Player> players) {
		
	}
	/**
	 * Initiate a new Game
	 */
	public void start() {
		
	}
	/**
	 * saves the current Game to DB, to be called after the Game has finished
	 */
	public void saveRound() {
		
	}

}
