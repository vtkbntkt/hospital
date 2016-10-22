package ua.nure.gudkov.ST4.command;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.service.api.AnamnesisService;
import ua.nure.gudkov.ST4.service.api.ServiceHistoryService;
import ua.nure.gudkov.ST4.service.api.UserService;

public class CommandContainerTest extends Mockito {
	private Map<String, String> params = new HashMap<String, String>();

	@Test
	public void testGetCommand() {
		AnamnesisService anamnesisService = mock(AnamnesisService.class);
		UserService userService = mock(UserService.class);
		ServiceHistoryService servHistoryService = mock(ServiceHistoryService.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
		CommandContainer cc = new CommandContainer(userService, anamnesisService, servHistoryService);

		assertEquals(cc.getCommand(request).getClass(), NoCommand.class);

		params.put(Constants.Commands.COMMAND, Constants.Commands.CHANGE_LANG_COMMAND);
		request = initRequest(request);
		assertEquals(cc.getCommand(request).getClass(), ChangeLangCommand.class);

		params.put(Constants.Commands.COMMAND, Constants.Commands.SIGN_IN_COMMAND);
		request = initRequest(request);
		assertEquals(cc.getCommand(request).getClass(), SignInCommand.class);

		params.put(Constants.Commands.COMMAND, Constants.Commands.GET_ACCOUNT_COMMAND);
		request = initRequest(request);
		assertEquals(cc.getCommand(request).getClass(), GetAccountCommand.class);

		params.put(Constants.Commands.COMMAND, Constants.Commands.GET_LOGIN_COMMAND);
		request = initRequest(request);
		assertEquals(cc.getCommand(request).getClass(), GetLoginCommand.class);

		params.put(Constants.Commands.COMMAND, Constants.Commands.REG_USER_COMMAND);
		request = initRequest(request);
		assertEquals(cc.getCommand(request).getClass(), RegUserCommand.class);

		params.put(Constants.Commands.COMMAND, Constants.Commands.GET_ALL_DOCTORS);
		request = initRequest(request);
		assertEquals(cc.getCommand(request).getClass(), GetAllDoctorsCommand.class);

		params.put(Constants.Commands.COMMAND, Constants.Commands.GET_ALL_PATIENTS);
		request = initRequest(request);
		assertEquals(cc.getCommand(request).getClass(), GetAllPatientsCommand.class);

		params.put(Constants.Commands.COMMAND, Constants.Commands.GET_ASSIGNDOCTOR_FORM);
		request = initRequest(request);
		assertEquals(cc.getCommand(request).getClass(), GetAssignDoctorFormCommand.class);

		params.put(Constants.Commands.COMMAND, Constants.Commands.ASSIGN_DOCTOR_COMMAND);
		request = initRequest(request);
		assertEquals(cc.getCommand(request).getClass(), AssignDoctorCommand.class);

		params.put(Constants.Commands.COMMAND, Constants.Commands.LOGOUT_COMMAND);
		request = initRequest(request);
		assertEquals(cc.getCommand(request).getClass(), LogoutCommand.class);

		params.put(Constants.Commands.COMMAND, Constants.Commands.GET_MY_PATIENTS);
		request = initRequest(request);
		assertEquals(cc.getCommand(request).getClass(), GetMyPatientsCommand.class);

		params.put(Constants.Commands.COMMAND, Constants.Commands.GET_PATIENT_CARD);
		request = initRequest(request);
		assertEquals(cc.getCommand(request).getClass(), GetPatientCardCommand.class);

		params.put(Constants.Commands.COMMAND, Constants.Commands.ADD_SERVICE);
		request = initRequest(request);
		assertEquals(cc.getCommand(request).getClass(), AddServiceCommand.class);

		params.put(Constants.Commands.COMMAND, Constants.Commands.ADD_DIAGNOSIS);
		request = initRequest(request);
		assertEquals(cc.getCommand(request).getClass(), AddDiagnosisCommand.class);

		params.put(Constants.Commands.COMMAND, Constants.Commands.GET_DOCTOR_TASKS);
		request = initRequest(request);
		assertEquals(cc.getCommand(request).getClass(), GetDoctorTasks.class);

		params.put(Constants.Commands.COMMAND, Constants.Commands.PERFORM_TASK);
		request = initRequest(request);
		assertEquals(cc.getCommand(request).getClass(), PerformTaskCommand.class);

		params.put(Constants.Commands.COMMAND, Constants.Commands.UPLOAD_PHOTO);
		request = initRequest(request);
		assertEquals(cc.getCommand(request).getClass(), UploadPhotoCommand.class);

		params.put(Constants.Commands.COMMAND, Constants.Commands.GET_PHOTO);
		request = initRequest(request);
		assertEquals(cc.getCommand(request).getClass(), GetPatientPhotoCommand.class);

		params.put(Constants.Commands.COMMAND, Constants.Commands.DISCHARGE_PATIENT);
		request = initRequest(request);
		assertEquals(cc.getCommand(request).getClass(), DischargePatientCommand.class);

		params.put(Constants.Commands.COMMAND, Constants.Commands.GET_MYCARDS);
		request = initRequest(request);
		assertEquals(cc.getCommand(request).getClass(), GetMyCardsCommand.class);

		params.put(Constants.Commands.COMMAND, Constants.Commands.PRINT_MYCARD);
		request = initRequest(request);
		assertEquals(cc.getCommand(request).getClass(), PrintMyCardCommand.class);

	}

	public HttpServletRequest initRequest(HttpServletRequest request) {
		return new HttpServletRequestWrapper(request) {
			public String getParameter(String name) {
				return params.get(name);
			}
		};
	}

}
