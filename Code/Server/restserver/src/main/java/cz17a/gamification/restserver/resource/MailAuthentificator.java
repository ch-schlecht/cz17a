package cz17a.gamification.restserver.resource;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/**
 * 
 * @author cz17a
 *
 */
public class MailAuthentificator extends Authenticator{

    private final String user;
    private final String password;

    public MailAuthentificator (String user, String password) {
        super();
        this.user = user;
        this.password = password;
    }

    /**
     * 
     * returns PasswordAuthentification (needed for web.de smtp connection)
     * @see javax.mail.Authenticator#getPasswordAuthentication()
     */
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(this.user, this.password);
    }

}
