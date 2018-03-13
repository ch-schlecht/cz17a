package game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import data.model.Player;
import data.model.Quiz;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Lobby-class Holding waiting Players on Sockets until enough Player registered
 * @author Michael Fritz
 * @version 1.2
 * @category Sockets
 */
public class Lobby {
	private Quiz quiz;
	private Deque<Player> players = new ArrayDeque<Player>();
	//private ServerSocket serverSocket = null;
	private ServerThreadPool threadPool = null;
	private Thread thread = null;
	//private Map<Integer, Socket> player_sockets;;
	int PORT = 0;
	
	/**
	 * create Lobby for quiz
	 * @param quiz quiz the Lobby is for
	 * @param firstPlayer first Player who registered in this Lobby
	 * @since 1.0
	 */
	public Lobby(Quiz quiz, Player firstPlayer) {
		System.out.println("create Lobby for:"+quiz.getTitle());
		this.quiz = quiz;
		try {
			threadPool =new ServerThreadPool(create(1810,1819)); //Search free Port on 1810 to 1819
			thread = new Thread(threadPool);
			thread.start();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		this.PORT = threadPool.PORT; //Set Port
		System.out.println("createt on: "+PORT);
		//this.player_sockets = new HashMap<Integer, Socket>(); //init Player Map
		this.addPlayer(firstPlayer); //add first Player to Lobby
	}
	
	/**
	 * Getter for Quiz
	 * @return quiz the Lobby is for
	 * @version 1.0
	 */
	public Quiz getQuiz() {
		return quiz;
	}

	/**
	 * Setter for Quiz
	 * @param quiz to change to
	 * @deprecated  1.2 Quiz shouldn't be changed after Lobby is created
	 * @since 1.0
	 */
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	
	/**
	 * Getter for List of Player
	 * @return get all Players
	 * @since 1.0
	 */
	public Deque<Player> getPlayers() {
		return players;
	}

	/**
	 * Setter for Players
	 * @param players to override existing
	 * @since 1.0 
	 */
	public void setPlayers(Deque<Player> players) {
		this.players = players;
	}
	

	
	/**
	 * adds a player to the lobby, opens socket connection, updates lobby state (and sends state to players)
	 * if enough players are in the lobby a game will be started
	 * @param p Player
	 * @since 1.0
	 */
	public void addPlayer(final Player p)  {

		players.add(p);
		sendLobbyStateToPlayers();

	}
	/**
	 * removes a Player from the lobby, closes socket connection and updates Lobby state (and sends it to players)
	 * @param p
	 * @since 1.0
	 * @deprecated
	 */
	public void removePlayer(Player p) {	
		if(players.contains(p)) {
			players.remove(p);
			
			/**
			try(Socket s = player_sockets.get(p.getId())) {
				s.close();
				player_sockets.remove(p.getId());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			**/
		}
		sendLobbyStateToPlayers();
	}
	
	/**
	 * checks if there are enough players to start a game
	 * @return true or false
	 * @since 1.0
	 */
	public boolean hasRequiredPlayers() {
		if(players.size() >= quiz.getMinParticipants())
			return true;
		return false;
	}

	/**
	 * outputs the current lobby state to Clients (Names of players as JSON)
	 * @since 1.0
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
		
		for(int i = 0; i < 5; i++) {
			threadPool.message.set(i, response);
		}
		/*
		for (Entry<Integer, Socket> e : player_sockets.entrySet()) {
			try  {
				Socket socket = e.getValue();
				OutputStream out = socket.getOutputStream();
				PrintWriter wr = new PrintWriter(out);
				System.out.print("sneding: "+response+" to "+e.getKey());
				wr.println(response);
				wr.flush();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		*/
		
	}

	/**
	 * opens a new game by taking players from the lobby
	 * sends "start_game" via Sockets
	 * @since 1.0
	 */
	private void openGame() {
		List<Player> playersForGame = new ArrayList<Player>();
		while(playersForGame.size() < quiz.getMinParticipants()) {
			playersForGame.add(players.removeFirst());
			
		
			for(int i = 0; i < 5; i++) {
				threadPool.message.set(i, "start_game");
			}
			
			/*
			for (Entry<Integer, Socket> e : player_sockets.entrySet()) {
				try  {
					Socket socket = e.getValue();
					OutputStream out = socket.getOutputStream();
					PrintWriter wr = new PrintWriter(out);
					wr.println("start_game");
					wr.flush();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			*/
			
			
			
			GamePool.startGame(quiz, playersForGame, threadPool);
		}
	}
	
	
	/**
	 * Searching a free port between min and max value
	 * @param port_min min port
	 * @param port_max max port
	 * @return free port
	 * @throws IOException if no free Port exists
	 * @since 1.2
	 */
	public int create(int port_min, int port_max) throws IOException {
	    for (int port = port_min; port <= port_max; port++) {
	        try {
	            ServerSocket s = new ServerSocket(port);
	            s.close();
	            return port;
	        } catch (IOException ex) {
	        	System.out.println("port "+port+" not free");
	            continue; // try next port
	        }
	    }

	    // if the program gets here, no port in the range was found
	    throw new IOException("no free port found");
	}
	
	
	
}
