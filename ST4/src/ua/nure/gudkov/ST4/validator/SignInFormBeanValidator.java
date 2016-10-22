package ua.nure.gudkov.ST4.validator;

import org.apache.commons.lang3.StringUtils;

import ua.nure.gudkov.ST4.bean.form.SignInFormBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.entity.ErrorHolder;

/**
 * Signin form bean validator.
 * 
 * @author A.Gudkov
 *
 */
public class SignInFormBeanValidator implements BeanValidator<SignInFormBean> {

	@Override
	public ErrorHolder validate(SignInFormBean e) {
		ErrorHolder errorHolder = new ErrorHolder();
		String email = e.getEmail();
		String password = e.getPassword();

		if (!(RegFormBeanValidator.isValidByPattern(email, Constants.RegexConstants.EMAIL_REG_EXP))) {
			errorHolder.add(Constants.SignInFormParameters.EMAIL_FIELD, Constants.ErrorConstants.LOGIN_EMAIL_KEY);
		}

		if (StringUtils.isEmpty(password)) {
			errorHolder.add(Constants.SignInFormParameters.PASSWORD_FIELD, Constants.ErrorConstants.LOGIN_PASSWORD_KEY);
		}
		return errorHolder;
	}

}
