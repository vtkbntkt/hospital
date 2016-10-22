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

import ua.nure.gudkov.ST4.bean.dto.DoctorBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.entity.User;
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.service.api.AnamnesisService;
import ua.nure.gudkov.ST4.service.api.UserService;

public class GetAllDoctorsCommandTest extends Mockito {

	@Test
	public void testExecute() throws ServletException, IOException, DBException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);

		Map<String, String> errors = new HashMap<String, String>();
		errors.put(Constants.DoctorListParameters.MSG_ERROR_DOCTORS, Constants.ErrorConstants.FIND_DOCTORS_KEY);

		List<User> userList = initUsers();

		List<Anamnesis> anamnesises = new ArrayList<Anamnesis>();
		Anamnesis anamnes = new Anamnesis();
		anamnesises.add(anamnes);

		UserService userService = mock(UserService.class);
		AnamnesisService anamnesisService = mock(AnamnesisService.class);

		when(request.getSession()).thenReturn(session);

		new GetAllDoctorsCommand(userService, anamnesisService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(Constants.ErrorConstants.DOCTOR_LIST_ERRORS, errors);

		when(userService.findAllDoctors()).thenReturn(userList);
		when(anamnesisService.findAllNewAnamnesis()).thenReturn(anamnesises);

		new GetAllDoctorsCommand(userService, anamnesisService).execute(request, response);
		verify(session, atLeast(1)).setAttribute(eq(Constants.SessionAttributes.LIST_OF_DOCTORS),
				Matchers.anyListOf(DoctorBean.class));

		when(request.getParameter(Constants.DoctorListParameters.SORTING_PARAMETER))
				.thenReturn(Constants.SortConstants.SORT_DOCTORS_BY_ALPHABET);
		new GetAllDoctorsCommand(userService, anamnesisService).execute(request, response);
		verify(request, atLeast(1)).getParameter(Constants.DoctorListParameters.SORTING_PARAMETER);
		verify(session, atLeast(1)).setAttribute(eq(Constants.SessionAttributes.LIST_OF_DOCTORS),
				Matchers.anyListOf(DoctorBean.class));

		when(request.getParameter(Constants.DoctorListParameters.SORTING_PARAMETER))
				.thenReturn(Constants.SortConstants.SORT_DOCTORS_BY_CATEGORY);
		new GetAllDoctorsCommand(userService, anamnesisService).execute(request, response);
		verify(request, atLeast(1)).getParameter(Constants.DoctorListParameters.SORTING_PARAMETER);
		verify(session, atLeast(1)).setAttribute(eq(Constants.SessionAttributes.LIST_OF_DOCTORS),
				Matchers.anyListOf(DoctorBean.class));

		when(request.getParameter(Constants.DoctorListParameters.SORTING_PARAMETER))
				.thenReturn(Constants.SortConstants.SORT_DOCTORS_BY_PATIENT_NUMBER);
		new GetAllDoctorsCommand(userService, anamnesisService).execute(request, response);
		verify(request, atLeast(1)).getParameter(Constants.DoctorListParameters.SORTING_PARAMETER);
		verify(session, atLeast(1)).setAttribute(eq(Constants.SessionAttributes.LIST_OF_DOCTORS),
				Matchers.anyListOf(DoctorBean.class));

		when(request.getParameter(Constants.DoctorListParameters.SORTING_PARAMETER)).thenReturn(" ");
		new GetAllDoctorsCommand(userService, anamnesisService).execute(request, response);
		verify(request, atLeast(1)).getParameter(Constants.DoctorListParameters.SORTING_PARAMETER);
		verify(session, atLeast(1)).setAttribute(eq(Constants.SessionAttributes.LIST_OF_DOCTORS),
				Matchers.anyListOf(DoctorBean.class));

		errors.clear();
		errors.put(Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION,
				Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION);
		when(userService.findAllDoctors())
				.thenThrow(new DBException(Constants.ErrorConstants.ERR_CANNOT_EXECUTE_OPERATION));
		new GetAllDoctorsCommand(userService, anamnesisService).execute(request, response);
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

}
