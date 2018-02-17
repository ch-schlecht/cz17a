package cz17a.gamification.restserver.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import data.access.PlayerDAO;
import data.model.Player;
import game.LobbyPool;

@Path("/Lobbies")
@Consumes(MediaType.APPLICATION_JSON)
public class LobbyResource {
	@PUT
	@Path("/{quiz_id}/join/{user_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response joinLobby(@PathParam("quiz_id") int quiz_id, @PathParam("player_id") int player_id){
		Player player = new PlayerDAO().getPlayer(player_id);
		LobbyPool.joinLobby(quiz_id, player);
		return Response.status(200).build();
	}
	
	@PUT
	@Path("/{quiz_id}/leave/{user_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response leaveLobby(@PathParam("quiz_id") int quiz_id, @PathParam("player_id") int player_id){
		Player player = new PlayerDAO().getPlayer(player_id);
		LobbyPool.leaveLobby(quiz_id, player);
		return Response.status(200).build();
	}
}
