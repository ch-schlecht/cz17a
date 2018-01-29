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

@Path("/questions")
public class QuestionResource {
	private QuestionDAO dao = new QuestionDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Question> getAllQuestions(@DefaultValue("null") @QueryParam("topic") String topic){
		if(topic.equals("null")) {
			System.out.println("No TOPIC set");
			return dao.getQuestions();
		}
		System.out.println("Topic: "+topic+" set");
		return dao.getQuestionsByTopic(topic);
	}
	
	@GET
	@Path("/{id}/answers/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Answer> getAnswersByQuestion(@PathParam("id") int question_id){
		List<Answer> answers = dao.getQuestion(question_id).getAnswers();
		return answers;
	}
}
