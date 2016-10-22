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
import ua.nure.gudkov.ST4.bean.dto.PatientBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.converter.PathConverter;
import ua.nure.gudkov.ST4.converter.UsersAnamnesisesBeanToDoctorsBeanConverter;
import ua.nure.gudkov.ST4.converter.UsersToPatientsBeanConverter;
import ua.nure.gudkov.ST4.converter.api.Converter;
import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.entity.ErrorHolder;
import ua.nure.gudkov.ST4.entity.User;
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.service.api.UserService;

/**
 * Get assigndoctor form command implementation.
 * 
 * @author A.Gudkov
 *
 */
public class GetAssignDoctorFormCommand extends AbstractCommand {

	private static final Logger LOG = Logger.getLogger(GetAssignDoctorFormCommand.class);
	private UserService userService;
	private Converter<UsersAnamnesisesBean, List<DoctorBean>> docConverter;
	private Converter<List<User>, List<PatientBean>> patConverter;

	public GetAssignDoctorFormCommand(UserService userService) {
		this.userService = userService;
		docConverter = new UsersAnamnesisesBeanToDoctorsBeanConverter();
		patConverter = new UsersToPatientsBeanConverter();

	}

	@Override
	public PageBean execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageBean pb = new PageBean();
		pb.setAction(Action.REDIRECT);
		pb.setPath(PathConverter.getInstance().getPath(Constants.PagePath.BASE_PATH + Constants.PagePath.ACCOUNT_PAGE));
		LOG.trace("Set path and action ----> " + pb.getPath() + ", " + pb.getAction());

		List<User> userDocList = new ArrayList<User>();
		List<User> userPatList = new ArrayList<User>();
		List<DoctorBean> doctorList = new ArrayList<DoctorBean>();
		List<PatientBean> patientList = new ArrayList<PatientBean>();
		ErrorHolder docPatErrors = new ErrorHolder();
		HttpSession session = request.getSession();

		try {
			userPatList = userService.findAllPatients();
			if (!userPatList.isEmpty()) {
				LOG.trace("list of users-patients got ----> " + ", " + userPatList);

				userDocList = userService.findAllDoctors();
				if (!userDocList.isEmpty()) {
					LOG.trace("list of users-doctors got ----> " + ", " + userDocList);

					doctorList = docConverter
							.convert(new UsersAnamnesisesBean(new ArrayList<Anamnesis>(), userDocList));
					LOG.trace("list of doctors got ----> " + ", " + doctorList);

					patientList = patConverter.convert(userPatList);
					LOG.trace("list of patients got ----> " + ", " + patientList);
				}
			}

		}

		catch (DBException e) {
			docPatErrors.add(e.getMessage());
			session.setAttribute(Constants.ErrorConstants.DB_ERRORS, docPatErrors.getErrors());
			LOG.trace("catched dbexception ----> ");
			LOG.error(e);
			return pb;
		}

		if (doctorList.isEmpty() || patientList.isEmpty()) {
			docPatErrors.add(Constants.AssignDoctorParameters.MSG_ERROR_DOC_PAT,
					Constants.ErrorConstants.FIND_DOCTORS_PATIENTS_KEY);
			session.setAttribute(Constants.ErrorConstants.DOCTOR_PATIENT_LIST_ERRORS, docPatErrors.getErrors());
			LOG.trace("list of doctors or patients is empty ----> ");
			return pb;
		}

		session.setAttribute(Constants.SessionAttributes.DATALIST_OF_DOCTORS, doctorList);
		session.setAttribute(Constants.SessionAttributes.DATALIST_OF_PATIENTS, patientList);

		return pb;
	}

}
