package ua.nure.gudkov.ST4.command;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.gudkov.ST4.constant.Constants;

public class GetPatientPhotoCommandTest extends Mockito {

	private Map<String, String> params = new HashMap<String, String>();

	@Test
	public void testExecute() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		new GetPatientPhotoCommand().execute(request, response);

		params.put(Constants.GenericParameters.ID_PATIENT, "50");
		request = initRequest(request);
		new GetPatientPhotoCommand().execute(request, response);

		
		
	}
	
	public HttpServletRequest initRequest(HttpServletRequest request) {
		return new HttpServletRequestWrapper(request) {
			public String getParameter(String name) {
				return params.get(name);
			}
		};
	}

}
