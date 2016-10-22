package ua.nure.gudkov.ST4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gudkov.ST4.bean.app.PageBean;
import ua.nure.gudkov.ST4.bean.app.PageBean.Action;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.converter.PathConverter;

/**
 * Logout command implementation.
 * 
 * @author A.Gudkov
 *
 */
public class LogoutCommand extends AbstractCommand {
	private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

	@Override
	public PageBean execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageBean pb = new PageBean();
		pb.setAction(Action.REDIRECT);
		pb.setPath(PathConverter.getInstance().getPath(Constants.PagePath.SIG_IN_PAGE));
		LOG.trace("Set path and action ----> " + pb.getPath() + ", " + pb.getAction());

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(Constants.SessionAttributes.USER_ATTR);
			LOG.debug("User Info removed");
			session.invalidate();
			LOG.debug("Session invalidate");
		}

		LOG.debug("Logout finished");
		return pb;
	}

}
