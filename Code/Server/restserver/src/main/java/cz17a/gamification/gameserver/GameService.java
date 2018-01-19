package cz17a.gamification.gameserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import data.access.PlayerDAO;
import data.access.QuizDAO;
import data.model.Player;
import data.model.Quiz;

public class GameService {
	/**
	 * Creates a new Game based on a quiz_id and player_id's
	 * @param quiz_id ID of the quiz that is going to be played
	 * @param player_ids flexible amount of player_ids that participate
	 */
	public void create_game(int quiz_id, int... player_ids) {
		QuizDAO quizdao = new QuizDAO(); //create Data Access Object
		PlayerDAO playerdao = new PlayerDAO(); //see above
		Quiz quiz = quizdao.getQuiz(quiz_id); //create quiz depening on its id
		ArrayList<Player> players = new ArrayList<Player>();
		for(int i = 0; i < player_ids.length; i++) { //flexible amount of parameters are stored in an array, iterate over it
			Player player = playerdao.getPlayer(player_ids[i]); //create a new Player out of every id
			if(player != null) { //only add players that are succefully imported from DB
				players.add(player);
			}
			else {
				throw new RuntimeException("@create_game: Tried to create a Player, but it is null");
			}
		}
		
		if(!(quiz == null || players.isEmpty())) { //only create and start a game if quiz and atleast one player are successfully created
			Game game = new Game(quiz, players);
			game.start();
		}
		else {
			throw new RuntimeException("@create_game: Quiz is null or player list is empty");
		}
		
	}
	

}
