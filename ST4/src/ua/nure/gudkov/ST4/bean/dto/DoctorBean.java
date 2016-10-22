package ua.nure.gudkov.ST4.bean.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 * Doctor bean implementation.
 * 
 * @author A.Gudkov
 *
 */
public class DoctorBean implements Serializable {
	private static final long serialVersionUID = -5962009072472003360L;
	private Long id;
	private String email;
	private String lastName;
	private String firstName;
	private String phone;
	private Date dateOfBirth;
	private String category;
	private int patientNum;

	/**
	 * Default constructor.
	 */
	public DoctorBean() {
	}

	/**
	 * Constructs bean with given arguments.
	 * 
	 * @param id
	 *            the doctor id
	 * @param email
	 *            the email
	 * @param lastName
	 *            the last name
	 * @param firstName
	 *            the first name
	 * @param phone
	 *            the phone
	 * @param dateOfBirth
	 *            the date of birth
	 * @param category
	 *            the category(specialization)
	 * @param patientNum
	 *            number of active patients
	 */
	public DoctorBean(Long id, String email, String lastName, String firstName, String phone, Date dateOfBirth,
			String category, int patientNum) {
		this.id = id;
		this.email = email;
		this.lastName = lastName;
		this.firstName = firstName;
		this.phone = phone;
		this.dateOfBirth = dateOfBirth;
		this.category = category;
		this.patientNum = patientNum;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPatientNum() {
		return patientNum;
	}

	public void setPatientNum(int patientNum) {
		this.patientNum = patientNum;
	}

	@Override
	public String toString() {
		return "DoctorBean [id=" + id + ", email=" + email + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", phone=" + phone + ", dateOfBirth=" + dateOfBirth + ", category=" + category + ", patientNum="
				+ patientNum + "]";
	}

}
