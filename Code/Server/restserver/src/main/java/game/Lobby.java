package game;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import data.model.Player;
import data.model.Quiz;

public class Lobby {
	private Quiz quiz;
	private Deque<Player> players = new ArrayDeque<Player>();

	
	public Lobby(Quiz quiz, Player firstPlayer) {
		this.quiz = quiz;
		players.add(firstPlayer);
		
	}
	
	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	
	public Deque<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Deque<Player> players) {
		this.players = players;
	}
	
	public void addPlayer(Player p) {
		players.add(p);
		sendLobbyStateToPlayers();
		if(hasRequiredPlayers() == true) {
			openGame();
		}
	}
	
	public void removePlayer(Player p) {	
		if(players.contains(p)) {
			players.remove(p);
		}
		sendLobbyStateToPlayers();
	}

	public boolean hasRequiredPlayers() {
		boolean requiredPlayers = players.size() >= quiz.getMinParticipants();
		return requiredPlayers;
	}
	
	private void sendLobbyStateToPlayers() {
		//Socketkram, Liste der Player an beteiligte Clients schicken.
	}
	
	private void openGame() {
		List<Player> playersForGame = new ArrayList<Player>();
		while(playersForGame.size() < quiz.getMinParticipants()) {
			playersForGame.add(players.removeFirst());
			GamePool.startGame(quiz, playersForGame);
		}
	}
}
