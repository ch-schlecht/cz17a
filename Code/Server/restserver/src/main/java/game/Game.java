package game;

import data.access.RoundDAO;
import data.model.Participation;
import data.model.Player;
import data.model.Question;
import data.model.Quiz;
import data.model.Round;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;
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

	public Game(int id, Quiz quiz, List<Player> players) {
		this.id = id;
		this.waitingPlayers = new HashSet<Integer>();
		this.jackpot = new Jackpot();
		this.playedQuestions = 0;
		this.round = new Round(quiz.getRandomQuestions(), players);
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

	public void start() throws IOException {
		startRound();
	}

	private void startRound() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Question> questions = getRound().getQuestions();
		for(Participation p : round.getParticipations()) {
			int port;
			InetAddress ip;
			Player player = p.getPlayer();
			port = player.getPort();
			ip = player.getIPAddress();
			try(Socket socket = new Socket(ip, port)) {
				OutputStream out = socket.getOutputStream();
				objectMapper.writeValue(out, questions);
				socket.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	private void end() {
		sendEndResults();
		saveEndResults();
		GamePool.removeGame(id);
	}

	private void startNextQuestion() {
		if (playedQuestions == round.getQuestions().size()) {
			end();
		} else {
			// TODO: Socketkram, go für nächste Frage an alle Teilnehmer senden
			playedQuestions++;
		}
	}

	private boolean hasAllPlayersAnswered() {
		boolean allPlayeresAndwered = waitingPlayers.size() == round.getParticipations().size();
		return allPlayeresAndwered;
	}

	private void sendEndResults() {
		// TODO: Socketkram, schicke Ergebnis an alle Clients
	}

	private void saveEndResults() {
		RoundDAO dao = new RoundDAO();
		dao.addRound(round);
	}
}
