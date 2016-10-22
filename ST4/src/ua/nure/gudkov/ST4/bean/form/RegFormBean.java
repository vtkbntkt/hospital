package ua.nure.gudkov.ST4.bean.form;

import java.io.Serializable;

/**
 * Registration form bean implementation.
 * 
 * @author A.Gudkov
 *
 */
public class RegFormBean implements Serializable {
	private static final long serialVersionUID = -4499094317431702000L;
	private String firstName;
	private String lastName;
	private String email;
	private String phonNumber;
	private String dateOfBirth;
	private String role;
	private String category;

	/**
	 * Default constructor.
	 */
	public RegFormBean() {
	}

	/**
	 * Constructs bean with given arguments.
	 * 
	 * @param firstName
	 *            the first name
	 * @param lastName
	 *            the last name
	 * @param email
	 *            the email
	 * @param phonNumber
	 *            the phone number
	 * @param dateOfBirth
	 *            the date of birth
	 * @param role
	 *            the role
	 * @param category
	 *            the category
	 */
	public RegFormBean(String firstName, String lastName, String email, String phonNumber, String dateOfBirth,
			String role, String category) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phonNumber = phonNumber;
		this.dateOfBirth = dateOfBirth;
		this.role = role;
		this.category = category;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhonNumber() {
		return phonNumber;
	}

	public void setPhonNumber(String phonNumber) {
		this.phonNumber = phonNumber;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "RegFormBean [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phonNumber="
				+ phonNumber + ", dateOfBirth=" + dateOfBirth + ", role=" + role + ", category=" + category + "]";
	}

}
