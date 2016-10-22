package ua.nure.gudkov.ST4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gudkov.ST4.bean.app.PageBean;
import ua.nure.gudkov.ST4.bean.app.PageBean.Action;
import ua.nure.gudkov.ST4.bean.form.DiagnosisFormBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.converter.PathConverter;
import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.entity.ErrorHolder;
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.extractor.DiagnosisFormBeanExtractor;
import ua.nure.gudkov.ST4.extractor.api.BeanExtractor;
import ua.nure.gudkov.ST4.service.api.AnamnesisService;
import ua.nure.gudkov.ST4.validator.BeanValidator;
import ua.nure.gudkov.ST4.validator.DiagnosisFormValidator;

/**
 * Discharge patient command implementation.
 * 
 * @author A.Gudkov
 *
 */
public class DischargePatientCommand extends AbstractCommand {

	private static final Logger LOG = Logger.getLogger(DischargePatientCommand.class);

	private AnamnesisService anamnesisService;
	private BeanExtractor<DiagnosisFormBean> beanExtractor;
	private BeanValidator<DiagnosisFormBean> beanValidator;

	public DischargePatientCommand(AnamnesisService anamnesisService) {
		this.anamnesisService = anamnesisService;
		beanExtractor = new DiagnosisFormBeanExtractor();
		beanValidator = new DiagnosisFormValidator();

	}

	@Override
	public PageBean execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageBean pb = new PageBean();
		pb.setAction(Action.REDIRECT);
		DiagnosisFormBean addDiagnosFormBean = beanExtractor.extract(request);
		LOG.trace("DiagnosisFormBean extracted ----> " + addDiagnosFormBean);

		pb.setPath(Constants.Commands.SERVLET_GET_PATIENT_CARD_COMMAND + addDiagnosFormBean.getIdPatient());
		LOG.trace("Set path and action ----> " + pb.getPath() + ", " + pb.getAction());

		ErrorHolder addDiagnosErrors = beanValidator.validate(addDiagnosFormBean);
		HttpSession session = request.getSession();

		if (!addDiagnosErrors.isEmpty()) {
			session.setAttribute(Constants.ErrorConstants.ADD_DIAGNOSIS_ERRORS, addDiagnosErrors.getErrors());
			LOG.trace("Add diagnosis errors ----> " + addDiagnosErrors.getErrors());
			return pb;
		}

		try {
			int idAnamnesis = Integer.parseInt(addDiagnosFormBean.getIdAnamnesis());
			Anamnesis anamnes = anamnesisService.findByIdAnamnesis(idAnamnesis);
			if (anamnes != null) {
				LOG.trace("anamnesis was obtained ----> " + anamnes);

				if (checkDoctorAuthority(session, anamnes)) {
					LOG.trace("doctor`s rights were confirmed ----> ");

					if (anamnesisService.dischargePatient(addDiagnosFormBean.getDiagnosisValue(), idAnamnesis)) {
						pb.setPath(Constants.Commands.SERVLET_GET_ALL_MYPATIENTS_COMMAND);
						LOG.trace("patient was discharged ----> ");

					}
				}
			}
		} catch (DBException e) {
			addDiagnosErrors.add(e.getMessage());
			session.setAttribute(Constants.ErrorConstants.DB_ERRORS, addDiagnosErrors.getErrors());
			LOG.trace("catched dbexception ----> ");
			LOG.error(e);
			pb.setPath(PathConverter.getInstance()
					.getPath(Constants.PagePath.BASE_PATH + Constants.PagePath.ACCOUNT_PAGE));
			return pb;
		}

		return pb;
	}

}
