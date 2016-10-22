package ua.nure.gudkov.ST4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.gudkov.ST4.bean.app.PageBean;
import ua.nure.gudkov.ST4.bean.app.PageBean.Action;
import ua.nure.gudkov.ST4.constant.Constants;

/**
 * Get login page command implementation.
 * 
 * @author A.Gudkov
 *
 */
public class GetLoginCommand extends AbstractCommand {

	@Override
	public PageBean execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageBean pb = new PageBean();
		pb.setAction(Action.FORWARD);
		pb.setPath(Constants.PagePath.SIG_IN_PAGE);

		HttpSession session = request.getSession();
		Object errorsSession = session.getAttribute(Constants.ErrorConstants.LOGIN_ERRORS);
		if (errorsSession != null) {
			session.removeAttribute(Constants.ErrorConstants.LOGIN_ERRORS);
			request.setAttribute(Constants.ErrorConstants.LOGIN_ERRORS, errorsSession);
		}
		
		Object dbErrorsSession = session.getAttribute(Constants.ErrorConstants.DB_ERRORS);
		if (dbErrorsSession != null) {
			session.removeAttribute(Constants.ErrorConstants.DB_ERRORS);
			request.setAttribute(Constants.ErrorConstants.DB_ERRORS, dbErrorsSession);
		}

		Object formBeanSession = session.getAttribute(Constants.SessionAttributes.LOGIN_USER_BEAN);
		if (formBeanSession != null) {
			session.removeAttribute(Constants.SessionAttributes.LOGIN_USER_BEAN);
			request.setAttribute(Constants.SessionAttributes.LOGIN_USER_BEAN, formBeanSession);
		}

		return pb;
	}

}
