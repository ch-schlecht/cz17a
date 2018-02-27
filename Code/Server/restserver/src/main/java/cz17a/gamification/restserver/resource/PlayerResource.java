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

import cz17a.gamification.adminpanel.application.PasswordManager;
import data.access.HibernateUtil;
import data.access.PasswordCodeDAO;
import data.access.PlayerDAO;
import data.model.PasswordCode;
import data.model.Player;
import data.model.User;

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
		String password;
		String salt = PasswordManager.generateSaltAsString();
		password = PasswordManager.hash(p.getPassword(), salt);
		if (dao.usernameExist(name)) {
			return "Dieser Nickname existiert bereits";
		} else if (dao.emailExist(email)) {
			return "Diese Email existiert bereits";
		} else {
			Player player = new Player(email, name, password);
			player.setSalt(salt);
			player.setPlaytimeInMinutes(0);
			player.setRegistration(Calendar.getInstance());
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
		String password = PasswordManager.hash(p.getPassword(), player.getSalt());
		boolean isPasswordValid = PasswordManager.validatePassword(player.getPassword(), password);
		if (player == null || isPasswordValid == false) {
			return "Die eingegebenen Daten stimmen nicht überein";
		} else {
			return "Sie haben sich erfolgreich angemeldet.";
		}
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
	@Path("/forgotPassword")
	public String sendPasswordToMail(User user) {
		String email = user.getMail();
		if (dao.emailExist(email) == false) {
			return "Diese E-Mailadresse existiert nicht.";
		}
		String code = PasswordManager.generateRandomCode();
		String username = "cz17a"; // this is the username of our mail address
		String password = "swtcz17a"; // corresponding password
		String senderAddress = "cz17a@web.de";// our email-address
		String recipientsAddress = email; // receivers email
		String subject = "Your Password for cz17a Quiz App";
		PasswordCode pwc = new PasswordCode(email, code);
		new PasswordCodeDAO().addPasswordCode(pwc);
		String text = "Ihr Link: pcai042.informatik.uni-leipzig.de:1810/forgot.jsp?email=" + email + "&code=" + code; // TODO
		String smtpHost = "smtp.web.de"; // smtp host of web.de
		try {
			MailResource mail = new MailResource();
			mail.sendMail(smtpHost, username, password, senderAddress, recipientsAddress, subject, text);
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			return "Beim Senden der Mail ist ein Fehler aufgetreten. Versuchen Sie es bitte erneut"; // if an exception
																										// occured while
																										// sending
																										// return fail
																										// code
		}
		return "Die Mail wurde erfolgreich gesendet. Klicken Sie dort bitte auf den Link zum Zurücksetzen ihres Passwortes!";
	}
}
