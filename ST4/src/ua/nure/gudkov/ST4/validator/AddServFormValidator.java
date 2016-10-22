package ua.nure.gudkov.ST4.validator;

import org.apache.commons.lang3.StringUtils;

import ua.nure.gudkov.ST4.bean.form.AddServiceFormBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.entity.ErrorHolder;
import ua.nure.gudkov.ST4.entity.Service;

/**
 * Add service form validator.
 * 
 * @author A.Gudkov
 *
 */
public class AddServFormValidator implements BeanValidator<AddServiceFormBean> {

	@Override
	public ErrorHolder validate(AddServiceFormBean addForm) {
		ErrorHolder errorHolder = new ErrorHolder();
		String idAnamnesis = addForm.getIdAnamnesis();
		String serviceType = addForm.getServiceType();
		String serviceValue = addForm.getServiceValue();

		if (StringUtils.isEmpty(serviceValue)) {
			errorHolder.add(Constants.PatientCardParameters.SERV_VALUE_FIELD, Constants.ErrorConstants.ADD_SERVICE_KEY);
		}

		if (!StringUtils.isNumeric((idAnamnesis))) {
			errorHolder.add(Constants.ErrorConstants.ADD_SERVICE_HIDDEN_PARAMS_KEY);
		}

		if (!containsInService(serviceType)) {
			errorHolder.add(Constants.ErrorConstants.ADD_SERVICE_HIDDEN_PARAMS_KEY);
		}

		return errorHolder;
	}

	/**
	 * Returns true if the value is service type, returns false if it`s not.
	 * 
	 * @param value
	 *            service value
	 * @return true if the value is service type, false if it`s not.
	 */
	private boolean containsInService(String value) {
		for (Service serv : Service.values()) {
			if (serv.equalsTo(value)) {
				return true;
			}
		}
		return false;
	}

}
