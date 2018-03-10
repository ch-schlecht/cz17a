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

public class Lobby {
	private Quiz quiz;
	private Deque<Player> players = new ArrayDeque<Player>();
	private ServerSocket serverSocket = null;
	private Map<Integer, Socket> player_sockets;;
	int PORT = 0;
	
	public Lobby(Quiz quiz, Player firstPlayer) {
		System.out.println("create Lobby for:"+quiz.getTitle());
		this.quiz = quiz;
		try {
			serverSocket = create(40000,40010);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.PORT = serverSocket.getLocalPort();
		System.out.println("createt on: "+PORT);
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


		PlayerThread thread = new PlayerThread(p) {

			
			@Override
			public void run() {

	        	System.out.println("open for client at port: "+serverSocket.getLocalPort()+"@"+serverSocket.getInetAddress().toString());
	        	try(Socket socket = serverSocket.accept()) {	
	    			System.out.println("accepted Client");
	    			player_sockets.put(p.getId(), socket);
	    			//socket.getOutputStream().write("200".getBytes()); //TODO

	    			sendLobbyStateToPlayers();
	    			if(hasRequiredPlayers() == true) {
	    				System.out.println("Lobby Full!");
	    				openGame();
	    			}
	   
	    		} catch (IOException ex) {
	    			ex.printStackTrace();
	    		}
	        	
			}
		};
		
		new Thread(thread).start();
		

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
		if(players.size() >= quiz.getMinParticipants())
			return true;
		return false;
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
			try  {
				Socket socket = e.getValue();
				OutputStream out = socket.getOutputStream();
				PrintWriter wr = new PrintWriter(out);
				wr.println(response);
				wr.flush();
				//wr.close();
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
			
			
			for (Entry<Integer, Socket> e : player_sockets.entrySet()) {
				try  {
					Socket socket = e.getValue();
					OutputStream out = socket.getOutputStream();
					PrintWriter wr = new PrintWriter(out);
					wr.println("start_game");
					wr.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			
			
			
			for(Player p : playersForGame) {
				Socket s = player_sockets.get(p.getId());
				sockets.add(s);
				player_sockets.remove(p.getId());
			}
			
			/**
			for (Socket socket : sockets) {
				try {
					OutputStream out = socket.getOutputStream();
					PrintWriter wr = new PrintWriter(out);
					wr.print("start_game");
					wr.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}**/
			
			
			
			
			GamePool.startGame(quiz, playersForGame, sockets);
		}
	}
	
	
	public ServerSocket create(int port_min, int port_max) throws IOException {
	    for (int port = port_min; port <= port_max; port++) {
	        try {
	            return new ServerSocket(port);
	        } catch (IOException ex) {
	        	System.out.println("port "+port+" not free");
	            continue; // try next port
	        }
	    }

	    // if the program gets here, no port in the range was found
	    throw new IOException("no free port found");
	}
	
	
	
	
}
