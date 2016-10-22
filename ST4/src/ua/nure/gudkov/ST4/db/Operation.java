package ua.nure.gudkov.ST4.db;

import ua.nure.gudkov.ST4.exception.DAOException;

/**
 * Operation interface.
 * 
 * @author A.Gudkov
 *
 * @param <T>
 *            returned object
 */
public interface Operation<T> {
	/**
	 * Execution method.
	 * 
	 * @return result of execution
	 * @throws DAOException
	 *             if query can not be performed because of sql exceptions.
	 */
	T execute() throws DAOException;
}
