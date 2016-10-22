package ua.nure.gudkov.ST4.bean.aggregation;

import java.io.Serializable;
import java.sql.Date;

import ua.nure.gudkov.ST4.entity.Status;


/**
 * Represents service value, status and creation date.
 * 
 * @author A.Gudkov
 *
 */
public class ServValueStatusBean implements Serializable {
	private static final long serialVersionUID = -3233383189215591928L;
	private String servValue;
	private Status servStatus;
	private Date servDate;

	/**
	 * Constructs bean with given arguments.
	 * 
	 * @param servValue
	 *            value of the service
	 * @param servStatus
	 *            the service status
	 * @param servDate
	 *            creation date
	 */
	public ServValueStatusBean(String servValue, Status servStatus, Date servDate) {
		this.servValue = servValue;
		this.servStatus = servStatus;
		this.servDate = servDate;
	}

	/**
	 * Constructs an empty bean.
	 */
	public ServValueStatusBean() {
	}

	public String getServValue() {
		return servValue;
	}

	public void setServValue(String servValue) {
		this.servValue = servValue;
	}

	public Status getServStatus() {
		return servStatus;
	}

	public void setServStatus(Status servStatus) {
		this.servStatus = servStatus;
	}

	public Date getServDate() {
		return servDate;
	}

	public void setServDate(Date servDate) {
		this.servDate = servDate;
	}

	@Override
	public String toString() {
		return "ServValueStatusBean [servValue=" + servValue + ", servStatus=" + servStatus + ", servDate=" + servDate
				+ "]";
	}

}
