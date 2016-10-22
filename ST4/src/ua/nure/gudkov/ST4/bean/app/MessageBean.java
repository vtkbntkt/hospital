package ua.nure.gudkov.ST4.bean.app;

import java.io.Serializable;

/**
 * Message bean implementation.
 * 
 * @author A.Gudkov
 *
 */
public class MessageBean implements Serializable {
	private static final long serialVersionUID = -4110916237405114253L;
	private String mail;
	private String subject;
	private String message;

	/**
	 * Constructs bean with given arguments.
	 * 
	 * @param mail
	 *            recipient mail
	 * @param subject
	 *            letter subject
	 * @param message
	 *            message
	 */
	public MessageBean(String mail, String subject, String message) {
		this.mail = mail;
		this.subject = subject;
		this.message = message;
	}

	/**
	 * Constructs an empty bean.
	 */
	public MessageBean() {
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MessageBean [mail=" + mail + ", subject=" + subject + ", message=" + message + "]";
	}

}
