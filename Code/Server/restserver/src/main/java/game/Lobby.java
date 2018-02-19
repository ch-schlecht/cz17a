package game;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.model.Player;
import data.model.Quiz;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Lobby {
	private Quiz quiz;
	private Deque<Player> players = new ArrayDeque<Player>();
	private Map<Integer, Socket> player_sockets;
	
	public Lobby(Quiz quiz, Player firstPlayer) {
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
	
	public void addPlayer(Player p) throws IOException {
		players.add(p);
		try(Socket socket = new Socket(p.getIPAddress(), p.getPort())) {
			player_sockets.put(p.getId(), socket);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		sendLobbyStateToPlayers();
		if(hasRequiredPlayers() == true) {
			openGame();
		}
	}
	
	public void removePlayer(Player p) throws IOException {	
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
