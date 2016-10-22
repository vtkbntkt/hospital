package ua.nure.gudkov.ST4.converter;

import java.util.ArrayList;
import java.util.List;

import ua.nure.gudkov.ST4.bean.aggregation.NameBean;
import ua.nure.gudkov.ST4.bean.aggregation.UsersAnamnesisesHistoryBean;
import ua.nure.gudkov.ST4.bean.dto.TaskBean;
import ua.nure.gudkov.ST4.converter.api.Converter;
import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.entity.Service;
import ua.nure.gudkov.ST4.entity.ServiceHistory;
import ua.nure.gudkov.ST4.entity.User;

/**
 * UsersAnamnesisesHistory bean to task bean converter.
 * 
 * @author A.Gudkov
 *
 */
public class UsersAnamnesisesHistoryToTaskBeanConverter
		implements Converter<UsersAnamnesisesHistoryBean, List<TaskBean>> {

	@Override
	public List<TaskBean> convert(UsersAnamnesisesHistoryBean uahb) {
		List<TaskBean> tasks = new ArrayList<TaskBean>();
		List<Anamnesis> anamnesises = uahb.getAnamnesises();
		List<User> users = uahb.getUsers();
		List<ServiceHistory> servHistories = uahb.getServHistories();
		for (ServiceHistory sh : servHistories) {
			TaskBean task = new TaskBean();
			extraxtServHistoryData(task, sh);
			extractAnamnesisData(task, anamnesises);
			if (task.getIdPatient() != 0) {
				extractDoctorData(task, users);
				extractPatientData(task, users);
				tasks.add(task);
			}
		}

		return tasks;
	}

	/**
	 * Extracts user personal information and put it into the task.
	 * 
	 * @param task
	 *            the task
	 * @param users
	 *            list of users
	 */
	private void extractPatientData(TaskBean task, List<User> users) {
		for (User user : users) {
			if (task.getIdPatient() == user.getId()) {
				task.setPatientName(new NameBean(user.getFirstName(), user.getLastName()));
				task.setDateOfBirth(user.getDateOfBirth());
				task.setPhone(user.getPhone());
			}
		}

	}

	/**
	 * Extracts doctor personal information and put it into the task.
	 * 
	 * @param task
	 *            the task
	 * @param users
	 *            list of users
	 */
	private void extractDoctorData(TaskBean task, List<User> users) {
		for (User user : users) {
			if (task.getIdDoctor() == user.getId()) {
				task.setDoctorName(new NameBean(user.getFirstName(), user.getLastName()));
			}
		}

	}

	/**
	 * Extracts anamnesis information and put it into the task.
	 * 
	 * @param task
	 *            the task
	 * @param anamnesises
	 *            list of anamnesises
	 */
	private void extractAnamnesisData(TaskBean task, List<Anamnesis> anamnesises) {
		for (Anamnesis a : anamnesises) {
			if (a.getIdanamnesis() == task.getIdAnamnesis()) {
				task.setIdDoctor(a.getIdDoctor());
				task.setIdPatient(a.getIdPatient());
				task.setInitialDiagnosis(a.getInitialDiagnosis());
			}
		}

	}

	/**
	 * Extracts service history information and put it into the task.
	 * 
	 * @param task
	 *            the task
	 * @param servHistory
	 *            list of service histories
	 */
	private void extraxtServHistoryData(TaskBean task, ServiceHistory servHistory) {
		task.setCreationDate(servHistory.getDateRecord());
		task.setIdAnamnesis(servHistory.getIdAnamnesis());
		task.setIdTask(servHistory.getIdRecord());
		task.setServValue(servHistory.getServiceValue());
		task.setType(Service.values()[servHistory.getIdService()]);

	}
}
