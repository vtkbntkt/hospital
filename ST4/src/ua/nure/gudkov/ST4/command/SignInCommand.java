package ua.nure.gudkov.ST4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import ua.nure.gudkov.ST4.bean.app.PageBean;
import ua.nure.gudkov.ST4.bean.app.PageBean.Action;
import ua.nure.gudkov.ST4.bean.form.SignInFormBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.converter.PathConverter;
import ua.nure.gudkov.ST4.entity.ErrorHolder;
import ua.nure.gudkov.ST4.entity.User;
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.extractor.SignInFormBeanExtractor;
import ua.nure.gudkov.ST4.extractor.api.BeanExtractor;
import ua.nure.gudkov.ST4.service.api.UserService;
import ua.nure.gudkov.ST4.validator.BeanValidator;
import ua.nure.gudkov.ST4.validator.SignInFormBeanValidator;

/**
 * Sign in command implementation.
 * 
 * @author A.Gudkov
 *
 */
public class SignInCommand extends AbstractCommand {
	private UserService userService;
	private BeanExtractor<SignInFormBean> beanExtractor;
	private BeanValidator<SignInFormBean> beanValidator;

	private static final Logger LOG = Logger.getLogger(SignInCommand.class);

	public SignInCommand(UserService userService) {
		this.userService = userService;
		beanExtractor = new SignInFormBeanExtractor();
		beanValidator = new SignInFormBeanValidator();
	}

	@Override
	public PageBean execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageBean pb = new PageBean();
		pb.setAction(Action.REDIRECT);
		pb.setPath(PathConverter.getInstance().getPath(Constants.PagePath.SIG_IN_PAGE));
		LOG.trace("Set path and action ----> "+pb.getPath()+", "+pb.getAction());
		
		HttpSession session = request.getSession();
		SignInFormBean signInFormBean = beanExtractor.extract(request);
		LOG.trace("SignInFormBean extracted ----> " + signInFormBean);
		
		ErrorHolder errorHolder = beanValidator.validate(signInFormBean);

		if (!errorHolder.isEmpty()) {
			signInFormBean.setPassword(StringUtils.EMPTY);
			session.setAttribute(Constants.SessionAttributes.LOGIN_USER_BEAN, signInFormBean);
			session.setAttribute(Constants.ErrorConstants.LOGIN_ERRORS, errorHolder.getErrors());
			LOG.trace("Login errors ----> " + errorHolder.getErrors());
			return pb;
		}

		User user = null;
		try {
			user = userService.login(signInFormBean.getEmail(), signInFormBean.getPassword());
			LOG.trace("User was received ----> " + user);
		} catch (DBException e) {
			errorHolder.add(e.getMessage());
			signInFormBean.setPassword(StringUtils.EMPTY);
			session.setAttribute(Constants.ErrorConstants.DB_ERRORS, errorHolder.getErrors());
			session.setAttribute(Constants.SessionAttributes.LOGIN_USER_BEAN, signInFormBean);
			LOG.trace("Catched dbexception ----> ");
			LOG.error(e);
			return pb;
		}

		if (user == null) {
			errorHolder.add(Constants.SignInFormParameters.EMAIL_FIELD + Constants.SignInFormParameters.PASSWORD_FIELD,
					Constants.ErrorConstants.LOGIN_PASSWORD_EMAIL_KEY);
			LOG.trace("Invalid email or password ----> ");
		}
		if (errorHolder.isEmpty()) {
			pb.setPath(PathConverter.getInstance()
					.getPath(Constants.PagePath.BASE_PATH + Constants.PagePath.ACCOUNT_PAGE));
			session.setAttribute(Constants.SessionAttributes.USER_ATTR, user);
			LOG.trace("Login successful ---> ");
			return pb;

		}

		signInFormBean.setPassword(StringUtils.EMPTY);
		session.setAttribute(Constants.SessionAttributes.LOGIN_USER_BEAN, signInFormBean);
		session.setAttribute(Constants.ErrorConstants.LOGIN_ERRORS, errorHolder.getErrors());
		return pb;
	}

}
