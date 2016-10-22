package ua.nure.gudkov.ST4.bean.aggregation;

import java.io.Serializable;
import java.util.List;

import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.entity.ServiceHistory;
import ua.nure.gudkov.ST4.entity.User;

/**
 * Contains the anamnesis, user and list of service history objects.
 * 
 * @author A.Gudkov
 *
 */
public class UserAnamnesisServHistoryBean implements Serializable {

	private static final long serialVersionUID = -1475557988864342644L;
	private Anamnesis anamnesis;
	private User user;
	private List<ServiceHistory> servHistory;

	/**
	 * Constructs bean with given arguments.
	 * 
	 * @param anamnesis
	 *            the anamnesis
	 * @param user
	 *            the user
	 * @param servHistory
	 *            list of service history objects
	 */
	public UserAnamnesisServHistoryBean(Anamnesis anamnesis, User user, List<ServiceHistory> servHistory) {
		this.anamnesis = anamnesis;
		this.user = user;
		this.servHistory = servHistory;
	}

	/**
	 * Default constructor.
	 */
	public UserAnamnesisServHistoryBean() {

	}

	public Anamnesis getAnamnesis() {
		return anamnesis;
	}

	public void setAnamnesis(Anamnesis anamnesis) {
		this.anamnesis = anamnesis;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ServiceHistory> getServHistory() {
		return servHistory;
	}

	public void setServHistory(List<ServiceHistory> servHistory) {
		this.servHistory = servHistory;
	}

	@Override
	public String toString() {
		return "UserAnamnesisServHistoryBean [anamnesis=" + anamnesis + ", user=" + user + ", servHistory="
				+ servHistory + "]";
	}

}
