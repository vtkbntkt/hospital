package ua.nure.gudkov.ST4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.gudkov.ST4.bean.app.PageBean;
import ua.nure.gudkov.ST4.bean.app.PageBean.Action;
import ua.nure.gudkov.ST4.constant.Constants;

/**
 * Get account page command implementation.
 * 
 * @author A.Gudkov
 *
 */
public class GetAccountCommand extends AbstractCommand {

	@Override
	public PageBean execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageBean pb = new PageBean();
		pb.setAction(Action.FORWARD);
		pb.setPath(Constants.PagePath.BASE_PATH + Constants.PagePath.ACCOUNT_PAGE);

		HttpSession session = request.getSession();
		/*
		 * DBerrors overall application
		 */
		Object dbErrorSession = session.getAttribute(Constants.ErrorConstants.DB_ERRORS);
		if (dbErrorSession != null) {
			session.removeAttribute(Constants.ErrorConstants.DB_ERRORS);
			request.setAttribute(Constants.ErrorConstants.DB_ERRORS, dbErrorSession);
		}

		/*
		 * User registration
		 */
		Object errorsSession = session.getAttribute(Constants.ErrorConstants.REGISTRATION_ERRORS);
		if (errorsSession != null) {
			session.removeAttribute(Constants.ErrorConstants.REGISTRATION_ERRORS);
			request.setAttribute(Constants.ErrorConstants.REGISTRATION_ERRORS, errorsSession);
		}

		Object formBeanSession = session.getAttribute(Constants.SessionAttributes.REGISTRATION_USER_BEAN);
		if (formBeanSession != null) {
			session.removeAttribute(Constants.SessionAttributes.REGISTRATION_USER_BEAN);
			request.setAttribute(Constants.SessionAttributes.REGISTRATION_USER_BEAN, formBeanSession);
		}
		/*
		 * Get all doctors
		 */
		Object doctorListSession = session.getAttribute(Constants.SessionAttributes.LIST_OF_DOCTORS);
		if (doctorListSession != null) {
			session.removeAttribute(Constants.SessionAttributes.LIST_OF_DOCTORS);
			request.setAttribute(Constants.SessionAttributes.LIST_OF_DOCTORS, doctorListSession);
		}

		Object doctorErrorsSession = session.getAttribute(Constants.ErrorConstants.DOCTOR_LIST_ERRORS);
		if (doctorErrorsSession != null) {
			session.removeAttribute(Constants.ErrorConstants.DOCTOR_LIST_ERRORS);
			request.setAttribute(Constants.ErrorConstants.DOCTOR_LIST_ERRORS, doctorErrorsSession);
		}
		/*
		 * Get all patients
		 */
		Object patientListSession = session.getAttribute(Constants.SessionAttributes.LIST_OF_PATIENTS);
		if (patientListSession != null) {
			session.removeAttribute(Constants.SessionAttributes.LIST_OF_PATIENTS);
			request.setAttribute(Constants.SessionAttributes.LIST_OF_PATIENTS, patientListSession);
		}

		Object patientErrorsSession = session.getAttribute(Constants.ErrorConstants.PATIENT_LIST_ERRORS);
		if (patientErrorsSession != null) {
			session.removeAttribute(Constants.ErrorConstants.PATIENT_LIST_ERRORS);
			request.setAttribute(Constants.ErrorConstants.PATIENT_LIST_ERRORS, patientErrorsSession);
		}

		/*
		 * Get assignDoctor form
		 */
		Object patientDataListSession = session.getAttribute(Constants.SessionAttributes.DATALIST_OF_PATIENTS);
		if (patientDataListSession != null) {
			session.removeAttribute(Constants.SessionAttributes.DATALIST_OF_PATIENTS);
			request.setAttribute(Constants.SessionAttributes.DATALIST_OF_PATIENTS, patientDataListSession);
		}

		Object doctorDataListSession = session.getAttribute(Constants.SessionAttributes.DATALIST_OF_DOCTORS);
		if (doctorDataListSession != null) {
			session.removeAttribute(Constants.SessionAttributes.DATALIST_OF_DOCTORS);
			request.setAttribute(Constants.SessionAttributes.DATALIST_OF_DOCTORS, doctorDataListSession);
		}

		Object docPatErrorsSession = session.getAttribute(Constants.ErrorConstants.DOCTOR_PATIENT_LIST_ERRORS);
		if (docPatErrorsSession != null) {
			session.removeAttribute(Constants.ErrorConstants.DOCTOR_PATIENT_LIST_ERRORS);
			request.setAttribute(Constants.ErrorConstants.DOCTOR_PATIENT_LIST_ERRORS, docPatErrorsSession);
		}

		/*
		 * Get assignDoctor form
		 */
		Object assignDoctorErrorsSession = session.getAttribute(Constants.ErrorConstants.ASSIGN_DOCTOR_ERRORS);
		if (assignDoctorErrorsSession != null) {
			session.removeAttribute(Constants.ErrorConstants.ASSIGN_DOCTOR_ERRORS);
			request.setAttribute(Constants.ErrorConstants.ASSIGN_DOCTOR_ERRORS, assignDoctorErrorsSession);
		}

		/*
		 * Get my patients
		 */
		Object myPatientListSession = session.getAttribute(Constants.SessionAttributes.LIST_OF_MYPATIENTS);
		if (myPatientListSession != null) {
			session.removeAttribute(Constants.SessionAttributes.LIST_OF_MYPATIENTS);
			request.setAttribute(Constants.SessionAttributes.LIST_OF_MYPATIENTS, myPatientListSession);
		}

		Object myPatientsErrorsSession = session.getAttribute(Constants.ErrorConstants.MY_PATIENTS_ERRORS);
		if (myPatientsErrorsSession != null) {
			session.removeAttribute(Constants.ErrorConstants.MY_PATIENTS_ERRORS);
			request.setAttribute(Constants.ErrorConstants.MY_PATIENTS_ERRORS, myPatientsErrorsSession);
		}

		/*
		 * Get my patient card
		 */
		Object myPatientCardSession = session.getAttribute(Constants.SessionAttributes.CARD_OF_MYPATIENT);
		if (myPatientCardSession != null) {
			session.removeAttribute(Constants.SessionAttributes.CARD_OF_MYPATIENT);
			request.setAttribute(Constants.SessionAttributes.CARD_OF_MYPATIENT, myPatientCardSession);
		}

		/*
		 * Add service
		 */
		Object addServErrorsSession = session.getAttribute(Constants.ErrorConstants.ADD_SERVICE_ERRORS);
		if (addServErrorsSession != null) {
			session.removeAttribute(Constants.ErrorConstants.ADD_SERVICE_ERRORS);
			request.setAttribute(Constants.ErrorConstants.ADD_SERVICE_ERRORS, addServErrorsSession);
		}

		/*
		 * Add diagnosis
		 */
		Object addDiagnosErrorsSession = session.getAttribute(Constants.ErrorConstants.ADD_DIAGNOSIS_ERRORS);
		if (addDiagnosErrorsSession != null) {
			session.removeAttribute(Constants.ErrorConstants.ADD_DIAGNOSIS_ERRORS);
			request.setAttribute(Constants.ErrorConstants.ADD_DIAGNOSIS_ERRORS, addDiagnosErrorsSession);
		}

		/*
		 * Find doc tasks
		 */
		Object findDocTasksErrorsSession = session.getAttribute(Constants.ErrorConstants.TASK_LIST_ERRORS);
		if (findDocTasksErrorsSession != null) {
			session.removeAttribute(Constants.ErrorConstants.TASK_LIST_ERRORS);
			request.setAttribute(Constants.ErrorConstants.TASK_LIST_ERRORS, findDocTasksErrorsSession);
		}

		Object findDocTasksSession = session.getAttribute(Constants.SessionAttributes.LIST_OF_TASKS);
		if (findDocTasksSession != null) {
			session.removeAttribute(Constants.SessionAttributes.LIST_OF_TASKS);
			request.setAttribute(Constants.SessionAttributes.LIST_OF_TASKS, findDocTasksSession);
		}

		/*
		 * Upload patient photo
		 */
		Object photoUploadingErrorsSession = session.getAttribute(Constants.ErrorConstants.UPLOADING_PHOTO_ERRORS);
		if (photoUploadingErrorsSession != null) {
			session.removeAttribute(Constants.ErrorConstants.UPLOADING_PHOTO_ERRORS);
			request.setAttribute(Constants.ErrorConstants.UPLOADING_PHOTO_ERRORS, photoUploadingErrorsSession);
		}

		/*
		 * Get my cards
		 */
		Object findMyCardsSession = session.getAttribute(Constants.SessionAttributes.LIST_OF_MY_CARDS);
		if (findMyCardsSession != null) {
			session.removeAttribute(Constants.SessionAttributes.LIST_OF_MY_CARDS);
			request.setAttribute(Constants.SessionAttributes.LIST_OF_MY_CARDS, findMyCardsSession);
		}

		Object findMyCardsErrorsSession = session.getAttribute(Constants.ErrorConstants.MYCARDS_LIST_ERRORS);
		if (findMyCardsErrorsSession != null) {
			session.removeAttribute(Constants.ErrorConstants.MYCARDS_LIST_ERRORS);
			request.setAttribute(Constants.ErrorConstants.MYCARDS_LIST_ERRORS, findMyCardsErrorsSession);
		}

		/*
		 * Backup params
		 */
		Object backupParamsSession = session.getAttribute(Constants.GenericParameters.BACKUP_PARAMS);
		if (backupParamsSession != null) {
			session.removeAttribute(Constants.GenericParameters.BACKUP_PARAMS);
			request.setAttribute(Constants.GenericParameters.BACKUP_PARAMS, backupParamsSession);
		}

		/*
		 * Get new patients
		 */
		Object newPatientsSession = session.getAttribute(Constants.SessionAttributes.LIST_OF_NEW_PATIENTS);
		if (newPatientsSession != null) {
			session.removeAttribute(Constants.SessionAttributes.LIST_OF_NEW_PATIENTS);
			request.setAttribute(Constants.SessionAttributes.LIST_OF_NEW_PATIENTS, newPatientsSession);
		}

		return pb;
	}

}
