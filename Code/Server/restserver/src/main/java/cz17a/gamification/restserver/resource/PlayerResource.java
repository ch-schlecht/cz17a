package cz17a.gamification.restserver.resource;

import java.util.Calendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Query;
import org.hibernate.Session;

import data.access.HibernateUtil;
import data.access.PlayerDAO;
import data.model.Player;

@Path("/players")
public class PlayerResource {
	private PlayerDAO dao = new PlayerDAO();

	@POST
	@Path("/register")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String register(Player p) {
		String email = p.getMail();
		String name = p.getNickname();
		String password = p.getPassword();
		if (dao.usernameExist(name)) {
			return "Dieser Nickname existiert bereits";
		} else if (dao.emailExist(email)) {
			return "Diese Email existiert bereits";
		} else if (dao.passwordExist(password)) {
			return "Dieses Passwort existiert bereits";
		} else {
			Player player = new Player(email, name, password);
			player.setRegistration(Calendar.getInstance());
			player.setPlaytimeInMinutes(0);
			dao.addPlayer(player);
			if (dao.getPlayer(name) != null) {
				return "Sie haben sich erfolgreich registriert";
			} else {
				return "Registrierung fehlgeschlagen. Bitte versuchen Sie es erneut!";
			}
		}
	}

	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String login(Player p) {
		Player player;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("Select p from Player p where p.nickname = :nickname OR p.mail = :mail");
		query.setParameter("nickname", p.getNickname());
		query.setParameter("mail", p.getMail());
		player = (Player) query.uniqueResult();
		session.close();
		if (player == null) {
			return "Unter diesem Nicknamen oder dieser Mail existiert kein Nutzer";
		} else if (player.getPassword().equals(p.getPassword()) != true) {
			return "Das eingegebene Passwort stimmt nicht überein.";
		} else {
			return "Sie haben sich erfolgreich angemeldet.";
		}
	}

	
	@GET
	@Path("/salt/{name}")
	public String getSalt(@PathParam("name") String name) {
		return dao.getSalt(name);
	}
	
	
	@POST
	@Path("/logout/{name}")
	public Response logout(@PathParam("name") String name) {
		Player player = dao.getPlayer(name);
		if (player != null) { // user does exist (and was logged in before, see todo in loginUser)
			return Response.status(200).build(); // for now just give the ok
		} else {
			return Response.status(400).build(); // user doesnt exist (or was not logged in before, see todo in
													// loginUser), fail response
		}
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
		Player player = dao.getPlayer(id);
		dao.deletePlayer(player);
		if (dao.getPlayer(player.getId()) == null) { // if the user was successfully deleted from DB, then return 200
			return Response.status(200).build();
		} else {
			return Response.status(400).build(); // fail return, user has not been deleted from DB
		}

	}

	@POST
	@Path("/forgotPassword/{name}")
	public Response sendPasswordToMail(@PathParam("name") String name) {
		try { // sourround with try catch to check if mail sends correctly
			Player user = dao.getPlayer(name);
			String username = "cz17a"; // this is the username of our mail address
			String password = "swtcz17a"; // corresponding password
			String senderAddress = "cz17a@web.de";// our email-address
			String recipientsAddress = user.getMail(); // receivers email
			String subject = "Your Password for cz17a Quiz App";
			String text = user.getPassword();
			String smtpHost = "smtp.web.de"; // smtp host of web.de
			MailResource mail = new MailResource();
			mail.sendMail(smtpHost, username, password, senderAddress, recipientsAddress, subject, text);
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			return Response.status(400).build(); // if an exception occured while sending return fail code
		} finally {
			return Response.status(200).build();
		}
	}
}
