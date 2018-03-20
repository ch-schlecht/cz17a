package game;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import data.model.Player;
import data.model.Quiz;

/**
 * Lobby-class Holding waiting Players on Sockets until enough Player registered
 * 
 * @author Michael Fritz
 * @version 1.2
 * @category Sockets
 */
public class Lobby {
	private Quiz quiz;
	private Deque<Player> players = new ArrayDeque<Player>();
	private ServerThreadPool threadPool = null;
	private Thread thread = null;
	int PORT = 0;

	/**
	 * create Lobby for quiz
	 * 
	 * @param quiz
	 *            quiz the Lobby is for
	 * @param firstPlayer
	 *            first Player who registered in this Lobby
	 * @since 1.0
	 */
	public Lobby(Quiz quiz, Player firstPlayer) {
		System.out.println("Create new Lobby");
		this.quiz = quiz;
		// Search free Port on 1810 to 1819
		int port = choosePort(1810, 1819);
		if (port != -1) {
			threadPool = new ServerThreadPool(port,5);
			this.PORT = port;
			thread = new Thread(threadPool);
			thread.start();
			
		
			System.out.println("Thread for ThreadPool started");
			
			this.addPlayer(firstPlayer);
		}
		else {
			System.out.println("No free Port");
		}

	}

	/**
	 * Getter for Quiz
	 * 
	 * @return quiz the Lobby is for
	 * @version 1.0
	 */
	public Quiz getQuiz() {
		return quiz;
	}

	/**
	 * Getter for List of Player
	 * 
	 * @return get all Players
	 * @since 1.0
	 */
	public Deque<Player> getPlayers() {
		return players;
	}

	/**
	 * Setter for Players
	 * 
	 * @param players
	 *            to override existing
	 * @since 1.0
	 */
	public void setPlayers(Deque<Player> players) {
		this.players = players;
	}

	/**
	 * adds a player to the lobby, opens socket connection, updates lobby state (and
	 * sends state to players) if enough players are in the lobby a game will be
	 * started
	 * 
	 * @param p
	 *            Player
	 * @since 1.0
	 */
	public void addPlayer(final Player p) {
		System.out.println("Add Player: "+p.getNickname()+" to "+this.quiz+"-Lobby");
		players.add(p);
		if (hasRequiredPlayers()) {
			openGame();
		}
		else {
			sendLobbyStateToPlayers();
		}
	}

	/**
	 * removes a Player from the lobby, closes socket connection and updates Lobby
	 * state (and sends it to players)
	 * 
	 * @param p
	 * @since 1.0
	 */
	public void removePlayer(Player p) {
		if (players.contains(p)) {
			players.remove(p);
		}
		sendLobbyStateToPlayers();
	}

	/**
	 * checks if there are enough players to start a game
	 * 
	 * @return true or false
	 * @since 1.0
	 */
	public boolean hasRequiredPlayers() {
		if (players.size() >= quiz.getMinParticipants()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * outputs the current lobby state to Clients
	 * 
	 * @since 1.0
	 */
	private void sendLobbyStateToPlayers() {
		List<String> playerNames = new ArrayList<String>();
		for (Player p : players) {
			playerNames.add(p.getNickname());
		}
		String response = "{";
		for (int i = 0; i < playerNames.size(); i++) {
			String name = playerNames.get(i);
			if (i < playerNames.size() - 1) {
				response += name + ",";
			} else {
				response += name + "}$";
			}
		}
		sendMessageToPlayers(response,1);
	}
	
	/**
	 * Sending Messages to Player witrh methode
	 * 0 = add
	 * 1 = override
	 * @param msg
	 * @param methode
	 */
	private void sendMessageToPlayers(String msg, int methode) {
		
		for (int i = 0; i < players.size(); i++) {
	
			if(methode == 0) {
				threadPool.message.set(i, threadPool.message.get(i)+msg);
			}else {
				threadPool.message.set(i, msg);
			}
			System.out.println("Setting ThreadPoolStack "+i+ "to: "+msg); //DEBUG
			

	
		}
	}

	/**
	 * opens a new game by taking players from the lobby sends "start_game" via
	 * Sockets
	 * 
	 * @since 1.0
	 */
	private void openGame() {
		List<Player> playersForGame = new ArrayList<Player>();
		while (playersForGame.size() < quiz.getMinParticipants()) {
			playersForGame.add(players.removeFirst());
			System.out.println("adding Player");
		}
		sendMessageToPlayers("start_game$",0);
		GamePool.startGame(quiz, playersForGame, threadPool);
	}

	/**
	 * Searching a free port between min and max value
	 * 
	 * @param port_min
	 *            min port
	 * @param port_max
	 *            max port
	 * @return free port
	 * @throws IOException
	 *             if no free Port exists
	 * @since 1.2
	 */
	public int choosePort(int port_min, int port_max) {
		for (int port = port_min; port <= port_max; port++) {
			try {
				ServerSocket s = new ServerSocket(port);
				s.close();
				return port;
			} catch (IOException ex) {
				System.out.println("Port:"+port+"is not free");
				continue;
			}
		}
		return -1; // Use, because it is not good, that an exception can be throw in constructor
	}
}
