package cz17a.gamification.restserver.resource.quiz;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import data.access.HibernateUtil;
import data.access.QuizDAO;
import data.model.Question;
import data.model.Quiz;

@Path("/quizzes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuizResource {
	private QuizDAO dao = new QuizDAO();
	
	@GET
	public List<Quiz> getQuizzes(){
		List<Quiz> quizzes = dao.getQuizzes();
		return quizzes;
	}
	
	@GET
	@Path("/{id}")
	public Quiz getQuiz(@PathParam("id") int quiz_id){
		Quiz quiz = dao.getQuiz(quiz_id);
		List<Question> questions = new ArrayList<Question>();
		for(Question q : quiz.getQuestions()) {
			Question question = new Question();
			question.setDynamic_difficulty(q.getDynamic_difficulty());
			question.setId(q.getId());
			question.setQuestioning(q.getQuestioning());
			question.setResponse_time(q.getResponse_time());
			question.setStatic_difficulty(q.getStatic_difficulty());
			question.setTopic(q.getTopic());
			questions.add(question);
		}
		quiz.setQuestions(questions);
		return quiz;
	}
	
	@POST
    @Path("/create")
	public Response addQuiz(Quiz quiz) {
		Quiz q = new Quiz();
		q.setLength(quiz.getLength());
		q.setMax_participants(quiz.getMax_participants());
		q.setMin_participants(quiz.getMin_participants());
		q.setQuestions(quiz.getQuestions());
		q.setTitle(quiz.getTitle());
		dao.addQuiz(q);
		return Response.ok().build();
	}
	
}
	
	