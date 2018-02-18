package cz17a.gamification.restserver.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import data.access.AnswerDAO;
import data.model.Answer;


/**
 * Resource-Class for Answers
 * @author cz17a
 * @version 1.0
 * @category Resource
 */
@Path("/answers")
@Consumes(MediaType.APPLICATION_JSON)
public class AnswerResource {
	private AnswerDAO dao = new AnswerDAO(); 
	
	/**
	 * Getting a List of all Answers from Database
	 * @return List of Answers
	 * @since 1.0
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Answer> getAnswers(){
		List<Answer> answers = dao.getAnswers();
		return answers;
	}
	
	/**
	 * Getting one specific Answer by ID
	 * @param answer_id ID of Answer given in REST-API-path
	 * @return answer with {ID}
	 * @since 1.0
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Answer getAnswer(@PathParam("id") int answer_id){
		Answer answer = dao.getAnswer(answer_id);
		return answer;
	}
}
