package ua.nure.gudkov.ST4.command;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
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
import ua.nure.gudkov.ST4.converter.PatientCardBeanToNote;
import ua.nure.gudkov.ST4.converter.UserAnamnesisServHistoryToPatientCardBeanConverter;
import ua.nure.gudkov.ST4.converter.api.Converter;
import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.entity.ErrorHolder;
import ua.nure.gudkov.ST4.entity.ServiceHistory;
import ua.nure.gudkov.ST4.entity.Status;
import ua.nure.gudkov.ST4.entity.User;
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.service.api.AnamnesisService;
import ua.nure.gudkov.ST4.service.api.ServiceHistoryService;
import ua.nure.gudkov.ST4.service.api.UserService;
import ua.nure.gudkov.ST4.util.NoteCreator;

/**
 * Print my card command implementation.
 * 
 * @author A.Gudkov
 *
 */
public class PrintMyCardCommand extends AbstractCommand {

	private static final Logger LOG = Logger.getLogger(PrintMyCardCommand.class);

	private UserService userService;
	private AnamnesisService anamnesisService;
	private ServiceHistoryService servHistoryService;
	private Converter<UserAnamnesisServHistoryBean, PatientCardBean> cardConverter;
	private Converter<PatientCardBean, String> noteConverter;

	public PrintMyCardCommand(UserService userService, AnamnesisService anamnesisService,
			ServiceHistoryService servHistoryService) {
		this.userService = userService;
		this.anamnesisService = anamnesisService;
		this.servHistoryService = servHistoryService;
		cardConverter = new UserAnamnesisServHistoryToPatientCardBeanConverter();
		noteConverter = new PatientCardBeanToNote();

	}

	@Override
	public PageBean execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageBean pb = new PageBean();
		pb.setPath(PathConverter.getInstance().getPath(Constants.PagePath.BASE_PATH + Constants.PagePath.ACCOUNT_PAGE));
		pb.setAction(Action.REDIRECT);

		Integer idAnamnesis = extractIdAnamnesis(request);
		LOG.trace("ID anamnesis extracted ----> " + idAnamnesis);

		ErrorHolder erroHolder = new ErrorHolder();
		HttpSession session = request.getSession();

		if (idAnamnesis == null) {
			LOG.trace("Invalid ID");
			return pb;
		}
		User patient = null;
		Anamnesis anamnesis = null;
		List<ServiceHistory> servHistory = null;
		PatientCardBean patientCard = null;
		String note = null;

		try {

			anamnesis = anamnesisService.findByIdAnamnesis(idAnamnesis);
			if (anamnesisVerified(anamnesis, session)) {
				LOG.trace("Anamnesis is verified");

				patient = userService.findById(anamnesis.getIdPatient());
				if (patient != null) {
					LOG.trace("patient was obtained ----> " + patient);

					servHistory = servHistoryService.findAllServiceHistoryByIdAnamnesis(anamnesis.getIdanamnesis());
					LOG.trace("Service history was obtained ----> " + servHistory);

					patientCard = cardConverter
							.convert(new UserAnamnesisServHistoryBean(anamnesis, patient, servHistory));
					LOG.trace("Patient card was obtained ----> " + patientCard);
				}
			}
		} catch (DBException e) {
			erroHolder.add(e.getMessage());
			session.setAttribute(Constants.ErrorConstants.DB_ERRORS, erroHolder.getErrors());
			LOG.trace("catched dbexception ----> ");
			LOG.error(e);
			return pb;
		}

		if (patientCard != null) {
			try {
				note = noteConverter.convert(patientCard);
				pb.setBufferedImage(getNote(note));
				pb.setAction(Action.DRAW);
			} catch (IOException ex) {
				LOG.error(ex);
			}
		}

		return pb;
	}

	/**
	 * Return image included given content and established blank.
	 * 
	 * @param content content of note
	 * @return note as image.
	 */
	private BufferedImage getNote(String content) throws IOException {
		Font font = new Font(Constants.NoteParameters.TIMES_ROMAN_FONT, Font.PLAIN, 16);
		return NoteCreator.createNote(Constants.NoteParameters.CARD_BLANK_PATH, content, 24, 200, Color.BLACK, font);
	}

	/**
	 * Checks whether the anamnesis can be provided for defined user.
	 * 
	 * @param anamnes the anamnesis
	 * @param session HttpSession
	 * @return true if the anamnesis is verified or false if the anamnesis is not verified
	 */
	private boolean anamnesisVerified(Anamnesis anamnes, HttpSession session) {
		if (anamnes == null) {
			LOG.error("null");
			return false;
		} else if (anamnes.getIdStatus() != Status.DISCHARGED.ordinal()) {
			LOG.error("active");
			return false;
		} else if (!checkPatientAuthority(session, anamnes)) {
			LOG.error("has not permissions");
			return false;
		}
		return true;
	}

}
