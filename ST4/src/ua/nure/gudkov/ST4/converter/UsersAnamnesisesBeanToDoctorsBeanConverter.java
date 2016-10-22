package ua.nure.gudkov.ST4.converter;

import java.util.ArrayList;
import java.util.List;

import ua.nure.gudkov.ST4.bean.aggregation.UsersAnamnesisesBean;
import ua.nure.gudkov.ST4.bean.dto.DoctorBean;
import ua.nure.gudkov.ST4.converter.api.Converter;
import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.entity.Category;
import ua.nure.gudkov.ST4.entity.User;

/**
 * UsersAnamnesises bean to doctors bean converter.
 * 
 * @author A.Gudkov
 *
 */
public class UsersAnamnesisesBeanToDoctorsBeanConverter implements Converter<UsersAnamnesisesBean, List<DoctorBean>> {

	@Override
	public List<DoctorBean> convert(UsersAnamnesisesBean uab) {
		List<User> users = uab.getUsers();
		List<Anamnesis> anamnesises = uab.getAnamnesises();
		List<DoctorBean> doctors = new ArrayList<DoctorBean>();
		for (User user : users) {
			DoctorBean doctor = userToDoctor(user);

			doctor.setPatientNum(getPatientNum(anamnesises, doctor.getId()));
			doctors.add(doctor);
		}
		return doctors;
	}

	/**
	 * Returns doctor bean extracted from user entity.
	 * 
	 * @param user
	 *            the user
	 * @return doctor bean
	 */
	private DoctorBean userToDoctor(User user) {
		DoctorBean doctorBean = new DoctorBean();
		doctorBean.setCategory((Category.values()[user.getCategoryId() - 1]).name());
		doctorBean.setDateOfBirth(user.getDateOfBirth());
		doctorBean.setEmail(user.getEmail());
		doctorBean.setFirstName(user.getFirstName());
		doctorBean.setLastName(user.getLastName());
		doctorBean.setId(user.getId());
		doctorBean.setPhone(user.getPhone());
		return doctorBean;
	}

	/**
	 * Returns number of patients of the doctor.
	 * 
	 * @param anamnesises
	 *            all anamnesises which have NEW status
	 * @param idDoctor
	 *            the doctor id
	 * @return number of patients
	 */
	private int getPatientNum(List<Anamnesis> anamnesises, long idDoctor) {
		int patientNum = 0;
		for (Anamnesis anamnesis : anamnesises) {
			if (anamnesis.getIdDoctor() == idDoctor) {
				patientNum++;
			}
		}
		return patientNum;
	}

}
