package cz17a.gamification.restserver.resource;

import java.util.Calendar;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import data.access.PlayerDAO;
import data.model.Player;

@Path("/users")
public class PlayerResource {
	private PlayerDAO dao = new PlayerDAO();
	@POST
	@Path("/register/{name}/{password}/{email}")
	public Response registerUser(@PathParam("name") String name, @PathParam("password") String password, @PathParam("email") String email) {
		if(!dao.usernameExist(name) && !dao.emailExist(email)) {
			Player player = new Player(email, name, password);
			player.setRegistration(Calendar.getInstance());
			dao.addPlayer(player);
			if(dao.getPlayer(player.getId()) != null) { 
				return Response.status(200).build();
			}
			return Response.status(400).build(); 
		}
		return Response.status(418).build();
	}
}
