package ua.nure.gudkov.ST4.converter;

import java.sql.Date;

import ua.nure.gudkov.ST4.bean.form.AssignDoctorFormBean;
import ua.nure.gudkov.ST4.converter.api.Converter;
import ua.nure.gudkov.ST4.entity.Anamnesis;

/**
 * Assign doctor form bean to anamnesis entity converter.
 * 
 * @author A.Gudkov
 *
 */
public class AssignDoctorFormBeanToAnamnesis implements Converter<AssignDoctorFormBean, Anamnesis> {

	@Override
	public Anamnesis convert(AssignDoctorFormBean assignDoctorFormBean) {
		Date currentDate = new Date(new java.util.Date().getTime());
		Anamnesis anamnesis = new Anamnesis();
		anamnesis.setCreationDate(currentDate);
		anamnesis.setIdDoctor(Integer.parseInt(assignDoctorFormBean.getIdDoctor()));
		anamnesis.setIdPatient(Integer.parseInt(assignDoctorFormBean.getIdPatient()));
		return anamnesis;
	}

}
