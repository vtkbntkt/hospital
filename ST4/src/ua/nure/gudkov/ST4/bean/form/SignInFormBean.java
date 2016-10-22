package ua.nure.gudkov.ST4.bean.form;

import java.io.Serializable;

/**
 * Signin form implementation.
 * 
 * @author A.Gudkov
 *
 */
public class SignInFormBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String email;
	private String password;

	/**
	 * Constructs bean with given arguments.
	 * 
	 * @param email the email
	 * @param password the password
	 */
	public SignInFormBean(String email, String password) {
		this.email = email;
		this.password = password;
	}

	/**
	 * Default constructor.
	 */
	public SignInFormBean() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "SignInFormBean [email=" + email + ", password=" + password + "]";
	}

}
