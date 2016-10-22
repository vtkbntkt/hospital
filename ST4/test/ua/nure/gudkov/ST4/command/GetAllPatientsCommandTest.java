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
import org.mockito.Matchers;
import org.mockito.Mockito;

import ua.nure.gudkov.ST4.bean.dto.PatientBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.entity.User;
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.service.api.UserService;

public class GetAllPatientsCommandTest extends Mockito {

	@Test
	public void testExecute() throws ServletException, IOException, DBException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);

		Map<String, String> errors = new HashMap<String, String>();
		errors.put(Constants.PatientListParameters.MSG_ERROR_PATIENTS, Constants.ErrorConstants.FIND_PATIENTS_KEY);

		List<User> userList = initUsers();
		UserService userService = mock(UserService.class);

		when(request.getSession()).thenReturn(session);
		new GetAllPatientsCommand(userService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.ErrorConstants.PATIENT_LIST_ERRORS, errors);

		when(userService.findAllPatients()).thenReturn(userList);
		new GetAllPatientsCommand(userService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(eq(Constants.SessionAttributes.LIST_OF_PATIENTS),
				Matchers.anyListOf(PatientBean.class));

		when(request.getParameter(Constants.PatientListParameters.SORTING_PARAMETER))
				.thenReturn(Constants.SortConstants.SORT_PATIENTS_BY_ALPHABET);
		new GetAllPatientsCommand(userService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(eq(Constants.SessionAttributes.LIST_OF_PATIENTS),
				Matchers.anyListOf(PatientBean.class));

		when(request.getParameter(Constants.PatientListParameters.SORTING_PARAMETER))
				.thenReturn(Constants.SortConstants.SORT_PATIENTS_BY_DATE_OF_BIRTH);
		new GetAllPatientsCommand(userService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(eq(Constants.SessionAttributes.LIST_OF_PATIENTS),
				Matchers.anyListOf(PatientBean.class));

		when(request.getParameter(Constants.PatientListParameters.SORTING_PARAMETER)).thenReturn(" ");
		new GetAllPatientsCommand(userService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(eq(Constants.SessionAttributes.LIST_OF_PATIENTS),
				Matchers.anyListOf(PatientBean.class));

		errors.clear();
		errors.put(Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION,
				Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION);
		when(userService.findAllPatients())
				.thenThrow(new DBException(Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION));
		new GetAllPatientsCommand(userService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.ErrorConstants.DB_ERRORS, errors);

	}

	public List<User> initUsers() {
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setCategoryId(1);
		user.setDateOfBirth(Date.valueOf("1987-11-11"));
		user.setEmail("bla@mail.ru");
		user.setFirstName("Test");
		user.setId(2016l);
		user.setLastName("Testow");
		user.setPassword("qwerty");
		user.setPhone("000 000 0000");
		user.setRoleId(1);
		users.add(user);
		return users;
	}

}
