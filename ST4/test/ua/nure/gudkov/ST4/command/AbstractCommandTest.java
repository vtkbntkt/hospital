package ua.nure.gudkov.ST4.command;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.entity.User;

public class AbstractCommandTest extends Mockito {

	private HttpServletRequest request = mock(HttpServletRequest.class);
	private HttpSession session = mock(HttpSession.class);
	private AbstractCommand ac = mock(AbstractCommand.class, Mockito.CALLS_REAL_METHODS);
	private Map<String, String> params = new HashMap<String, String>();


	@Test
	public void testCheckPatientAuthority() {
		Anamnesis anamnes = new Anamnesis();
		anamnes.setIdPatient(2016);
		User user = new User();
		
		when(session.getAttribute(Constants.SessionAttributes.USER_ATTR)).thenReturn(user);
		assertEquals(false, ac.checkPatientAuthority(session, anamnes));
		
		user.setId(2017l);
		assertEquals(false, ac.checkPatientAuthority(session, anamnes));
		
		user.setId(2016l);
		assertEquals(true, ac.checkPatientAuthority(session, anamnes));



	}

	@Test
	public void testCheckDoctorAuthority() {
		Anamnesis anamnes = new Anamnesis();
		anamnes.setIdDoctor(2016);
		User user = new User();
		
		when(session.getAttribute(Constants.SessionAttributes.USER_ATTR)).thenReturn(user);
		assertEquals(false, ac.checkDoctorAuthority(session, anamnes));
		
		user.setId(2017l);
		assertEquals(false, ac.checkDoctorAuthority(session, anamnes));
		
		user.setId(2016l);
		assertEquals(true, ac.checkDoctorAuthority(session, anamnes));
	}

	@Test
	public void testBackupParams() {
		
		Map<String, String> values = new HashMap<String, String>();
		ac.backupParams(request, session);
		
		params.put(Constants.GenericParameters.START_INDEX, "2016");
		values.put(Constants.GenericParameters.START_INDEX, "2016");
		request = initRequest(request);
		ac.backupParams(request, session);
        verify(session, atLeast(1)).setAttribute(Constants.GenericParameters.BACKUP_PARAMS, values);

	}
	
	public HttpServletRequest initRequest(HttpServletRequest request) {
		return new HttpServletRequestWrapper(request) {
			public String getParameter(String name) {
				return params.get(name);
			}
		};
	}

}
