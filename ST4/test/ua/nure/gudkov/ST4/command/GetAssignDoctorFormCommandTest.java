package ua.nure.gudkov.ST4.command;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import ua.nure.gudkov.ST4.bean.dto.DoctorBean;
import ua.nure.gudkov.ST4.bean.dto.PatientBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.entity.User;
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.service.api.UserService;

public class GetAssignDoctorFormCommandTest extends Mockito {

	@Test
	public void testExecute() throws ServletException, IOException, DBException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		UserService userService = mock(UserService.class);

		Map<String, String> errors = new HashMap<String, String>();
		errors.put(Constants.AssignDoctorParameters.MSG_ERROR_DOC_PAT,
				Constants.ErrorConstants.FIND_DOCTORS_PATIENTS_KEY);

		when(request.getSession()).thenReturn(session);
		new GetAssignDoctorFormCommand(userService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.ErrorConstants.DOCTOR_PATIENT_LIST_ERRORS, errors);
		
		List<User> userPatList = new ArrayList<User>();
		userPatList.add(new User());
		when(userService.findAllPatients()).thenReturn(userPatList);
		new GetAssignDoctorFormCommand(userService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.ErrorConstants.DOCTOR_PATIENT_LIST_ERRORS, errors);
		
		List<User> userDocList = new ArrayList<User>();
		User user = new User();
		user.setCategoryId(1);
		user.setId(2016l);
		userDocList.add(user);
		when(userService.findAllDoctors()).thenReturn(userDocList);
		new GetAssignDoctorFormCommand(userService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(eq(Constants.SessionAttributes.DATALIST_OF_DOCTORS),
				Matchers.anyListOf(DoctorBean.class));
		verify(session, atLeast(1)).setAttribute(eq(Constants.SessionAttributes.DATALIST_OF_PATIENTS),
				Matchers.anyListOf(PatientBean.class));
		
		errors.clear();
		errors.put(Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION,
				Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION);
		when(userService.findAllDoctors())
				.thenThrow(new DBException(Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION));
		new GetAssignDoctorFormCommand(userService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.ErrorConstants.DB_ERRORS, errors);


	}

}
