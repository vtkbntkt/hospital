package ua.nure.gudkov.ST4.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import ua.nure.gudkov.ST4.command.api.Command;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.entity.Role;
import ua.nure.gudkov.ST4.entity.User;

/**
 * Base class for all command. Contains realized methods which are used most of
 * commands.
 * 
 * @author A.Gudkov
 *
 */
public abstract class AbstractCommand implements Command {

	/**
	 * Extracts the role value from session. Return null if session does not
	 * contain the role.
	 * 
	 * @param session
	 *            HttpSession
	 * @return user role
	 */
	public Role extractRole(HttpSession session) {
		Role role = null;
		User user = extractUser(session);
		if (user != null) {
			role = Role.values()[user.getRoleId()];
		}
		return role;
	}

	/**
	 * Extract the user from session.
	 * 
	 * @param session
	 *            HttpSession
	 * @return user bean
	 */
	public User extractUser(HttpSession session) {
		return (User) session.getAttribute(Constants.SessionAttributes.USER_ATTR);
	}

	/**
	 * Extracts user id from session. Returns null if session does not contain
	 * the id.
	 * 
	 * @param session
	 *            HttpSession
	 * @return user id
	 */
	public Long extractIdUser(HttpSession session) {
		Long idUser = null;
		User user = extractUser(session);
		if (user != null) {
			idUser = user.getId();
		}
		return idUser;
	}

	/**
	 * Extracts the patient id from request. Return null if request does not
	 * contain the id or id is not numeric.
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return patient id
	 */
	public Integer extractIdPatient(HttpServletRequest request) {
		String idPatient = request.getParameter(Constants.GenericParameters.ID_PATIENT);
		if (StringUtils.isNumeric(idPatient)) {
			return Integer.parseInt(idPatient);
		}
		return null;
	}

	/**
	 * Extracts the anamnesis id from request. Return null if request does not
	 * contain the id or id is not numeric.
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return anamnesis id
	 */
	public Integer extractIdAnamnesis(HttpServletRequest request) {
		String idAnamnesis = request.getParameter(Constants.GenericParameters.ID_ANAMNESIS);
		if (StringUtils.isNumeric(idAnamnesis)) {
			return Integer.parseInt(idAnamnesis);
		}
		return null;
	}

	/**
	 * Extracts the anamnesis id from request. Return null if request does not
	 * contain the id or id is not numeric.
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return anamnesis id
	 */
	public Integer extractIdServHistory(HttpServletRequest request) {
		String idHistory = request.getParameter(Constants.GenericParameters.ID_SERV_HISTORY);
		if (StringUtils.isNumeric(idHistory)) {
			return Integer.parseInt(idHistory);
		}
		return null;
	}

	/**
	 * Checks whether the user can use the anamnesis. Return true if the user
	 * have permission to use the anamnesis, return false if the session does
	 * not contain user information or user does not have appropriate
	 * permissions.
	 * 
	 * @param session
	 *            HttpSession
	 * @param anamnesis
	 *            the anamnesis
	 * @return true if user have permission to use the anamnesis, return false
	 *         if the session does not contain user information or user does not
	 *         has appropriate permissions.
	 */
	public boolean checkPatientAuthority(HttpSession session, Anamnesis anamnesis) {
		Long idUser = extractIdUser(session);
		if (idUser != null) {
			if (idUser == anamnesis.getIdPatient()) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Checks whether the doctor can use the anamnesis. Return true if the
	 * doctor have permission to use the anamnesis, return false if the session
	 * does not contain user information or the doctor does not have appropriate
	 * permissions.
	 * 
	 * @param session
	 *            HttpSession
	 * @param anamnesis
	 *            the anamnesis
	 * @return true if doctor have permission to use the anamnesis, return false
	 *         if the session does not contain user information or doctor does
	 *         not has appropriate permissions.
	 */
	public boolean checkDoctorAuthority(HttpSession session, Anamnesis anamnesis) {
		Long idUser = extractIdUser(session);
		if (idUser != null) {
			if (anamnesis.getIdDoctor() == idUser) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Extracts defined parameters from the request and puts them into the
	 * session. The method allows to save parameters which should be used after
	 * the redirect action.
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param session
	 *            HttpSession
	 */
	public void backupParams(HttpServletRequest request, HttpSession session) {
		Map<String, String> params = new HashMap<String, String>();
		String startIndex = request.getParameter(Constants.GenericParameters.START_INDEX);
		if (startIndex != null) {
			params.put(Constants.GenericParameters.START_INDEX, startIndex);
		}
		if (!params.isEmpty()) {
			session.setAttribute(Constants.GenericParameters.BACKUP_PARAMS, params);
		}
	}

}
