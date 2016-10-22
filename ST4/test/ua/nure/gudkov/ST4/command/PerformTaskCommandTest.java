package ua.nure.gudkov.ST4.command;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.entity.ServiceHistory;
import ua.nure.gudkov.ST4.entity.User;
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.service.api.ServiceHistoryService;

public class PerformTaskCommandTest extends Mockito {

	private Map<String, String> params = new HashMap<String, String>();

	@Test
	public void testExecute() throws ServletException, IOException, DBException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		ServiceHistoryService servHistoryService = mock(ServiceHistoryService.class);
		Map<String, String> errors = new HashMap<String, String>();

		when(request.getSession()).thenReturn(session);
		new PerformTaskCommand(servHistoryService).execute(request, response);

		params.put(Constants.GenericParameters.ID_SERV_HISTORY, "2016");
		request = initRequest(request);
		new PerformTaskCommand(servHistoryService).execute(request, response);

		ServiceHistory sh = new ServiceHistory();
		sh.setIdService(2);
		User user = new User();
		user.setRoleId(0);
		when(session.getAttribute(Constants.SessionAttributes.USER_ATTR)).thenReturn(user);
		when(servHistoryService.findById(2016)).thenReturn(sh);
		new PerformTaskCommand(servHistoryService).execute(request, response);

		user.setRoleId(2);
		new PerformTaskCommand(servHistoryService).execute(request, response);

		sh.setIdService(1);
		new PerformTaskCommand(servHistoryService).execute(request, response);

		when(servHistoryService.changeServiceStatusToPerformed(2016)).thenReturn(true);
		new PerformTaskCommand(servHistoryService).execute(request, response);

		errors.put(Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION,
				Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION);
		when(servHistoryService.changeServiceStatusToPerformed(2016))
				.thenThrow(new DBException(Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION));
		new PerformTaskCommand(servHistoryService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.ErrorConstants.DB_ERRORS, errors);

	}

	public HttpServletRequest initRequest(HttpServletRequest request) {

		return new HttpServletRequestWrapper(request) {
			public String getParameter(String name) {
				return params.get(name);
			}
		};
	}

}
