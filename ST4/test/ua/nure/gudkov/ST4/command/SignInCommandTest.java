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
import org.mockito.Matchers;
import org.mockito.Mockito;

import ua.nure.gudkov.ST4.bean.form.SignInFormBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.entity.User;
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.service.api.UserService;

public class SignInCommandTest extends Mockito {

	@Test
	public void testExecute() throws ServletException, IOException, DBException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		UserService userService = mock(UserService.class);

		when(request.getSession()).thenReturn(session);

		new SignInCommand(userService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(eq(Constants.SessionAttributes.LOGIN_USER_BEAN),
				Matchers.any(SignInFormBean.class));
		verify(session, atLeast(1)).setAttribute(eq(Constants.ErrorConstants.LOGIN_ERRORS),
				Matchers.anyMapOf(String.class, String.class));

		request = initRequest(request);
		new SignInCommand(userService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(eq(Constants.SessionAttributes.LOGIN_USER_BEAN),
				Matchers.any(SignInFormBean.class));
		verify(session, atLeast(1)).setAttribute(eq(Constants.ErrorConstants.LOGIN_ERRORS),
				Matchers.anyMapOf(String.class, String.class));

		User user = initUsers().get(0);
		when(userService.login(any(), any())).thenReturn(user);
		new SignInCommand(userService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(eq(Constants.SessionAttributes.USER_ATTR), eq(user));

		Map<String, String> errors = new HashMap<String, String>();
		errors.put(Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION,
				Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION);
		when(userService.login(any(), any()))
				.thenThrow(new DBException(Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION));
		new SignInCommand(userService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.ErrorConstants.DB_ERRORS, errors);

	}

	public List<User> initUsers() {
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setCategoryId(1);
		user.setDateOfBirth(Date.valueOf("1987-11-11"));
		user.setEmail("bla@mail.ru");
		user.setFirstName("Test");
		user.setId(2016l);
		user.setLastName("Testow");
		user.setPassword("qwerty");
		user.setPhone("000 000 0000");
		user.setRoleId(1);
		users.add(user);
		return users;
	}

	public HttpServletRequest initRequest(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.SignInFormParameters.EMAIL_FIELD, "test@mail.com");
		params.put(Constants.SignInFormParameters.PASSWORD_FIELD, "test");

		return new HttpServletRequestWrapper(request) {
			public String getParameter(String name) {
				return params.get(name);
			}
		};

	}

}
