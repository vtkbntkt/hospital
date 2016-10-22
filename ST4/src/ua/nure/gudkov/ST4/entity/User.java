package ua.nure.gudkov.ST4.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * User entity.
 * 
 * @author A.Gudkov
 * 
 */
public class User implements Serializable {
	private static final long serialVersionUID = 2818994794207450460L;
	private Long id;
	private String email;
	private String password;
	private int roleId;
	private String lastName;
	private String firstName;
	private String phone;
	private Date dateOfBirth;
	private int categoryId;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
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

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", roleId=" + roleId + ", lastName="
				+ lastName + ", firstName=" + firstName + ", phone=" + phone + ", dateOfBirth=" + dateOfBirth
				+ ", categoryId=" + categoryId + "]";
	}

}
