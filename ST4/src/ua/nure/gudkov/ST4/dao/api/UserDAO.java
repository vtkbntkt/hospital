package ua.nure.gudkov.ST4.dao.api;

import java.util.List;

import ua.nure.gudkov.ST4.entity.User;
import ua.nure.gudkov.ST4.exception.DAOException;

/**
 * User DAO interface.
 * 
 * @author A.Gudkov
 *
 */
public interface UserDAO {
	/**
	 * Find user by its id.
	 * 
	 * @param email
	 *            the email
	 * @return user entity
	 * @throws DAOException
	 *             if query can not be performed because of sql exceptions.
	 */
	User findByEmail(String email) throws DAOException;

	/**
	 * Inserts user into the data base
	 * 
	 * @param user
	 *            the user
	 * @return true if user was added into the data base, false if was not
	 * @throws DAOException
	 *             if query can not be performed because of sql exceptions.
	 */
	boolean insertUser(User user) throws DAOException;

	/**
	 * Returns all users.
	 * 
	 * @return list of users
	 * @throws DAOException
	 *             if query can not be performed because of sql exceptions.
	 */
	List<User> findAllUsers() throws DAOException;

	/**
	 * Returns user found by its id.
	 * 
	 * @param idUser
	 *            the user id
	 * @return user
	 * @throws DAOException
	 *             if query can not be performed because of sql exceptions.
	 */
	User findById(int idUser) throws DAOException;

	/**
	 * Returns list of users found by role id.
	 * 
	 * @param idRole
	 *            the role id
	 * @return user list
	 * @throws DAOException
	 *             if query can not be performed because of sql exceptions.
	 */
	List<User> findByIdRole(int idRole) throws DAOException;

}
