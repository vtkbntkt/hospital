package ua.nure.gudkov.ST4.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gudkov.ST4.bean.aggregation.UsersAnamnesisesHistoryBean;
import ua.nure.gudkov.ST4.bean.app.PageBean;
import ua.nure.gudkov.ST4.bean.app.PageBean.Action;
import ua.nure.gudkov.ST4.bean.dto.TaskBean;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.converter.PathConverter;
import ua.nure.gudkov.ST4.converter.UsersAnamnesisesHistoryToTaskBeanConverter;
import ua.nure.gudkov.ST4.converter.api.Converter;
import ua.nure.gudkov.ST4.entity.Anamnesis;
import ua.nure.gudkov.ST4.entity.ErrorHolder;
import ua.nure.gudkov.ST4.entity.Role;
import ua.nure.gudkov.ST4.entity.Service;
import ua.nure.gudkov.ST4.entity.ServiceHistory;
import ua.nure.gudkov.ST4.entity.User;
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.service.api.AnamnesisService;
import ua.nure.gudkov.ST4.service.api.ServiceHistoryService;
import ua.nure.gudkov.ST4.service.api.UserService;

/**
 * Get doctor`s tasks command implementation.
 * 
 * @author A.Gudkov
 *
 */
public class GetDoctorTasks extends AbstractCommand {
	private static final Logger LOG = Logger.getLogger(GetDoctorTasks.class);

	private UserService userService;
	private AnamnesisService anamnesisService;
	private ServiceHistoryService servHistoryService;
	private Converter<UsersAnamnesisesHistoryBean, List<TaskBean>> converter;

	public GetDoctorTasks(UserService userService, AnamnesisService anamnesisService,
			ServiceHistoryService servHistoryService) {
		this.userService = userService;
		this.anamnesisService = anamnesisService;
		this.servHistoryService = servHistoryService;
		converter = new UsersAnamnesisesHistoryToTaskBeanConverter();

	}

	@Override
	public PageBean execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageBean pb = new PageBean();
		pb.setAction(Action.REDIRECT);
		pb.setPath(PathConverter.getInstance().getPath(Constants.PagePath.BASE_PATH + Constants.PagePath.ACCOUNT_PAGE));
		LOG.trace("Set path and action ----> " + pb.getPath() + ", " + pb.getAction());

		ErrorHolder erroHolder = new ErrorHolder();
		HttpSession session = request.getSession();

		List<User> users = null;
		List<Anamnesis> anamnesis = null;
		List<ServiceHistory> servHistory = null;
		List<TaskBean> tasks = new ArrayList<TaskBean>();

		try {
			users = userService.findAllUsers();
			LOG.trace("users were obtained ----> " + users);

			anamnesis = anamnesisService.findAllNewAnamnesis();
			if (!anamnesis.isEmpty()) {
				LOG.trace("anamnesises were obtained ----> " + anamnesis);

				servHistory = servHistoryService.findAllTakenServiceHistory();
				if (!servHistory.isEmpty()) {
					LOG.trace("service histories were obtained ----> " + servHistory);

					tasks = converter.convert(new UsersAnamnesisesHistoryBean(anamnesis, users, servHistory));
					LOG.trace("tasks were obtained ----> " + tasks);
				}
			}
		} catch (DBException e) {
			erroHolder.add(e.getMessage());
			session.setAttribute(Constants.ErrorConstants.DB_ERRORS, erroHolder.getErrors());
			LOG.trace("catched dbexception ----> ");
			LOG.error(e);
			pb.setPath(PathConverter.getInstance()
					.getPath(Constants.PagePath.BASE_PATH + Constants.PagePath.ACCOUNT_PAGE));
			return pb;
		}

		tasks = filterByRole(tasks, session);
		if (tasks.isEmpty()) {
			erroHolder.add(Constants.TaskListParameters.MSG_ERROR_TASKS, Constants.ErrorConstants.FIND_TASKS_KEY);
			session.setAttribute(Constants.ErrorConstants.TASK_LIST_ERRORS, erroHolder.getErrors());
			LOG.trace("list of tasks is empty ----> ");
			return pb;

		}

		session.setAttribute(Constants.SessionAttributes.LIST_OF_TASKS, tasks);
		return pb;
	}

	/**
	 * Filters list of tasks by defined role. So, if user role is the nurse, the
	 * method return the list without the surgery tasks.
	 * 
	 * @param tasks
	 *            list of tasks
	 * @param session
	 *            HttpSession
	 * @return filtered list of tasks
	 */
	private List<TaskBean> filterByRole(List<TaskBean> tasks, HttpSession session) {
		List<TaskBean> filteredTasks = new ArrayList<TaskBean>();
		if (!Role.NURSE.equals(extractRole(session))) {
			return tasks;
		}
		for (TaskBean tb : tasks) {
			if (!tb.getType().equals(Service.SURGERY)) {
				filteredTasks.add(tb);
			}
		}
		return filteredTasks;
	}

}
