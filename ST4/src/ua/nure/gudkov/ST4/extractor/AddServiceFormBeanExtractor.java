package ua.nure.gudkov.ST4.extractor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import ua.nure.gudkov.ST4.bean.form.AddServiceFormBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.extractor.api.BeanExtractor;

/**
 * Add service form bean extractor.
 * 
 * @author A.Gudkov
 *
 */
public class AddServiceFormBeanExtractor implements BeanExtractor<AddServiceFormBean> {

	@Override
	public AddServiceFormBean extract(HttpServletRequest request) throws IOException, ServletException {
		AddServiceFormBean addForm = new AddServiceFormBean();
		addForm.setIdAnamnesis(request.getParameter(Constants.PatientCardParameters.ID_ANAMNESIS));
		addForm.setIdPatient(request.getParameter(Constants.PatientCardParameters.ID_PATIENT));
		addForm.setServiceType(request.getParameter(Constants.PatientCardParameters.TYPE_SERVICE));
		addForm.setServiceValue(request.getParameter(Constants.PatientCardParameters.SERV_VALUE_FIELD));
		return addForm;
	}

}
