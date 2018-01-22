package cz17a.gamification.restserver.resource.quiz;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import data.access.HibernateUtil;
import data.access.QuizDAO;
import data.model.Quiz;

@Path("/quiz")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuizResource {
	private QuizDAO dao = new QuizDAO();
	
	@GET
    @Path("/list")
	public List<Quiz> getQuizzes(){
		List<Quiz> quizzes = dao.getQuizzes();
		return quizzes;
	}
	
	@GET
    @Path("/cp")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCp(){
		HibernateUtil.getSession().close();
		return "test";
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
	
	