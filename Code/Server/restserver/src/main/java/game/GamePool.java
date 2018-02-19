package game;

import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import data.model.Player;
import data.model.Quiz;

public class GamePool {
	public static Map<Integer, Game> games = new HashMap<Integer, Game>();
	
	public static void startGame(Quiz quiz, List<Player> players, List<Socket> sockets) {
		int id;
		for(id = 0; id <= 1000; id++) {
			if(games.containsKey(id) == false) {
				Game game = new Game(id, quiz, players, sockets);
				games.put(id, game);
			}
		}
		games.get(id).start();
	}
	
	public static void removeGame(int id) {
		if(games.containsKey(id)) {
			games.remove(id);
		}
	}
}
