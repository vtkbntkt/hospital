package ua.nure.gudkov.ST4.command;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ua.nure.gudkov.ST4.command.api.Command;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.service.api.AnamnesisService;
import ua.nure.gudkov.ST4.service.api.ServiceHistoryService;
import ua.nure.gudkov.ST4.service.api.UserService;

/**
 * Holder for all commands.
 * 
 * @author A.Gudkov
 * 
 */
public class CommandContainer {
	private static final Logger LOG = Logger.getLogger(CommandContainer.class);
	private HashMap<String, Command> commands = new HashMap<String, Command>();

	public CommandContainer(UserService userService, AnamnesisService anamnesisService,
			ServiceHistoryService servHistoryService) {
		commands.put(Constants.Commands.CHANGE_LANG_COMMAND, new ChangeLangCommand());
		commands.put(Constants.Commands.SIGN_IN_COMMAND, new SignInCommand(userService));
		commands.put(Constants.Commands.GET_ACCOUNT_COMMAND, new GetAccountCommand());
		commands.put(Constants.Commands.GET_LOGIN_COMMAND, new GetLoginCommand());
		commands.put(Constants.Commands.REG_USER_COMMAND, new RegUserCommand(userService));
		commands.put(Constants.Commands.GET_ALL_DOCTORS, new GetAllDoctorsCommand(userService, anamnesisService));
		commands.put(Constants.Commands.GET_ALL_PATIENTS, new GetAllPatientsCommand(userService));
		commands.put(Constants.Commands.GET_ASSIGNDOCTOR_FORM, new GetAssignDoctorFormCommand(userService));
		commands.put(Constants.Commands.ASSIGN_DOCTOR_COMMAND, new AssignDoctorCommand(userService, anamnesisService));
		commands.put(Constants.Commands.LOGOUT_COMMAND, new LogoutCommand());
		commands.put(Constants.Commands.GET_MY_PATIENTS, new GetMyPatientsCommand(userService, anamnesisService));
		commands.put(Constants.Commands.GET_PATIENT_CARD,
				new GetPatientCardCommand(userService, anamnesisService, servHistoryService));
		commands.put(Constants.Commands.ADD_SERVICE, new AddServiceCommand(anamnesisService, servHistoryService));
		commands.put(Constants.Commands.ADD_DIAGNOSIS, new AddDiagnosisCommand(anamnesisService));
		commands.put(Constants.Commands.GET_DOCTOR_TASKS,
				new GetDoctorTasks(userService, anamnesisService, servHistoryService));
		commands.put(Constants.Commands.PERFORM_TASK, new PerformTaskCommand(servHistoryService));
		commands.put(Constants.Commands.UPLOAD_PHOTO, new UploadPhotoCommand());
		commands.put(Constants.Commands.GET_PHOTO, new GetPatientPhotoCommand());
		commands.put(Constants.Commands.DISCHARGE_PATIENT, new DischargePatientCommand(anamnesisService));
		commands.put(Constants.Commands.GET_MYCARDS, new GetMyCardsCommand(userService, anamnesisService));
		commands.put(Constants.Commands.PRINT_MYCARD,
				new PrintMyCardCommand(userService, anamnesisService, servHistoryService));
		commands.put(Constants.Commands.GET_NEW_PATIENTS, new GetNewPatients(userService, anamnesisService));

		LOG.debug("Command container was successfully initialized");
		LOG.trace("Number of commands --> " + commands.size());

	}

	/**
	 * Returns command object with the given name.
	 * 
	 * @param request
	 *            to be used to extract name of the command.
	 * @return Command object.
	 */
	public Command getCommand(HttpServletRequest request) {
		Command command = commands.get(request.getParameter(Constants.Commands.COMMAND));
		if (command == null) {
			command = new NoCommand();
		}
		return command;
	}

}
