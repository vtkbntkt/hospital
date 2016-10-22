package ua.nure.gudkov.ST4.service.api;

import java.util.List;

import ua.nure.gudkov.ST4.entity.ServiceHistory;
import ua.nure.gudkov.ST4.exception.DBException;

/**
 * Service history service interface.
 * 
 * @author A.Gudkov
 */
public interface ServiceHistoryService {

	/**
	 * Returns all service histories found by the anamnesis id.
	 * 
	 * @param idAnamnesis
	 *            anamnesis id
	 * @return service history list
	 * @throws DBException
	 *             if query can not be performed because of DAO exceptions.
	 */
	List<ServiceHistory> findAllServiceHistoryByIdAnamnesis(int idAnamnesis) throws DBException;

	/**
	 * Adds the service history into the data base.
	 * 
	 * @param serviceHistory
	 *            service history
	 * @return true if the service history was added into the data base, false
	 *         if was not
	 * @throws DBException
	 *             if query can not be performed because of DAO exceptions.
	 */
	boolean addServiceHistory(ServiceHistory serviceHistory) throws DBException;

	/**
	 * Returns service history list with taken status.
	 * 
	 * @return service history list
	 * @throws DBException
	 *             if query can not be performed because of DAO exceptions.
	 */
	List<ServiceHistory> findAllTakenServiceHistory() throws DBException;

	/**
	 * Returns service history found by its id.
	 * 
	 * @param idServHistory
	 *            service history id.
	 * @return service history
	 * @throws DBException
	 *             if query can not be performed because of DAO exceptions.
	 */
	ServiceHistory findById(int idServHistory) throws DBException;

	/**
	 * Changes service history status to performed.
	 * 
	 * @param idServHistory
	 *            service history id
	 * @return true if service history status was changed, false if was not
	 * @throws DBException
	 *             if query can not be performed because of DAO exceptions.
	 */
	boolean changeServiceStatusToPerformed(int idServHistory) throws DBException;

}
