package cz17a.gamification.restserver.resource.round;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/round")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoundResource {


	@GET
	public List<Round> getAllRound(){
		return RoundService.getAllRoundsHibernate();
	}
	
	@GET
	@Path("/{ID}")
	public Round getRound(@PathParam("ID") int ID) {
		return RoundService.getRoundHibernate(ID);
	}


	
}