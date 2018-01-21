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
		round = new Round(quiz.getRandomQuestions(), players);
		
	}
	/**
	 * Initiate a new Game
	 */
	public void start() {
		int amountOfQuestions = round.getQuestions().size(); //set this once, because in round the questions get removed from the arrayList once they are played
		for(int i = 0; i< amountOfQuestions; i++) { //play exactly as often as there are questions within the round
			round.play_question(); //not quite sure if this is the correct approach?!
		}
	}
	/**
	 * saves the current Game to DB, to be called after the Game has finished
	 */
	public void saveRound() {
		
	}

}
