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
import ua.nure.gudkov.ST4.bean.dto.DoctorBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.converter.PathConverter;
import ua.nure.gudkov.ST4.converter.UsersAnamnesisesBeanToDoctorsBeanConverter;
import ua.nure.gudkov.ST4.converter.api.Converter;
import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.entity.ErrorHolder;
import ua.nure.gudkov.ST4.entity.User;
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.service.api.AnamnesisService;
import ua.nure.gudkov.ST4.service.api.UserService;
import ua.nure.gudkov.ST4.util.Sorter;
/**
 * Get all doctors command implementation.
 * 
 * @author A.Gudkov
 *
 */
public class GetAllDoctorsCommand extends AbstractCommand {
	private static final Logger LOG = Logger.getLogger(GetAllDoctorsCommand.class);
	private UserService userService;
	private AnamnesisService anamnesisService;
	private Converter<UsersAnamnesisesBean, List<DoctorBean>> converter;

	public GetAllDoctorsCommand(UserService userService, AnamnesisService anamnesisService) {
		this.userService = userService;
		this.anamnesisService = anamnesisService;
		converter = new UsersAnamnesisesBeanToDoctorsBeanConverter();

	}

	@Override
	public PageBean execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageBean pb = new PageBean();
		pb.setAction(Action.REDIRECT);
		pb.setPath(PathConverter.getInstance().getPath(Constants.PagePath.BASE_PATH + Constants.PagePath.ACCOUNT_PAGE));
		LOG.trace("Set path and action ----> " + pb.getPath() + ", " + pb.getAction());

		List<User> userList = new ArrayList<User>();
		List<Anamnesis> anamnesList = new ArrayList<Anamnesis>();
		List<DoctorBean> doctorList = new ArrayList<DoctorBean>();
		ErrorHolder doctorErrors = new ErrorHolder();
		HttpSession session = request.getSession();

		try {
			userList = userService.findAllDoctors();
			LOG.trace("list of doctors ----> "+userList);
			if (!userList.isEmpty()) {
				LOG.trace("list of users got ----> " + ", " + userList);

				anamnesList = anamnesisService.findAllNewAnamnesis();
				LOG.trace("list of anamnesis got ----> " + ", " + anamnesList);

				doctorList = converter.convert(new UsersAnamnesisesBean(anamnesList, userList));
				LOG.trace("list of doctors got ----> " + ", " + doctorList);
			}
		} catch (DBException e) {
			doctorErrors.add(e.getMessage());
			session.setAttribute(Constants.ErrorConstants.DB_ERRORS, doctorErrors.getErrors());
			LOG.trace("catched dbexception ----> ");
			LOG.error(e);
			return pb;
		} 

		if (doctorList.isEmpty()) {
			doctorErrors.add(Constants.DoctorListParameters.MSG_ERROR_DOCTORS,
					Constants.ErrorConstants.FIND_DOCTORS_KEY);
			session.setAttribute(Constants.ErrorConstants.DOCTOR_LIST_ERRORS, doctorErrors.getErrors());
			LOG.trace("list of doctors is empty ----> ");
			return pb;
		}
		String sortType = request.getParameter(Constants.DoctorListParameters.SORTING_PARAMETER);
		if (sortType != null) {
			sortDoctorList(doctorList, sortType);
		}
		session.setAttribute(Constants.SessionAttributes.LIST_OF_DOCTORS, doctorList);

		return pb;
	}

	/**
	 * Sorts list of doctors by defined type.
	 * 
	 * @param doctorList list of doctors 
	 * @param sortType type of sorting
	 */
	private void sortDoctorList(List<DoctorBean> doctorList, String sortType) {
		switch (sortType) {
		case Constants.SortConstants.SORT_DOCTORS_BY_ALPHABET:
			LOG.trace("sorting type ----> " + sortType);
			Sorter.sortDoctorsByAlphabet(doctorList);
			return;
		case Constants.SortConstants.SORT_DOCTORS_BY_CATEGORY:
			Sorter.sortDoctorsByCategory(doctorList);
			LOG.trace("sorting type ----> " + sortType);
			return;
		case Constants.SortConstants.SORT_DOCTORS_BY_PATIENT_NUMBER:
			Sorter.sortDoctorsByPatientNum(doctorList);
			LOG.trace("sorting type ----> " + sortType);
			return;
		default:
			LOG.trace("sorting type ----> " + "default");
		}

	}

}
