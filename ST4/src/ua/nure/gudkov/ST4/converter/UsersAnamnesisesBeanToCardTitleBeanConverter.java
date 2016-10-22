package ua.nure.gudkov.ST4.converter;

import java.util.ArrayList;
import java.util.List;

import ua.nure.gudkov.ST4.bean.aggregation.NameBean;
import ua.nure.gudkov.ST4.bean.aggregation.UsersAnamnesisesBean;
import ua.nure.gudkov.ST4.bean.dto.CardTitleBean;
import ua.nure.gudkov.ST4.converter.api.Converter;
import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.entity.User;

/**
 * UserAnamnesises bean to user card title bean converter.
 * 
 * @author A.Gudkov
 *
 */
public class UsersAnamnesisesBeanToCardTitleBeanConverter
		implements Converter<UsersAnamnesisesBean, List<CardTitleBean>> {

	@Override
	public List<CardTitleBean> convert(UsersAnamnesisesBean uab) {
		List<CardTitleBean> titles = new ArrayList<CardTitleBean>();
		List<User> users = uab.getUsers();
		List<Anamnesis> anamnesises = uab.getAnamnesises();
		for (Anamnesis a : anamnesises) {
			CardTitleBean cardTitle = new CardTitleBean();
			extractAnamnesisData(a, cardTitle);
			extractDoctorName(users, cardTitle);
			titles.add(cardTitle);
		}
		return titles;
	}

	/**
	 * Extracts name of attending medical doctor.
	 * 
	 * @param users
	 *            user list
	 * @param cardTitle
	 *            the card title
	 */
	private void extractDoctorName(List<User> users, CardTitleBean cardTitle) {
		for (User user : users) {
			if (user.getId() == cardTitle.getIdDoctor()) {
				cardTitle.setDoctorName(new NameBean(user.getFirstName(), user.getLastName()));
			}
		}
	}

	/**
	 * Extracts anamnesis information.
	 * 
	 * @param anamnes
	 *            the anamnesis
	 * @param cardTitle
	 *            the card title
	 */
	private void extractAnamnesisData(Anamnesis anamnes, CardTitleBean cardTitle) {
		cardTitle.setCreationDate(anamnes.getCreationDate());
		cardTitle.setIdCard(anamnes.getIdanamnesis());
		cardTitle.setIdDoctor(anamnes.getIdDoctor());
		cardTitle.setFinalDiagnosis(anamnes.getFinalDiagnosis());
	}

}
