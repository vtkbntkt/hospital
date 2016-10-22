package ua.nure.gudkov.ST4.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gudkov.ST4.bean.aggregation.UsersAnamnesisesBean;
import ua.nure.gudkov.ST4.bean.app.PageBean;
import ua.nure.gudkov.ST4.bean.app.PageBean.Action;
import ua.nure.gudkov.ST4.bean.dto.NewPatientBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.converter.PathConverter;
import ua.nure.gudkov.ST4.converter.UsersAnamnesisesBeanToNewPatientsBeanConverter;
import ua.nure.gudkov.ST4.converter.api.Converter;
import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.entity.ErrorHolder;
import ua.nure.gudkov.ST4.entity.User;
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.service.api.AnamnesisService;
import ua.nure.gudkov.ST4.service.api.UserService;
import ua.nure.gudkov.ST4.util.Sorter;

public class GetNewPatients extends AbstractCommand {

	private static final Logger LOG = Logger.getLogger(GetNewPatients.class);
	private UserService userService;
	private AnamnesisService anamnesisService;
	private Converter<UsersAnamnesisesBean, List<NewPatientBean>> converter;

	public GetNewPatients(UserService userService, AnamnesisService anamnesisService) {
		this.userService = userService;
		this.anamnesisService = anamnesisService;
		converter = new UsersAnamnesisesBeanToNewPatientsBeanConverter();
	}

	@Override
	public PageBean execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageBean pb = new PageBean();
		pb.setAction(Action.REDIRECT);
		pb.setPath(PathConverter.getInstance().getPath(Constants.PagePath.BASE_PATH + Constants.PagePath.ACCOUNT_PAGE));
		LOG.trace("Set path and action ----> " + pb.getPath() + ", " + pb.getAction());

		HttpSession session = request.getSession();

		List<User> userList = new ArrayList<User>();
		List<NewPatientBean> patientList = new ArrayList<NewPatientBean>();
		List<Anamnesis> anamnesList = new ArrayList<Anamnesis>();
		ErrorHolder patientErrors = new ErrorHolder();

		try {
			userList = userService.findAllUsers();
			if (!userList.isEmpty()) {
				LOG.trace("list of users got ----> " + ", " + userList);

				anamnesList = anamnesisService.findAllNewAnamnesis();
				if (!anamnesList.isEmpty()) {
					LOG.trace("list of anamnesis got ----> " + ", " + anamnesList);

					patientList = converter.convert(new UsersAnamnesisesBean(anamnesList, userList));
					LOG.trace("list of patients got ----> " + ", " + patientList);
				}
			}
		} catch (DBException e) {
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

		session.setAttribute(Constants.SessionAttributes.LIST_OF_NEW_PATIENTS, patientList);
		return pb;
	}

	private void sortPatientList(List<NewPatientBean> patientList, String sortType) {
		switch (sortType) {
		case Constants.SortConstants.SORT_PATIENTS_BY_DOCTOR:
			LOG.trace("sorting type ----> " + sortType);
			Sorter.sortPatientsByDoctor(patientList);
			return;
		default:
			LOG.trace("sorting type ----> " + "default");
		}

	}

}
