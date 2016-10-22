package ua.nure.gudkov.ST4.bean.aggregation;

import java.io.Serializable;

/**
 * User name bean implementation. Represents users first name and last name.
 * 
 * @author A.Gudkov
 *
 */
public class NameBean implements Serializable {
	private static final long serialVersionUID = -7923755259033083460L;
	private String firstName;
	private String lastName;

	/**
	 * Constructs bean with given arguments.
	 *
	 * @param firstName
	 *            the first name
	 * @param lastName
	 *            the last name
	 */
	public NameBean(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Constructs an empty bean.
	 */
	public NameBean() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "NameBean [firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}


