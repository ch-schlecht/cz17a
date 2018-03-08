package game;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import data.model.Player;
import data.model.Quiz;
import java.io.OutputStream;
import java.io.PrintWriter;

public class Lobby {
	private Quiz quiz;
	private Deque<Player> players = new ArrayDeque<Player>();
	private Map<Integer, Socket> player_sockets;
	
	public Lobby(Quiz quiz, Player firstPlayer) {
		System.out.println("create Lobby:"+quiz);
		this.quiz = quiz;
		this.player_sockets = new HashMap<Integer, Socket>();
		this.addPlayer(firstPlayer);
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
	
	/**
	 * adds a player to the lobby, opens socket connection, updates lobby state (and sends state to players)
	 * if enough players are in the lobby a game will be started
	 * @param p Player
	 */
	public void addPlayer(Player p)  {

		players.add(p);
		System.out.println("Try to connect to:"+p.getIPAddress()+":"+p.getPort());
		try(Socket socket = new Socket(p.getIPAddress(), p.getPort())) {
			System.out.println("created Socket to:"+p.getIPAddress()+":"+p.getPort());
			player_sockets.put(p.getId(), socket);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		sendLobbyStateToPlayers();
		if(hasRequiredPlayers() == true) {
			openGame();
		}
	}
	/**
	 * removes a Player from the lobby, closes socket connection and updates Lobby state (and sends it to players)
	 * @param p
	 */
	public void removePlayer(Player p) {	
		if(players.contains(p)) {
			players.remove(p);
			try(Socket s = player_sockets.get(p.getId())) {
				s.close();
				player_sockets.remove(p.getId());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		sendLobbyStateToPlayers();
	}
	
	/**
	 * checks if there are enough players to start a game
	 * @return true or false
	 */
	public boolean hasRequiredPlayers() {
		boolean requiredPlayers = players.size() >= quiz.getMinParticipants();
		return requiredPlayers;
	}

	/**
	 * outputs the current lobby state to Clients (Names of players as JSON)
	 */
	private void sendLobbyStateToPlayers() {
		List<String> playerNames = new ArrayList<String>();
		for(Player p : players) {
			playerNames.add(p.getNickname());
		}
		String response = "{";
		for (int i = 0; i < playerNames.size(); i++) {
			String name = playerNames.get(i);
			if(i < playerNames.size() - 1) {
				response += name + ",";
			}
			else {
				response += name + "}";
			}
		}
		for (Entry<Integer, Socket> e : player_sockets.entrySet()) {
			try (Socket socket = e.getValue();) {
				OutputStream out = socket.getOutputStream();
				PrintWriter wr = new PrintWriter(out);
				wr.print(response);
				wr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * opens a new game by taking players from the lobby
	 */
	private void openGame() {
		List<Player> playersForGame = new ArrayList<Player>();
		while(playersForGame.size() < quiz.getMinParticipants()) {
			playersForGame.add(players.removeFirst());
			List<Socket> sockets = new ArrayList<Socket>();
			for(Player p : playersForGame) {
				Socket s = player_sockets.get(p.getId());
				sockets.add(s);
				player_sockets.remove(p.getId());
			}
			GamePool.startGame(quiz, playersForGame, sockets);
		}
	}
}
