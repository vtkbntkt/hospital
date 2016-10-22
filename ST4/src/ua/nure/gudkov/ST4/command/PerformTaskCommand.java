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
import ua.nure.gudkov.ST4.entity.ErrorHolder;
import ua.nure.gudkov.ST4.entity.Role;
import ua.nure.gudkov.ST4.entity.Service;
import ua.nure.gudkov.ST4.entity.ServiceHistory;
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.service.api.ServiceHistoryService;

/**
 * Perform tasks command implementation.
 * 
 * @author A.Gudkov
 *
 */
public class PerformTaskCommand extends AbstractCommand {

	private static final Logger LOG = Logger.getLogger(PerformTaskCommand.class);

	private ServiceHistoryService servHistoryService;

	public PerformTaskCommand(ServiceHistoryService servHistoryService) {
		this.servHistoryService = servHistoryService;

	}

	@Override
	public PageBean execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageBean pb = new PageBean();
		pb.setAction(Action.REDIRECT);
		pb.setPath(Constants.Commands.SERVLET_GET_TASKS_COMMAND);
		LOG.trace("Set path and action ----> " + pb.getPath() + ", " + pb.getAction());

		Integer idServHistory = extractIdServHistory(request);
		LOG.trace("ID of service history  extracted ----> " + idServHistory);

		ErrorHolder erroHolder = new ErrorHolder();
		HttpSession session = request.getSession();

		if (idServHistory == null) {
			LOG.trace("Invalid ID");
			return pb;
		}

		ServiceHistory servHistory = null;
		try {
			servHistory = servHistoryService.findById(idServHistory);
			if (servHistory != null) {
				LOG.trace("Service history was obtained ----> " + servHistory);

				if (checkAuthorityUser(session, servHistory)) {
					LOG.trace("user`s rights were confirmed ----> ");

					if (servHistoryService.changeServiceStatusToPerformed(idServHistory)) {
						LOG.trace("service status was updated ----> ");
					}
				}
			}

		} catch (DBException e) {
			erroHolder.add(e.getMessage());
			session.setAttribute(Constants.ErrorConstants.DB_ERRORS, erroHolder.getErrors());
			LOG.trace("catched dbexception ----> ");
			LOG.error(e);
			pb.setPath(PathConverter.getInstance()
					.getPath(Constants.PagePath.BASE_PATH + Constants.PagePath.ACCOUNT_PAGE));
			return pb;
		}

		return pb;
	}

	/**
	 * Checks whether the user can perform the task. Return true if the user
	 * have permission to perform the task, return false if the session does not
	 * contain user information or the user does not have appropriate
	 * permissions.
	 * 
	 * @param session
	 *            HttpSession
	 * @param servHistory
	 *            the service history
	 * @return true if the user have permission to perform the task, return
	 *         false if the session does not contain user information or the
	 *         user does not have appropriate permissions.
	 */
	private boolean checkAuthorityUser(HttpSession session, ServiceHistory servHistory) {
		Role role = extractRole(session);
		Service serv = Service.values()[servHistory.getIdService()];
		if (serv.equals(Service.SURGERY) && !role.equals(Role.DOCTOR)) {
			return false;
		}
		return true;
	}

}
