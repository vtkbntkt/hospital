package ua.nure.gudkov.ST4.command;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.gudkov.ST4.constant.Constants;

public class LogoutCommandTest extends Mockito {

	@Test
	public void testExecute() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		
		new LogoutCommand().execute(request, response);
		
		when(request.getSession(false)).thenReturn(session);
		new LogoutCommand().execute(request, response);
		verify(session, atLeast(1)).removeAttribute(Constants.SessionAttributes.USER_ATTR);
		verify(session, atLeast(1)).invalidate();


	}

}
