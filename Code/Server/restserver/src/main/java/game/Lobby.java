package game;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import data.model.Player;
import data.model.Quiz;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Lobby {
	int port = 50000;
	private Quiz quiz;
	private Deque<Player> players = new ArrayDeque<Player>();

	private ServerThreadPool threadPool;
	
	
	public Lobby(Quiz quiz, Player firstPlayer) {
		this.quiz = quiz;
		players.add(firstPlayer);
		threadPool = new ServerThreadPool(port);
		new Thread(threadPool).start();
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
	
	public void addPlayer(Player p) throws IOException {
		players.add(p);
		sendLobbyStateToPlayers();
		if(hasRequiredPlayers() == true) {
			openGame();
		}
	}
	
	public void removePlayer(Player p) throws IOException {	
		if(players.contains(p)) {
			players.remove(p);
			if(players.isEmpty()) {
				destroyLobby();
			}
		}
		sendLobbyStateToPlayers();
	}

	public int getPort() {
		return port;
	}
	
	public boolean destroyLobby(){
	
		threadPool.stop();
		return true;
	}

		
	public boolean hasRequiredPlayers() {
		boolean requiredPlayers = players.size() >= quiz.getMinParticipants();
		return requiredPlayers;
	}
	
	private void sendLobbyStateToPlayers() throws IOException {
		for(Player p : players) {
                        int port = p.getPort();
                        InetAddress ip = p.getIPAddress();
                    
                        try(Socket socket = new Socket(ip, port)) {
                                OutputStream out = socket.getOutputStream();
                                out.write(players.size());
                        } catch (IOException ex) {
                                ex.printStackTrace();
                        }
                }
	}
	
	private void openGame() {
		List<Player> playersForGame = new ArrayList<Player>();
		while(playersForGame.size() < quiz.getMinParticipants()) {
			playersForGame.add(players.removeFirst());
			GamePool.startGame(quiz, playersForGame);
		}
	}
}
