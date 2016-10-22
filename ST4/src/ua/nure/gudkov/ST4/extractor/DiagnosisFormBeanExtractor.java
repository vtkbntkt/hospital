package ua.nure.gudkov.ST4.extractor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import ua.nure.gudkov.ST4.bean.form.DiagnosisFormBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.extractor.api.BeanExtractor;

/**
 * Diagnosis form bean extractor.
 * 
 * @author A.Gudkov
 *
 */
public class DiagnosisFormBeanExtractor implements BeanExtractor<DiagnosisFormBean> {

	@Override
	public DiagnosisFormBean extract(HttpServletRequest request) throws IOException, ServletException {
		DiagnosisFormBean diagnosForm = new DiagnosisFormBean();
		diagnosForm.setDiagnosisValue(request.getParameter(Constants.PatientCardParameters.DIAGNOSIS_VALUE_FIELD));
		diagnosForm.setIdAnamnesis(request.getParameter(Constants.PatientCardParameters.ID_ANAMNESIS));
		diagnosForm.setIdPatient(request.getParameter(Constants.PatientCardParameters.ID_PATIENT));
		return diagnosForm;
	}

}
