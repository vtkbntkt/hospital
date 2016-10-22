package ua.nure.gudkov.ST4.converter;

import java.util.ArrayList;
import java.util.List;

import ua.nure.gudkov.ST4.bean.aggregation.UsersAnamnesisesBean;
import ua.nure.gudkov.ST4.bean.dto.PatientBean;
import ua.nure.gudkov.ST4.converter.api.Converter;
import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.entity.User;

/**
 * UsersAnamnesisesy bean to patients bean converter.
 * 
 * @author A.Gudkov
 *
 */
public class UsersAnamnesisesBeanToPatientsBeanConverter implements Converter<UsersAnamnesisesBean, List<PatientBean>> {

	@Override
	public List<PatientBean> convert(UsersAnamnesisesBean uab) {
		List<User> users = uab.getUsers();
		List<Anamnesis> anamnesises = uab.getAnamnesises();
		List<PatientBean> patients = new ArrayList<PatientBean>();
		for (Anamnesis anamnes : anamnesises) {
			User user = getUserById(anamnes.getIdPatient(), users);
			if (user != null) {
				patients.add(userToPatient(user));
			}
		}

		return patients;
	}

	/**
	 * Return user found by patient id from the anamnesis.
	 * 
	 * @param idUser
	 *            the patient id from anamnesis
	 * @param users
	 *            list of users
	 * @return user
	 */
	private User getUserById(long idUser, List<User> users) {
		for (User user : users) {
			if (user.getId() == idUser) {
				return user;
			}
		}
		return null;
	}

	/**
	 * Converts user bean to patient bean.
	 * 
	 * @param user
	 *            the user
	 * @return patient bean
	 */
	private PatientBean userToPatient(User user) {
		PatientBean patient = new PatientBean();
		patient.setDateOfBirth(user.getDateOfBirth());
		patient.setEmail(user.getEmail());
		patient.setFirstName(user.getFirstName());
		patient.setId(user.getId());
		patient.setLastName(user.getLastName());
		patient.setPhone(user.getPhone());
		return patient;
	}
}
