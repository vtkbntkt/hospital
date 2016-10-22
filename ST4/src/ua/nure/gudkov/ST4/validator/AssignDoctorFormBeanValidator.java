package ua.nure.gudkov.ST4.validator;

import org.apache.commons.lang3.StringUtils;

import ua.nure.gudkov.ST4.bean.form.AssignDoctorFormBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.entity.ErrorHolder;

/**
 * Assign doctor form bean validator.
 * 
 * @author A.Gudkov
 *
 */
public class AssignDoctorFormBeanValidator implements BeanValidator<AssignDoctorFormBean> {

	@Override
	public ErrorHolder validate(AssignDoctorFormBean assignDoctorFormBean) {
		ErrorHolder errorHolder = new ErrorHolder();
		String idPatient = assignDoctorFormBean.getIdPatient();
		String idDoctor = assignDoctorFormBean.getIdDoctor();
		if (!(StringUtils.isNumeric(idPatient))) {
			errorHolder.add(Constants.AssignDoctorParameters.ID_PATIENT_FIELD,
					Constants.ErrorConstants.CHOOSE_PATIENT_KEY);
		}
		if (!(StringUtils.isNumeric(idDoctor))) {
			errorHolder.add(Constants.AssignDoctorParameters.ID_DOCTOR_FIELD,
					Constants.ErrorConstants.CHOOSE_DOCTOR_KEY);
		}

		return errorHolder;
	}

}
