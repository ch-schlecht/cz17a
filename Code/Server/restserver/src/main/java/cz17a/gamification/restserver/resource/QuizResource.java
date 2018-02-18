package cz17a.gamification.restserver.resource;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import data.access.QuizDAO;
import data.model.Question;
import data.model.Quiz;
/**
 * Resource-Class for Quiz
 * @author cz17a
 * @category Resource
 * @version 1.0
 *
 */
@Path("/quizzes")
public class QuizResource {
	private QuizDAO dao = new QuizDAO();
	/**
	 * Get all Quizzes
	 * @return List of All Quizzes
	 * @since 1.0
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Quiz> getQuizzes(){
		List<Quiz> quizzes = dao.getQuizzes();
		return quizzes;
	}
	
	/**
	 * Get all Questions of an Quiz
	 * @param quiz_id ID of Quiz
	 * @return List of all Question containing to Quiz {ID}
	 * @since 1.0 
	 */
	@GET
	@Path("/{id}/random_questions")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Question> get(@PathParam("id") int quiz_id) {
		Quiz quiz = dao.getQuiz(quiz_id);
		return quiz.getRandomQuestions();
	}
	
	/**
	 * Get one Quiz by ID
	 * @param quiz_id ID of Quiz
	 * @return Quiz with ID
	 * @since 1.0
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Quiz getQuiz(@PathParam("id") int quiz_id){
		Quiz quiz = dao.getQuiz(quiz_id);
		return quiz;
	}
}
	
	