package ua.nure.gudkov.ST4.bean.dto;

import java.io.Serializable;
import java.sql.Date;

import ua.nure.gudkov.ST4.bean.aggregation.NameBean;

/**
 * Title of patient card bean implementation.
 * 
 * @author A.Gudkov
 *
 */
public class CardTitleBean implements Serializable {
	private static final long serialVersionUID = -6941701516765439124L;
	private int idCard;
	private Date creationDate;
	private int idDoctor;
	private NameBean doctorName;
	private String finalDiagnosis;

	/**
	 * Default constructor.
	 */
	public CardTitleBean() {
	}

	/**
	 * Constructs bean with given arguments.
	 * 
	 * @param idCard the card id
	 * @param creationDate date of card creation
	 * @param idDoctor the doctor id
	 * @param doctorName the doctor name
	 * @param finalDiagnosis the final diagnosis
	 */
	public CardTitleBean(int idCard, Date creationDate, int idDoctor, NameBean doctorName, String finalDiagnosis) {
		this.idCard = idCard;
		this.creationDate = creationDate;
		this.idDoctor = idDoctor;
		this.doctorName = doctorName;
		this.finalDiagnosis = finalDiagnosis;
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

	public String getFinalDiagnosis() {
		return finalDiagnosis;
	}

	public void setFinalDiagnosis(String finalDiagnosis) {
		this.finalDiagnosis = finalDiagnosis;
	}

	@Override
	public String toString() {
		return "CardTitleBean [idCard=" + idCard + ", creationDate=" + creationDate + ", idDoctor=" + idDoctor
				+ ", doctorName=" + doctorName + ", finalDiagnosis=" + finalDiagnosis + "]";
	}

}
