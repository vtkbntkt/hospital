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
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.service.api.AnamnesisService;
import ua.nure.gudkov.ST4.service.api.UserService;

public class AssignDoctorCommandTest extends Mockito {
	private Map<String, String> params = new HashMap<String, String>();

	@Test
	public void testExecute() throws ServletException, IOException, DBException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		AnamnesisService anamnesisService = mock(AnamnesisService.class);
		UserService userService = mock(UserService.class);

		Map<String, String> errors = new HashMap<String, String>();

		errors.put(Constants.AssignDoctorParameters.ID_PATIENT_FIELD, Constants.ErrorConstants.CHOOSE_PATIENT_KEY);
		errors.put(Constants.AssignDoctorParameters.ID_DOCTOR_FIELD, Constants.ErrorConstants.CHOOSE_DOCTOR_KEY);

		when(request.getSession()).thenReturn(session);
		new AssignDoctorCommand(userService, anamnesisService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.ErrorConstants.ASSIGN_DOCTOR_ERRORS, errors);

		params.put(Constants.AssignDoctorParameters.ID_PATIENT_FIELD, "2016");
		params.put(Constants.AssignDoctorParameters.ID_DOCTOR_FIELD, "2016");
		request = initRequest(request);
		errors.clear();
		errors.put(Constants.AssignDoctorParameters.MSG_FOUND_DOCTOR_ERROR, Constants.ErrorConstants.FOUND_DOCTOR_KEY);
		new AssignDoctorCommand(userService, anamnesisService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.ErrorConstants.ASSIGN_DOCTOR_ERRORS, errors);

		when(userService.verifyDoctor(2016)).thenReturn(true);
		errors.clear();
		errors.put(Constants.AssignDoctorParameters.MSG_FOUND_PATIENT_ERROR,
				Constants.ErrorConstants.FOUND_PATIENT_KEY);
		new AssignDoctorCommand(userService, anamnesisService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.ErrorConstants.ASSIGN_DOCTOR_ERRORS, errors);

		when(userService.verifyPatient(2016)).thenReturn(true);
		errors.clear();
		errors.put(Constants.AssignDoctorParameters.MSG_ANAMNES_ADD_ERROR,
				Constants.ErrorConstants.ADD_ANAMNESIS_KEY);
		new AssignDoctorCommand(userService, anamnesisService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.ErrorConstants.ASSIGN_DOCTOR_ERRORS, errors);
		
		when(anamnesisService.verifyByIdPatient(2016)).thenReturn(true);
		errors.clear();
		errors.put(Constants.AssignDoctorParameters.MSG_ANAMNES_ADD_ERROR,
				Constants.ErrorConstants.ADD_ANAMNESIS_KEY);
		new AssignDoctorCommand(userService, anamnesisService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.ErrorConstants.ASSIGN_DOCTOR_ERRORS, errors);
		
		when(anamnesisService.verifyByIdPatient(2016)).thenReturn(false);
		when(anamnesisService.addAnamnesis(any())).thenReturn(true);
		errors.clear();
		errors.put(Constants.AssignDoctorParameters.MSG_ANAMNES_ADD_ERROR,
				Constants.ErrorConstants.ADD_ANAMNESIS_KEY);
		new AssignDoctorCommand(userService, anamnesisService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.ErrorConstants.ASSIGN_DOCTOR_ERRORS, errors);
		
		when(anamnesisService.verifyByIdPatient(2016)).thenReturn(false);
		when(anamnesisService.addAnamnesis(any())).thenReturn(false);
		errors.clear();
		errors.put(Constants.AssignDoctorParameters.MSG_ASSIGN_DOCTOR_SUCCESS,
				Constants.SuccessConstants.ASSIGN_NOTICE_KEY);
		new AssignDoctorCommand(userService, anamnesisService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.ErrorConstants.ASSIGN_DOCTOR_ERRORS, errors);
		
		errors.clear();
		errors.put(Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION,
				Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION);
		when(userService.verifyDoctor(2016))
				.thenThrow(new DBException(Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION));
		new AssignDoctorCommand(userService, anamnesisService).execute(request, response);
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
