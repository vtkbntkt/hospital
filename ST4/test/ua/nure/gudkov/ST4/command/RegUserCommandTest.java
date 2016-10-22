package ua.nure.gudkov.ST4.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import ua.nure.gudkov.ST4.bean.form.RegFormBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.service.api.UserService;

public class RegUserCommandTest extends Mockito {

	@Test
	public void testExecute() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		UserService userService = mock(UserService.class);

		when(request.getSession()).thenReturn(session);

		new RegUserCommand(userService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(eq(Constants.SessionAttributes.REGISTRATION_USER_BEAN),
				Matchers.any(RegFormBean.class));
		verify(session, atLeast(1)).setAttribute(eq(Constants.ErrorConstants.REGISTRATION_ERRORS),
				Matchers.anyMapOf(String.class, String.class));

		request = initRequest(request);
		new RegUserCommand(userService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(eq(Constants.ErrorConstants.REGISTRATION_ERRORS),
				Matchers.anyMapOf(String.class, String.class));


		when(userService.registerUser(any())).thenReturn(true);
		new RegUserCommand(userService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(eq(Constants.ErrorConstants.REGISTRATION_ERRORS),
				Matchers.anyMapOf(String.class, String.class));
		
		Map<String, String> errors = new HashMap<String, String>();
		errors.put(Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION,
				Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION);
		when(userService.registerUser(any()))
				.thenThrow(new DBException(Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION));
		new RegUserCommand(userService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.ErrorConstants.DB_ERRORS, errors);
	}

	public HttpServletRequest initRequest(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.RegFormParameters.CATEGORY_FIELD, "0");
		params.put(Constants.RegFormParameters.DATE_OF_BIRTH_FIELD, "1987-11-11");
		params.put(Constants.RegFormParameters.EMAIL_FIELD, "bla@mail.ru");
		params.put(Constants.RegFormParameters.FIRST_NAME_FIELD, "Test");
		params.put(Constants.RegFormParameters.LAST_NAME_FIELD, "Testow");
		params.put(Constants.RegFormParameters.PHONE_NUMBER_FIELD, "000 000 0000");
		params.put(Constants.RegFormParameters.ROLE_FIELD, "patient");

		return new HttpServletRequestWrapper(request) {
			public String getParameter(String name) {
				return params.get(name);
			}
		};

	}

}
