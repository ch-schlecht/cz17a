package cz17a.gamification.restserver.resource.quiz;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import data.access.AnswerDAO;
import data.model.Answer;

@Path("/answers")
@Consumes(MediaType.APPLICATION_JSON)
public class AnswerRecource {

	
	@GET
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public List<Answer> getAnswers(){
		AnswerDAO dao = new AnswerDAO();
		List<Answer> answers = dao.getAnswers();
		return answers;
	}
}
