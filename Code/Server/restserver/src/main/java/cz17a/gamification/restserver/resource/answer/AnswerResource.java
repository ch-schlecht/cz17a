package cz17a.gamification.restserver.resource.answer;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cz17a.gamification.restserver.resource.question.Question;


@Path("/answers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
/**
 * Resource for Handling /answer requests
 * @author Michael
 * @version 1.1
 */
public class AnswerResource {

	AnswerService answerService = new AnswerService();
		
	/**
	 * GET requests
	 * @return list of all Answers
	 * @since 1.0
	 */
	@GET
	public List<Answer> getAnswers() {
		return answerService.getAllAnswersHibernate();
	}
	
	/**
	 * POST Request
	 * @param answer Answer to Add
	 * @return added Answer
	 * @since 1.0
	 * @deprecated since 1.1 - user shouldnt post new answers
	 */
	@POST
	public Question addAnswer(Answer answer) {
		
		//return answerService.addAnswer(answer);
		return null;
	}
	
	/**
	 * GET request to all /answers/{ID}
	 * @param id of requested Answer
	 * @return answer 
	 * @since 1.0
	 */
	@GET
	@Path("/{ID}")
	public Answer getAnswer(@PathParam("ID") int id) {
		return answerService.getAnswerHibernate(id);
	}
	
	
}
