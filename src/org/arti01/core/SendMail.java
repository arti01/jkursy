package org.arti01.core;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class SendMail {
	Logger logger = Logger.getLogger(SendMail.class);
	
	public void postMail(String smtpHost, String recipients[], String subject, String message,
			String from) throws MessagingException {
		boolean debug = false;
		java.security.Security
				.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
 
		//Set the host smtp address
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.auth", "true");
 
		Authenticator auth = new SMTPAuthenticator();
		Session session1 = Session.getDefaultInstance(props, auth);
 
		session1.setDebug(debug);
 
		// create a message
		Message msg = new MimeMessage(session1);
 
		// set the from and to address
		InternetAddress addressFrom = new InternetAddress(from);
		msg.setFrom(addressFrom);
 
		InternetAddress[] addressTo = new InternetAddress[recipients.length];
		logger.info(recipients.length);
		for (int i = 0; i < recipients.length; i++) {
			logger.info(i);
			addressTo[i] = new InternetAddress(recipients[i]);
		}
		msg.setRecipients(Message.RecipientType.TO, addressTo);
 
		// Setting the Subject and Content Type
		msg.setSubject(subject);
		msg.setContent(message, "text/plain");
		Transport.send(msg);
	}
}

class SMTPAuthenticator extends javax.mail.Authenticator {

	public PasswordAuthentication getPasswordAuthentication() {
		String username = "arti4077";
		String password = "atos01";
		return new PasswordAuthentication(username, password);
	}
}