package ua.nure.gudkov.ST4.bean.dto;

import java.sql.Date;

import ua.nure.gudkov.ST4.bean.aggregation.NameBean;

public class NewPatientBean {
	private Long id;
	private String email;
	private String lastName;
	private String firstName;
	private String phone;
	private Date dateOfBirth;
	private NameBean doctor;

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

	public NameBean getDoctor() {
		return doctor;
	}

	public void setDoctor(NameBean doctor) {
		this.doctor = doctor;
	}

	@Override
	public String toString() {
		return "NewPatientBean [id=" + id + ", email=" + email + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", phone=" + phone + ", dateOfBirth=" + dateOfBirth + ", doctor=" + doctor + "]";
	}

}
