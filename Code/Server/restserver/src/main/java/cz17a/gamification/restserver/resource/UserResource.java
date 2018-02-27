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
import data.access.UserDAO;
import data.model.PasswordCode;
import data.model.User;

/**
 * REST: Resource-Class for Users <br/>
 * Linked to Path /users
 * 
 * @author cz17a
 * @category Resource
 * @version 1.0
 *
 */
@Path("/users")
public class UserResource {
	UserDAO dao = new UserDAO();
	PasswordCodeDAO pwdao = new PasswordCodeDAO();

	@GET
	@Path("/{name}")
	public User getUser(@PathParam("name") String name) {
		return dao.getUser(name);
	}

	/**
	 * registers a user to DB by giving name, email and password
	 * 
	 * @param name
	 *            String
	 * @param password
	 *            String
	 * @param email
	 *            String
	 * @return status code 200, if stored successfully, 400 if not
	 */
	@POST
	@Path("/register")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String register(User u) {
		String email = u.getMail();
		String name = u.getNickname();
		String password;
		String salt = PasswordManager.generateSaltAsString();
		password = PasswordManager.hash(u.getPassword(), salt);
		if (dao.usernameExist(name)) {
			return "Dieser Nickname existiert bereits";
		} else if (dao.emailExist(email)) {
			return "Diese Email existiert bereits";
		} else {
			User user = new User(email, name, password, salt);
			user.setRegistration(Calendar.getInstance());
			dao.addUser(user);
			if (dao.getUser(name) != null) {
				return "Sie haben sich erfolgreich registriert";
			} else {
				return "Registrierung fehlgeschlagen. Bitte versuchen Sie es erneut!";
			}
		}
	}

	/**
	 * Function to perform login of a user by giving nickname (or email) and
	 * password
	 * 
	 * @param name
	 *            String (or email)
	 * @param password
	 *            String
	 * @return status code 200 if logged in successfully, 418 else (password is
	 *         wrong/user dont exist)
	 */
	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String login(User u) {
		User user;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("Select p from Person p where p.nickname = :nickname OR p.mail = :mail");
		query.setParameter("nickname", u.getNickname());
		query.setParameter("mail", u.getMail());
		user = (User) query.uniqueResult();
		session.close();
		String password = PasswordManager.hash(u.getPassword(), user.getSalt());
		boolean isPasswordValid = PasswordManager.validatePassword(user.getPassword(), password);
		if (user == null || isPasswordValid == false) {
			return "Die eingegebenen Daten stimmen nicht überein";
		} else {
			return "Sie haben sich erfolgreich angemeldet.";
		}
	}

	/**
	 * Function to perform logout of a user by giving the name
	 * 
	 * @param name
	 *            String
	 * @return status code 200 if logged out successfully, 400 else
	 */
	@POST
	@Path("/logout/{name}")
	public Response logoutUser(@PathParam("name") String name) {
		User user = dao.getUser(name);
		if (user != null) { // user does exist (and was logged in before, see todo in loginUser)
			return Response.status(200).build(); // for now just give the ok
		}
		return Response.status(400).build(); // user doesnt exist (or was not logged in before, see todo in loginUser),
												// fail response
	}

	/**
	 * Function that sends the password to the users email if he has forgotten his
	 * password
	 * 
	 * @param name
	 *            nickname of the user
	 * @return status code 200 if sent successfully, 400 else
	 */
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
		pwdao.addPasswordCode(pwc);
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

	/**
	 * Function that removes a user from the DB TODO!!! Only when authentificated
	 * 
	 * @param id
	 *            of the user
	 * @return status code 200 if sent successfully, 400 else
	 */
	@DELETE
	@Path("/{id}")
	public Response removeUser(@PathParam("id") int id) {
		User user = dao.getUser(id);
		dao.removeUser(id);
		if (dao.getUser(user.getId()) == null) { // if the user was successfully deleted from DB, then return 200
			return Response.status(200).build();
		}
		return Response.status(400).build(); // fail return, user has not been deleted from DB
	}

}