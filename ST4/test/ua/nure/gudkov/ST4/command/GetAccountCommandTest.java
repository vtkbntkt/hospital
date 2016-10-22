package ua.nure.gudkov.ST4.command;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.gudkov.ST4.constant.Constants;

public class GetAccountCommandTest extends Mockito {

	@Test
	public void testExecute() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);

		when(request.getSession()).thenReturn(session);
		new GetAccountCommand().execute(request, response);
		
		Object ob = new Object();
		when(session.getAttribute(Constants.ErrorConstants.DB_ERRORS)).thenReturn(ob);
		when(session.getAttribute(Constants.ErrorConstants.REGISTRATION_ERRORS)).thenReturn(ob);
		when(session.getAttribute(Constants.SessionAttributes.REGISTRATION_USER_BEAN)).thenReturn(ob);
		when(session.getAttribute(Constants.SessionAttributes.LIST_OF_DOCTORS)).thenReturn(ob);
		when(session.getAttribute(Constants.ErrorConstants.DOCTOR_LIST_ERRORS)).thenReturn(ob);		
		when(session.getAttribute(Constants.SessionAttributes.LIST_OF_PATIENTS)).thenReturn(ob);
		when(session.getAttribute(Constants.ErrorConstants.PATIENT_LIST_ERRORS)).thenReturn(ob);
		when(session.getAttribute(Constants.SessionAttributes.DATALIST_OF_PATIENTS)).thenReturn(ob);
		when(session.getAttribute(Constants.SessionAttributes.DATALIST_OF_DOCTORS)).thenReturn(ob);
		when(session.getAttribute(Constants.ErrorConstants.DOCTOR_PATIENT_LIST_ERRORS)).thenReturn(ob);
		when(session.getAttribute(Constants.ErrorConstants.ASSIGN_DOCTOR_ERRORS)).thenReturn(ob);
		when(session.getAttribute(Constants.SessionAttributes.LIST_OF_MYPATIENTS)).thenReturn(ob);
		when(session.getAttribute(Constants.ErrorConstants.MY_PATIENTS_ERRORS)).thenReturn(ob);
		when(session.getAttribute(Constants.SessionAttributes.CARD_OF_MYPATIENT)).thenReturn(ob);
		when(session.getAttribute(Constants.ErrorConstants.ADD_SERVICE_ERRORS)).thenReturn(ob);
		when(session.getAttribute(Constants.ErrorConstants.ADD_DIAGNOSIS_ERRORS)).thenReturn(ob);
		when(session.getAttribute(Constants.ErrorConstants.TASK_LIST_ERRORS)).thenReturn(ob);
		when(session.getAttribute(Constants.SessionAttributes.LIST_OF_TASKS)).thenReturn(ob);
		when(session.getAttribute(Constants.ErrorConstants.UPLOADING_PHOTO_ERRORS)).thenReturn(ob);
		when(session.getAttribute(Constants.SessionAttributes.LIST_OF_MY_CARDS)).thenReturn(ob);
		when(session.getAttribute(Constants.ErrorConstants.MYCARDS_LIST_ERRORS)).thenReturn(ob);
		when(session.getAttribute(Constants.GenericParameters.BACKUP_PARAMS)).thenReturn(ob);

		new GetAccountCommand().execute(request, response);
		verify(session, atLeast(1)).removeAttribute(Constants.ErrorConstants.UPLOADING_PHOTO_ERRORS);
		verify(request, atLeast(1)).setAttribute(Constants.ErrorConstants.UPLOADING_PHOTO_ERRORS, ob);
		
		verify(session, atLeast(1)).removeAttribute(Constants.SessionAttributes.LIST_OF_MY_CARDS);
		verify(request, atLeast(1)).setAttribute(Constants.SessionAttributes.LIST_OF_MY_CARDS, ob);
		
		verify(session, atLeast(1)).removeAttribute(Constants.ErrorConstants.MYCARDS_LIST_ERRORS);
		verify(request, atLeast(1)).setAttribute(Constants.ErrorConstants.MYCARDS_LIST_ERRORS, ob);
		
		verify(session, atLeast(1)).removeAttribute(Constants.GenericParameters.BACKUP_PARAMS);
		verify(request, atLeast(1)).setAttribute(Constants.GenericParameters.BACKUP_PARAMS, ob);
		
		verify(session, atLeast(1)).removeAttribute(Constants.SessionAttributes.CARD_OF_MYPATIENT);
		verify(request, atLeast(1)).setAttribute(Constants.SessionAttributes.CARD_OF_MYPATIENT, ob);
		
		verify(session, atLeast(1)).removeAttribute(Constants.ErrorConstants.ADD_SERVICE_ERRORS);
		verify(request, atLeast(1)).setAttribute(Constants.ErrorConstants.ADD_SERVICE_ERRORS, ob);
		
		verify(session, atLeast(1)).removeAttribute(Constants.ErrorConstants.ADD_DIAGNOSIS_ERRORS);
		verify(request, atLeast(1)).setAttribute(Constants.ErrorConstants.ADD_DIAGNOSIS_ERRORS, ob);
		
		verify(session, atLeast(1)).removeAttribute(Constants.ErrorConstants.TASK_LIST_ERRORS);
		verify(request, atLeast(1)).setAttribute(Constants.ErrorConstants.TASK_LIST_ERRORS, ob);
		
		verify(session, atLeast(1)).removeAttribute(Constants.SessionAttributes.LIST_OF_TASKS);
		verify(request, atLeast(1)).setAttribute(Constants.SessionAttributes.LIST_OF_TASKS, ob);
		
		verify(session, atLeast(1)).removeAttribute(Constants.SessionAttributes.DATALIST_OF_DOCTORS);
		verify(request, atLeast(1)).setAttribute(Constants.SessionAttributes.DATALIST_OF_DOCTORS, ob);
		
		verify(session, atLeast(1)).removeAttribute(Constants.ErrorConstants.DOCTOR_PATIENT_LIST_ERRORS);
		verify(request, atLeast(1)).setAttribute(Constants.ErrorConstants.DOCTOR_PATIENT_LIST_ERRORS, ob);
		
		verify(session, atLeast(1)).removeAttribute(Constants.ErrorConstants.ASSIGN_DOCTOR_ERRORS);
		verify(request, atLeast(1)).setAttribute(Constants.ErrorConstants.ASSIGN_DOCTOR_ERRORS, ob);
		
		verify(session, atLeast(1)).removeAttribute(Constants.SessionAttributes.LIST_OF_MYPATIENTS);
		verify(request, atLeast(1)).setAttribute(Constants.SessionAttributes.LIST_OF_MYPATIENTS, ob);
		
		verify(session, atLeast(1)).removeAttribute(Constants.ErrorConstants.MY_PATIENTS_ERRORS);
		verify(request, atLeast(1)).setAttribute(Constants.ErrorConstants.MY_PATIENTS_ERRORS, ob);
		
		verify(session, atLeast(1)).removeAttribute(Constants.SessionAttributes.LIST_OF_DOCTORS);
		verify(request, atLeast(1)).setAttribute(Constants.SessionAttributes.LIST_OF_DOCTORS, ob);
		
		verify(session, atLeast(1)).removeAttribute(Constants.ErrorConstants.DOCTOR_LIST_ERRORS);
		verify(request, atLeast(1)).setAttribute(Constants.ErrorConstants.DOCTOR_LIST_ERRORS, ob);
		
		verify(session, atLeast(1)).removeAttribute(Constants.SessionAttributes.LIST_OF_PATIENTS);
		verify(request, atLeast(1)).setAttribute(Constants.SessionAttributes.LIST_OF_PATIENTS, ob);
		
		verify(session, atLeast(1)).removeAttribute(Constants.ErrorConstants.PATIENT_LIST_ERRORS);
		verify(request, atLeast(1)).setAttribute(Constants.ErrorConstants.PATIENT_LIST_ERRORS, ob);
		
		verify(session, atLeast(1)).removeAttribute(Constants.SessionAttributes.DATALIST_OF_PATIENTS);
		verify(request, atLeast(1)).setAttribute(Constants.SessionAttributes.DATALIST_OF_PATIENTS, ob);
		
		verify(session, atLeast(1)).removeAttribute(Constants.ErrorConstants.DB_ERRORS);
		verify(request, atLeast(1)).setAttribute(Constants.ErrorConstants.DB_ERRORS, ob);
		
		verify(session, atLeast(1)).removeAttribute(Constants.ErrorConstants.REGISTRATION_ERRORS);
		verify(request, atLeast(1)).setAttribute(Constants.ErrorConstants.REGISTRATION_ERRORS, ob);
		
		verify(request, atLeast(1)).setAttribute(Constants.SessionAttributes.REGISTRATION_USER_BEAN, ob);
		verify(request, atLeast(1)).setAttribute(Constants.SessionAttributes.REGISTRATION_USER_BEAN, ob);
	}

}
