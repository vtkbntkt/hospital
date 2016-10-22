package ua.nure.gudkov.ST4.dao;

import ua.nure.gudkov.ST4.dao.api.AnamnesisDAO;
import ua.nure.gudkov.ST4.dao.api.ServiceHistoryDAO;
import ua.nure.gudkov.ST4.dao.api.UserDAO;

/**
 * DAO factory.
 * 
 * @author A.Gudkov
 *
 */
public abstract class DAOFactory {
	/*
	 * DAO types supported by the factory
	 */
	public static final int MySQL = 1;

	/**
	 * Returns user DAO.
	 * 
	 * @return userDAO
	 */
	public abstract UserDAO getUserDAO();
	
	/**
	 * Returns anamnesis DAO.
	 * 
	 * @return anamnesis DAO
	 */
	public abstract AnamnesisDAO getAnamnesisDAO();
	
	/**
	 * Returns serviceHistory DAO.
	 * 
	 * @return serviceHistory DAO
	 */
	public abstract ServiceHistoryDAO getServiceHistoryDAO();
	

	/**
	 * Returns DAO factory according to type.
	 * 
	 * @param whichFactory type of DAO factory
	 * @return DAO factory
	 */
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
		case MySQL:
			return new MySqlDAOFactory();
		default:
			return null;
		}
	}

}
