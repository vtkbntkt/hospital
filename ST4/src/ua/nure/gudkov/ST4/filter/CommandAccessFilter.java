package ua.nure.gudkov.ST4.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.entity.Role;
import ua.nure.gudkov.ST4.entity.User;

/**
 * Security filter.
 * 
 * @author A.Gudkov
 * 
 */
public class CommandAccessFilter implements Filter {
	private static final Logger LOG = Logger.getLogger(CommandAccessFilter.class);

	private Map<Role, List<String>> accessMap = new HashMap<Role, List<String>>();
	private List<String> commons = new ArrayList<String>();
	private List<String> outOfControl = new ArrayList<String>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOG.debug("Filter initialization starts");

		accessMap.put(Role.ADMIN, asList(filterConfig.getInitParameter(Constants.FilterConstants.ADMIN_ROLE)));
		accessMap.put(Role.DOCTOR, asList(filterConfig.getInitParameter(Constants.FilterConstants.DOCTOR_ROLE)));
		accessMap.put(Role.NURSE, asList(filterConfig.getInitParameter(Constants.FilterConstants.NURSE_ROLE)));
		accessMap.put(Role.PATIENT, asList(filterConfig.getInitParameter(Constants.FilterConstants.PATIENT_ROLE)));
		LOG.trace("Access map --> " + accessMap);

		commons = asList(filterConfig.getInitParameter(Constants.FilterConstants.COMMON_ACCESS));
		LOG.trace("Common commands --> " + commons);

		outOfControl = asList(filterConfig.getInitParameter(Constants.FilterConstants.NOCONTROL_ACCESS));
		LOG.trace("Out of control commands --> " + outOfControl);

		LOG.debug("Filter initialization finished");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOG.debug("Filter starts");

		if (accessAllowed(request)) {
			LOG.debug("Filter finished");
			chain.doFilter(request, response);
		} else {
			String errorMessasge = "You do not have permission to access the requested resource";
			LOG.trace("Set the request attribute: errorMessage --> " + errorMessasge);
			request.getRequestDispatcher(Constants.PagePath.SIG_IN_PAGE).forward(request, response);
		}

	}

	@Override
	public void destroy() {
		LOG.debug("Filter destruction starts");
		LOG.debug("Filter destruction finished");

	}

	private boolean accessAllowed(ServletRequest request) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String commandName = request.getParameter(Constants.Commands.COMMAND);

		if (commandName == null || commandName.isEmpty()) {
			return false;
		}

		if (outOfControl.contains(commandName)) {
			return true;
		}

		HttpSession session = httpRequest.getSession(false);
		if (session == null) {
			return false;
		}

		User user = (User) session.getAttribute(Constants.SessionAttributes.USER_ATTR);
		if (user == null) {
			return false;
		}

		Role userRole = Role.values()[user.getRoleId()];
		if (userRole == null) {
			return false;
		}

		return accessMap.get(userRole).contains(commandName) || commons.contains(commandName);
	}

	/**
	 * Extracts parameter values from string.
	 * 
	 * @param str
	 *            parameter values string.
	 * @return list of parameter values.
	 */
	private List<String> asList(String str) {
		List<String> list = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(str);
		while (st.hasMoreTokens()) {
			list.add(st.nextToken());
		}
		return list;
	}

}
