package game;

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
	
	/**
	 * creates a new lobby if there is no current lobby to a certain quiz (given by quiz_id), 
	 * or adds the players to the existing lobby
	 * @param quiz_id
	 * @param player
	 */
	public static String joinLobby(int quiz_id, Player player) {
		if(activeLobbies.containsKey(quiz_id) == false) {
			Quiz quiz = new QuizDAO().getQuiz(quiz_id);
			Lobby lobby = new Lobby(quiz, player); //TODO
			System.out.println("Added player:"+player.getId()+" to Lobby"+quiz_id);
			activeLobbies.put(quiz_id, lobby);
			return ""+lobby.PORT;
		}
		
		else {
			System.out.println("Added player:"+player.getId()+" to Lobby"+quiz_id);
			activeLobbies.get(quiz_id).addPlayer(player);
			return ""+activeLobbies.get(quiz_id).PORT;
		}
	}
	
	/**
	 * removes Players from a lobby and deletes it if there are no players inside
	 * @param quiz_id
	 * @param player
	 */
	public static void leaveLobby(int quiz_id, Player player) {
		Lobby lobby = activeLobbies.get(quiz_id);
		lobby.removePlayer(player);
		if(lobby.getPlayers().size() == 0) {
			activeLobbies.remove(quiz_id);
		}
	}
	
	
	
	
}
