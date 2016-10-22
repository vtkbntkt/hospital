package ua.nure.gudkov.ST4.bean.aggregation;

import java.io.Serializable;
import java.util.List;

import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.entity.ServiceHistory;
import ua.nure.gudkov.ST4.entity.User;

/**
 * Contains list of anamnesis, users and service history objects.
 * 
 * @author A.Gudkov
 *
 */
public class UsersAnamnesisesHistoryBean implements Serializable {
	private static final long serialVersionUID = -1167966164663466423L;
	private List<Anamnesis> anamnesises;
	private List<User> users;
	private List<ServiceHistory> servHistories;

	/**
	 * Constructs bean with given arguments.
	 * 
	 * @param anamnesises
	 *            list of anamnesis
	 * @param users
	 *            list of users
	 * @param servHistories
	 *            list of servicehistory
	 */
	public UsersAnamnesisesHistoryBean(List<Anamnesis> anamnesises, List<User> users,
			List<ServiceHistory> servHistories) {
		this.anamnesises = anamnesises;
		this.users = users;
		this.servHistories = servHistories;
	}

	/**
	 * Constructs an empty bean.
	 */
	public UsersAnamnesisesHistoryBean() {
	}

	public List<Anamnesis> getAnamnesises() {
		return anamnesises;
	}

	public void setAnamnesises(List<Anamnesis> anamnesises) {
		this.anamnesises = anamnesises;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<ServiceHistory> getServHistories() {
		return servHistories;
	}

	public void setServHistories(List<ServiceHistory> servHistories) {
		this.servHistories = servHistories;
	}

	@Override
	public String toString() {
		return "UsersAnamnesisesHistoryBean [anamnesises=" + anamnesises + ", users=" + users + ", servHistories="
				+ servHistories + "]";
	}

}
