package ua.nure.gudkov.ST4.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ua.nure.gudkov.ST4.bean.app.MessageBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.entity.User;

/**
 * Mail sending tool implementation.
 * 
 * @author A.Gudkov
 *
 */
public class Mailer {
	public static final String SEPARATOR = System.lineSeparator();

	/**
	 * Sends letter with given content to given mail.
	 * 
	 * @param mail
	 *            recipient mail
	 * @param subject
	 *            letter subject
	 * @param message
	 *            message
	 */
	public static void sendMail(String mail, String subject, String message)
			throws AddressException, MessagingException, UnsupportedEncodingException {
		Message msg = new MimeMessage(getSession());
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
		msg.setSubject(subject);
		msg.setText(message);
		msg.setFrom(new InternetAddress(Constants.Mailer.FROM_ADDRESS, Constants.Mailer.FROM_NAME));
		Transport.send(msg);
	}

	/**
	 * Returns session.
	 * 
	 * @return session
	 */
	private static Session getSession() {
		Session session = Session.getDefaultInstance(getProperties(), new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(Constants.Mailer.USERNAME, Constants.Mailer.PASSWORD);
			}
		});
		return session;
	}

	/**
	 * Returns properties.
	 * 
	 * @return properties.
	 */
	private static Properties getProperties() {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");
		return properties;
	}

	/**
	 * Send mail using message bean object.
	 * 
	 * @param msgBean
	 *            message bean
	 */
	public static void sendMail(MessageBean msgBean)
			throws AddressException, UnsupportedEncodingException, MessagingException {
		sendMail(msgBean.getMail(), msgBean.getSubject(), msgBean.getMessage());
	}

	/**
	 * Returns message bean extracted from user entity.
	 * 
	 * @param user
	 *            user
	 * @return message bean
	 */
	public static MessageBean createMessage(User user) {
		MessageBean msg = new MessageBean();
		StringBuilder sb = new StringBuilder();
		sb.append(user.getEmail()).append(SEPARATOR).append(user.getPassword());
		/*
		 * use own email
		 */
		msg.setMail("aleksej.gudkov@gmail.com");
		msg.setMessage(sb.toString());
		msg.setSubject(Constants.Mailer.TITLE_ACC_INFO);
		return msg;
	}

}
