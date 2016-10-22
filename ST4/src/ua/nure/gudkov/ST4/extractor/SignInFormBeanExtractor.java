package ua.nure.gudkov.ST4.extractor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import ua.nure.gudkov.ST4.bean.form.SignInFormBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.extractor.api.BeanExtractor;

/**
 * Sign in form bean extractor.
 * 
 * @author A.Gudkov
 *
 */
public class SignInFormBeanExtractor implements BeanExtractor<SignInFormBean> {

	@Override
	public SignInFormBean extract(HttpServletRequest request) throws IOException, ServletException {
		SignInFormBean signInFormBean = new SignInFormBean();
		signInFormBean.setEmail(request.getParameter(Constants.SignInFormParameters.EMAIL_FIELD));
		signInFormBean.setPassword(request.getParameter(Constants.SignInFormParameters.PASSWORD_FIELD));
		return signInFormBean;
	}

}
