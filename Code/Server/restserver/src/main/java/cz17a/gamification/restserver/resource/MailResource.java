package cz17a.gamification.restserver.resource;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailResource {
	/**
	 * sends an Email from sender to recipient, only works for web.de 
	 * @param smtpHost 
	 * @param username
	 * @param password
	 * @param senderAddress
	 * @param recipientsAddress
	 * @param subject
	 * @param text
	 */
    public void sendMail(String smtpHost,String username,String password,String senderAddress,String recipientsAddress,String subject,String text ){
        MailAuthentificator auth = new MailAuthentificator(username, password);
 
        Properties properties = new Properties();
 
        // set host
        properties.setProperty("mail.smtp.host", smtpHost);
 
        //web.de needs an authentification + port 587 + tls security
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.starttls.enable", "true");
 
        //generate mail session
        Session session = Session.getInstance(properties, auth);
 
        try {
            // create message
            Message msg = new MimeMessage(session);
 
            // set sender and recipient
            msg.setFrom(new InternetAddress(senderAddress));
            Address toAddress = new InternetAddress(recipientsAddress);
            msg.setRecipient(Message.RecipientType.TO, toAddress);
 
            // set subject and text
            msg.setSubject(subject);
            msg.setText(text);
 
            // header information, not neccessarily needed
            msg.setHeader("Test", "Test");
            msg.setSentDate(new Date( ));
 
            // send mail
            Transport.send(msg);
 
        }
        catch (Exception e) {
            e.printStackTrace( );
        }
    }

}
