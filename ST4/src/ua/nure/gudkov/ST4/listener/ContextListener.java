package ua.nure.gudkov.ST4.listener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.nure.gudkov.ST4.command.CommandContainer;
import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.dao.DAOFactory;
import ua.nure.gudkov.ST4.dao.api.AnamnesisDAO;
import ua.nure.gudkov.ST4.dao.api.ServiceHistoryDAO;
import ua.nure.gudkov.ST4.dao.api.UserDAO;
import ua.nure.gudkov.ST4.db.DBManager;
import ua.nure.gudkov.ST4.service.AnamnesisServiceImpl;
import ua.nure.gudkov.ST4.service.ServiceHistoryServiceImpl;
import ua.nure.gudkov.ST4.service.UserServiceImpl;
import ua.nure.gudkov.ST4.service.api.AnamnesisService;
import ua.nure.gudkov.ST4.service.api.ServiceHistoryService;
import ua.nure.gudkov.ST4.service.api.UserService;

/**
 * Context listener.
 * 
 * @author A.Gudkov
 * 
 */
@WebListener
public class ContextListener implements ServletContextListener {
	private static ServletContext context;
	private static final Logger LOGGER = LogManager.getLogger(ContextListener.class);

	public void contextInitialized(ServletContextEvent sce) {
		LOGGER.trace("Context initialize starts.");
		context = sce.getServletContext();
		DAOFactory mySqlFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);

		DBManager dbManager = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup(Constants.Connection.OBJECT_TO_LOCK_UP_NAME);
			dbManager = new DBManager(ds);
		} catch (NamingException e) {
			LOGGER.warn(e);
			throw new RuntimeException(e);
		}
		UserDAO userDAO = mySqlFactory.getUserDAO();
		AnamnesisDAO anamnesisDAO = mySqlFactory.getAnamnesisDAO();
		ServiceHistoryDAO serviceHistoryDAO = mySqlFactory.getServiceHistoryDAO();

		UserService userService = new UserServiceImpl(userDAO, dbManager);
		AnamnesisService anamnesisService = new AnamnesisServiceImpl(anamnesisDAO, dbManager);
		ServiceHistoryService serviceHistoryServise = new ServiceHistoryServiceImpl(serviceHistoryDAO, dbManager);

		CommandContainer commandContainer = new CommandContainer(userService, anamnesisService, serviceHistoryServise);
		context.setAttribute(Constants.ContextAttr.COMMAND_CONTAINER, commandContainer);
		LOGGER.trace("Context initialize finished.");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		 LOGGER.trace("Context destroy started.");
	        ServletContext servletContext = sce.getServletContext();
	        servletContext.removeAttribute(Constants.ContextAttr.COMMAND_CONTAINER);
	        LOGGER.trace("Context destroy finished.");

	}

}
