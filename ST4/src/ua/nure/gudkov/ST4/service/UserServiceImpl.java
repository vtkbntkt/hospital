package ua.nure.gudkov.ST4.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.gudkov.ST4.constant.Constants;
import ua.nure.gudkov.ST4.dao.api.UserDAO;
import ua.nure.gudkov.ST4.db.DBManager;
import ua.nure.gudkov.ST4.db.Operation;
import ua.nure.gudkov.ST4.entity.Role;
import ua.nure.gudkov.ST4.entity.User;
import ua.nure.gudkov.ST4.exception.DAOException;
import ua.nure.gudkov.ST4.exception.DBException;
import ua.nure.gudkov.ST4.service.api.UserService;
import ua.nure.gudkov.ST4.util.Password;

/**
 * User service implementation.
 * 
 * @author A.Gudkov
 *
 */
public class UserServiceImpl implements UserService {
	private UserDAO userDAO;
	private DBManager dbManager;
	private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

	public UserServiceImpl(UserDAO userDAO, DBManager dbManager) {
		this.userDAO = userDAO;
		this.dbManager = dbManager;
		LOG.debug("User service was successfully initialized");

	}

	@Override
	public User login(String email, String password) throws DBException {
		User user;
		try {
			password = Password.hash(password);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			LOG.warn(Constants.Messages.ERR_SHA_PASSWORD, e);
			throw new DBException(Constants.Messages.ERR_CANNOT_EXECUTE_OPERATION);
		}
		user = dbManager.execute(new Operation<User>() {
			@Override
			public User execute() throws DAOException {
				return userDAO.findByEmail(email);
			}
		});
		if (user == null || !user.getPassword().equals(password)) {
			user = null;
		}
		return user;
	}

	@Override
	public boolean registerUser(User user) throws DBException {
		String passwordHash;
		try {
			passwordHash = Password.hash(user.getPassword());
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			LOG.warn(Constants.Messages.ERR_SHA_PASSWORD, e);
			throw new DBException(Constants.Messages.ERR_CANNOT_EXECUTE_OPERATION);
		}

		return dbManager.execute(new Operation<Boolean>() {
			@Override
			public Boolean execute() throws DAOException {
				if (userDAO.findByEmail(user.getEmail()) != null) {
					LOG.trace(Constants.Messages.ERR_USER_ALREADY_EXISTS);
					return false;
				}
				user.setPassword(passwordHash);
				return userDAO.insertUser(user);
			}
		});
	}

	@Override
	public List<User> findAllUsers() throws DBException {
		return dbManager.execute(new Operation<List<User>>() {
			@Override
			public List<User> execute() throws DAOException {
				return userDAO.findAllUsers();
			}

		});
	}

	@Override
	public User findById(int idUser) throws DBException {
		return dbManager.execute(new Operation<User>() {
			@Override
			public User execute() throws DAOException {
				return userDAO.findById(idUser);
			}
		});
	}

	@Override
	public boolean verifyDoctor(int idDoctor) throws DBException {
		User user = findById(idDoctor);
		return (user != null && Role.DOCTOR.ordinal() == user.getRoleId());
	}

	@Override
	public boolean verifyPatient(int idPatient) throws DBException {
		User user = findById(idPatient);
		return (user != null && Role.PATIENT.ordinal() == user.getRoleId());
	}

	@Override
	public List<User> findAllDoctors() throws DBException {
		return dbManager.execute(new Operation<List<User>>() {
			@Override
			public List<User> execute() throws DAOException {
				return userDAO.findByIdRole(Role.DOCTOR.ordinal());
			}

		});
	}

	@Override
	public List<User> findAllPatients() throws DBException {
		return dbManager.execute(new Operation<List<User>>() {
			@Override
			public List<User> execute() throws DAOException {
				return userDAO.findByIdRole(Role.PATIENT.ordinal());
			}

		});
	}

}
