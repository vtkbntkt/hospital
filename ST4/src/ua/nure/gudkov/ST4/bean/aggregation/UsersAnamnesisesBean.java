package ua.nure.gudkov.ST4.bean.aggregation;

import java.io.Serializable;
import java.util.List;

import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.entity.User;

/**
 * Contains list of anamnesis and list of users objects.
 * 
 * @author A.Gudkov
 *
 */
public class UsersAnamnesisesBean implements Serializable {
	private static final long serialVersionUID = -828711234641323343L;
	private List<Anamnesis> anamnesises;
	private List<User> users;

	/**
	 * Constructs bean with given arguments.
	 * 
	 * @param anamnesises
	 *            list of anamnesis
	 * @param users
	 *            list of users objects
	 */
	public UsersAnamnesisesBean(List<Anamnesis> anamnesises, List<User> users) {
		this.anamnesises = anamnesises;
		this.users = users;
	}

	/**
	 * Constructs an empty bean.
	 */
	public UsersAnamnesisesBean() {
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

	@Override
	public String toString() {
		return "UsersAnamnesisesBean [anamnesises=" + anamnesises + ", users=" + users + "]";
	}

}
