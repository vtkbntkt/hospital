package ua.nure.gudkov.ST4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.gudkov.ST4.constant.Constants;

public class GetLoginCommandTest extends Mockito {

	@Test
	public void testExecute() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);

		when(request.getSession()).thenReturn(session);
		new GetLoginCommand().execute(request, response);

		Object ob = new Object();
		when(session.getAttribute(Constants.ErrorConstants.LOGIN_ERRORS)).thenReturn(ob);
		when(session.getAttribute(Constants.ErrorConstants.DB_ERRORS)).thenReturn(ob);
		when(session.getAttribute(Constants.SessionAttributes.LOGIN_USER_BEAN)).thenReturn(ob);
		new GetLoginCommand().execute(request, response);
		verify(session, atLeast(1)).removeAttribute(Constants.ErrorConstants.LOGIN_ERRORS);
		verify(request, atLeast(1)).setAttribute(Constants.ErrorConstants.LOGIN_ERRORS, ob);
		verify(session, atLeast(1)).removeAttribute(Constants.ErrorConstants.DB_ERRORS);
		verify(request, atLeast(1)).setAttribute(Constants.ErrorConstants.DB_ERRORS, ob);
		verify(request, atLeast(1)).setAttribute(Constants.SessionAttributes.LOGIN_USER_BEAN, ob);
		verify(request, atLeast(1)).setAttribute(Constants.SessionAttributes.LOGIN_USER_BEAN, ob);

	}

}
