package ua.nure.gudkov.ST4.validator;

import org.apache.commons.lang3.StringUtils;

import ua.nure.gudkov.ST4.bean.form.DiagnosisFormBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.entity.ErrorHolder;

/**
 * Diagnosis form validator.
 * 
 * @author A.Gudkov
 *
 */
public class DiagnosisFormValidator implements BeanValidator<DiagnosisFormBean> {

	@Override
	public ErrorHolder validate(DiagnosisFormBean diagnosForm) {
		ErrorHolder errorHolder = new ErrorHolder();
		String idAnamnesis = diagnosForm.getIdAnamnesis();
		String diagnosValue = diagnosForm.getDiagnosisValue();

		if (StringUtils.isEmpty(diagnosValue)) {
			errorHolder.add(Constants.PatientCardParameters.DIAGNOSIS_VALUE_FIELD,
					Constants.ErrorConstants.ADD_DIAGNOSIS_KEY);
		}

		if (!StringUtils.isNumeric((idAnamnesis))) {
			errorHolder.add(Constants.ErrorConstants.ADD_DIAGNOSIS_HIDDEN_PARAMS_KEY);
		}

		return errorHolder;
	}

}
