package ua.nure.gudkov.ST4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.gudkov.ST4.constant.Constants;

public class ChangeLangCommandTest extends Mockito {

	@Test
	public void testExecute() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);

		when(request.getParameter(Constants.LangFormParameters.SELECTED_LANG)).thenReturn(null);
		when(request.getSession(true)).thenReturn(session);

		new ChangeLangCommand().execute(request, response);
		verify(request, atLeast(1)).getParameter(Constants.LangFormParameters.SELECTED_LANG);
		verify(session, atLeast(1)).setAttribute(Constants.SessionAttributes.LANG_ATTR,
				Constants.LangValues.DEFAULT_VALUE);

		when(request.getParameter(Constants.LangFormParameters.SELECTED_LANG)).thenReturn("foo");
		new ChangeLangCommand().execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.SessionAttributes.LANG_ATTR,
				Constants.LangValues.DEFAULT_VALUE);

		when(request.getParameter(Constants.LangFormParameters.SELECTED_LANG))
				.thenReturn(Constants.LangValues.RU_VALUE);
		new ChangeLangCommand().execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.SessionAttributes.LANG_ATTR, Constants.LangValues.RU_VALUE);

		when(request.getParameter(Constants.LangFormParameters.SELECTED_LANG))
				.thenReturn(Constants.LangValues.EN_VALUE);
		new ChangeLangCommand().execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.SessionAttributes.LANG_ATTR, Constants.LangValues.EN_VALUE);

	}

}
