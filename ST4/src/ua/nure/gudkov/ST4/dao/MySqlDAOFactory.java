package ua.nure.gudkov.ST4.dao;

import ua.nure.gudkov.ST4.dao.api.AnamnesisDAO;
import ua.nure.gudkov.ST4.dao.api.ServiceHistoryDAO;
import ua.nure.gudkov.ST4.dao.api.UserDAO;

/**
 * MySQL implementation of DAO factory. 
 * 
 * @author A.Gudkov
 *
 */
public class MySqlDAOFactory extends DAOFactory {

	@Override
	public UserDAO getUserDAO() {
		return new MySqlUserDAO();
	}

	@Override
	public AnamnesisDAO getAnamnesisDAO() {
		return new MySqlAnamnesisDAO();
	}

	@Override
	public ServiceHistoryDAO getServiceHistoryDAO() {
		return new MySqlServiceHistoryDAO();
	}

}
