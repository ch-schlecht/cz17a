package cz17a.gamification.restserver.resource;

import java.util.Calendar;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import data.access.UserDAO;
import data.model.User;
/**
 * REST: Resource-Class for Users <br/>
 * Linked to Path /users
 * @author cz17a
 * @category Resource
 * @version 1.0
 *
 */
@Path("/users")
public class UserResource {
	UserDAO userdao = new UserDAO();
	
	
	@GET
	@Path("/{name}")
	public User getUser(@PathParam("name") String name) {
		return userdao.getUser(name);
	}
	
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

		if(!userdao.usernameExist(name) && !userdao.emailExist(email)) {
			
			User user = new User(email, name, password);
			user.setRegistration(Calendar.getInstance());
			user.setId(userdao.countUsers() + 1); //may cause trouble if users are removed from DB and later new ones are created
			userdao.addUser(user); //add the user to DB
		
		
			if(userdao.getUser(user.getId()) != null) { //if the user was successfully stored to DB, the return 200
				return Response.status(200).build();
			}
			return Response.status(400).build(); //fail return, user has not been stored to DB

		}
		return Response.status(418).build(); //registert user exists
	}
	
	/**
	 * Function to perform login of a user by giving nickname (or email) and password
	 * @param name String (or email)
	 * @param password String
	 * @return status code 200 if logged in successfully, 418 else (password is wrong/user dont exist)
	 */
	@POST
	@Path("/login/{name}/{password}")
	public Response loginUser(@PathParam("name") String name, @PathParam("password") String password){
		User user = userdao.getUser(name);
		if(user == null) {
			user = userdao.getUserByMail(name);
			if(user == null) 	return Response.status(418).build();
		}
		
		if(user.getPassword().equals(password)) { //if the sent password is equal to the password stored in the DB
			//TODO once game lobby is implemented, pass this user over to the lobby/give the game a sign that this user is logged in and potentially ready to play
			return Response.status(200).build(); //return ok --> successfully logged in
		}
		else {
			return Response.status(418).build(); //--> password is incorrect, reject login "IM A TEAPOT"
		}
	}
	
	/**
	 * Function to perform logout of a user by giving the name
	 * @param name String
	 * @return status code 200 if logged out successfully, 400 else
	 */
	@POST
	@Path("/logout/{name}")
	public Response logoutUser(@PathParam("name") String name){
		User user = userdao.getUser(name);
		if(user != null) { //user does exist (and was logged in before, see todo in loginUser)
			return Response.status(200).build(); //for now just give the ok
		}
		return Response.status(400).build(); //user doesnt exist (or was not logged in before, see todo in loginUser), fail response
	}
	
	/**
	 * Function that sends the password to the users email if he has forgotten his password
	 * @param name nickname of the user
	 * @return status code 200 if sent successfully, 400 else
	 */
	@POST
	@Path("/forgotPassword/{name}")
	public Response sendPasswordToMail(@PathParam("name") String name){
		try { //sourround with try catch to check if mail sends correctly
			User user = userdao.getUser(name);
		
			String username = "cz17a"; //this is the username of our mail address
			String password = "swtcz17a"; //corresponding password
			String senderAddress ="cz17a@web.de";//our email-address
			String recipientsAddress = user.getMail(); //receivers email
			String subject = "Your Password for cz17a Quiz App";
			String text = user.getPassword();
			String smtpHost = "smtp.web.de"; //smtp host of web.de
        
			MailResource mail = new MailResource();
			mail.sendMail(smtpHost, username, password, senderAddress, recipientsAddress, subject, text);
		}
		catch(Exception ex) {
			ex.printStackTrace(System.err);
			return Response.status(400).build(); //if an exception occured while sending return fail code
		}
		finally {
			return Response.status(200).build();
		}
	}

	/**
	 * Function that removes a user from the DB 
	 * TODO!!! Only when authentificated
	 * @param id of the user 
	 * @return status code 200 if sent successfully, 400 else 
	 */ 
	@DELETE 
	@Path("/{id}") 
	public Response removeUser(@PathParam("id") int id) { 
		User user = userdao.getUser(id);
		userdao.removeUser(id);
		if(userdao.getUser(user.getId()) == null) { //if the user was successfully deleted from DB, then return 200 
			return Response.status(200).build(); 
		} 
		return Response.status(400).build(); //fail return, user has not been deleted from DB
	}
	
	
	
}