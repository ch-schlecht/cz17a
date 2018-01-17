package cz17a.gamification.restserver.resource.question;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import cz17a.gamification.restserver.resource.answer.Answer;
import cz17a.gamification.restserver.resource.answer.AnswerService;

/**
 * Class to Handle all /question requests
 * @author Michael
 * @version 1.2
 */
@Path("/questions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuestionResource {

	QuestionService questionService = new QuestionService();
	AnswerService answerService = new AnswerService();
	
	/**
	 * GET requests
	 * @return list of all Questions
	 * @since 1.0
	 */
	@GET
	public List<Question> getQuestions(@QueryParam("topic") String topic, @QueryParam("min") int min, @QueryParam("max") int max) {
		if(topic != null) {
			if(min > 0 || max > 0) {
				if(!(min > 0)) {
					min = max;
				}
				
				if(!(max > 0)) {
					max = min;
				}
				
				return questionService.getAllQuestionsByFilterHibernate("topic = '"+topic+"' AND dynamic_difficulty >= "+min+" AND dynamic_difficulty <="+max);
		
			}
			return questionService.getAllQuestionsByTopicHibernate(topic);
		}
		else if(min > 0 || max > 0) {
			if(!(min > 0)) {
				min = max;
			}
			
			if(!(max > 0)) {
				max = min;
			}
			
			return questionService.getAllQuestionsByFilterHibernate("dynamic_difficulty >= "+min+" AND dynamic_difficulty <="+max);
		}
		
		return questionService.getAllQuestionsHibernate();
	}
	
	/**
	 * POST Request
	 * @param question Question to Add
	 * @return added Question
	 * @since 1.0
	 * @deprecated 1.1 user should'nt add Questions
	 */
	@POST
	public Question addQuestion(Question question) {
		//return questionService.addQuestion(question);
		return null;
	}
	
	/**
	 * PUT request
	 * @param question question to update <-- only needs id //TODO id only?
	 * @return updated question
	 * @since 1.2
	 */
	@PUT
	public Question updateQuestion(Question question) {
		return questionService.updateQuestion(question);
	}
	
	/**
	 * DELETE request
	 * @param ID id of Question
	 * @return deleted Question
	 * @since 1.2
	 */
	@DELETE
	public Question removeQuestion(int ID){
		return questionService.removeQuestion(ID);
	}
	
	/**
	 * GET request to all /question/{ID}
	 * @param id of requested question
	 * @return question 
	 * @since 1.0
	 */
	@GET
	@Path("/{ID}")
	public Question getQuestion(@PathParam("ID") int id) {
		return questionService.getQuestionHibernate(id);
	}
	
	/**
	 * Get all Answers to an Question
	 * @param id of Question
	 * @return list of Answers
	 * @since 1.1
	 */
	@GET
	@Path("/{ID}/answers")
	public List<Answer> getAnswers(@PathParam("ID") int id) {
		return answerService.getAnswerByQuestionHibernate(id);
	}
	
	
	
	
}
