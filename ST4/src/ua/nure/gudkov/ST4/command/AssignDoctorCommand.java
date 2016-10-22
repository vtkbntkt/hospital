package ua.nure.gudkov.ST4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gudkov.ST4.bean.app.PageBean;
import ua.nure.gudkov.ST4.bean.app.PageBean.Action;
import ua.nure.gudkov.ST4.bean.form.AssignDoctorFormBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.converter.AssignDoctorFormBeanToAnamnesis;
import ua.nure.gudkov.ST4.converter.PathConverter;
import ua.nure.gudkov.ST4.converter.api.Converter;
import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.entity.ErrorHolder;
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.extractor.AssignDoctorFormBeanExtractor;
import ua.nure.gudkov.ST4.extractor.api.BeanExtractor;
import ua.nure.gudkov.ST4.service.api.AnamnesisService;
import ua.nure.gudkov.ST4.service.api.UserService;
import ua.nure.gudkov.ST4.validator.AssignDoctorFormBeanValidator;
import ua.nure.gudkov.ST4.validator.BeanValidator;

/**
 *Assign doctor command implementation.
 * 
 * @author A.Gudkov
 *
 */
public class AssignDoctorCommand extends AbstractCommand {
	private static final Logger LOG = Logger.getLogger(AssignDoctorCommand.class);
	private UserService userService;
	private AnamnesisService anamnesisService;
	private BeanExtractor<AssignDoctorFormBean> beanExtractor;
	private BeanValidator<AssignDoctorFormBean> beanValidator;
	private Converter<AssignDoctorFormBean, Anamnesis> converter;

	public AssignDoctorCommand(UserService userService, AnamnesisService anamnesisService) {
		this.userService = userService;
		this.anamnesisService = anamnesisService;
		beanExtractor = new AssignDoctorFormBeanExtractor();
		beanValidator = new AssignDoctorFormBeanValidator();
		converter = new AssignDoctorFormBeanToAnamnesis();
	}

	@Override
	public PageBean execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageBean pb = new PageBean();
		pb.setAction(Action.REDIRECT);
		pb.setPath(Constants.Commands.SERVLET_GET_ASSIGN_DOC_FORM_COMMAND);
		LOG.trace("Set path and action ----> " + pb.getPath() + ", " + pb.getAction());

		AssignDoctorFormBean assignDoctorFormBean = beanExtractor.extract(request);
		LOG.trace("AssignDoctorFormBean extracted ----> " + assignDoctorFormBean);

		ErrorHolder assignDoctorFormErrors = beanValidator.validate(assignDoctorFormBean);
		HttpSession session = request.getSession();
		Anamnesis anamnesis = null;

		if (!assignDoctorFormErrors.isEmpty()) {
			session.setAttribute(Constants.ErrorConstants.ASSIGN_DOCTOR_ERRORS, assignDoctorFormErrors.getErrors());
			LOG.trace("Registration errors ----> " + assignDoctorFormErrors.getErrors());
			return pb;
		}

		anamnesis = converter.convert(assignDoctorFormBean);
		LOG.trace("Anamnesis converted ----> " + anamnesis);

		try {
			/*
			 * check whether doctor exists
			 */
			if (!userService.verifyDoctor(anamnesis.getIdDoctor())) {
				assignDoctorFormErrors.add(Constants.AssignDoctorParameters.MSG_FOUND_DOCTOR_ERROR,
						Constants.ErrorConstants.FOUND_DOCTOR_KEY);
				LOG.trace("Doctor does not exist ----> " + anamnesis.getIdDoctor());
			}

			/*
			 * check whether patient exists
			 */
			else if (!userService.verifyPatient(anamnesis.getIdPatient())) {
				assignDoctorFormErrors.add(Constants.AssignDoctorParameters.MSG_FOUND_PATIENT_ERROR,
						Constants.ErrorConstants.FOUND_PATIENT_KEY);
				LOG.trace("Patient does not exist ----> " + anamnesis.getIdPatient());
			}

			/*
			 * check whether anamnesis exists
			 */
			else if (anamnesisService.verifyByIdPatient(anamnesis.getIdPatient())) {
				assignDoctorFormErrors.add(Constants.AssignDoctorParameters.MSG_ANAMNES_EXIST_ERROR,
						Constants.ErrorConstants.EXISTS_ANAMNESIS_KEY);
				LOG.trace("Anamnesis already exists ----> " + anamnesis.getIdPatient());

			/*
			 * add anamnesis
			 */
			} else if (!anamnesisService.addAnamnesis(anamnesis)) {
				assignDoctorFormErrors.add(Constants.AssignDoctorParameters.MSG_ANAMNES_ADD_ERROR,
						Constants.ErrorConstants.ADD_ANAMNESIS_KEY);
				LOG.trace("Anamnesis was not added ----> " + anamnesis.getIdPatient());
				
			/*
			 * add success message
			 */
			} else {
				assignDoctorFormErrors.add(Constants.AssignDoctorParameters.MSG_ASSIGN_DOCTOR_SUCCESS,
						Constants.SuccessConstants.ASSIGN_NOTICE_KEY);
				LOG.trace("Anamnesis was added successfully ----> " + anamnesis.getIdPatient());
			}

		} catch (DBException e) {
			assignDoctorFormErrors.add(e.getMessage());
			session.setAttribute(Constants.ErrorConstants.DB_ERRORS, assignDoctorFormErrors.getErrors());
			LOG.trace("catched dbexception ----> ");
			LOG.error(e);
			pb.setPath(PathConverter.getInstance()
					.getPath(Constants.PagePath.BASE_PATH + Constants.PagePath.ACCOUNT_PAGE));
			return pb;
		}
		/*
		 * set success or error message
		 */
		session.setAttribute(Constants.ErrorConstants.ASSIGN_DOCTOR_ERRORS, assignDoctorFormErrors.getErrors());
		return pb;
	}

}
