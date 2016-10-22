package ua.nure.gudkov.ST4.bean.form;

import java.io.Serializable;

/**
 * Add service form bean implementation.
 * 
 * @author A.Gudkov
 *
 */
public class AddServiceFormBean implements Serializable {

	private static final long serialVersionUID = 3104751969952315585L;
	private String idAnamnesis;
	private String serviceType;
	private String idPatient;
	private String serviceValue;

	/**
	 * Default constructor.
	 */
	public AddServiceFormBean() {
	}

	/**
	 * Constructs bean with given arguments.
	 * 
	 * @param idAnamnesis
	 *            the anamnesis id
	 * @param serviceType
	 *            the type of service
	 * @param idPatient
	 *            the patient id
	 * @param serviceValue
	 *            the value of a service
	 */
	public AddServiceFormBean(String idAnamnesis, String serviceType, String idPatient, String serviceValue) {
		this.idAnamnesis = idAnamnesis;
		this.serviceType = serviceType;
		this.idPatient = idPatient;
		this.serviceValue = serviceValue;
	}

	public String getIdAnamnesis() {
		return idAnamnesis;
	}

	public void setIdAnamnesis(String idAnamnesis) {
		this.idAnamnesis = idAnamnesis;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(String idPatient) {
		this.idPatient = idPatient;
	}

	public String getServiceValue() {
		return serviceValue;
	}

	public void setServiceValue(String serviceValue) {
		this.serviceValue = serviceValue;
	}

	@Override
	public String toString() {
		return "AddServiceFormBean [idAnamnesis=" + idAnamnesis + ", serviceType=" + serviceType + ", idPatient="
				+ idPatient + ", serviceValue=" + serviceValue + "]";
	}

}
