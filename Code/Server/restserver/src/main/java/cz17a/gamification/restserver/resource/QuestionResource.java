package cz17a.gamification.restserver.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import data.access.PlayerDAO;
import data.access.QuestionDAO;
import data.access.QuizDAO;
import data.model.Answer;
import data.model.PlayedQuestion;
import data.model.Player;
import data.model.Question;
import data.model.Quiz;
import game.Game;
import game.GamePool;

/**
 * Resource-Class for Questions
 * 
 * @author cz17a
 * @version 1.0
 * @category Resource
 */
@Path("/questions")
public class QuestionResource {
	private QuestionDAO dao = new QuestionDAO();

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Question getQuestion(@PathParam("id") int questionId) {
		return dao.getQuestion(questionId);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Question> getAllQuestions() {
		return dao.getQuestions();
	}

	@GET
	@Path("/{id}/answers/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Answer> getAnswersByQuestion(@PathParam("id") int question_id) {
		List<Answer> answers = dao.getQuestion(question_id).getAnswers();
		return answers;
	}

	@POST
	@Path("/add")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addQuestions(List<Question> questions) {
		QuizDAO quizDAO = new QuizDAO();
		System.out.println(questions.size());
		String title = questions.get(0).getTopic();
		Quiz quiz = quizDAO.getQuiz(title);
		boolean isNew = false;
		System.out.println(quiz == null);
		if (quiz == null) {
			isNew = true;
			quiz = new Quiz();
			quiz.setMinParticipants(5);
			quiz.setMinParticipants(5);
			quiz.setLength(10);
			quiz.setTitle(title);
			quizDAO.addQuiz(quiz);
		}
		for (Question q : questions) {
			q.setAnswers(q.getAnswers());
			quiz.addQuestion(q);
			quizDAO.updateQuestion(q);
		}
		if (isNew == true) {
			return "Neues Quiz mit Thema " + title + " erstellt";
		} else {
			return "Fragem zum Quiz mit dem Thema " + title + " hinzugefügt";
		}
	}

	@POST
	@Path("/played/{game_id}/{question_id}/{player_id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPlayedQuestion(@PathParam("game_id") int gameId, @PathParam("question_id") int questionId,
			@PathParam("player_id") int playerId, PlayedQuestion playedQuestion) {
		Game game = GamePool.games.get(gameId);
		Player player = new PlayerDAO().getPlayer(playerId);
		Question question = new QuestionDAO().getQuestion(questionId);
		playedQuestion.setRound(game.getRound());
		playedQuestion.setPlayer(player);
		playedQuestion.setQuestion(question);
		game.getRound().addPlayedQuestion(playedQuestion);
		game.updateScoreboard(playerId, playedQuestion.getScore());
		game.addWaitingPlayer(playerId);
		if(playedQuestion.getIsJackpot() && playedQuestion.getIsCorrect()) {
			game.getJackpot().payedOut();
		}
		return Response.status(200).build();
	}
}
