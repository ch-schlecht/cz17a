package cz17a.gamification.restserver.resource;

import java.util.Calendar;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import data.access.UserDAO;
import data.model.User;

@Path("/users")
public class UserResource {
	UserDAO userdao = new UserDAO();
	
	/**
	 * registers a user to DB by giving name, email and password
	 * @param name String
	 * @param password String
	 * @param email String
	 * @return status code 200, if stored successfully, 400 if not
	 */
	@POST
	@Path("/register/{name}/{password}/{email}")
	public Response registerUser(@PathParam("name") String name, @PathParam("password") String password, @PathParam("email") String email) {
		User user = new User(email, name, password);
		user.setRegistration(Calendar.getInstance());
		user.setId(userdao.countUsers() + 1); //may cause trouble if users are removed from DB and later new ones are created
		userdao.addUser(user); //add the user to DB
		
		if(userdao.getUser(user.getId()) != null) { //if the user was successfully stored to DB, the return 200
			return Response.status(200).build();
		}
		return Response.status(400).build(); //fail return, user has not been stored to DB
		
	}

}
