package ua.nure.gudkov.ST4.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gudkov.ST4.bean.aggregation.UserAnamnesisServHistoryBean;
import ua.nure.gudkov.ST4.bean.app.PageBean;
import ua.nure.gudkov.ST4.bean.app.PageBean.Action;
import ua.nure.gudkov.ST4.bean.dto.PatientCardBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.converter.PathConverter;
import ua.nure.gudkov.ST4.converter.UserAnamnesisServHistoryToPatientCardBeanConverter;
import ua.nure.gudkov.ST4.converter.api.Converter;
import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.entity.ErrorHolder;
import ua.nure.gudkov.ST4.entity.ServiceHistory;
import ua.nure.gudkov.ST4.entity.User;
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.service.api.AnamnesisService;
import ua.nure.gudkov.ST4.service.api.ServiceHistoryService;
import ua.nure.gudkov.ST4.service.api.UserService;

/**
 * Get patient`s card command implementation.
 * 
 * @author A.Gudkov
 *
 */
public class GetPatientCardCommand extends AbstractCommand {
	private static final Logger LOG = Logger.getLogger(GetPatientCardCommand.class);

	private UserService userService;
	private AnamnesisService anamnesisService;
	private ServiceHistoryService servHistoryService;
	private Converter<UserAnamnesisServHistoryBean, PatientCardBean> converter;

	public GetPatientCardCommand(UserService userService, AnamnesisService anamnesisService,
			ServiceHistoryService servHistoryService) {
		this.userService = userService;
		this.anamnesisService = anamnesisService;
		this.servHistoryService = servHistoryService;
		converter = new UserAnamnesisServHistoryToPatientCardBeanConverter();

	}

	@Override
	public PageBean execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageBean pb = new PageBean();
		pb.setAction(Action.REDIRECT);
		pb.setPath(PathConverter.getInstance().getPath(Constants.PagePath.BASE_PATH + Constants.PagePath.ACCOUNT_PAGE));
		LOG.trace("Set path and action ----> " + pb.getPath() + ", " + pb.getAction());

		Integer idPatient = extractIdPatient(request);
		LOG.trace("ID of patient extracted ----> " + idPatient);

		ErrorHolder erroHolder = new ErrorHolder();
		HttpSession session = request.getSession();

		if (idPatient == null) {
			LOG.trace("Invalid ID");
			return pb;
		}

		User patient = null;
		Anamnesis anamnesis = null;
		List<ServiceHistory> servHistory = null;
		PatientCardBean patientCard = null;

		try {

			if (userService.verifyPatient(idPatient)) {
				patient = userService.findById(idPatient);
				LOG.trace("patient was obtained ----> " + patient);

				anamnesis = anamnesisService.findNewByIdPatient(idPatient);
				if (anamnesis != null) {
					LOG.trace("anamnesis was obtained ----> " + anamnesis);

					if (checkDoctorAuthority(session, anamnesis)) {
						LOG.trace("doctor`s rights were confirmed ----> ");

						servHistory = servHistoryService.findAllServiceHistoryByIdAnamnesis(anamnesis.getIdanamnesis());
						LOG.trace("Service history was obtained ----> " + servHistory);

						patientCard = createPatientCard(patient, anamnesis, servHistory);
						LOG.trace("Patient card was obtained ----> " + patientCard);
					}

				}

			}
    
		} catch (DBException e) {
			erroHolder.add(e.getMessage());
			session.setAttribute(Constants.ErrorConstants.DB_ERRORS, erroHolder.getErrors());
			LOG.trace("catched dbexception ----> ");
			LOG.error(e);
			pb.setPath(PathConverter.getInstance()
					.getPath(Constants.PagePath.BASE_PATH + Constants.PagePath.ACCOUNT_PAGE));
			return pb;
		}

		if (patientCard != null) {
			session.setAttribute(Constants.SessionAttributes.CARD_OF_MYPATIENT, patientCard);
		}

		

		return pb;
	}

	private PatientCardBean createPatientCard(User patient, Anamnesis anamnesis, List<ServiceHistory> servHistory) {
		PatientCardBean patientCard = converter
				.convert(new UserAnamnesisServHistoryBean(anamnesis, patient, servHistory));
		return patientCard;
	}

}
