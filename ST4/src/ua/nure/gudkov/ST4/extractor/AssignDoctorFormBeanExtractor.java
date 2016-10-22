package ua.nure.gudkov.ST4.extractor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import ua.nure.gudkov.ST4.bean.form.AssignDoctorFormBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.extractor.api.BeanExtractor;

/**
 * Assign doctor form bean extractor.
 * 
 * @author A.Gudkov
 *
 */
public class AssignDoctorFormBeanExtractor implements BeanExtractor<AssignDoctorFormBean> {

	@Override
	public AssignDoctorFormBean extract(HttpServletRequest request) throws IOException, ServletException {
		AssignDoctorFormBean assingDoctorFormBean = new AssignDoctorFormBean();
		assingDoctorFormBean.setIdPatient(request.getParameter(Constants.AssignDoctorParameters.ID_PATIENT_FIELD));
		assingDoctorFormBean.setIdDoctor(request.getParameter(Constants.AssignDoctorParameters.ID_DOCTOR_FIELD));
		return assingDoctorFormBean;
	}

}
