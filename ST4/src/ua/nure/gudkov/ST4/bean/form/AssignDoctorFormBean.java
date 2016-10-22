package ua.nure.gudkov.ST4.bean.form;

import java.io.Serializable;

/**
 * Assign doctor form bean implementation.
 * 
 * @author A.Gudkov
 *
 */
public class AssignDoctorFormBean implements Serializable {

	private static final long serialVersionUID = 3411254017447827340L;
	private String idPatient;
	private String idDoctor;

	/**
	 * Default constructor.
	 */
	public AssignDoctorFormBean() {
	}

	/**
	 * Constructs bean with given arguments.
	 * 
	 * @param idPatient
	 *            the patient id
	 * @param idDoctor
	 *            the doctor id
	 */
	public AssignDoctorFormBean(String idPatient, String idDoctor) {
		this.idPatient = idPatient;
		this.idDoctor = idDoctor;
	}

	public String getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(String idPatient) {
		this.idPatient = idPatient;
	}

	public String getIdDoctor() {
		return idDoctor;
	}

	public void setIdDoctor(String idDoctor) {
		this.idDoctor = idDoctor;
	}

	@Override
	public String toString() {
		return "AssignDoctorFormBean [idPatient=" + idPatient + ", idDoctor=" + idDoctor + "]";
	}

}
