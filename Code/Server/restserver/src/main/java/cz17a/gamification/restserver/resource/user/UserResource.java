package cz17a.gamification.restserver.resource.user;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {


	UserService userService = new UserService();
	

	@GET
	public List<User> getAllUser(){
		return userService.getAllUserHibernate();
	}
	
	@GET
	@Path("/{ID}")
	public User getUser(@PathParam("ID") int ID) {
		return userService.getUserHibernate(ID);
	}

	@GET
	@Path("/name/{name}")
	public User getUserByName(@PathParam("name") String name) {
		return userService.getUserByNameHiebrnate(name);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addUser(User user) {
		userService.addUser(user);
	}
	
	
	
	
	
}
