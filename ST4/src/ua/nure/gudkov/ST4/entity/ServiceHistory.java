package ua.nure.gudkov.ST4.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * ServiceHistory entity.
 * 
 * @author A.Gudkov
 *
 */
public class ServiceHistory implements Serializable {
	private static final long serialVersionUID = 6315354036095009371L;
	private int idRecord;
	private Date dateRecord;
	private int idAnamnesis;
	private int idService;
	private String serviceValue;
	private int idStatus;

	public int getIdRecord() {
		return idRecord;
	}

	public void setIdRecord(int idRecord) {
		this.idRecord = idRecord;
	}

	public Date getDateRecord() {
		return dateRecord;
	}

	public void setDateRecord(Date dateRecord) {
		this.dateRecord = dateRecord;
	}

	public int getIdAnamnesis() {
		return idAnamnesis;
	}

	public void setIdAnamnesis(int idAnamnesis) {
		this.idAnamnesis = idAnamnesis;
	}

	public int getIdService() {
		return idService;
	}

	public void setIdService(int idService) {
		this.idService = idService;
	}

	public String getServiceValue() {
		return serviceValue;
	}

	public void setServiceValue(String serviceValue) {
		this.serviceValue = serviceValue;
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
		return "ServiceHistory [idRecord=" + idRecord + ", dateRecord=" + dateRecord + ", idAnamnesis=" + idAnamnesis
				+ ", idService=" + idService + ", serviceValue=" + serviceValue + ", idStatus=" + idStatus + "]";
	}

}
