package ua.nure.gudkov.ST4.converter;

import java.util.ArrayList;
import java.util.List;

import ua.nure.gudkov.ST4.bean.dto.PatientBean;
import ua.nure.gudkov.ST4.converter.api.Converter;
import ua.nure.gudkov.ST4.entity.User;

/**
 * Users bean to patients bean converter.
 * 
 * @author A.Gudkov
 *
 */
public class UsersToPatientsBeanConverter implements Converter<List<User>, List<PatientBean>> {

	@Override
	public List<PatientBean> convert(List<User> users) {
		List<PatientBean> patients = new ArrayList<PatientBean>();
		for (User user : users) {
			patients.add(userToPatient(user));
		}
		return patients;
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
