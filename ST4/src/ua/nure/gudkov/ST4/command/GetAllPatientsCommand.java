package ua.nure.gudkov.ST4.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gudkov.ST4.bean.app.PageBean;
import ua.nure.gudkov.ST4.bean.app.PageBean.Action;
import ua.nure.gudkov.ST4.bean.dto.PatientBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.converter.PathConverter;
import ua.nure.gudkov.ST4.converter.UsersToPatientsBeanConverter;
import ua.nure.gudkov.ST4.converter.api.Converter;
import ua.nure.gudkov.ST4.entity.ErrorHolder;
import ua.nure.gudkov.ST4.entity.User;
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.service.api.UserService;
import ua.nure.gudkov.ST4.util.Sorter;

/**
 * Get all patients command implementation.
 * 
 * @author A.Gudkov
 *
 */
public class GetAllPatientsCommand extends AbstractCommand {

	private static final Logger LOG = Logger.getLogger(GetAllPatientsCommand.class);
	private UserService userService;

	private Converter<List<User>, List<PatientBean>> converter;

	public GetAllPatientsCommand(UserService userService) {
		this.userService = userService;
		converter = new UsersToPatientsBeanConverter();
	}

	@Override
	public PageBean execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageBean pb = new PageBean();
		pb.setAction(Action.REDIRECT);
		pb.setPath(PathConverter.getInstance().getPath(Constants.PagePath.BASE_PATH + Constants.PagePath.ACCOUNT_PAGE));
		LOG.trace("Set path and action ----> " + pb.getPath() + ", " + pb.getAction());

		List<User> userList = new ArrayList<User>();
		List<PatientBean> patientList = new ArrayList<PatientBean>();
		ErrorHolder patientErrors = new ErrorHolder();
		HttpSession session = request.getSession();

		try {
			userList = userService.findAllPatients();
			if (!userList.isEmpty()) {
				LOG.trace("list of users got ----> " + ", " + userList);

				patientList = converter.convert(userList);
				LOG.trace("list of patients got ----> " + ", " + patientList);
			}

		}

		catch (DBException e) {
			patientErrors.add(e.getMessage());
			session.setAttribute(Constants.ErrorConstants.DB_ERRORS, patientErrors.getErrors());
			LOG.trace("catched dbexception ----> ");
			LOG.error(e);
			return pb;
		}

		if (patientList.isEmpty()) {
			patientErrors.add(Constants.PatientListParameters.MSG_ERROR_PATIENTS,
					Constants.ErrorConstants.FIND_PATIENTS_KEY);
			session.setAttribute(Constants.ErrorConstants.PATIENT_LIST_ERRORS, patientErrors.getErrors());
			LOG.trace("list of patients is empty ----> ");
			return pb;
		}

		String sortType = request.getParameter(Constants.PatientListParameters.SORTING_PARAMETER);
		if (sortType != null) {
			sortPatientList(patientList, sortType);
		}

		session.setAttribute(Constants.SessionAttributes.LIST_OF_PATIENTS, patientList);

		return pb;
	}

	/**
	 * Sorts list of patients by defined type.
	 * 
	 * @param patientList list of patients 
	 * @param sortType type of sorting
	 */
	private void sortPatientList(List<PatientBean> patientList, String sortType) {
		switch (sortType) {
		case Constants.SortConstants.SORT_PATIENTS_BY_ALPHABET:
			LOG.trace("sorting type ----> " + sortType);
			Sorter.sortPatientsByAlphabet(patientList);
			return;
		case Constants.SortConstants.SORT_PATIENTS_BY_DATE_OF_BIRTH:
			Sorter.sortPatientsByDateOfBirth(patientList);
			LOG.trace("sorting type ----> " + sortType);
			return;
		default:
			LOG.trace("sorting type ----> " + "default");
		}

	}

}
