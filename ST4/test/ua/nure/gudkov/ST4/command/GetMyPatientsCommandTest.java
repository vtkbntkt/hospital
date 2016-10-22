package ua.nure.gudkov.ST4.command;


import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.entity.User;
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.service.api.AnamnesisService;
import ua.nure.gudkov.ST4.service.api.UserService;

public class GetMyPatientsCommandTest extends Mockito {

	@Test
	public void testExecute() throws ServletException, IOException, DBException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		UserService userService = mock(UserService.class);
		AnamnesisService anamnesisService = mock(AnamnesisService.class);
		Map<String, String> errors = new HashMap<String, String>();

		errors.put(Constants.MyPatientsParameters.MSG_ERROR_PATIENTS,
		Constants.ErrorConstants.FIND_PATIENTS_KEY);
		when(request.getSession()).thenReturn(session);
		new GetMyPatientsCommand(userService, anamnesisService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.ErrorConstants.MY_PATIENTS_ERRORS, errors);
		
		
		when(userService.findAllPatients()).thenReturn(initUsers());
		when(session.getAttribute(Constants.SessionAttributes.USER_ATTR)).thenReturn(initUsers().get(0));
		new GetMyPatientsCommand(userService, anamnesisService).execute(request, response);
		
		when(anamnesisService.findAllNewAnamnesisByIdDoctor(2016)).thenReturn(initAnamnesis());
		new GetMyPatientsCommand(userService, anamnesisService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(eq(Constants.SessionAttributes.LIST_OF_MYPATIENTS), notNull());
		
		errors.clear();
		errors.put(Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION,
				Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION);
		when(anamnesisService.findAllNewAnamnesisByIdDoctor(2016))
				.thenThrow(new DBException(Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION));
		new GetMyPatientsCommand(userService, anamnesisService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.ErrorConstants.DB_ERRORS, errors);


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
	
	public List<Anamnesis> initAnamnesis() {
		List<Anamnesis> anamnesises = new ArrayList<Anamnesis>();
		Anamnesis anamnes = new Anamnesis();
		anamnes.setIdStatus(2);
		anamnes.setIdDoctor(2016);
		anamnes.setIdPatient(2016);
		anamnesises.add(anamnes);
		return anamnesises;
	}

}
