package ua.nure.gudkov.ST4.bean.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ua.nure.gudkov.ST4.bean.aggregation.NameBean;
import ua.nure.gudkov.ST4.bean.aggregation.ServValueStatusBean;

/**
 * Patient medical card mean implementation.
 * 
 * @author A.Gudkov
 *
 */
public class PatientCardBean implements Serializable {

	private static final long serialVersionUID = 7700651199151622369L;
	private int idCard;
	private Date creationDate;
	private int idPatient;
	private String email;
	private String lastName;
	private String firstName;
	private String phone;
	private Date dateOfBirth;
	private int idDoctor;
	private NameBean doctorName;
	private String initialDiagnosis;
	private String finalDiagnosis;
	private List<ServValueStatusBean> manipulations = new ArrayList<ServValueStatusBean>();
	private List<ServValueStatusBean> drugs = new ArrayList<ServValueStatusBean>();
	private List<ServValueStatusBean> surgeries = new ArrayList<ServValueStatusBean>();

	/**
	 * Default constructor.
	 */
	public PatientCardBean() {
	}

	/**
	 * Constructs bean with given arguments.
	 * 
	 * @param idCard
	 *            the card id
	 * @param creationDate
	 *            the creation date
	 * @param idPatient
	 *            the patient id
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
	 * @param idDoctor
	 *            the doctor id
	 * @param doctorName
	 *            the doctor name
	 * @param initialDiagnosis
	 *            the initial diagnosis
	 * @param finalDiagnosis
	 *            the final diagnosis
	 * @param manipulations
	 *            list of assigned manipulation
	 * @param drugs
	 *            list of assigned drugs
	 * @param surgeries
	 *            list assigned surgeries
	 */
	public PatientCardBean(int idCard, Date creationDate, int idPatient, String email, String lastName,
			String firstName, String phone, Date dateOfBirth, int idDoctor, NameBean doctorName,
			String initialDiagnosis, String finalDiagnosis, List<ServValueStatusBean> manipulations,
			List<ServValueStatusBean> drugs, List<ServValueStatusBean> surgeries) {
		this.idCard = idCard;
		this.creationDate = creationDate;
		this.idPatient = idPatient;
		this.email = email;
		this.lastName = lastName;
		this.firstName = firstName;
		this.phone = phone;
		this.dateOfBirth = dateOfBirth;
		this.idDoctor = idDoctor;
		this.doctorName = doctorName;
		this.initialDiagnosis = initialDiagnosis;
		this.finalDiagnosis = finalDiagnosis;
		this.manipulations = manipulations;
		this.drugs = drugs;
		this.surgeries = surgeries;
	}

	public int getIdCard() {
		return idCard;
	}

	public void setIdCard(int idCard) {
		this.idCard = idCard;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
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

	public NameBean getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(NameBean doctorName) {
		this.doctorName = doctorName;
	}

	public String getInitialDiagnosis() {
		return initialDiagnosis;
	}

	public void setInitialDiagnosis(String initialDiagnosis) {
		this.initialDiagnosis = initialDiagnosis;
	}

	public String getFinalDiagnosis() {
		return finalDiagnosis;
	}

	public void setFinalDiagnosis(String finalDiagnosis) {
		this.finalDiagnosis = finalDiagnosis;
	}

	public List<ServValueStatusBean> getManipulations() {
		return manipulations;
	}

	public void setManipulations(List<ServValueStatusBean> manipulations) {
		this.manipulations = manipulations;
	}

	public List<ServValueStatusBean> getDrugs() {
		return drugs;
	}

	public void setDrugs(List<ServValueStatusBean> drugs) {
		this.drugs = drugs;
	}

	public List<ServValueStatusBean> getSurgeries() {
		return surgeries;
	}

	public void setSurgeries(List<ServValueStatusBean> surgeries) {
		this.surgeries = surgeries;
	}

	@Override
	public String toString() {
		return "PatientCardBean [idCard=" + idCard + ", creationDate=" + creationDate + ", idPatient=" + idPatient
				+ ", email=" + email + ", lastName=" + lastName + ", firstName=" + firstName + ", phone=" + phone
				+ ", dateOfBirth=" + dateOfBirth + ", idDoctor=" + idDoctor + ", doctorName=" + doctorName
				+ ", initialDiagnosis=" + initialDiagnosis + ", finalDiagnosis=" + finalDiagnosis + ", manipulations="
				+ manipulations + ", drugs=" + drugs + ", surgeries=" + surgeries + "]";
	}

}
