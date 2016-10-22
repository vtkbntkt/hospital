package ua.nure.gudkov.ST4.bean.dto;

import java.io.Serializable;
import java.sql.Date;

import ua.nure.gudkov.ST4.bean.aggregation.NameBean;
import ua.nure.gudkov.ST4.entity.Service;

/**
 * Task bean implementation.
 * 
 * @author A.Gudkov
 *
 */
public class TaskBean implements Serializable {
	private static final long serialVersionUID = 7345742075078981053L;
	private int idTask;
	private Service type;
	private Date creationDate;
	private NameBean patientName;
	private String phone;
	private Date dateOfBirth;
	private NameBean doctorName;
	private String initialDiagnosis;
	private String servValue;

	/*
	 * service values
	 */
	private int idAnamnesis;
	private int idPatient;
	private int idDoctor;

	/**
	 * Default constructor.
	 */
	public TaskBean() {
	}

	/**
	 * Constructs bean with given arguments.
	 * 
	 * @param idTask
	 *            the task id
	 * @param type
	 *            the type of task
	 * @param creationDate
	 *            creation date
	 * @param patientName
	 *            the name of patient
	 * @param phone
	 *            the phone
	 * @param dateOfBirth
	 *            the date of birth
	 * @param doctorName
	 *            the name of a doctor
	 * @param initialDiagnosis
	 *            the initial diagnosis
	 * @param servValue
	 *            the service value
	 * @param idAnamnesis
	 *            the anamnesis id
	 * @param idPatient
	 *            the patient id
	 * @param idDoctor
	 *            the doctor id
	 */
	public TaskBean(int idTask, Service type, Date creationDate, NameBean patientName, String phone, Date dateOfBirth,
			NameBean doctorName, String initialDiagnosis, String servValue, int idAnamnesis, int idPatient,
			int idDoctor) {
		super();
		this.idTask = idTask;
		this.type = type;
		this.creationDate = creationDate;
		this.patientName = patientName;
		this.phone = phone;
		this.dateOfBirth = dateOfBirth;
		this.doctorName = doctorName;
		this.initialDiagnosis = initialDiagnosis;
		this.servValue = servValue;
		this.idAnamnesis = idAnamnesis;
		this.idPatient = idPatient;
		this.idDoctor = idDoctor;
	}

	public int getIdTask() {
		return idTask;
	}

	public void setIdTask(int idTask) {
		this.idTask = idTask;
	}

	public Service getType() {
		return type;
	}

	public void setType(Service type) {
		this.type = type;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public NameBean getPatientName() {
		return patientName;
	}

	public void setPatientName(NameBean patientName) {
		this.patientName = patientName;
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

	public String getServValue() {
		return servValue;
	}

	public void setServValue(String servValue) {
		this.servValue = servValue;
	}

	public int getIdAnamnesis() {
		return idAnamnesis;
	}

	public void setIdAnamnesis(int idAnamnesis) {
		this.idAnamnesis = idAnamnesis;
	}

	public int getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}

	public int getIdDoctor() {
		return idDoctor;
	}

	public void setIdDoctor(int idDoctor) {
		this.idDoctor = idDoctor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "TaskBean [idTask=" + idTask + ", type=" + type + ", creationDate=" + creationDate + ", patientName="
				+ patientName + ", phone=" + phone + ", dateOfBirth=" + dateOfBirth + ", doctorName=" + doctorName
				+ ", initialDiagnosis=" + initialDiagnosis + ", servValue=" + servValue + ", idAnamnesis=" + idAnamnesis
				+ ", idPatient=" + idPatient + ", idDoctor=" + idDoctor + "]";
	}

}
