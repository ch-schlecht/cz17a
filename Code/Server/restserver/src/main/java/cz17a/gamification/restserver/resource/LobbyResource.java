package cz17a.gamification.restserver.resource;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import data.access.PlayerDAO;
import data.model.Player;
import game.LobbyPool;
/**
 * REST-Resource for Lobbies
 * @author cz17a
 * @version 1.0
 * @category Resource
 */
@Path("/Lobbies")
@Consumes(MediaType.APPLICATION_JSON)
public class LobbyResource {
	/**
	 * Adds an User to an Lobby
	 * @param quiz_id ID of Quiz -> Lobby
	 * @param player_id ID of Player to Add
	 * @return Response Status-Code
	 * @since 1.0
	 */
	@PUT
	@Path("/{quiz_id}/join/{user_id}/{port}")
	//@Produces(MediaType.APPLICATION_JSON)
	public Response joinLobby(@Context HttpServletRequest request, @PathParam("quiz_id") int quiz_id, @PathParam("player_id") int player_id, @PathParam("port") int port){
		Player player = new PlayerDAO().getPlayer(player_id);
		player.setPort(port);
		InetAddress ip;
		try {
			ip = InetAddress.getByName(request.getRemoteAddr());
			player.setIPAddress(ip);
			LobbyPool.joinLobby(quiz_id, player);
			return Response.status(200).build();
		}
		catch(UnknownHostException e) {
			e.printStackTrace();
			//Verbesserungswürdig
			return Response.status(400).build();
		}
	}
	
	/**
	 * Remove Player from his lobby
	 * @param quiz_id ID of quiz, the player wanted to play
	 * @param player_id id of player to remove
	 * @return Response Status-Code
	 * @since 1.0
	 */
	@PUT
	@Path("/{quiz_id}/leave/{user_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response leaveLobby(@PathParam("quiz_id") int quiz_id, @PathParam("player_id") int player_id){
		Player player = new PlayerDAO().getPlayer(player_id);
		LobbyPool.leaveLobby(quiz_id, player);
		return Response.status(200).build();
		//TODO return other status on Fial
	}
}
