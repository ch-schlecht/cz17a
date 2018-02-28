package game;

import data.access.RoundDAO;
import data.model.Player;
import data.model.Question;
import data.model.Quiz;
import data.model.Round;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.eclipse.persistence.jaxb.MarshallerProperties;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Game {
	private int id;
	/**
	 * Id's of the players, which have answered the current question
	 */
	private Set<Integer> waitingPlayers = new HashSet<Integer>();
	private Round round;
	private Jackpot jackpot;
	/**
	 * Counts how many questions were played
	 */
	private int playedQuestions;
	private List<Socket> playerSockets;
	/**
	 * Holds score for every player. Key is the id of a player
	 */
	private Map<Integer, Integer> scoreboard = new HashMap<Integer, Integer>();

	public Game(int id, Quiz quiz, List<Player> players, List<Socket> sockets) {
		this.id = id;
		this.jackpot = new Jackpot();
		this.playedQuestions = 0;
		this.round = new Round(quiz.getRandomQuestions(), players);
		this.playerSockets = sockets;
		for(Player p : players) {
			scoreboard.put(p.getId(), 0);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Integer> getWaitingPlayers() {
		return waitingPlayers;
	}

	public void setWaitingPlayers(Set<Integer> waitingPlayers) {
		this.waitingPlayers = waitingPlayers;
	}

	public void addWaitingPlayer(int player_id) {
		waitingPlayers.add(player_id);
		if (hasAllPlayersAnswered() == true) {
			waitingPlayers.clear();
			startNextQuestion();
		}
	}

	public Round getRound() {
		return round;
	}

	public void setRound(Round round) {
		this.round = round;
	}

	public Jackpot getJackpot() {
		return jackpot;
	}

	public void setJackpot(Jackpot jackpot) {
		this.jackpot = jackpot;
	}

	public int getPlayedQuestions() {
		return playedQuestions;
	}

	public void setPlayedQuestions(int playedQuestions) {
		this.playedQuestions = playedQuestions;
	}

	public List<Socket> getPlayerSockets() {
		return playerSockets;
	}

	public void setPlayerSockets(List<Socket> player_sockets) {
		this.playerSockets = player_sockets;
	}

	public Map<Integer, Integer> getScoreboard() {
		return scoreboard;
	}

	public void setScoreboard(Map<Integer, Integer> scoreboard) {
		this.scoreboard = scoreboard;
	}

	public void start() {
		for (Socket s : playerSockets) {
			try (OutputStream out = s.getOutputStream();) {
				String message = Integer.toString(id);
				out.write(message.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		startRound();
	}

	private void startRound() {
		List<Question> questions = getRound().getQuestions();
		try {
			JAXBContext jc = JAXBContext.newInstance(Question.class);
			Marshaller marshaller = jc.createMarshaller();
	        marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
			for (Socket s : playerSockets) {
				try (OutputStream out = s.getOutputStream()) {
					marshaller.marshal(questions, out);
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (JAXBException e1) {
			e1.printStackTrace();
		}
	}

	private void startNextQuestion() {
		if (playedQuestions == round.getQuestions().size()) {
			end();
		} else {
			for (Socket s : playerSockets) {
				try (OutputStream out = s.getOutputStream()) {
					out.write(1); // For now just 1 as signal, that next question can begin
					out.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			playedQuestions++;
		}
	}
	
	private void end() {
		sendEndResults();
		saveEndResults();
		for (Socket s : playerSockets) {
			try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		GamePool.removeGame(id);
	}

	private void sendEndResults() {
		ObjectMapper objectMapper = new ObjectMapper();
		for (Socket s : playerSockets) {
			try (OutputStream out = s.getOutputStream()) {
				objectMapper.writeValue(out, scoreboard);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void saveEndResults() {
		RoundDAO dao = new RoundDAO();
		dao.addRound(round);
	}
	
	public void updateScoreboard(int playerId, int points) {
		int score = scoreboard.get(playerId);
		score += points;
		scoreboard.put(playerId, score);
	}
	
	private boolean hasAllPlayersAnswered() {
		boolean allPlayeresAndwered = waitingPlayers.size() == round.getParticipations().size();
		return allPlayeresAndwered;
	}
	
}
