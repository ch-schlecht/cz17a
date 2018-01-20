package cz17a.gamification.restserver.resource.quiz;



import javax.ws.rs.Consumes;
import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/quiz")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuizResource {


	@GET
	public Quiz getAllData(){
		return QuizService.getAllDataHibernate();
	}
	
}
	
	