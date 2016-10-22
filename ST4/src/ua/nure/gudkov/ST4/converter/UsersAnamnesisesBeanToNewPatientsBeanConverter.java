package ua.nure.gudkov.ST4.converter;

import java.util.ArrayList;
import java.util.List;

import ua.nure.gudkov.ST4.bean.aggregation.NameBean;
import ua.nure.gudkov.ST4.bean.aggregation.UsersAnamnesisesBean;
import ua.nure.gudkov.ST4.bean.dto.NewPatientBean;
import ua.nure.gudkov.ST4.converter.api.Converter;
import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.entity.User;

public class UsersAnamnesisesBeanToNewPatientsBeanConverter
		implements Converter<UsersAnamnesisesBean, List<NewPatientBean>> {

	@Override
	public List<NewPatientBean> convert(UsersAnamnesisesBean uab) {
		List<NewPatientBean> patients = new ArrayList<NewPatientBean>();
		List<User> users = uab.getUsers();
		List<Anamnesis> anamnesises = uab.getAnamnesises();
		for (Anamnesis anamnes : anamnesises) {
			NewPatientBean newPatient = convertUserToNewPatient(users, anamnes.getIdPatient());
			newPatient.setDoctor(getDoctorName(users, anamnes.getIdDoctor()));
			patients.add(newPatient);

		}

		return patients;

	}

	private NameBean getDoctorName(List<User> users, int idDoctor) {
		NameBean nb = new NameBean();
		for (User user : users) {
			if (user.getId() == idDoctor) {
				nb.setFirstName(user.getFirstName());
				nb.setLastName(user.getLastName());
			}
		}

		return nb;
	}

	private NewPatientBean convertUserToNewPatient(List<User> users, int idUser) {
		NewPatientBean newPatient = new NewPatientBean();
		for (User user : users) {
			if (user.getId() == idUser) {
				newPatient.setDateOfBirth(user.getDateOfBirth());
				newPatient.setEmail(user.getEmail());
				newPatient.setFirstName(user.getFirstName());
				newPatient.setId(user.getId());
				newPatient.setLastName(user.getLastName());
				newPatient.setPhone(user.getPhone());
			}
		}
		return newPatient;
	}

}
