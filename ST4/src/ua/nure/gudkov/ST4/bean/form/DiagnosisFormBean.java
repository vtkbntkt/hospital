package ua.nure.gudkov.ST4.bean.form;

import java.io.Serializable;

/**
 * Diagnosis form bean implementation.
 * 
 * @author A.Gudkov
 *
 */
public class DiagnosisFormBean implements Serializable {
	private static final long serialVersionUID = -2513909010459093622L;
	private String idAnamnesis;
	private String idPatient;
	private String diagnosisValue;

	/**
	 * Default constructor.
	 */
	public DiagnosisFormBean() {
	}

	/**
	 * Constructs bean with given arguments.
	 * 
	 * @param idAnamnesis
	 *            the anamnesis id
	 * @param idPatient
	 *            the patient id
	 * @param diagnosisValue
	 *            the diagnosis value
	 */
	public DiagnosisFormBean(String idAnamnesis, String idPatient, String diagnosisValue) {
		this.idAnamnesis = idAnamnesis;
		this.idPatient = idPatient;
		this.diagnosisValue = diagnosisValue;
	}

	public String getIdAnamnesis() {
		return idAnamnesis;
	}

	public void setIdAnamnesis(String idAnamnesis) {
		this.idAnamnesis = idAnamnesis;
	}

	public String getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(String idPatient) {
		this.idPatient = idPatient;
	}

	public String getDiagnosisValue() {
		return diagnosisValue;
	}

	public void setDiagnosisValue(String diagnosisValue) {
		this.diagnosisValue = diagnosisValue;
	}

	@Override
	public String toString() {
		return "DiagnosisFormBean [idAnamnesis=" + idAnamnesis + ", idPatient=" + idPatient + ", diagnosisValue="
				+ diagnosisValue + "]";
	}

}
