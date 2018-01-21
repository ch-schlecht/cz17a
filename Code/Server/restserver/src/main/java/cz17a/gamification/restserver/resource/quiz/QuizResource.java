package cz17a.gamification.restserver.resource.quiz;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import data.access.QuizDAO;
import data.model.Quiz;

@Path("/quiz")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuizResource {

	@GET
	public List<Quiz> getQuizzes(){
		QuizDAO dao = new QuizDAO();
		List<Quiz> quizzes = dao.getQuizzes();
		return quizzes;
	}
	
}
	
	