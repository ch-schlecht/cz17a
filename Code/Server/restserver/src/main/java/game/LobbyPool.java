package game;

import java.util.HashMap;
import java.util.Map;

import data.access.QuizDAO;
import data.model.Player;
import data.model.Quiz;

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
		lobby.removePlayer(player);
		if(lobby.getPlayers().size() == 0) {
			activeLobbies.remove(quiz_id);
		}
	}
}
