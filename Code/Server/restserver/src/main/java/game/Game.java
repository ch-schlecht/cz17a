package game;

import data.access.RoundDAO;
import data.model.Participation;
import data.model.Player;
import data.model.Question;
import data.model.Quiz;
import data.model.Round;

import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.eclipse.persistence.jaxb.MarshallerProperties;

import com.fasterxml.jackson.core.JsonProcessingException;
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
	private ServerThreadPool threadPool;
	/**
	 * Holds score for every player. Key is the id of a player
	 */
	private Map<Integer, Integer> scoreboard = new HashMap<Integer, Integer>();

	public Game(int id, Quiz quiz, List<Player> players, ServerThreadPool threadPool) {
		this.id = id;
		this.jackpot = new Jackpot();
		this.playedQuestions = 0;
		this.round = new Round(quiz.getRandomQuestions(), players);
		this.threadPool = threadPool;
		for (Player p : players) {
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

	/**
	 * adds a player to the waiting list (waiting because he has already answered)
	 * if all players have answered the list will be cleared and a next question
	 * will be started
	 * 
	 * @param player_id
	 */
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

	public Map<Integer, Integer> getScoreboard() {
		return scoreboard;
	}

	public void setScoreboard(Map<Integer, Integer> scoreboard) {
		this.scoreboard = scoreboard;
	}

	/**
	 * outputs the game-ID to all Clients connected via Socket
	 */
	public void start() {
		String message = String.format("{%d}$", id);
		sendMessage(message);
		startRound();
	}

	public void startRound() {
		List<Question> questions = getRound().getQuestions();
		String questionList;
		try {
			JAXBContext jc = JAXBContext.newInstance(Question.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
			StringWriter writer = new StringWriter();
			marshaller.marshal(questions, writer);
			questionList = writer.toString();
			System.out.println(questionList);
		} catch (JAXBException e1) {
			e1.printStackTrace();
			questionList = "[]";
		}
		sendMessage(questionList + "$");
	}

	/**
	 * starts the next question, but checks if there are no more questions (-->end),
	 * or if it is the last question (-->activate Jackpot)
	 */
	private void startNextQuestion() {
		playedQuestions++;
		if (playedQuestions == round.getQuestions().size()) {
			end();
		} else if (playedQuestions == round.getQuestions().size() - 1) {
			jackpot.setActive(true);
		} else {
			jackpot.randomActivation();
		}
		ObjectMapper mapper = new ObjectMapper();
		String jackpotInformation;
		try {
			jackpotInformation = mapper.writeValueAsString(jackpot);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			jackpotInformation = "{}";
		}
		sendMessage(jackpotInformation + "$");
	}

	/**
	 * saves results to DB, sends result to Clients to display them, removes game
	 * from GamePool, closes socket Connections
	 */
	private void end() {
		sendEndResults();
		saveEndResults();
		threadPool.stop();
		GamePool.removeGame(id);
	}

	/**
	 * sends the results of the game to every Client
	 */
	private void sendEndResults() {
		ObjectMapper objectMapper = new ObjectMapper();
		String endResults;
		try {
			endResults = objectMapper.writeValueAsString(scoreboard);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			endResults = "{}";
		}
		sendMessage(endResults + "$");
	}

	/**
	 * saves the results to DB
	 */
	private void saveEndResults() {
		List<Participation> participations = round.getParticipations();
		for (Participation p : participations) {
			p.setScore(scoreboard.get(p.getPlayer().getId()));
			participations.add(p);
		}
		Collections.sort(participations);
		for (int rank = 1; rank <= participations.size(); rank++) {
			participations.get(rank - 1).setRank(rank);
			if (rank == participations.size())
				round.setWinner(participations.get(rank - 1).getPlayer());
		}
		RoundDAO dao = new RoundDAO();
		dao.addRound(round);
	}

	/**
	 * updates the score of a player
	 * 
	 * @param playerId
	 * @param points
	 */
	public void updateScoreboard(int playerId, int points) {
		int score = scoreboard.get(playerId);
		score += points;
		scoreboard.put(playerId, score);
	}

	/**
	 * checks if all players have sent their reply that they answered the question
	 * 
	 * @return true or false
	 */
	private boolean hasAllPlayersAnswered() {
		boolean allPlayeresAndwered = waitingPlayers.size() == round.getParticipations().size();
		return allPlayeresAndwered;
	}
	
	private void sendMessage(String message) {
		for (int i = 0; i < threadPool.getSize(); i++) {
			threadPool.message.set(i, message);
		}
	}
}
