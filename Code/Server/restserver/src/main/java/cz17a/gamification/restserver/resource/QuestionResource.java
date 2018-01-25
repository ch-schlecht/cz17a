package cz17a.gamification.restserver.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import data.access.QuestionDAO;
import data.model.Answer;

@Path("/questions")
public class QuestionResource {
	private QuestionDAO dao = new QuestionDAO();
	
	@GET
	@Path("/{id}/answers/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Answer> getAnswersByQuestion(@PathParam("id") int question_id){
		List<Answer> answers = dao.getQuestion(question_id).getAnswers();
		return answers;
	}
}
