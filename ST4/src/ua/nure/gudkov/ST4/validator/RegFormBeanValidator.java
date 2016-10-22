package ua.nure.gudkov.ST4.validator;

import java.sql.Date;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import ua.nure.gudkov.ST4.bean.form.RegFormBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.entity.Category;
import ua.nure.gudkov.ST4.entity.ErrorHolder;
import ua.nure.gudkov.ST4.entity.Role;

/**
 * Registration form bean validator.
 * 
 * @author A.Gudkov
 *
 */
public class RegFormBeanValidator implements BeanValidator<RegFormBean> {

	@Override
	public ErrorHolder validate(RegFormBean e) {
		ErrorHolder errorHolder = new ErrorHolder();
		String firstName = e.getFirstName();
		String lastName = e.getLastName();
		String email = e.getEmail();
		String phone = e.getPhonNumber();
		String dateOfBirth = e.getDateOfBirth();
		String role = e.getRole();
		String category = e.getCategory();
		Date currentDate = new Date(new java.util.Date().getTime());
		System.err.println(e);

		if (!isValidByPattern(firstName, Constants.RegexConstants.NAME_REG_EXP)) {
			errorHolder.add(Constants.RegFormParameters.FIRST_NAME_FIELD,
					Constants.ErrorConstants.REGISTER_FIRSTNAME_KEY);
		}
		if (!isValidByPattern(lastName, Constants.RegexConstants.NAME_REG_EXP)) {
			errorHolder.add(Constants.RegFormParameters.LAST_NAME_FIELD,
					Constants.ErrorConstants.REGISTER_LASTNAME_KEY);
		}
		if (!(isValidByPattern(email, Constants.RegexConstants.EMAIL_REG_EXP))) {
			errorHolder.add(Constants.RegFormParameters.EMAIL_FIELD, Constants.ErrorConstants.REGISTER_EMAIL_KEY);
		}
		if (!isValidByPattern(phone, Constants.RegexConstants.PHONE_REG_EXP)) {
			errorHolder.add(Constants.RegFormParameters.PHONE_NUMBER_FIELD,
					Constants.ErrorConstants.REGISTER_PHONE_KEY);
		}

		if (!isValidByPattern(dateOfBirth, Constants.RegexConstants.DATE_REG_EXP)) {
			errorHolder.add(Constants.RegFormParameters.DATE_OF_BIRTH_FIELD,
					Constants.ErrorConstants.REGISTER_DATE_OF_BIRTH__KEY);
		} else {
			if (Date.valueOf(dateOfBirth).after(currentDate)) {
				errorHolder.add(Constants.RegFormParameters.DATE_OF_BIRTH_FIELD,
						Constants.ErrorConstants.REGISTER_DATE_OF_BIRTH__KEY);
			}
		}

		if (containsInRoles(role)) {
			if (Role.DOCTOR.equalsTo(role) && !containsInCategories(category)) {
				errorHolder.add(Constants.RegFormParameters.CATEGORY_FIELD, Constants.ErrorConstants.REGISTER_CATEGORY);
			}
		} else {
			errorHolder.add(Constants.RegFormParameters.ROLE_FIELD, Constants.ErrorConstants.REGISTER_ROLE);
		}

		return errorHolder;
	}

	/**
	 * Returns true if the value is category type, returns false if it`s not.
	 * 
	 * @param value
	 *            category value
	 * @return true if the value is category type, false if it`s not.
	 */
	private boolean containsInCategories(String value) {
		for (Category category : Category.values()) {
			if (category.equalsTo(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns true if the value is role type, returns false if it`s not.
	 * 
	 * @param value
	 *            role value
	 * @return true if the value is role type, false if it`s not.
	 */
	private boolean containsInRoles(String value) {
		for (Role role : Role.values()) {
			if (role.equalsTo(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns true if the text matches to the regular expression, returns false
	 * if it doesn`t.
	 * 
	 * @param inputText
	 *            input text
	 * @param regEx
	 *            regular expression
	 * @return true if the text matches to the regular expression, false if it
	 *         doesn`t
	 */
	public static boolean isValidByPattern(String inputText, String regEx) {
		return !(StringUtils.isEmpty(inputText) || !Pattern.matches(regEx, inputText));
	}

}
