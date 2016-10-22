package ua.nure.gudkov.ST4.command;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gudkov.ST4.bean.app.MessageBean;
import ua.nure.gudkov.ST4.bean.app.PageBean;
import ua.nure.gudkov.ST4.bean.app.PageBean.Action;
import ua.nure.gudkov.ST4.bean.form.RegFormBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.converter.PathConverter;
import ua.nure.gudkov.ST4.converter.RegFormBeanToUserConverter;
import ua.nure.gudkov.ST4.converter.api.Converter;
import ua.nure.gudkov.ST4.entity.ErrorHolder;
import ua.nure.gudkov.ST4.entity.User;
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.extractor.RegFormBeanExtractor;
import ua.nure.gudkov.ST4.extractor.api.BeanExtractor;
import ua.nure.gudkov.ST4.service.api.UserService;
import ua.nure.gudkov.ST4.util.Mailer;
import ua.nure.gudkov.ST4.validator.BeanValidator;
import ua.nure.gudkov.ST4.validator.RegFormBeanValidator;

/**
 * Register user command implementation.
 * 
 * @author A.Gudkov
 *
 */
public class RegUserCommand extends AbstractCommand {

	private static final Logger LOG = Logger.getLogger(RegUserCommand.class);
	private UserService userService;
	private BeanExtractor<RegFormBean> beanExtractor;
	private BeanValidator<RegFormBean> beanValidator;
	private Converter<RegFormBean, User> converter;

	public RegUserCommand(UserService userService) {
		this.userService = userService;
		beanExtractor = new RegFormBeanExtractor();
		beanValidator = new RegFormBeanValidator();
		converter = new RegFormBeanToUserConverter();
	}

	@Override
	public PageBean execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageBean pb = new PageBean();
		pb.setAction(Action.REDIRECT);
		pb.setPath(PathConverter.getInstance().getPath(Constants.PagePath.BASE_PATH + Constants.PagePath.ACCOUNT_PAGE));
		LOG.trace("Set path and action ----> " + pb.getPath() + ", " + pb.getAction());

		RegFormBean regformBean = beanExtractor.extract(request);
		LOG.trace("RegFormBean extracted ----> " + regformBean);

		ErrorHolder regFormErrors = beanValidator.validate(regformBean);
		HttpSession session = request.getSession();
		User user = null;
		if (!regFormErrors.isEmpty()) {
			session.setAttribute(Constants.ErrorConstants.REGISTRATION_ERRORS, regFormErrors.getErrors());
			session.setAttribute(Constants.SessionAttributes.REGISTRATION_USER_BEAN, regformBean);
			LOG.trace("Registration errors ----> " + regFormErrors.getErrors());
			return pb;
		}

		user = converter.convert(regformBean);
		LOG.trace("User converted ----> " + user);
        MessageBean messageBean = Mailer.createMessage(user);
		try {
			if (userService.registerUser(user)) {
				regFormErrors.add(Constants.RegFormParameters.MSG_NOTICE_SUCCESS,
						Constants.SuccessConstants.REGISTER_NOTICE_KEY);
				LOG.trace("user registered ----> " + ", " + user);

				try {
					Mailer.sendMail(messageBean);
					regFormErrors.add(Constants.RegFormParameters.MSG_MAIL_SUCCESS,
							Constants.SuccessConstants.REGISTER_MAIL_KEY);
					LOG.trace("mail sent ----> " + user.getEmail() + ", " + user.getPassword());

				} catch (MessagingException e) {
					regFormErrors.add(Constants.RegFormParameters.MSG_MAIL_ERROR,
							Constants.ErrorConstants.REGISTER_MAIL_KEY);
					LOG.trace("mail is not sent ----> ");
					LOG.error(e.getMessage());
				}
			} else {
				regFormErrors.add(Constants.RegFormParameters.MSG_NOTICE_ERROR,
						Constants.ErrorConstants.REGISTER_NOTICE_KEY);
				session.setAttribute(Constants.SessionAttributes.REGISTRATION_USER_BEAN, regformBean);
				LOG.trace("user is not registered ----> ");
			}
			session.setAttribute(Constants.ErrorConstants.REGISTRATION_ERRORS, regFormErrors.getErrors());

		} catch (DBException e) {
			regFormErrors.add(e.getMessage());
			session.setAttribute(Constants.ErrorConstants.DB_ERRORS, regFormErrors.getErrors());
			session.setAttribute(Constants.SessionAttributes.REGISTRATION_USER_BEAN, regformBean);
			LOG.trace("catched dbexception ----> ");
			LOG.error(e);
			return pb;
		}
		return pb;
	}

}
