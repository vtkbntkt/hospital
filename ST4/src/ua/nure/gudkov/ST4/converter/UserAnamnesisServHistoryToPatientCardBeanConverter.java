package ua.nure.gudkov.ST4.converter;

import java.sql.Date;
import java.util.List;

import ua.nure.gudkov.ST4.bean.aggregation.ServValueStatusBean;
import ua.nure.gudkov.ST4.bean.aggregation.UserAnamnesisServHistoryBean;
import ua.nure.gudkov.ST4.bean.dto.PatientCardBean;
import ua.nure.gudkov.ST4.converter.api.Converter;
import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.entity.Service;
import ua.nure.gudkov.ST4.entity.ServiceHistory;
import ua.nure.gudkov.ST4.entity.Status;
import ua.nure.gudkov.ST4.entity.User;

/**
 * UserAnamnesisServHistory bean to patient card bean converter.
 * 
 * @author A.Gudkov
 *
 */
public class UserAnamnesisServHistoryToPatientCardBeanConverter
		implements Converter<UserAnamnesisServHistoryBean, PatientCardBean> {

	@Override
	public PatientCardBean convert(UserAnamnesisServHistoryBean uashb) {
		PatientCardBean card = new PatientCardBean();
		addUserInfo(card, uashb.getUser());
		addAnamnesis(card, uashb.getAnamnesis());
		addServices(card, uashb.getServHistory());
		return card;
	}

	/**
	 * Adds user personal information into the card.
	 * 
	 * @param card the card of patient
	 * @param user the user
	 */
	private void addUserInfo(PatientCardBean card, User user) {
		card.setEmail(user.getEmail());
		card.setFirstName(user.getFirstName());
		card.setLastName(user.getLastName());
		card.setPhone(user.getPhone());
		card.setDateOfBirth(user.getDateOfBirth());
	}

	/**
	 * Adds anamnesis information into the card.
	 * 
	 * @param card the card of patient
	 * @param anamnes the anamnesis
	 */
	private void addAnamnesis(PatientCardBean card, Anamnesis anamnes) {
		card.setCreationDate(anamnes.getCreationDate());
		card.setFinalDiagnosis(anamnes.getFinalDiagnosis());
		card.setIdCard(anamnes.getIdanamnesis());
		card.setIdDoctor(anamnes.getIdDoctor());
		card.setIdPatient(anamnes.getIdPatient());
		card.setInitialDiagnosis(anamnes.getInitialDiagnosis());
	}

	/**
	 * Adds services into the card.
	 * 
	 * @param card the card of patient
	 * @param servHistory the service history 
	 */
	private void addServices(PatientCardBean card, List<ServiceHistory> servHistory) {
		for (ServiceHistory sh : servHistory) {
			String servValue = sh.getServiceValue();
			Status servStatus = Status.values()[sh.getIdStatus()];
			Date servDate = sh.getDateRecord();
			if (sh.getIdService() == Service.MANIPULATION.ordinal()) {
				card.getManipulations().add(new ServValueStatusBean(servValue, servStatus, servDate));
			}
			if (sh.getIdService() == Service.DRUG.ordinal()) {
				card.getDrugs().add(new ServValueStatusBean(servValue, servStatus, servDate));
			}
			if (sh.getIdService() == Service.SURGERY.ordinal()) {
				card.getSurgeries().add(new ServValueStatusBean(servValue, servStatus, servDate));
			}

		}

	}

}
