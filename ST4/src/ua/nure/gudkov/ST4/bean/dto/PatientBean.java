package ua.nure.gudkov.ST4.bean.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 * Patient bean implementation.
 * 
 * @author A.Gudkov
 *
 */
public class PatientBean implements Serializable {
	private static final long serialVersionUID = -5293483786209439350L;
	private Long id;
	private String email;
	private String lastName;
	private String firstName;
	private String phone;
	private Date dateOfBirth;
	private int idDoctor;

	/**
	 * Default constructor.
	 */
	public PatientBean() {
	}

	/**
	 *  Constructs bean with given arguments.
	 * 
	 * @param id the patient id
	 * @param email the email
	 * @param lastName the last name
	 * @param firstName the first name
	 * @param phone the phone
	 * @param dateOfBirth the date of birth
	 * @param idDoctor the doctor id
	 */
	public PatientBean(Long id, String email, String lastName, String firstName, String phone, Date dateOfBirth,
			int idDoctor) {
		super();
		this.id = id;
		this.email = email;
		this.lastName = lastName;
		this.firstName = firstName;
		this.phone = phone;
		this.dateOfBirth = dateOfBirth;
		this.idDoctor = idDoctor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getIdDoctor() {
		return idDoctor;
	}

	public void setIdDoctor(int idDoctor) {
		this.idDoctor = idDoctor;
	}

	@Override
	public String toString() {
		return "PatientBean [id=" + id + ", email=" + email + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", phone=" + phone + ", dateOfBirth=" + dateOfBirth + ", idDoctor=" + idDoctor + "]";
	}

}
