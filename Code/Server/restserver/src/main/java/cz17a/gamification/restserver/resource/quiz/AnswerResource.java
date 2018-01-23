package cz17a.gamification.restserver.resource.quiz;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import data.access.AnswerDAO;
import data.access.QuestionDAO;
import data.access.QuizDAO;
import data.model.Answer;
import data.model.Question;

@Path("/answers")
@Consumes(MediaType.APPLICATION_JSON)
public class AnswerResource {

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Answer> getAnswers(){
		AnswerDAO dao = new AnswerDAO();
		List<Answer> answers = new ArrayList<Answer>();
		for(Answer a : dao.getAnswers()) {
			Question question = new Question();
			question.setDynamic_difficulty(a.getQuestion().getDynamic_difficulty());
			question.setId(a.getQuestion().getId());
			question.setQuestioning(a.getQuestion().getQuestioning());
			question.setResponse_time(a.getQuestion().getResponse_time());
			question.setStatic_difficulty(a.getQuestion().getStatic_difficulty());
			question.setTopic(a.getQuestion().getTopic());
			a.setQuestion(question);
			answers.add(a);
		}
		return answers;
	}
	
	@GET
	@Path("/questions/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Answer> getAnswersByQuestionId(@PathParam("id") int question_id){
		QuestionDAO dao = new QuestionDAO();
		List<Answer> answers = new ArrayList<Answer>();
		for(Answer a : dao.getQuestion(question_id).getAnswers()) {
			Question question = new Question();
			question.setDynamic_difficulty(a.getQuestion().getDynamic_difficulty());
			question.setId(a.getQuestion().getId());
			question.setQuestioning(a.getQuestion().getQuestioning());
			question.setResponse_time(a.getQuestion().getResponse_time());
			question.setStatic_difficulty(a.getQuestion().getStatic_difficulty());
			question.setTopic(a.getQuestion().getTopic());
			a.setQuestion(question);
			answers.add(a);
		}
		return answers;
	}
}
