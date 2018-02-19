package game;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import data.access.QuizDAO;
import data.model.Player;
import data.model.Quiz;
/**
 * Pool of every active Lobby
 * @author cz17a
 * @version 1.0
 * @category Lobby
 *
 */
public class LobbyPool {
	public static Map<Integer, Lobby> activeLobbies = new HashMap<Integer, Lobby>();
	
	public static void joinLobby(int quiz_id, Player player) {
		if(activeLobbies.containsKey(quiz_id) == false) {
			Quiz quiz = new QuizDAO().getQuiz(quiz_id);
			Lobby lobby = new Lobby(quiz, player);
			activeLobbies.put(quiz_id, lobby);
		}
		else {
			activeLobbies.get(quiz_id).addPlayer(player);
		}
	}
	
	public static void leaveLobby(int quiz_id, Player player) {
		Lobby lobby = activeLobbies.get(quiz_id);
		try {
			lobby.removePlayer(player);
		} catch (IOException e) {
			System.out.println("Error removing player from Lobby "+quiz_id);
			e.printStackTrace();
		}
		if(lobby.getPlayers().size() == 0) {
			activeLobbies.remove(quiz_id);
		}
	}
	
	
	
}
