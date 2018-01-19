package cz17a.gamification.gameserver;

import java.util.ArrayList;

import model.Player;
import model.Quiz;

public class GameService {
	
	public void create_game() {
		//TODO communicate to REST to get quiz_id, player_id
		Game game = new Game(new Quiz(), new ArrayList<Player>());
		game.start();
	}

}
