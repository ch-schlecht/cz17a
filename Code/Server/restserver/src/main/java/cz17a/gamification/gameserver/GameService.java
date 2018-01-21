package cz17a.gamification.gameserver;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import data.access.QuizDAO;
import data.model.Question;
import data.model.Quiz;

@Path("/game")
@Produces(MediaType.APPLICATION_JSON)
public class GameService {
	
	/**
	 * Sends a list of random questions to a corresponding quiz configuration
	 * @param quiz_id Id of the quiz, which was selected from the client
	 * @return List of question as JSON
	 */
	@GET
	@Path("initiate/{id}")
	public List<Question> create_game (@PathParam("id") int quiz_id) {
		QuizDAO quizdao = new QuizDAO();
		Quiz quiz = quizdao.getQuiz(quiz_id);
		List<Question> questions = quiz.getRandomQuestions();
		return questions;
	}
}
