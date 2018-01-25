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

@Path("/quizzes")
public class QuizResource {
	private QuizDAO dao = new QuizDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Quiz> getQuizzes(){
		List<Quiz> quizzes = dao.getQuizzes();
		return quizzes;
	}
	
	@GET
	@Path("/{id}/random_questions")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Question> get(@PathParam("id") int quiz_id) {
		Quiz quiz = dao.getQuiz(quiz_id);
		return quiz.getRandomQuestions();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Quiz getQuiz(@PathParam("id") int quiz_id){
		Quiz quiz = dao.getQuiz(quiz_id);
		return quiz;
	}
}
	
	