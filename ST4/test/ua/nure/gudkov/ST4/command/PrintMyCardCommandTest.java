package ua.nure.gudkov.ST4.command;


import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

public class PrintMyCardCommandTest extends Mockito {
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
		new PrintMyCardCommand(userService, anamnesisService, servHistoryService).execute(request, response);

		params.put(Constants.GenericParameters.ID_ANAMNESIS, "2016");
		request = initRequest(request);
		new PrintMyCardCommand(userService, anamnesisService, servHistoryService).execute(request, response);

		Anamnesis anamnes = initAnamnesis();
		when(anamnesisService.findByIdAnamnesis(2016)).thenReturn(anamnes);
		new PrintMyCardCommand(userService, anamnesisService, servHistoryService).execute(request, response);

		anamnes.setIdStatus(1);
		new PrintMyCardCommand(userService, anamnesisService, servHistoryService).execute(request, response);

		User user = initUsers().get(0);
		anamnes.setIdStatus(1);
		when(session.getAttribute(Constants.SessionAttributes.USER_ATTR)).thenReturn(user);
		new PrintMyCardCommand(userService, anamnesisService, servHistoryService).execute(request, response);

		when(userService.findById(2016)).thenReturn(user);
		new PrintMyCardCommand(userService, anamnesisService, servHistoryService).execute(request, response);

		errors.put(Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION,
				Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION);
		when(userService.findById(2016))
				.thenThrow(new DBException(Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION));
		new PrintMyCardCommand(userService, anamnesisService, servHistoryService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.ErrorConstants.DB_ERRORS, errors);

	}

	public HttpServletRequest initRequest(HttpServletRequest request) {

		return new HttpServletRequestWrapper(request) {
			public String getParameter(String name) {
				return params.get(name);
			}
		};
	}

	public Anamnesis initAnamnesis() {
		Anamnesis anamnes = new Anamnesis();
		anamnes.setIdStatus(2);
		anamnes.setIdPatient(2016);
		return anamnes;
	}

	public List<User> initUsers() {
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setCategoryId(2);
		user.setDateOfBirth(Date.valueOf("1987-11-11"));
		user.setEmail("bla@mail.ru");
		user.setFirstName("Test");
		user.setId(2016l);
		user.setLastName("Testow");
		user.setPassword("qwerty");
		user.setPhone("000 000 0000");
		user.setRoleId(2);
		users.add(user);
		return users;
	}

}
