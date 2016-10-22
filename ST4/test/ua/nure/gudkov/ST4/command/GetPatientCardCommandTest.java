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
import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.entity.User;
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.service.api.AnamnesisService;
import ua.nure.gudkov.ST4.service.api.ServiceHistoryService;
import ua.nure.gudkov.ST4.service.api.UserService;

public class GetPatientCardCommandTest extends Mockito {
	private Map<String, String> params = new HashMap<String, String>();

	@Test
	public void testExecute() throws ServletException, IOException, DBException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		UserService userService = mock(UserService.class);
		AnamnesisService anamnesisService = mock(AnamnesisService.class);
		ServiceHistoryService servHistoryService = mock(ServiceHistoryService.class);
		Map<String, String> errors = new HashMap<String, String>();

		when(request.getSession()).thenReturn(session);
		new GetPatientCardCommand(userService, anamnesisService, servHistoryService).execute(request, response);

		params.put(Constants.GenericParameters.ID_PATIENT, "2016");
		request = initRequest(request);
		new GetPatientCardCommand(userService, anamnesisService, servHistoryService).execute(request, response);

		User user = new User();
		when(userService.verifyPatient(2016)).thenReturn(true);
		when(userService.findById(2016)).thenReturn(user);
		new GetPatientCardCommand(userService, anamnesisService, servHistoryService).execute(request, response);

		Anamnesis anamnes = new Anamnesis();
		anamnes.setIdDoctor(2016);
		when(anamnesisService.findNewByIdPatient(2016)).thenReturn(anamnes);
		when(session.getAttribute(Constants.SessionAttributes.USER_ATTR)).thenReturn(user);
		new GetPatientCardCommand(userService, anamnesisService, servHistoryService).execute(request, response);

		user.setId(2016l);
		new GetPatientCardCommand(userService, anamnesisService, servHistoryService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(eq(Constants.SessionAttributes.CARD_OF_MYPATIENT), notNull());

		errors.clear();
		errors.put(Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION,
				Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION);
		when(userService.verifyPatient(2016))
				.thenThrow(new DBException(Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION));
		new GetPatientCardCommand(userService, anamnesisService, servHistoryService).execute(request, response);
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
