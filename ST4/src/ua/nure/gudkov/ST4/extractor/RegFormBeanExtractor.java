package ua.nure.gudkov.ST4.extractor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import ua.nure.gudkov.ST4.bean.form.RegFormBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.extractor.api.BeanExtractor;

/**
 * User registration form bean extractor.
 * 
 * @author A.Gudkov
 *
 */
public class RegFormBeanExtractor implements BeanExtractor<RegFormBean> {

	@Override
	public RegFormBean extract(HttpServletRequest request) throws IOException, ServletException {
		RegFormBean regFormBean = new RegFormBean();
		regFormBean.setCategory(request.getParameter(Constants.RegFormParameters.CATEGORY_FIELD));
		regFormBean.setDateOfBirth(request.getParameter(Constants.RegFormParameters.DATE_OF_BIRTH_FIELD));
		regFormBean.setEmail(request.getParameter(Constants.RegFormParameters.EMAIL_FIELD));
		regFormBean.setFirstName(request.getParameter(Constants.RegFormParameters.FIRST_NAME_FIELD));
		regFormBean.setLastName(request.getParameter(Constants.RegFormParameters.LAST_NAME_FIELD));
		regFormBean.setPhonNumber(request.getParameter(Constants.RegFormParameters.PHONE_NUMBER_FIELD));
		regFormBean.setRole(request.getParameter(Constants.RegFormParameters.ROLE_FIELD));
		return regFormBean;
	}

}
