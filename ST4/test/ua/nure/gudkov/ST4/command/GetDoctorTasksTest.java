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
import ua.nure.gudkov.ST4.entity.ServiceHistory;
import ua.nure.gudkov.ST4.entity.User;
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.service.api.AnamnesisService;
import ua.nure.gudkov.ST4.service.api.ServiceHistoryService;
import ua.nure.gudkov.ST4.service.api.UserService;

public class GetDoctorTasksTest extends Mockito {

	@Test
	public void testExecute() throws ServletException, IOException, DBException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		UserService userService = mock(UserService.class);
		AnamnesisService anamnesisService = mock(AnamnesisService.class);
		ServiceHistoryService servHistoryService = mock(ServiceHistoryService.class);

		Map<String, String> errors = new HashMap<String, String>();

		errors.put(Constants.TaskListParameters.MSG_ERROR_TASKS, Constants.ErrorConstants.FIND_TASKS_KEY);
		when(request.getSession()).thenReturn(session);
		new GetDoctorTasks(userService, anamnesisService, servHistoryService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.ErrorConstants.TASK_LIST_ERRORS, errors);

		when(userService.findAllUsers()).thenReturn(initUsers());
		when(anamnesisService.findAllNewAnamnesis()).thenReturn(initAnamnesis());
		new GetDoctorTasks(userService, anamnesisService, servHistoryService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.ErrorConstants.TASK_LIST_ERRORS, errors);

		when(servHistoryService.findAllTakenServiceHistory()).thenReturn(initServHistory());
		new GetDoctorTasks(userService, anamnesisService, servHistoryService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(eq(Constants.SessionAttributes.LIST_OF_TASKS), notNull());

		User user = initUsers().get(0);
		user.setRoleId(3);
		when(session.getAttribute(Constants.SessionAttributes.USER_ATTR)).thenReturn(user);
		new GetDoctorTasks(userService, anamnesisService, servHistoryService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(eq(Constants.SessionAttributes.LIST_OF_TASKS), notNull());

		
		List<ServiceHistory> shistories = initServHistory();
		shistories.get(0).setIdService(2);
		when(servHistoryService.findAllTakenServiceHistory()).thenReturn(shistories);
		new GetDoctorTasks(userService, anamnesisService, servHistoryService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(eq(Constants.SessionAttributes.LIST_OF_TASKS), notNull());
		
		errors.clear();
		errors.put(Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION,
				Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION);
		when(servHistoryService.findAllTakenServiceHistory())
				.thenThrow(new DBException(Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION));
		new GetDoctorTasks(userService, anamnesisService, servHistoryService).execute(request, response);
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

	public List<ServiceHistory> initServHistory() {
		List<ServiceHistory> shistories = new ArrayList<ServiceHistory>();
		ServiceHistory sh = new ServiceHistory();

		shistories.add(sh);
		return shistories;
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
