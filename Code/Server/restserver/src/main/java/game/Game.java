package game;

import data.access.RoundDAO;
import data.model.Participation;
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

import com.fasterxml.jackson.databind.ObjectMapper;

public class Game {
	private int id;
	/**
	 * Id's of the players, which have answered the current question
	 */
	private Set<Integer> waitingPlayers;
	private Round round;
	private Jackpot jackpot;
	/**
	 * Counts how many questions were played
	 */
	private int playedQuestions;
	private List<Socket> playerSockets;

	public Game(int id, Quiz quiz, List<Player> players, List<Socket> sockets) {
		this.id = id;
		this.waitingPlayers = new HashSet<Integer>();
		this.jackpot = new Jackpot();
		this.playedQuestions = 0;
		this.round = new Round(quiz.getRandomQuestions(), players);
		this.playerSockets = sockets;
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

	public void start() {
		for (Socket s : playerSockets) {
			try (OutputStream out = s.getOutputStream();) {
				out.write(id);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		startRound();
	}

	private void startRound() {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Question> questions = getRound().getQuestions();
		for (Socket s : playerSockets) {
			try (OutputStream out = s.getOutputStream()) {
				if (questions.isEmpty()) {
					end();
				} else {
					objectMapper.writeValue(out, questions.get(0));
					out.flush();
					questions.remove(0);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

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

	private boolean hasAllPlayersAnswered() {
		boolean allPlayeresAndwered = waitingPlayers.size() == round.getParticipations().size();
		return allPlayeresAndwered;
	}

	private void sendEndResults() {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Integer> pointsMap = new HashMap<>();
		for (Participation p : round.getParticipations()) {
			Player player = p.getPlayer();
			pointsMap.put(player.getNickname(), p.getScore());
		}
		for (Socket s : playerSockets) {
			try (OutputStream out = s.getOutputStream()) {
				objectMapper.writeValue(out, pointsMap);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private void saveEndResults() {
		RoundDAO dao = new RoundDAO();
		dao.addRound(round);
	}
}
