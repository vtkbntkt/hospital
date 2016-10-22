package ua.nure.gudkov.ST4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gudkov.ST4.bean.app.PageBean;
import ua.nure.gudkov.ST4.bean.app.PageBean.Action;
import ua.nure.gudkov.ST4.bean.form.AddServiceFormBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.converter.AddServiceFormBeanToServiceHistory;
import ua.nure.gudkov.ST4.converter.PathConverter;
import ua.nure.gudkov.ST4.converter.api.Converter;
import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.entity.ErrorHolder;
import ua.nure.gudkov.ST4.entity.ServiceHistory;
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.extractor.AddServiceFormBeanExtractor;
import ua.nure.gudkov.ST4.extractor.api.BeanExtractor;
import ua.nure.gudkov.ST4.service.api.AnamnesisService;
import ua.nure.gudkov.ST4.service.api.ServiceHistoryService;
import ua.nure.gudkov.ST4.validator.AddServFormValidator;
import ua.nure.gudkov.ST4.validator.BeanValidator;

/**
 * Add service command implementation.
 * 
 * @author A.Gudkov
 *
 */
public class AddServiceCommand extends AbstractCommand {
	private static final Logger LOG = Logger.getLogger(AddServiceCommand.class);

	private AnamnesisService anamnesisService;
	private ServiceHistoryService servHistoryService;
	private BeanExtractor<AddServiceFormBean> beanExtractor;
	private BeanValidator<AddServiceFormBean> beanValidator;
	private Converter<AddServiceFormBean, ServiceHistory> converter;

	public AddServiceCommand(AnamnesisService anamnesisService, ServiceHistoryService servHistoryService) {
		this.anamnesisService = anamnesisService;
		this.servHistoryService = servHistoryService;
		converter = new AddServiceFormBeanToServiceHistory();
		beanExtractor = new AddServiceFormBeanExtractor();
		beanValidator = new AddServFormValidator();

	}

	@Override
	public PageBean execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageBean pb = new PageBean();
		pb.setAction(Action.REDIRECT);
		AddServiceFormBean addServFormBean = beanExtractor.extract(request);
		LOG.trace("AddServiceFormBean extracted ----> " + addServFormBean);
		
		pb.setPath(Constants.Commands.SERVLET_GET_PATIENT_CARD_COMMAND + addServFormBean.getIdPatient());
		LOG.trace("Set path and action ----> " + pb.getPath() + ", " + pb.getAction());
		
		ErrorHolder addServErrors = beanValidator.validate(addServFormBean);
		HttpSession session = request.getSession();

		if (!addServErrors.isEmpty()) {
			session.setAttribute(Constants.ErrorConstants.ADD_SERVICE_ERRORS, addServErrors.getErrors());
			LOG.trace("Add service errors ----> " + addServErrors.getErrors());
			return pb;
		}

		ServiceHistory servHistory = converter.convert(addServFormBean);
		LOG.trace("ServiceHistory converted ----> " + servHistory);
		try {
			Anamnesis anamnes = anamnesisService.findByIdAnamnesis(servHistory.getIdAnamnesis());
			if (anamnes!=null){
				LOG.trace("anamnesis was obtained ----> " + anamnes);
				
				if(checkDoctorAuthority(session, anamnes)){
					LOG.trace("doctor`s rights were confirmed ----> ");
					
					servHistoryService.addServiceHistory(servHistory);
				}
			}
		} catch (DBException e) {
			addServErrors.add(e.getMessage());
			session.setAttribute(Constants.ErrorConstants.DB_ERRORS, addServErrors.getErrors());
			LOG.trace("catched dbexception ----> ");
			LOG.error(e);
			pb.setPath(PathConverter.getInstance()
					.getPath(Constants.PagePath.BASE_PATH + Constants.PagePath.ACCOUNT_PAGE));
			return pb;
		}

		return pb;
	}


}
