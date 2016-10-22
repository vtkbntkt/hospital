package ua.nure.gudkov.ST4.service.api;

import java.util.List;

import ua.nure.gudkov.ST4.entity.User;
import ua.nure.gudkov.ST4.exception.DBException;

/**
 * User service interface.
 * 
 * @author A.Gudkov
 *
 */
public interface UserService {

	/**
	 * Checks whether user with given email and password exists and returns
	 * user.
	 * 
	 * @param email
	 *            user email
	 * @param password
	 *            user account password
	 * @return user
	 * @throws DBException
	 *             if query can not be performed because of DAO exceptions.
	 */
	User login(String email, String password) throws DBException;

	/**
	 * Checks whether the user exists in the data base. If the user is new, puts
	 * in the data base user registration information.
	 * 
	 * @param user
	 *            user
	 * @return true if the user does not exists in the data base, false if not.
	 * @throws DBException
	 *             if query can not be performed because of DAO exceptions.
	 */
	boolean registerUser(User user) throws DBException;

	/**
	 * Returns all users.
	 * 
	 * @return user list
	 * @throws DBException
	 *             if query can not be performed because of DAO exceptions.
	 */
	List<User> findAllUsers() throws DBException;

	/**
	 * Returns user found by its id.
	 * 
	 * @param idUser
	 *            user id.
	 * @return user
	 * @throws DBException
	 *             if query can not be performed because of DAO exceptions.
	 */
	User findById(int idUser) throws DBException;

	/**
	 * Returns true if user with given id exists and was registered as doctor,
	 * returns false if user with given id does not exists or not a doctor.
	 * 
	 * @param idDoctor
	 *            doctor id
	 * @return true if user with given id exists and was registered as doctor,
	 *         false if user with given id does not exists or not a doctor
	 * @throws DBException
	 *             if query can not be performed because of DAO exceptions.
	 */
	boolean verifyDoctor(int idDoctor) throws DBException;

	/**
	 * Returns true if user with given id exists and was registered as patient,
	 * returns false if user with given id does not exists or not a patient.
	 * 
	 * @param idPatient
	 *            patient id
	 * @return true if user with given id exists and was registered as patient,
	 *         false if user with given id does not exists or not a patient
	 * @throws DBException
	 *             if query can not be performed because of DAO exceptions.
	 */
	boolean verifyPatient(int idPatient) throws DBException;

	/**
	 * Returns all users were registered like doctors.
	 * 
	 * @return user list
	 * @throws DBException
	 *             if query can not be performed because of DAO exceptions.
	 */
	List<User> findAllDoctors() throws DBException;

	/**
	 * Returns all users were registered like patients.
	 * 
	 * @return user list
	 * @throws DBException
	 *             if query can not be performed because of DAO exceptions.
	 */
	List<User> findAllPatients() throws DBException;
}
