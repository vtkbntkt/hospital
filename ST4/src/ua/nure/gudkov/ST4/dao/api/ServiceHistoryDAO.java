package ua.nure.gudkov.ST4.dao.api;

import java.util.List;

import ua.nure.gudkov.ST4.entity.ServiceHistory;
import ua.nure.gudkov.ST4.exception.DAOException;

/**
 * ServiceHistory DAO interface.
 * 
 * @author A.Gudkov
 *
 */
public interface ServiceHistoryDAO {
	/**
	 * Returns list of all serviceHistories.
	 * 
	 * @param idAnamnesis
	 *            anamnesis id
	 * @return serviceHistory list
	 * @throws DAOException
	 *             if query can not be performed because of sql exceptions.
	 */
	List<ServiceHistory> findAllByIdAnamnesis(int idAnamnesis) throws DAOException;

	/**
	 * Inserts serviceHistory in the data base.
	 * 
	 * @param servHistory
	 *            serviceHistory entity
	 * @return true if servHistory was added in the data base, false if was not.
	 * @throws DAOException
	 *             if query can not be performed because of sql exceptions.
	 */
	boolean insertServHistory(ServiceHistory servHistory) throws DAOException;

	/**
	 * Returns list of serviceHistories found by status id.
	 * 
	 * @param idStatus
	 *            id of serviceHistory status
	 * @return serviceHistory list
	 * @throws DAOException
	 *             if query can not be performed because of sql exceptions.
	 */
	public List<ServiceHistory> findAllByIdStatus(int idStatus) throws DAOException;

	/**
	 * Returns service history found by its id.
	 * 
	 * @param idServHistory
	 *            serviceHistory id
	 * @return service history entity
	 * @throws DAOException
	 *             if query can not be performed because of sql exceptions.
	 */
	ServiceHistory findByIdServiceHistory(int idServHistory) throws DAOException;

	/**
	 * Updates service history status
	 * 
	 * @param idStatus
	 *            id status
	 * @param idServHistory
	 *            id of service history
	 * @return true if status of the service history was updated, false if was
	 *         not
	 * @throws DAOException
	 *             if query can not be performed because of sql exceptions.
	 */
	boolean updateServiceStatus(int idStatus, int idServHistory) throws DAOException;
}
