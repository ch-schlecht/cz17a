package cz17a.gamification.restserver.resource;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import data.access.QuestionDAO;
import data.model.Answer;
import data.model.Question;
/**
 * Resource-Class for Questions
 * @author cz17a
 * @version 1.0
 * @category Resource
 */
@Path("/questions")
public class QuestionResource {
	private QuestionDAO dao = new QuestionDAO();
	/**
	 * Getting List of All Questions <br/>
	 * Able to Filter by:
	 * <li>topic</li>
	 * @param topic to filter by, default = null
	 * @return List of All Questions (with topic)
	 * @since 1.0
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Question> getAllQuestions(@DefaultValue("null") @QueryParam("topic") String topic){
		if(topic.equals("null")) { //no filter
			//System.out.println("No TOPIC set"); //TEST ONLY
			return dao.getQuestions(); //return all Questions
		}
		//System.out.println("Topic: "+topic+" set"); //TEST ONLY
		return dao.getQuestionsByTopic(topic); //return all Questions with topic
	}
	
	/**
	 * Getting all Answers of an specific Question
	 * @param question_id ID of Question
	 * @return List of Answers to Question {ID}
	 * @since 1.0
	 */
	@GET
	@Path("/{id}/answers/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Answer> getAnswersByQuestion(@PathParam("id") int question_id){
		List<Answer> answers = dao.getQuestion(question_id).getAnswers();
		return answers;
	}
}
