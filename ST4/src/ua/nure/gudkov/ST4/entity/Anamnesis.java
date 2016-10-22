package ua.nure.gudkov.ST4.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * Anamnesis entity.
 * 
 * @author A.Gudkov
 *
 */
public class Anamnesis implements Serializable {

	private static final long serialVersionUID = -9149712339617215922L;
	private int idanamnesis;
	private Date creationDate;
	private int idPatient;
	private String initialDiagnosis;
	private int idDoctor;
	private String finalDiagnosis;
	private int idStatus;

	public int getIdanamnesis() {
		return idanamnesis;
	}

	public void setIdanamnesis(int idanamnesis) {
		this.idanamnesis = idanamnesis;
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

	public String getInitialDiagnosis() {
		return initialDiagnosis;
	}

	public void setInitialDiagnosis(String initialDiagnosis) {
		this.initialDiagnosis = initialDiagnosis;
	}

	public int getIdDoctor() {
		return idDoctor;
	}

	public void setIdDoctor(int idDoctor) {
		this.idDoctor = idDoctor;
	}

	public String getFinalDiagnosis() {
		return finalDiagnosis;
	}

	public void setFinalDiagnosis(String finalDiagnosis) {
		this.finalDiagnosis = finalDiagnosis;
	}

	public int getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Anamnesis [idanamnesis=" + idanamnesis + ", creationDate=" + creationDate + ", idPatient=" + idPatient
				+ ", initialDiagnosis=" + initialDiagnosis + ", idDoctor=" + idDoctor + ", finalDiagnosis="
				+ finalDiagnosis + ", idStatus=" + idStatus + "]";
	}

}
