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

public class AddDiagnosisCommandTest extends Mockito {
	private Map<String, String> params = new HashMap<String, String>();

	@Test
	public void testExecute() throws ServletException, IOException, DBException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		AnamnesisService anamnesisService = mock(AnamnesisService.class);		
		Map<String, String> errors = new HashMap<String, String>();

		errors.put(Constants.PatientCardParameters.DIAGNOSIS_VALUE_FIELD,
				Constants.ErrorConstants.ADD_DIAGNOSIS_KEY);
		errors.put(Constants.ErrorConstants.ADD_DIAGNOSIS_HIDDEN_PARAMS_KEY,
				Constants.ErrorConstants.ADD_DIAGNOSIS_HIDDEN_PARAMS_KEY);

		when(request.getSession()).thenReturn(session);
		new AddDiagnosisCommand(anamnesisService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.ErrorConstants.ADD_DIAGNOSIS_ERRORS, errors);
		
		params.put(Constants.PatientCardParameters.DIAGNOSIS_VALUE_FIELD, "nothing");
		params.put(Constants.PatientCardParameters.ID_ANAMNESIS, "2016");
		params.put(Constants.PatientCardParameters.ID_PATIENT, "2016");
		request = initRequest(request);
		new AddDiagnosisCommand(anamnesisService).execute(request, response);
		
		Anamnesis anamnes = new Anamnesis();
		anamnes.setIdDoctor(2016);
		when(anamnesisService.findByIdAnamnesis(anyInt())).thenReturn(anamnes);
		new AddDiagnosisCommand(anamnesisService).execute(request, response);
		
		User user = new User();
		user.setId(2016l);
		when(session.getAttribute(Constants.SessionAttributes.USER_ATTR)).thenReturn(user);
		new AddDiagnosisCommand(anamnesisService).execute(request, response);
		
		when(anamnesisService.updateInitialDiagnosis(any(),anyInt())).thenReturn(true);
		new AddDiagnosisCommand(anamnesisService).execute(request, response);
		
		errors.clear();
		errors.put(Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION,
				Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION);
		when(anamnesisService.updateInitialDiagnosis(any(),anyInt()))
				.thenThrow(new DBException(Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION));
		new AddDiagnosisCommand(anamnesisService).execute(request, response);
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
